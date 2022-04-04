package com.aryan.stumps11.HomePageClick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.stumps11.Activity.ContestCodeActivity;
import com.aryan.stumps11.Activity.PrivateContestActivity;
import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.CreateTeam.CreateTeamAdapter;
import com.aryan.stumps11.CreateTeam.CreateTeams;
import com.aryan.stumps11.CreateTeam.DataBase;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.R;
import com.aryan.stumps11.dataPassing.DataPassringInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import maes.tech.intentanim.CustomIntent;

public class HomePageClick extends AppCompatActivity  {
    public static String Match_id,cid;
    TabLayout tt;
    ViewPager vv;
    ImageView i16;
    RelativeLayout button;
    private String teamNameA,teamNameB;
    private String team1,team2;
    private TextView tvPrivateContext;
    private TextView tvTeamName;
    private TextView tvTeamTime;
    private String  team;
    private String  teamTiming;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.Color1));
        setContentView(R.layout.activity_home_page_click);
        tvPrivateContext=findViewById(R.id.home_page_private_context);
        tvTeamName=findViewById(R.id.home_page_click_team_name);
        tvTeamTime=findViewById(R.id.home_page_click_time);

        teamNameA=getIntent().getStringExtra("TeamNameA");
        teamNameB=getIntent().getStringExtra("TeamNameB");
        team1=getIntent().getStringExtra("teamImage1");
        team2=getIntent().getStringExtra("teamImage2");
        team=teamNameA+" Vs "+teamNameB;
        teamTiming=getIntent().getStringExtra("time");
        tvTeamName.setText(team);
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
                matchDateTime = LocalDateTime.parse(teamTiming,
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
                    tvTeamTime.setText(time);
                }

                public void onFinish() {
                    //Close the popup
                    String time = "0 days : 0 hrs : 0 min : 0 sec";
                    tvTeamTime.setText(time);
                }
            }.start();

        }catch (Exception e){

            Toast.makeText(this, "Exception :- "+e, Toast.LENGTH_SHORT).show();
        }


        db=new DataBase(HomePageClick.this);


        Intent intent=getIntent();

        if(intent.hasExtra(CommonData.Match_id)){
            Match_id=intent.getStringExtra(CommonData.Match_id);

            Log.e("klfk",Match_id+"");

        }

        if (intent.hasExtra(CommonData.C_I_D)){
            cid=intent.getStringExtra(CommonData.C_I_D);
            Log.e("cid>>>",cid);
        }


        tvPrivateContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomePageClick.this);
                bottomSheetDialog.setContentView(R.layout.custom_private_context_dialog_box);
                bottomSheetDialog.show();
                TextView tvCreateContext=bottomSheetDialog.findViewById(R.id.custom_create_context);
                TextView tvCreateInviteLink=bottomSheetDialog.findViewById(R.id.custom_priviate_invite_code);
                tvCreateContext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePageClick.this, PrivateContestActivity.class));
                        finish();
                    }
                });


                tvCreateInviteLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePageClick.this, ContestCodeActivity.class));
                        finish();
                    }
                });


            }
        });






        button=findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (db==null || db.equals(0)){
                }else{
                    db.deleteReminder();

                }

                Intent intent=new Intent(HomePageClick.this,CreateTeams.class);
                intent.putExtra(CommonData.Match_id,Match_id);
                intent.putExtra("TeamNameA",teamNameA);
                intent.putExtra("TeamNameB",teamNameB);
                intent.putExtra("teamImage1",team1);
                intent.putExtra("teamImage2",team2);
                intent.putExtra("time",teamTiming);
               // intent.putExtra("cid",cid);
                intent.putExtra(CommonData.C_I_D,cid);


                startActivity(intent);
                finish();
               /* startActivity(new Intent(HomePageClick.this, CreateTeams.class));
                CustomIntent.customType(HomePageClick.this,"left-to-right");
                finish();*/
            }
        });
        i16=findViewById(R.id.imageView16);
        i16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tt=findViewById(R.id.tablayout);
        vv=findViewById(R.id.viewpager);
        setup(vv);
        tt.setupWithViewPager(vv);
    }

    private void setup(ViewPager viewPager){
        TabAdapter tabadapter=new TabAdapter(getSupportFragmentManager());
        tabadapter.addfrag("Contests",new Contests());
        tabadapter.addfrag("Joined Contest",new JoinedContest());
        tabadapter.addfrag("Teams",new MyTeams());
        vv.setAdapter(tabadapter);
    }

    @Override
    public void onBackPressed() {
        teamNameA=getIntent().getStringExtra("TeamNameA");
        teamNameB=getIntent().getStringExtra("TeamNameB");
        team1=getIntent().getStringExtra("teamImage1");
        team2=getIntent().getStringExtra("teamImage2");
        team=teamNameA+" Vs "+teamNameB;
        teamTiming=getIntent().getStringExtra("time");
        tvTeamTime.setText(teamTiming);
        tvTeamName.setText(team);
        startActivity(new Intent(HomePageClick.this, HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        teamNameA=getIntent().getStringExtra("TeamNameA");
        teamNameB=getIntent().getStringExtra("TeamNameB");
        team1=getIntent().getStringExtra("teamImage1");
        team2=getIntent().getStringExtra("teamImage2");
        team=teamNameA+" Vs "+teamNameB;
        teamTiming=getIntent().getStringExtra("time");
        tvTeamTime.setText(teamTiming);
        tvTeamName.setText(team);
    }

    @Override
    protected void onRestart() {
        super.onRestart();


        teamNameA=getIntent().getStringExtra("TeamNameA");
        teamNameB=getIntent().getStringExtra("TeamNameB");
        team1=getIntent().getStringExtra("teamImage1");
        team2=getIntent().getStringExtra("teamImage2");
        team=teamNameA+" Vs "+teamNameB;
        teamTiming=getIntent().getStringExtra("time");
        tvTeamTime.setText(teamTiming);
        tvTeamName.setText(team);

    }
}