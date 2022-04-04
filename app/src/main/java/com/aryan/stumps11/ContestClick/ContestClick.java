package com.aryan.stumps11.ContestClick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.R;
import com.google.android.material.tabs.TabLayout;

public class ContestClick extends AppCompatActivity {
    TabLayout tt;
    ViewPager vv;
    public static String TeamID;
    private ImageView imgBackBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_contest_click);
        tt=findViewById(R.id.tablayout);
        vv=findViewById(R.id.viewpager);
        imgBackBtn=findViewById(R.id.joined_context_back_btn);

        setup(vv);
        tt.setupWithViewPager(vv);

        Intent intent=getIntent();
        if (intent.hasExtra(CommonData.TEAM_ID)){
            TeamID=intent.getStringExtra(CommonData.TEAM_ID);
            Log.e("lmmklkkk",TeamID +"");

        }

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void setup(ViewPager viewPager){
        TabAdapter tabadapter=new TabAdapter(getSupportFragmentManager());
        tabadapter.addfrag("Winnings",new Winnings());
        tabadapter.addfrag("Leaderboard",new Leaderboard());
        vv.setAdapter(tabadapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ContestClick.this, HomePageClick.class));
        overridePendingTransition(0,0);
        finish();
    }
}