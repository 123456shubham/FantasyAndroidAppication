package com.aryan.stumps11.More;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.aryan.stumps11.Adapters.MoreAdapter;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.Model.MoreModel;
import com.aryan.stumps11.MyMatches.MyMatches;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Winners.WinnersActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends AppCompatActivity {
    RecyclerView Moreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Bottom();
        SetupMore();
    }
    private void Bottom(){
        BottomNavigationView bbn=findViewById(R.id.bottomnav);
        bbn.setSelectedItemId(R.id.More);
        bbn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.HomePage:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.MyMatches:
                        startActivity(new Intent(getApplicationContext(), MyMatches.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.Winners:
                        startActivity(new Intent(getApplicationContext(), WinnersActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.More:
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

    private void SetupMore(){
        Moreview=findViewById(R.id.MoreView);
        Moreview.setLayoutManager(new LinearLayoutManager(MoreActivity.this));
        Moreview.setHasFixedSize(true);
        List<MoreModel> list=new ArrayList<>();

        list.add(new MoreModel(R.drawable.ic_baseline_person_outline_24,"Profile"));
        list.add(new MoreModel(R.drawable.ic_bank_24,"Update Account Details"));
//        list.add(new MoreModel(R.drawable.ic_baseline_money_24,"Recent Transactions"));
        list.add(new MoreModel(R.drawable.ic_baseline_fingerprint_24,"KYC"));
        list.add(new MoreModel(R.drawable.ic_outline_share_24,"Refer & Earn"));
        list.add(new MoreModel(R.drawable.ic_baseline_payment_24,"Enter Referral Code"));
//        list.add(new MoreModel(R.drawable.ic_outline_share_24,"Share Stumps11!"));
        list.add(new MoreModel(R.drawable.ic_baseline_facebook_24,"Follow Us on Social Media"));
        list.add(new MoreModel(R.drawable.ic_baseline_call_24,"Contact Us"));
        list.add(new MoreModel(R.drawable.ic_outline_bar_chart_24,"Fantasy Points System"));
        list.add(new MoreModel(R.drawable.ic_outline_info_24,"About Us"));
        list.add(new MoreModel(R.drawable.ic_outline_article_24,"Terms & Conditions"));
        list.add(new MoreModel(R.drawable.ic_baseline_help_outline_24,"How to Play?"));
        list.add(new MoreModel(R.drawable.ic_outline_privacy_tip_24,"Privacy & Policy"));
        list.add(new MoreModel(R.drawable.ic_baseline_balance_24,"Legality"));

        MoreAdapter ma=new MoreAdapter(MoreActivity.this,list);
        Moreview.setAdapter(ma);
    }
}