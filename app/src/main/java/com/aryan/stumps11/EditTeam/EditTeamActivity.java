package com.aryan.stumps11.EditTeam;

import static com.aryan.stumps11.EditTeam.EditPlayer.wkQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateReqData;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamReq;
import com.aryan.stumps11.ApiModel.profile.dummyCreateRes.DummyResponse;
import com.aryan.stumps11.CreateTeam.AR;
import com.aryan.stumps11.CreateTeam.BAT;
import com.aryan.stumps11.CreateTeam.BWL;
import com.aryan.stumps11.CreateTeam.ChooseCaptainandVC;
import com.aryan.stumps11.CreateTeam.CreateTeams;
import com.aryan.stumps11.CreateTeam.DataBase;
import com.aryan.stumps11.CreateTeam.EditTabAdapter;
import com.aryan.stumps11.CreateTeam.GreenBackground;
import com.aryan.stumps11.CreateTeam.SelectedData;
import com.aryan.stumps11.CreateTeam.WK;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTeamActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{


    public static String MATCH_ID;
    private TabLayout tt;
    private ViewPager vv;
    private TextView t80;
    private MaterialButton bb,preview;
    private SharedPreferences sp;
    private  int hello,wk,bat,all,bwl;
    private ProgressBar pp;
    private String teamNameA;
    private String teamNameB;
    private EditDatabase db;
    public static  String C_I_D;
    private CircleImageView circleImageViewTeamA,circleImageViewTeamB;
    private TextView tvNameA,tvNameB;
    private TextView tvCreditPoints;
    private  float creditPoints;
    private Cursor cc;
    private  String mobile;
    private List<Integer> addCredit;
    private String pname,prole,C,vc,pid,credit;
    private TextView tvCreateTeamName;
    private TextView tvCreateTeamTime;
    private ImageView imgBacKBtn;
    private List<EditTeamModel> list;
    private CreateReqData createReqData;
    private List<CreateReqData> createReqData1;
    private String ppts;
    private String captainName,viceCaptain;
    public static String teamId;
    public static String comId;
    public static String ID;
    private  int all1,wk1,bat1,bwl1;
    private ProgressDialog progressDialog;

  private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        sharedPreferences=getSharedPreferences("Counts",MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        progressDialog=new ProgressDialog(EditTeamActivity.this);
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");


        if (sharedPreferences.contains("bKey")){
            hello=sharedPreferences.getInt("Key",0);
            wk=sharedPreferences.getInt("wKey",0);
            bat=sharedPreferences.getInt("bKey",0);
            all=sharedPreferences.getInt("aKey",0);
            bwl=sharedPreferences.getInt("bwlKey",0);
            creditPoints=sharedPreferences.getFloat("creditPoints",0);
            Log.e("jkhkh",creditPoints+"");
        }



        circleImageViewTeamA=findViewById(R.id.edit_team_create_team_a_logo);
        circleImageViewTeamB=findViewById(R.id.edit_team_create_team_b_logo);
        tvNameA=findViewById(R.id.edit_team_create_team_a_name);
        tvNameB=findViewById(R.id.edit_team_create_team_b_name);
        tvCreditPoints=findViewById(R.id.create_team_credit_points);
        tvCreateTeamName=findViewById(R.id.edit_team_create_team_name);
        tvCreateTeamTime=findViewById(R.id.edit_team_create_team_time);
        imgBacKBtn=findViewById(R.id.edit_team_create_team_image_back_btn);
        preview=findViewById(R.id.edit_team_Preview);
        bb=findViewById(R.id.edit_team_Next);
        tt=findViewById(R.id.edit_team_tablayout);
        vv=findViewById(R.id.edit_team_viewpager);
        pp=findViewById(R.id.edit_team_progressBar);
        t80=findViewById(R.id.edit_team_count_player);

        setTeamDetails();

        imgBacKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditTeamActivity.this, GreenBackground.class));
                finish();
            }
        });
        createReqData1=new ArrayList<>();
        SharedPreferences mob=getSharedPreferences("Mobile",MODE_PRIVATE);
        mobile=mob.getString("mKey","0");
//        SharedPreferences sp=getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
//
//        cc=db.EditDisplayPlayer(mobile);
         teamId=getIntent().getStringExtra("match_id");
         comId=getIntent().getStringExtra("cid");
         ID=getIntent().getStringExtra("id");

