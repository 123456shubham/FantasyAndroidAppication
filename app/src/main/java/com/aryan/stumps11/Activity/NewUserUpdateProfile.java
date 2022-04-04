package com.aryan.stumps11.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileReq;
import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileRes;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileResponse;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.More.MoreActivity;
import com.aryan.stumps11.More.Profile;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserUpdateProfile extends AppCompatActivity {
ImageView ii;
TextInputEditText u,n,p,e,state,d;
TextInputLayout u1,n1,p1,e1,s1,d1;
SharedPreferences sp,mob,states,sharedPreferences;
private SharedPreferences.Editor editor;
private String  MonthDate;
    String key,mkey,mobileNo;
    TextView tt;
    MaterialButton bb;
    RelativeLayout delta;
    int year,mmonth,day;
    Calendar calendar;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_update_profile);

//        sharedPreferences=getSharedPreferences("MY_APP",MODE_PRIVATE);
//        editor=sharedPreferences.edit();

        Intent intent=getIntent();
        if (intent.hasExtra("MobileNo")){
             mobileNo=intent.getStringExtra("MobileNo");

        }


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
        state=findViewById(R.id.State);
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
//
//        if(mkey!="0"){
//            p.setText(mkey);
//        }

        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
//        overridePendingTransition(0,0);
//        finish();
    }



    private void API(){

        SharedPreferences preferences = NewUserUpdateProfile.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        Log.e("lbm",tokenName+"");

        String name=n.getText().toString();
        String email=e.getText().toString();
        String dob=d.getText().toString();
        String State=state.getText().toString();

        EditProfileReq editProfileReq=new EditProfileReq();
        editProfileReq.setState(State);
        editProfileReq.setDob(dob);
        editProfileReq.setFullName(name);
        editProfileReq.setEmail(email);
        Log.e("kkl",editProfileReq.toString()+"");


        try{

            CheckConnection.api.updateProfile(tokenName,editProfileReq).enqueue(new Callback<EditProfileRes>() {
                @Override
                public void onResponse(Call<EditProfileRes> call, Response<EditProfileRes> response) {
                    Log.e("kmkjk",response.toString()+"");
                    if(response.isSuccessful()){
//                        editor.putString("MobileNo",mobileNo);
//                        editor.putString("UserEmail",email);
//                        editor.apply();
//                        editor.commit();

                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        finish();

//                        Toast.makeText(NewUserUpdateProfile.this,"Profile : "+response.body().getErrors(),Toast.LENGTH_SHORT).show();


                    }else
                    if(response.code()==422){

                        Toast.makeText(NewUserUpdateProfile.this,"Profile Error : ",Toast.LENGTH_SHORT).show();

                    } else if(response.code()==500) {
                        Toast.makeText(NewUserUpdateProfile.this, "Profile Error  500: ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(NewUserUpdateProfile.this, "Profile Error  other: ", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<EditProfileRes> call, Throwable t) {
                    Toast.makeText(NewUserUpdateProfile.this,"OnFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });


        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(NewUserUpdateProfile.this,"Exception"+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }



//        RequestQueue rq= Volley.newRequestQueue(Profile.this);
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://www.stumps11.com/APIS/Apis.asmx/Update", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    String msg=jsonObject.getString("message");
//                    Snackbar snackbar=Snackbar.make(delta,msg,Snackbar.LENGTH_SHORT);
//                    snackbar.show();
//                    snackbar.setBackgroundTint(Color.RED);
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String>map=new HashMap<String, String>();
//                map.put("name",n.getText().toString());
//                map.put("email",e.getText().toString());
//                map.put("state",s.getText().toString());
//                map.put("dob",d.getText().toString());
//                map.put("mob",p.getText().toString());
//                return map;
//            }
//        };
//        rq.add(stringRequest);
    }


    private void DatePicker(){
        calendar= Calendar.getInstance();
        mmonth=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(NewUserUpdateProfile.this, new DatePickerDialog.OnDateSetListener() {
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