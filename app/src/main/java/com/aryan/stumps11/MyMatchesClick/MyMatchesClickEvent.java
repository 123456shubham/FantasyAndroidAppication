package com.aryan.stumps11.MyMatchesClick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.aryan.stumps11.MyMatches.MyMatches;
import com.aryan.stumps11.R;

public class MyMatchesClickEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches_click_event);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyMatchesClickEvent.this, MyMatches.class));
        overridePendingTransition(0,0);
        finish();
    }
}