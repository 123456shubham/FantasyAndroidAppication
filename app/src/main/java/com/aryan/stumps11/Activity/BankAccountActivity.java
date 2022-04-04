package com.aryan.stumps11.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aryan.stumps11.ApiModel.profile.EditProfile.EditProfileReq;
import com.aryan.stumps11.ApiModel.profile.addBank.AddBankRequest;
import com.aryan.stumps11.ApiModel.profile.addBank.AddBankResponse;
import com.aryan.stumps11.ApiModel.profile.addBank.GetBankAccountDetails;
import com.aryan.stumps11.More.KYC;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankAccountActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String UserMobile,UserEmail,UName,UEmail,UAccount,UIfs,Upid;
    private MaterialButton btn_Update;
    private TextInputEditText et_Fullname,et_Email,et_AccountNoConfirm,et_IfsCode,et_UPIID;
    private ImageView imgBackBtn;
    // regex validation

    private String regIFSC = "^[A-Z]{4}[0][A-Z0-9]{6}$";
    private boolean isvalid = false;
    private String regAccount="[0-9]{9,18}";
    private String MobileRegex="(0|91)?[6-9][0-9]{9}";
    private String pincodeRegx="^[1-9][0-9]{5}$";
    private String upiRegex="^[\\w\\.\\-_]{3,}@[a-zA-Z]{3,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);

       sharedPreferences=getSharedPreferences("MY_APP",MODE_PRIVATE);
       if (sharedPreferences.contains("MobileNo")){
            UserMobile=sharedPreferences.getString("MobileNo","");
           UserEmail=sharedPreferences.getString("UserName","");
       }
    Initilization();
       getBankAccountDetails();


    }

    private void Initilization() {

        btn_Update=findViewById(R.id.btn_Update);

        et_Fullname=findViewById(R.id.et_Fullname);
        et_Email=findViewById(R.id.et_Email);
        et_AccountNoConfirm=findViewById(R.id.et_AccountNoConfirm);
        et_IfsCode=findViewById(R.id.et_IfsCode);
        et_UPIID=findViewById(R.id.et_UPIID);

        imgBackBtn=findViewById(R.id.bank_account_back_btn);

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });


        long TimeStamp=System.currentTimeMillis();


        Log.e("klmlm",TimeStamp+"");



    }

    private void Validation() {

        UName=et_Fullname.getText().toString();
        UEmail=et_Email.getText().toString();

        UAccount=et_AccountNoConfirm.getText().toString();
        UIfs=et_IfsCode.getText().toString();
        Upid=et_UPIID.getText().toString();

        String uifsc=et_IfsCode.getText().toString();
        String upid=et_UPIID.getText().toString();
        String uAccount=et_AccountNoConfirm.getText().toString();



        if (TextUtils.isEmpty(UName)){
            et_Fullname.setError("Enter Name");
            et_Fullname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(UEmail)){
            et_Email.setError("Enter Email");
            et_Email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(UAccount)){
            et_AccountNoConfirm.setError("Enter Account No");
            et_AccountNoConfirm.requestFocus();
            return;
        }

        if(!uAccount.matches(regAccount)){
            et_AccountNoConfirm.setError("In-Valid Account Number");
            et_AccountNoConfirm.requestFocus();
            return;

        }

        if (TextUtils.isEmpty(UIfs)){
            et_IfsCode.setError("Enter IFSC Code");
            et_IfsCode.requestFocus();
            return;
        }
        if(!uifsc.matches(regIFSC)){
            et_IfsCode.setError("In-valid IFSC Code");
            et_IfsCode.requestFocus();
            return;
        }



        if (TextUtils.isEmpty(Upid)){
            et_UPIID.setError("Enter UPI ID");
            et_UPIID.requestFocus();
            return;
        }



        if (!upid.matches(upiRegex)){
            et_UPIID.setError("In-valid UPI ");
            et_UPIID.requestFocus();
            return;
        }

        CallUpdateBankApi();

    }

    private void CallUpdateBankApi() {


        String accountHolder=et_Fullname.getText().toString();
        String email=et_Email.getText().toString();
        String accountNo=et_AccountNoConfirm.getText().toString();
        String ifcode=et_IfsCode.getText().toString();
        String upiID=et_UPIID.getText().toString();

        SharedPreferences preferences = BankAccountActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        AddBankRequest addBankRequest=new AddBankRequest();
        addBankRequest.setBankAccount(accountNo);
        addBankRequest.setEmail(email);
        addBankRequest.setIfsc(ifcode);
        addBankRequest.setName(accountHolder);
        addBankRequest.setVpn(upiID);

        try{
            CheckConnection.api.addBankDetails(tokenName,addBankRequest).enqueue(new Callback<AddBankResponse>() {
                @Override
                public void onResponse(Call<AddBankResponse> call, Response<AddBankResponse> response) {

                    if (response.isSuccessful()){
                        Toast.makeText(BankAccountActivity.this,"Sucess "+response.body().getUserBeneficiary(),Toast.LENGTH_SHORT).show();
                    }else if(response.code()==422){
                        Toast.makeText(BankAccountActivity.this,"Error " +response.body().getUserBeneficiary(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BankAccountActivity.this,"Other error code ",Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<AddBankResponse> call, Throwable t) {
                    Toast.makeText(BankAccountActivity.this,"OnFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();


                }
            });

        }catch(Exception e){
            Toast.makeText(BankAccountActivity.this,"Exception  "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }


    private void getBankAccountDetails(){

        SharedPreferences preferences = BankAccountActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        try {
            CheckConnection.api.getBankAccount(tokenName).enqueue(new Callback<GetBankAccountDetails>() {
                @Override
                public void onResponse(Call<GetBankAccountDetails> call, Response<GetBankAccountDetails> response) {

                    if (response.isSuccessful()){
                        try{

                            String accountNumber=response.body().getGetBankData().getBankAccount();
                            String accountHolderName=response.body().getGetBankData().getName();
                            String accountIfscNo=response.body().getGetBankData().getIfsc();
                            String vpa=response.body().getGetBankData().getVpa();
                            String email=response.body().getGetBankData().getEmail();

                            et_AccountNoConfirm.setText(accountNumber);
//                        accountType.setText(accountTpe);
                            et_UPIID.setText(vpa);
                            et_Fullname.setText(accountHolderName);
                            et_IfsCode.setText(accountIfscNo);
                            et_Email.setText(email);
                            btn_Update.setVisibility(View.INVISIBLE);


                        }catch (Exception e){
                            e.printStackTrace();
                        }
                       


                    }else{
                        Toast.makeText(BankAccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<GetBankAccountDetails> call, Throwable t) {
                    Toast.makeText(BankAccountActivity.this, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

}