//        totalAll=getIntent().getStringExtra("all");
//        totalBat=getIntent().getStringExtra("bat");
//        totalBwl=getIntent().getStringExtra("bwl");
//        totalWk=getIntent().getStringExtra("wk");
//
//         all1=Integer.parseInt(totalAll);
//         bat1=Integer.parseInt(totalBat);
//         bwl1=Integer.parseInt(totalBwl);
//         wk1=Integer.parseInt(totalWk);
//
//        sp=getSharedPreferences("Counts",MODE_PRIVATE);
//        sp.registerOnSharedPreferenceChangeListener(this);


//        hello=sp.getInt("Key",0);


//        wk=sp.getInt("wKey",0);
//        bat=sp.getInt("bKey",0);
//        all=sp.getInt("aKey",0);
//        bwl=sp.getInt("bwlKey",0);

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      db.EditdeleteReminder();
                startActivity(new Intent(EditTeamActivity.this,EditCVCActivity.class));
                finish();
            }
        });

        tvCreditPoints.setText(String.valueOf(creditPoints));

        setup(vv);
        tt.setupWithViewPager(vv);


    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        sharedPreferences=getSharedPreferences("Counts",MODE_PRIVATE);
        hello=sharedPreferences.getInt("Key",0);
        wk=sharedPreferences.getInt("wKey",0);
        bat=sharedPreferences.getInt("bKey",0);
        all=sharedPreferences.getInt("aKey",0);
        bwl=sharedPreferences.getInt("bwlKey",0);
        creditPoints=sharedPreferences.getInt("creditPoints",0);
        pp.setProgress(hello*100/(11000/1000));

        t80.setText(""+hello+" Players Selected");
//        t80.setText("11 Player Selected");

        tt.getTabAt(0).setText("WK"+"("+wk+")");
        tt.getTabAt(1).setText("BAT"+"("+bat+")");
        tt.getTabAt(2).setText("AR"+"("+all+")");
        tt.getTabAt(3).setText("BWL"+"("+bwl+")");


        Log.e("kmljl",wk+"");

    }



    private void setup(ViewPager viewPager){




        try{
            EditTabAdapter tabadapter=new EditTabAdapter(getSupportFragmentManager());
            tabadapter.EditFrag("WK("+wk+")",new EditWkFargment());
            tabadapter.EditFrag("BAT("+bat+")",new EditBatFragments());
            tabadapter.EditFrag("AR("+all+")",new EditAllRounderFragments());
            tabadapter.EditFrag("BWL("+bwl+")",new EditBallFargment());
            viewPager.setAdapter(tabadapter);

        }catch (Exception e){
            Toast.makeText(this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        clearKeys();

    }

    private void clearKeys(){
        SharedPreferences  sharedPreferences=getSharedPreferences("Save",MODE_PRIVATE);
        SharedPreferences.Editor s=sharedPreferences.edit();
        s.clear();
        s.apply();
    }

    private void setTeamDetails(){

        teamNameA=SelectedData.teamNameA;
        teamNameB=SelectedData.teamNameB;
        String imageTeam1=SelectedData.teamAImage;
        String imageTeam2=SelectedData.teamBImage;
        String time=SelectedData.headTime;
        tvNameB.setText(teamNameB);
        tvNameA.setText(teamNameA);
        String hd=SelectedData.teamsheader;
        tvCreateTeamName.setText(hd);


        try{
            DateTimeFormatter dtf = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            }
            LocalDateTime now = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                now = LocalDateTime.now();
            }

            LocalDateTime matchDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                matchDateTime = LocalDateTime.parse(time,
                        dtf);
            }


            long millis = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                millis = Duration.between(now, matchDateTime).toMillis();
            }
            new CountDownTimer(millis, 1000) {
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long dd = hours / 24;
                    String time = dd + " days : " + hours % 24 + " hrs : " + minutes % 60 + " min : " + seconds % 60 + " sec";
                    tvCreateTeamTime.setText(time);
                }

                public void onFinish() {
                    //Close the popup
                    String time = "0 days : 0 hrs : 0 min : 0 sec";
                    tvCreateTeamTime.setText(time);
                }
            }.start();

        }catch (Exception e){

            e.printStackTrace();

            Toast.makeText(this, "Exception :- "+e, Toast.LENGTH_SHORT).show();
        }
//        tvCreateTeamTime.setText(time);

        Glide.with(EditTeamActivity.this).load(imageTeam1).into(circleImageViewTeamA);
        Glide.with(EditTeamActivity.this).load(imageTeam2).into(circleImageViewTeamB);

    }




}