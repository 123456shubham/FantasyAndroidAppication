package com.aryan.stumps11.More;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.GetReferCodeResponse;
import com.aryan.stumps11.Class.Class;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferandEarn extends AppCompatActivity {
ImageView i6,i8;
TextView t24;
MaterialButton share;
SharedPreferences mob;
String mkey;
private TextView tvMsg;
String appUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referand_earn);

        Class c=new Class();
        mob=getSharedPreferences("Mobile",MODE_PRIVATE);
        mkey=mob.getString("mKey","0");

        t24=findViewById(R.id.refer_code_number);
        share=findViewById(R.id.Share);
        i6=findViewById(R.id.imageView6);
        i8=findViewById(R.id.imageView8);
        tvMsg=findViewById(R.id.refer_code_msg);
        DisplayProfile();
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferRules();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.Share(ReferandEarn.this,appUrl+t24.getText().toString());
            }
        });

        t24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Copy();
            }
        });

        DisplayReferralCode();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MoreActivity.class));
        overridePendingTransition(0,0);
        finish();
    }

    private void ReferRules(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ReferandEarn.this);
        bottomSheetDialog.setContentView(R.layout.referrules);
        bottomSheetDialog.show();
        MaterialButton verify = bottomSheetDialog.findViewById(R.id.ok);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void Copy(){
        ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd =(ClipData) ClipData.newPlainText("Stump 11",t24.getText());
        clipboardManager.setPrimaryClip(cd);
        Toast.makeText(ReferandEarn.this, "Referral Code Copied!", Toast.LENGTH_SHORT).show();
    }

    private void DisplayReferralCode(){
        Dialog dialog=new Dialog(ReferandEarn.this);
        dialog.setContentView(R.layout.progressbar);
        dialog.show();
        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);

        try {
            SharedPreferences preferences = ReferandEarn.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            String tokenName="Bearer "+retrivedToken;
            CheckConnection.api.getRefferCode(tokenName).enqueue(new Callback<GetReferCodeResponse>() {
                @Override
                public void onResponse(Call<GetReferCodeResponse> call, Response<GetReferCodeResponse> response) {
                    if(response.isSuccessful()){
                        dialog.dismiss();
//                        Toast.makeText(ReferandEarn.this,"Your error Code",Toast.LENGTH_SHORT).show();
                        String referCodeMsg=response.body().getData().getMessage();
                        appUrl=response.body().getData().getUrl();
                        tvMsg.setText(referCodeMsg);

                    }else{
                        Toast.makeText(ReferandEarn.this,"Error : ",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetReferCodeResponse> call, Throwable t) {
                    Toast.makeText(ReferandEarn.this,"onFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ReferandEarn.this,"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();


        }

    }
    private void DisplayProfile(){

        SharedPreferences preferences = ReferandEarn.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        CheckConnection.api.getProfile(tokenName).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(Profile.this,"Suucc"+response.body().getData().getUsername(),Toast.LENGTH_SHORT).show();


                    String referCode=response.body().getData().getGetReferCode();
                    t24.setText(referCode);

                }else {
                    Toast.makeText(ReferandEarn.this,"Token Expire",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(ReferandEarn.this,"onFailure",Toast.LENGTH_SHORT).show();
            }
        });
    }

}