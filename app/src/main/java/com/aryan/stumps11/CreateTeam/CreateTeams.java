package com.aryan.stumps11.CreateTeam;

import static com.aryan.stumps11.CreateTeam.CreateTeamAdapter.addCreditPoint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateReqData;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamReq;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamResponse;
import com.aryan.stumps11.ApiModel.profile.dummyCreateRes.DummyResponse;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.HomePageClick.HomePageClick;
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
import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTeams extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{
    public static String MATCH_ID;
    private TabLayout tt;
    private ViewPager vv;
    private TextView t80;
    private MaterialButton bb,preview;
    private SharedPreferences sp;
    private int hello,wk,bat,all,bwl;
    private ProgressBar pp;
    private String teamNameA;
    private String teamNameB;
    public static  String cid;
    private CircleImageView circleImageViewTeamA,circleImageViewTeamB;
    private TextView tvNameA,tvNameB;
    public static TextView tvCreditPoints;
    private  float creditPoints;
    private Cursor cc;
    private  String mobile;
    private List<Integer> addCredit;
    private String pname,prole,C,vc,pid,credit;
//    private static int addCreditPoint;
    private TextView tvCreateTeamName;
    private TextView tvCreateTeamTime;
    private db d;


    private ImageView imgBacKBtn;
    private List<ModelClass> list;
    private CreateReqData createReqData;
    private   List<CreateReqData> createReqData1;
    private String ppts;
    private String captainName,viceCaptain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teams);

        circleImageViewTeamA=findViewById(R.id.create_team_a_logo);
        circleImageViewTeamB=findViewById(R.id.create_team_b_logo);
        tvNameA=findViewById(R.id.create_team_a_name);
        tvNameB=findViewById(R.id.create_team_b_name);
        tvCreditPoints=findViewById(R.id.create_team_credit_points);
        tvCreateTeamName=findViewById(R.id.create_team_name);
        tvCreateTeamTime=findViewById(R.id.create_team_time);
        imgBacKBtn=findViewById(R.id.create_team_image_back_btn);
        createReqData1=new ArrayList<>();




  //      SelectedData.getSelectedData().clearData();


        imgBacKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    SelectedData.getSelectedData().clearData();
                    startActivity(new Intent(CreateTeams.this,HomePageClick.class));
                    finish();


            }
        });

        setTeamDetails();
        Intent intent=getIntent();
        if (intent.hasExtra(CommonData.Match_id)){

             MATCH_ID=intent.getStringExtra(CommonData.Match_id);
            Log.e("klkll",MATCH_ID+"");

        }

        if (intent.hasExtra(CommonData.C_I_D)){
            cid=intent.getStringExtra(CommonData.C_I_D);
            Log.e("cid>>>",cid);
        }

//        cid=getIntent().getStringExtra(cid);

        if (intent.hasExtra(CommonData.CREDIT_POINT)){
            credit=intent.getStringExtra(CommonData.CREDIT_POINT);
            Log.e("credit Point",credit);
        }

        sp=getSharedPreferences("Counts",MODE_PRIVATE);
        hello=sp.getInt("Key",0);
        pp=findViewById(R.id.progressBar2);
        wk=sp.getInt("wKey",0);
        bat=sp.getInt("bKey",0);
        all=sp.getInt("aKey",0);
        bwl=sp.getInt("bwlKey",0);

