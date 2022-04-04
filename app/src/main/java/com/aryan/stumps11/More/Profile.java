package com.aryan.stumps11.More;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileReq;
import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileRes;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileResponse;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.Winners.WinnersActivity;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
ImageView ii;
TextInputEditText u,n,p,e,s,d;
TextInputLayout u1,n1,p1,e1,s1,d1;
SharedPreferences sp,mob,states,sharedPreferences;
SharedPreferences.Editor editor;
private String MonthDate;

    String key,mkey;
    TextView tt;
    MaterialButton bb;
    RelativeLayout delta;
    int year,mmonth,day;
    Calendar calendar;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences=getSharedPreferences("MY_APP",MODE_PRIVATE);
        editor=sharedPreferences.edit();
//        states=getSharedPreferences("state",MODE_PRIVATE);
//        String skey=states.getString("sKey","0");
//        textView=findViewById(R.id.res);
//
//        mob=getSharedPreferences("Mobile",MODE_PRIVATE);
//        mkey=mob.getString("mKey","0");
//
//        sp=getSharedPreferences("Login",MODE_PRIVATE);
//        key=sp.getString("Key","0");

        bb=findViewById(R.id.save);
        u=findViewById(R.id.Username);
        u1=findViewById(R.id.Username1);
        n=findViewById(R.id.Fullname);
        n1=findViewById(R.id.Fullname1);
        p=findViewById(R.id.Phone);
        p1=findViewById(R.id.Phone1);
        e=findViewById(R.id.Email);
        e1=findViewById(R.id.Email1);
        s1=findViewById(R.id.State1);
        d1=findViewById(R.id.Dob1);
        d=findViewById(R.id.Dob);
        s=findViewById(R.id.State);
//        if(skey!="0"){
//            s.setText(skey);
//        }
        delta=findViewById(R.id.deltaRelative);
        tt=findViewById(R.id.textView10);
        ii=findViewById(R.id.imageView4);

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API();
            }
        });

//        if(mkey!="0"){
//            p.setText(mkey);
//        }

        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
            }
        });

        DisplayProfile();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MoreActivity.class));
        overridePendingTransition(0,0);
        finish();
    }

    private void Dialog(){
        AlertDialog.Builder alert=new AlertDialog.Builder(Profile.this);
        alert.setMessage("Are You sure You Want To Logout?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                SharedPreferences.Editor editor=sp.edit();
//                editor.remove("Key");
//                editor.apply();

                editor.clear();
                editor.remove("sKey");
                editor.apply();


                startActivity(new Intent(Profile.this, MobileNumber.class));
                CustomIntent.customType(Profile.this,"fadein-to-fadeout");


                finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=alert.create();
        alertDialog.show();
    }

    private void API(){


        SharedPreferences preferences = Profile.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        String name=n.getText().toString();
        String email=e.getText().toString();
        String dob=d.getText().toString();
        EditProfileReq editProfileReq=new EditProfileReq();

        editProfileReq.setFullName(name);
        editProfileReq.setEmail(email);
        editProfileReq.setDob(dob);

        Log.e("jnvjkh",name+""+email+""+dob);


        try{

            CheckConnection.api.updateProfile(tokenName,editProfileReq).enqueue(new Callback<EditProfileRes>() {
                @Override
                public void onResponse(Call<EditProfileRes> call, Response<EditProfileRes> response) {
                    if (response.isSuccessful()){
                        String name= String.valueOf(response.body().getStatus());
                        Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Profile.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<EditProfileRes> call, Throwable t) {

                    Toast.makeText(Profile.this, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(Profile.this,"Exception"+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }



    }

    private void DisplayProfile(){

        SharedPreferences preferences = Profile.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        CheckConnection.api.getProfile(tokenName).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
//                    API();
//                    Toast.makeText(Profile.this,"Suucc"+response.body().getData().getUsername(),Toast.LENGTH_SHORT).show();
                    String userName=response.body().getData().getUsername();
                 String name=response.body().getData().getFullName();
                    String email=response.body().getData().getEmail();
                    String dob=response.body().getData().getDob();
                    String phone=response.body().getData().getPhone();
                    String state=response.body().getData().getState();
                    u.setText(userName);
                    n.setText(name);
                    e.setText(email);
                    d.setText(dob);
                    p.setText(phone);
                    s.setText(state);


                }else {
                    Toast.makeText(Profile.this,"Token Expire",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(Profile.this,"onFailure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void DatePicker(){
        calendar= Calendar.getInstance();
        mmonth=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(Profile.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;

                if (month<9){
                    MonthDate="0"+month;
                }else{
                    MonthDate= String.valueOf(month);
                }

                String date=year +"/" + MonthDate +"/"+ dayOfMonth;
                d.setText(date);


            }
        },year,mmonth,day);
        datePickerDialog.show();
    }



}