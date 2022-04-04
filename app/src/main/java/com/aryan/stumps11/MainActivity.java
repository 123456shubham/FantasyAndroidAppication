package com.aryan.stumps11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.aryan.stumps11.Activity.NewUserUpdateProfile;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.Signup.MobileNumber;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {
SharedPreferences sp;
String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);
        Timer();

        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
         key  = preferences.getString("TOKEN",null);//second parameter default value.

        //sp=getSharedPreferences("Login",MODE_PRIVATE);
        //key=sp.getString("Key","0");
    }

    @Override
    public void onBackPressed() {
    }

    private void Timer () {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    Log.e("lkjh",key+"");

                    if (key==null) {
                        startActivity(new Intent(MainActivity.this, MobileNumber.class));
                        CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                        finish();

                    } else {

                        startActivity(new Intent(MainActivity.this, HomePage.class));
                        CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                        finish();


                    }
                }
            }, 2000);
        }
    }