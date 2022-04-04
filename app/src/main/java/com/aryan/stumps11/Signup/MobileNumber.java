package com.aryan.stumps11.Signup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Activity.NewUserUpdateProfile;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.More.TermsandConditions;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.model.Data;
import com.aryan.stumps11.Signup.model.MobileRequest;
import com.aryan.stumps11.Signup.model.MobileResponse;
import com.aryan.stumps11.Signup.model.MobileVerfiyOTPRequest;
import com.aryan.stumps11.Signup.model.MobileVerifyOtpResponse;
import com.aryan.stumps11.api_integration.API_INTERFACE;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileNumber extends AppCompatActivity {
TextInputEditText contact,otp2;
TextInputLayout contact1,otp1;
MaterialButton bb,verify;
TextView terms,tt;
ImageView ii;
private boolean UserType=false;
CountDownTimer cc;
private static final String FORMAT = "%02d:%02d";
String Teamname,Refer,otpmsg;
private SharedPreferences sharedPreferences,sp,mob;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_mobile_number);

        sp=getSharedPreferences("Login",MODE_PRIVATE);
        mob=getSharedPreferences("Mobile",MODE_PRIVATE);

        contact=findViewById(R.id.Contact_Number);
        contact1=findViewById(R.id.Contact_Number1);
        bb=findViewById(R.id.Continue);
        terms=findViewById(R.id.Terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MobileNumber.this, TermsandConditions.class));
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpdialog();
                Username();
                GenerateOTP();
                //ReferralCode();
               // API();



            }
        });
        TextWatcher();
    }

    private void otpdialog(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MobileNumber.this);
        bottomSheetDialog.setContentView(R.layout.dialogforotp);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        otp1=bottomSheetDialog.findViewById(R.id.OTP1);
        otp2=bottomSheetDialog.findViewById(R.id.OTP);
        tt=bottomSheetDialog.findViewById(R.id.textView);
        ii=bottomSheetDialog.findViewById(R.id.imageView9);
        verify = bottomSheetDialog.findViewById(R.id.Continue);

        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                cc.cancel();

            }
        });

        verify.setEnabled(false);
        verify.setBackgroundColor(ContextCompat.getColor(MobileNumber.this,R.color.Color2));
        TextWatcher tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp2.getText().toString().length()==6){
                    verify.setEnabled(true);
                    verify.setBackgroundColor(ContextCompat.getColor(MobileNumber.this,R.color.Color5));
                }
                else {
                    verify.setEnabled(false);
                    verify.setBackgroundColor(ContextCompat.getColor(MobileNumber.this,R.color.Color2));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        otp2.addTextChangedListener(tw);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API();

//                if(otpmsg.equals(otp2.getText().toString())){
//                    SharedPreferences.Editor editor=sp.edit();
//                    editor.putString("Key",""+otp2.getText().toString());
//                    editor.apply();
//
//                    SharedPreferences.Editor editor1=mob.edit();
//                    editor1.putString("mKey",""+contact.getText().toString());
//                    editor1.apply();
//
//                    startActivity(new Intent(MobileNumber.this, HomePage.class));
//                    CustomIntent.customType(MobileNumber.this,"fadein-to-fadeout");
//                    finish();
//                    API();
//
//                }
//                else {
//                    Dialog dialog=new Dialog(MobileNumber.this);
//                    dialog.setContentView(R.layout.progressbar);
//                    dialog.show();
//                    dialog.setCancelable(false);
//                    dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MobileNumber.this, "Please Enter Valid OTP.", Toast.LENGTH_SHORT).show();
//                            dialog.hide();
//                        }
//                    },1000);
//                }
            }
        });



        Timer_for_Resend();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void API() {
        Dialog dialog=new Dialog(MobileNumber.this);
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        dialog.setContentView(R.layout.progressbar);
        String verifyOTP= otp2.getText().toString();
        String mobile=contact.getText().toString();



        MobileVerfiyOTPRequest mobileVerfiyOTPRequest=new MobileVerfiyOTPRequest();
        mobileVerfiyOTPRequest.setOtp(verifyOTP);
        mobileVerfiyOTPRequest.setPhone(mobile);

        CheckConnection.api.verifyOTP(mobileVerfiyOTPRequest).enqueue(new Callback<MobileVerifyOtpResponse>() {
            @Override
            public void onResponse(Call<MobileVerifyOtpResponse> call, Response<MobileVerifyOtpResponse> response) {
                if (response.isSuccessful()){
                    //Save app token here


                    String token1 = response.body().getAccessToken();
                    SharedPreferences preferences = getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                    preferences.edit().putString("TOKEN",token1).apply();

                    if (UserType==true){

//                        String token1 = response.body().getAccessToken();
//                        SharedPreferences preferences = getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
//                        preferences.edit().putString("TOKEN",token1).apply();
                        //startActivity(new Intent(getApplicationContext(), NewUserUpdateProfile.class));
                        Intent intent=new Intent(getApplicationContext(),NewUserUpdateProfile.class);
                        intent.putExtra("MobileNo",mobile);
                        startActivity(intent);



                    }else{


                        startActivity(new Intent(MobileNumber.this, HomePage.class));
                        CustomIntent.customType(MobileNumber.this,"fadein-to-fadeout");
                        finish();
                    }




                } else{
//                    Dialog dialog=new Dialog(MobileNumber.this);
//                    dialog.setContentView(R.layout.progressbar);
//                    dialog.dismiss();
////                    dialog.setCancelable(false);
//                    dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
//                    dialog.dismiss();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(MobileNumber.this, "Please Enter Valid OTP.", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    },1000);
                    dialog.dismiss();
               }

            }

            @Override
            public void onFailure(Call<MobileVerifyOtpResponse> call, Throwable t) {
                Toast.makeText(MobileNumber.this,"onFailure"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

//        RequestQueue rq= Volley.newRequestQueue(MobileNumber.this);
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://www.stumps11.com/APIS/Apis.asmx/RegisterandLogin", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                JSONObject jsonObject=new JSONObject(response);
//                String msg=jsonObject.getString("message");
//                dialog.hide();
//                } catch (JSONException e) {
//                    e.printStackTrace();
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
//                Map <String,String>map=new HashMap<String, String>();
//                map.put("mob",contact.getText().toString());
//                map.put("teamname",Teamname);
//                map.put("refercode",Refer);
//                map.put("name","");
//                map.put("email","");
//                map.put("state","");
//                map.put("dob","");
//                return map;
//            }
//        };
//        rq.add(stringRequest);
    }

    private void CloseKeyboad(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void TextWatcher(){
        bb.setEnabled(false);
        bb.setBackgroundColor(ContextCompat.getColor(MobileNumber.this,R.color.Color2));
        TextWatcher tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(contact.getText().toString().length()==10){
                    bb.setEnabled(true);
                    bb.setBackgroundColor(ContextCompat.getColor(MobileNumber.this,R.color.Color5));
                    CloseKeyboad();
                }
                else {
                    bb.setEnabled(false);
                    bb.setBackgroundColor(ContextCompat.getColor(MobileNumber.this,R.color.Color2));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        contact.addTextChangedListener(tw);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Username(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 11;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Teamname = generatedString;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ReferralCode(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 7;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        int leftLimit1 = 97;
        int rightLimit1 = 122;
        int targetStringLength1 = 7;
        Random random1 = new Random();

        String generatedString1 = random1.ints(leftLimit1, rightLimit1 + 1)
                .limit(targetStringLength1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Random r = new Random();
        String k=String. format("%04d", r.nextInt(9999));

        Refer=generatedString+k+generatedString1;
    }

    private void GenerateOTP(){


        String phone=contact.getText().toString();
        MobileRequest mobileRequest=new MobileRequest();
        mobileRequest.setPhone(phone);
        CheckConnection.api.getAllData(mobileRequest).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.isSuccessful()){

                    assert response.body() != null;
                    String otp=response.body().getMobileModelResponse().getData().getOtp();

                     UserType=response.body().getMobileModelResponse().getNewUser();

                     Log.e("jhg",UserType+"");



                    Toast.makeText(MobileNumber.this,"OTP : "+otp,Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MobileNumber.this,"False",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(MobileNumber.this,"OnFailure",Toast.LENGTH_LONG).show();


            }
        });





//        Random r = new Random();
//        otpmsg=String. format("%06d", r.nextInt(999999));
//        Toast.makeText(this, ""+otpmsg, Toast.LENGTH_SHORT).show();
    }

    private void Timer_for_Resend(){
        cc=new CountDownTimer(30000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                tt.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + " " + "? Resend OTP");
                tt.setClickable(false);
                tt.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Color3));
            }

            public void onFinish() {
                tt.setText("Resend OTP?");
                tt.setClickable(true);
                tt.setTextColor(Color.BLACK);
                tt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Timer_for_Resend();
                        GenerateOTP();
                    }
                });
            }
        }.start();
    }
}