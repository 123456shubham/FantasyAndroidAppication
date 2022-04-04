package com.aryan.stumps11.MyMatches;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.More.MoreActivity;
import com.aryan.stumps11.NewUiData.Activity.Fragment.CompleteMatchFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.LiveMatchFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.UpcomingMatchFragment;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Winners.WinnersActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Set;

public class MyMatches extends AppCompatActivity {
    TabLayout tt;
    ViewPager vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        Bottom();
        tt=findViewById(R.id.tablayout);
        vv=findViewById(R.id.viewpager);
        SetTab(vv);
        tt.setupWithViewPager(vv);

    }

    private void Bottom(){
        BottomNavigationView bbn=findViewById(R.id.bottomnav);
        bbn.setSelectedItemId(R.id.MyMatches);
        bbn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.HomePage:
                        startActivity(new Intent(getApplicationContext(),HomePage.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.MyMatches:
                        return true;

                    case R.id.Winners:
                        startActivity(new Intent(getApplicationContext(), WinnersActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.More:
                        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }


                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }

    private void SetTab(ViewPager viewPager){
        TabAdapter tabadapter=new TabAdapter(getSupportFragmentManager());
        tabadapter.addfrag("Upcoming",new UpcomingMatchFragment());
        tabadapter.addfrag("Live",new LiveMatchFragment());
        tabadapter.addfrag("Completed",new CompleteMatchFragment());
        vv.setAdapter(tabadapter);
    }
}