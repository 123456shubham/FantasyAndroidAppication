package com.aryan.stumps11.NewUiData.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterViewPagerMatch;
import com.aryan.stumps11.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MymatchActivity extends AppCompatActivity {
    private TabLayout tabLayoutMatch;
    private ViewPager viewPager_Match;
    private   AdapterViewPagerMatch adapterViewPagerMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymatch);

        Initialization();


    }

    private void Initialization() {

        tabLayoutMatch=findViewById(R.id.tabLayoutMatch);
        viewPager_Match=findViewById(R.id.viewPager_Match);


        adapterViewPagerMatch = new AdapterViewPagerMatch(getSupportFragmentManager(), tabLayoutMatch.getTabCount());
        viewPager_Match.setAdapter(adapterViewPagerMatch);
        viewPager_Match.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMatch));
        tabLayoutMatch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager_Match.setCurrentItem((tab.getPosition()));
                Log.e("TABBBB25897", tab.getPosition() + "");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}