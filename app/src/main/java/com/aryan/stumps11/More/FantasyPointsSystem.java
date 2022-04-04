package com.aryan.stumps11.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.Class.Class;
import com.aryan.stumps11.MyMatches.Completed;
import com.aryan.stumps11.MyMatches.Live;
import com.aryan.stumps11.MyMatches.Upcoming;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Winners.WinnersActivity;
import com.google.android.material.tabs.TabLayout;

public class FantasyPointsSystem extends AppCompatActivity {
    TabLayout tt;
    ImageView i4;
    ViewPager vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_points_system);

        //For ProgressBar.
        Class clas=new Class();
        clas.DisplayprogressforTexts(FantasyPointsSystem.this);
        i4=findViewById(R.id.imageView4);
        tt=findViewById(R.id.tablayout);
        vv=findViewById(R.id.viewpager);
        SetTab(vv);
        tt.setupWithViewPager(vv);

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void SetTab(ViewPager viewPager){
        TabAdapter tabadapter=new TabAdapter(getSupportFragmentManager());
        tabadapter.addfrag("Test",new Test());
        tabadapter.addfrag("ODI",new ODI());
        tabadapter.addfrag("T20",new T20());
        tabadapter.addfrag("T10",new T10());
        vv.setAdapter(tabadapter);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MoreActivity.class));
        overridePendingTransition(0,0);
        finish();
    }
}