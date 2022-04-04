package com.aryan.stumps11.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.stumps11.ApiModel.profile.addBank.GetBankAccountDetails;
import com.aryan.stumps11.ApiModel.profile.wallet.WalletResponse;
import com.aryan.stumps11.ApiModel.profile.withdraw.AmountWithdrawResponse;
import com.aryan.stumps11.ApiModel.profile.withdraw.WithDrawMoneyReq;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Wallet.Wallet;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifiedBankAccountDetails extends AppCompatActivity {

    private ImageView verifyImageBackBtn;
    private TextView balanceTv;
    private TextView accountNo;
    private TextView accountifsc;
    private TextView accountName;
    private TextView accountType;
    private TextInputEditText withdrawMoney;
    private MaterialButton btnSendMoney;
    private int balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_bank_account_details);
        verifyImageBackBtn=findViewById(R.id.verified_bank_account_back_btn);
        balanceTv=findViewById(R.id.verified_bank_account_money);
        accountNo=findViewById(R.id.verify_account_no);
        accountifsc=findViewById(R.id.verified_bank_account_details_ifsc);
//        accountName=findViewById(R.id.verified_bank_account_type);
        accountName=findViewById(R.id.verified_bank_account_name);
        withdrawMoney=findViewById(R.id.verify_bank_account_withdraw_money);
        btnSendMoney=findViewById(R.id.verify_send_money);

        btnSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransfermoneyFromBankAccount();
            }
        });

        verifyImageBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getBankAccountDetails();
        checkWalletBalance();
    }

    private void getBankAccountDetails(){

        SharedPreferences preferences = VerifiedBankAccountDetails.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        try {
            CheckConnection.api.getBankAccount(tokenName).enqueue(new Callback<GetBankAccountDetails>() {
                @Override
                public void onResponse(Call<GetBankAccountDetails> call, Response<GetBankAccountDetails> response) {

                    if (response.isSuccessful()){

                        String accountNumber=response.body().getGetBankData().getBankAccount();
//                        String accountTpe=response.body().getGetBankData().;
                        String accountHolderName=response.body().getGetBankData().getName();
                        String accountIfscNo=response.body().getGetBankData().getIfsc();
                        accountNo.setText(accountNumber);
//                        accountType.setText(accountTpe);
                        accountName.setText(accountHolderName);
                        accountifsc.setText(accountIfscNo);



                    }else{
                        Toast.makeText(VerifiedBankAccountDetails.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<GetBankAccountDetails> call, Throwable t) {
                    Toast.makeText(VerifiedBankAccountDetails.this, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void TransfermoneyFromBankAccount(){
        String money=withdrawMoney.getText().toString();
        SharedPreferences preferences = VerifiedBankAccountDetails.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        if (money.isEmpty()){
            withdrawMoney.setError("Please Enter Number");
            withdrawMoney.requestFocus();
            return;
        }

        if (Integer.parseInt(money)>balance){

            Toast.makeText(this, "Your Wallet Balance To Low", Toast.LENGTH_SHORT).show();

        }else {


            WithDrawMoneyReq withDrawMoneyReq = new WithDrawMoneyReq();
            withDrawMoneyReq.setAmount(money);


            try {
                CheckConnection.api.getWithdraw(tokenName, withDrawMoneyReq).enqueue(new Callback<AmountWithdrawResponse>() {
                    @Override
                    public void onResponse(Call<AmountWithdrawResponse> call, Response<AmountWithdrawResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            Toast.makeText(VerifiedBankAccountDetails.this, response.body().getAmountResponse().getStatus(), Toast.LENGTH_SHORT).show();

                            Toast.makeText(VerifiedBankAccountDetails.this, response.body().getAmountResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VerifiedBankAccountDetails.this, "Error", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<AmountWithdrawResponse> call, Throwable t) {
                        Toast.makeText(VerifiedBankAccountDetails.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void checkWalletBalance(){
        SharedPreferences preferences = VerifiedBankAccountDetails.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        CheckConnection.api.getWalletBalance(tokenName).enqueue(new Callback<WalletResponse>() {
            @Override
            public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                if (response.isSuccessful()){
                     balance=response.body().getData().getWallet();

                    int addBouns=response.body().getData().getBounsWallet();
                    int addWinning=response.body().getData().getWinningWallet();
                    int addCash=response.body().getData().getAddCash();




//                    Toast.makeText(Wallet.this,"Balance : "+response.body().getData().getWallet(),Toast.LENGTH_SHORT).show();
                    balanceTv.setText(String.valueOf("₹ "+balance));


                }else{
                    Toast.makeText(VerifiedBankAccountDetails.this,"ERR : ",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WalletResponse> call, Throwable t) {
                Toast.makeText(VerifiedBankAccountDetails.this,"on Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }



}