///////////Change Qasim
      /*  Log.e("kljnkj",sp.getFloat("creditPoints",0)+"");

        creditPoints=sp.getFloat("creditPoints",0);


        try{
            creditPoints=sp.getFloat("creditPoints",0);


            Log.e("kjhjh",creditPoints+"");

        }catch (Exception e){
            e.printStackTrace();

        }*/

        sp.registerOnSharedPreferenceChangeListener(this);
        t80=findViewById(R.id.textView80);

        tvCreditPoints.setText(String.valueOf(creditPoints));


        preview=findViewById(R.id.Preview);
        bb=findViewById(R.id.Next);
        tt=findViewById(R.id.tablayout);
        vv=findViewById(R.id.viewpager);
        setup(vv);
        tt.setupWithViewPager(vv);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTeamDetails();
                if (wk<1 || wk>4){
                    toastShow("Please select wicket keeper between 1 to 4");
                    return;
                }
                if (bat<1 || bat>6){
                    toastShow("Please select batsman between 1 to 6");
                    return;
                }
                if (bwl<1 || bwl>6){
                    toastShow("Please select bowler between 1 to 6");
                    return;
                }
                if (all<1 || all>6){
                    toastShow("Please select all rounder between 1 to 6");
                    return;
                }

                if (hello==11){

                    Intent intent1=new Intent(CreateTeams.this,ChooseCaptainandVC.class);
                    intent1.putExtra(CommonData.Match_id,MATCH_ID);
                    intent1.putExtra(CommonData.C_I_D,cid);
                    startActivity(intent1);
                }else {
                    Toast.makeText(CreateTeams.this, "Please select 11 player", Toast.LENGTH_SHORT).show();
                }
              SharedPreferences  sharedPreferences=getSharedPreferences("Counts",MODE_PRIVATE);
              SharedPreferences.Editor s=sharedPreferences.edit();
              s.clear();
              s.apply();
                SelectedData.getSelectedData().clearData();
            }
        });
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateTeams.this,GreenBackground.class));
                overridePendingTransition(0,0);
            }
        });
        Conditions();
    }
    private void Conditions() {
        TextWatcher tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(t80.getText().toString().equals("0 Players Selected")){
                    preview.setEnabled(false);
                    preview.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Color2));
                }
                else {
                    preview.setEnabled(true);
                    preview.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Color6));
                }
                if(t80.getText().toString().equals("11 Players Selected")){
                    bb.setEnabled(true);
                    bb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Color5));

                }
                else {
                    bb.setEnabled(false);
                    bb.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Color2));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        t80.addTextChangedListener(tw);
    }

    private void setTeamDetails(){

        teamNameA=getIntent().getStringExtra("TeamNameA");
        teamNameB=getIntent().getStringExtra("TeamNameB");
        String imageTeam1=getIntent().getStringExtra("teamImage1");
        String imageTeam2=getIntent().getStringExtra("teamImage2");
        String time=getIntent().getStringExtra("time");

        tvNameB.setText(teamNameB);
        tvNameA.setText(teamNameA);


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

            Toast.makeText(this, "Exception :- "+e, Toast.LENGTH_SHORT).show();
        }
        tvCreateTeamName.setText(teamNameA+" Vs "+teamNameB);
//        tvCreateTeamTime.setText(time);

        Glide.with(CreateTeams.this).load(imageTeam1).into(circleImageViewTeamA);
        Glide.with(CreateTeams.this).load(imageTeam2).into(circleImageViewTeamB);

    }



    private void setup(ViewPager viewPager){


        TabAdapter tabadapter=new TabAdapter(getSupportFragmentManager());
        tabadapter.addfrag("WK("+wk+")",new WK());
        tabadapter.addfrag("BAT("+bat+")",new BAT());
        tabadapter.addfrag("AR("+all+")",new AR());
        tabadapter.addfrag("BWL("+bwl+")",new BWL());
        vv.setAdapter(tabadapter);
    }

    @Override
    public void onBackPressed() {



            SharedPreferences  sharedPreferences=getSharedPreferences("Counts",MODE_PRIVATE);
            SharedPreferences.Editor s=sharedPreferences.edit();
            s.clear();
            s.apply();
            clearKeys();


        SelectedData.getSelectedData().clearData();

        setTeamDetails();
        startActivity(new Intent(CreateTeams.this, HomePageClick.class));
        CustomIntent.customType(CreateTeams.this,"right-to-left");
        finish();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        sharedPreferences=getSharedPreferences("Counts",MODE_PRIVATE);
        hello=sharedPreferences.getInt("Key",0);
        wk=sharedPreferences.getInt("wKey",0);
        bat=sharedPreferences.getInt("bKey",0);
        all=sharedPreferences.getInt("aKey",0);
        bwl=sharedPreferences.getInt("bwlKey",0);
        creditPoints=sharedPreferences.getFloat("creditPoints",0);
        pp.setProgress(hello*100/(11000/1000));

        t80.setText(""+hello+" Players Selected");

        tt.getTabAt(0).setText("WK"+"("+wk+")");
        tt.getTabAt(1).setText("BAT"+"("+bat+")");
        tt.getTabAt(2).setText("AR"+"("+all+")");
        tt.getTabAt(3).setText("BWL"+"("+bwl+")");
    }






    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String ItemName = intent.getStringExtra("c");
            Toast.makeText(CreateTeams.this,ItemName +" khudrc" ,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
//

            SharedPreferences  sharedPreferences=getSharedPreferences("Counts",MODE_PRIVATE);
            SharedPreferences.Editor s=sharedPreferences.edit();
            s.clear();
            s.apply();
            clearKeys();


    }

    private void clearKeys(){
        SharedPreferences  sharedPreferences=getSharedPreferences("Preference",MODE_PRIVATE);
        SharedPreferences.Editor s=sharedPreferences.edit();
        s.clear();
        s.apply();
        SelectedData.getSelectedData().clearData();

    }

    private void toastShow(String msg){
        Toast.makeText(CreateTeams.this, "Error "+ msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectedData.getSelectedData().clearData();
    }
}