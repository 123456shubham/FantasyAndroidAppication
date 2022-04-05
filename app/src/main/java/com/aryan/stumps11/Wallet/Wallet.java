package com.aryan.stumps11.Wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.Activity.NewUserUpdateProfile;
import com.aryan.stumps11.Activity.VerifiedBankAccountDetails;
import com.aryan.stumps11.Adapters.TransactionAdapter;
import com.aryan.stumps11.ApiModel.profile.addMoney.AddMoneyRequest;
import com.aryan.stumps11.ApiModel.profile.addMoney.AddMoneyResponse;
import com.aryan.stumps11.ApiModel.profile.transaction.MyTransactionData;
import com.aryan.stumps11.ApiModel.profile.transaction.MyTransactionResponse;
import com.aryan.stumps11.ApiModel.profile.verifyAccount.VerifyAccountResponse;
import com.aryan.stumps11.ApiModel.profile.wallet.WalletResponse;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.Model.TransactionModel;
import com.aryan.stumps11.More.KYC;
import com.aryan.stumps11.More.Profile;
import com.aryan.stumps11.More.RecentTransactions;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.Wallet.webViewPage.AddMoneyWebViewActivity;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wallet extends AppCompatActivity {
private TextView withdraw,add;
private ImageView i11;
private TextView tvBalance;
private RecyclerView tractionRecyclerview;
private TextInputEditText cash,withdrawMoney;
private TransactionAdapter transactionAdapter;
private List<MyTransactionData> transactionModels;
private TextView tv_wallet_added_cash;
private boolean isVerifyAccount=false;
private TextView tvWalletBouns;
private TextView tvAddCouns;
private TextView tvWinning;
private ImageView imgNoTrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        withdraw=findViewById(R.id.WithdrawCash);

        add=findViewById(R.id.AddCash);
        i11=findViewById(R.id.imageView11);
        tvBalance=findViewById(R.id.wallet_balance);
        tractionRecyclerview=findViewById(R.id.wallet_transaction);
        tractionRecyclerview.setHasFixedSize(false);
        tractionRecyclerview.setLayoutManager(new LinearLayoutManager(Wallet.this,LinearLayoutManager.VERTICAL,false));

        tv_wallet_added_cash=findViewById(R.id.wallet_added_cash);
        tvWalletBouns=findViewById(R.id.wallet_bonus);
        tvWinning=findViewById(R.id.wallet_winning);
        imgNoTrans=findViewById(R.id.wallet_no_trans);

//        transactionModels=new ArrayList<>();

//        transactionAdapter=new TransactionAdapter(transactionModels,Wallet.this);
//        tractionRecyclerview.setAdapter(transactionAdapter);


        //

        i11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // DialogWithdrawCash();
                VerifyBankAccountDetails();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddCash();
            }
        });

//        recent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Wallet.this, RecentTransactions.class));
//                finish();
//            }
//        });

        checkWalletBalance();
        allTransaction();

    }

    private void VerifyBankAccountDetails(){

        SharedPreferences preferences = Wallet.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        CheckConnection.api.verifyBankAccount(tokenName).enqueue(new Callback<VerifyAccountResponse>() {
            @Override
            public void onResponse(Call<VerifyAccountResponse> call, Response<VerifyAccountResponse> response) {
                if(response.isSuccessful()){
                    isVerifyAccount=response.body().getBeneficiary();
                    if(isVerifyAccount){
                        startActivity(new Intent(Wallet.this, VerifiedBankAccountDetails.class));
                        finish();
                    }else{
                        startActivity(new Intent(Wallet.this, BankAccountActivity.class));
                        finish();
                    }

                }else{

                    Toast.makeText(Wallet.this,"Enter Bank Account Details",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyAccountResponse> call, Throwable t) {
                Toast.makeText(Wallet.this,"onFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void DialogAddCash(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Wallet.this);
        bottomSheetDialog.setContentView(R.layout.addcashdialog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputLayout cash1=bottomSheetDialog.findViewById(R.id.Cash1);
        cash=bottomSheetDialog.findViewById(R.id.add_money_wallet);
        ImageView ii=bottomSheetDialog.findViewById(R.id.imageView9);
        MaterialButton addc = bottomSheetDialog.findViewById(R.id.add);


        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        addc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoneyFromBankAccount();
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void DialogWithdrawCash(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Wallet.this);
        bottomSheetDialog.setContentView(R.layout.withdrawcashdialog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputLayout cash1=bottomSheetDialog.findViewById(R.id.Cash1);
         withdrawMoney=bottomSheetDialog.findViewById(R.id.with_draw_money);
        ImageView ii=bottomSheetDialog.findViewById(R.id.imageView9);
        MaterialButton withdrawc = bottomSheetDialog.findViewById(R.id.wc);

        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        withdrawc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void checkWalletBalance(){
        SharedPreferences preferences = Wallet.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        CheckConnection.api.getWalletBalance(tokenName).enqueue(new Callback<WalletResponse>() {
            @Override
            public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                if (response.isSuccessful()){
                    int balance=response.body().getData().getWallet();

                    int addBouns=response.body().getData().getBounsWallet();
                    int addWinning=response.body().getData().getWinningWallet();
                    int addCash=response.body().getData().getAddCash();


//                    Toast.makeText(Wallet.this,"Balance : "+response.body().getData().getWallet(),Toast.LENGTH_SHORT).show();
                    tvBalance.setText(String.valueOf("₹ "+balance));
                    tvWalletBouns.setText(String.valueOf("₹ "+addBouns));
                    tvWinning.setText(String.valueOf("₹ "+addWinning));
                    tv_wallet_added_cash.setText(String.valueOf("₹ "+addCash));


                }else{
                    Toast.makeText(Wallet.this,"ERR : ",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WalletResponse> call, Throwable t) {
                Toast.makeText(Wallet.this,"on Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addMoneyFromBankAccount(){

        int amount= Integer.parseInt(cash.getText().toString());


        if (amount==0 ){
            cash.setError("Enter Valid Amount");
            return;
        }



        SharedPreferences preferences = Wallet.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        AddMoneyRequest addMoneyRequest=new AddMoneyRequest();
        addMoneyRequest.setAmount(amount);

        CheckConnection.api.addMoney(tokenName,addMoneyRequest).enqueue(new Callback<AddMoneyResponse>() {
            @Override
            public void onResponse(Call<AddMoneyResponse> call, Response<AddMoneyResponse> response) {

                if (response.isSuccessful()){
//                    Toast.makeText(Wallet.this,"Payment Link"+response.body().getPaymentLink(),Toast.LENGTH_SHORT).show();
                    String paymentUrl=response.body().getPaymentLink();
                    Intent addMoney=new Intent(Wallet.this, AddMoneyWebViewActivity.class);
                    addMoney.putExtra("payment",paymentUrl);
                    startActivity(addMoney);

                }else{
                    Toast.makeText(Wallet.this,"Errror",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<AddMoneyResponse> call, Throwable t) {

                Toast.makeText(Wallet.this,"onFailure"+t.getMessage(),Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void allTransaction(){
        SharedPreferences preferences = Wallet.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        try{

            CheckConnection.api.getAllTransaction(tokenName).enqueue(new Callback<MyTransactionResponse>() {
                @Override
                public void onResponse(Call<MyTransactionResponse> call, Response<MyTransactionResponse> response) {
                    if (response.isSuccessful()){

                        transactionModels=response.body().getTransactionData();

                        if (transactionModels.equals("0") || transactionModels.size()==0 || transactionModels.isEmpty()){
                            imgNoTrans.setVisibility(View.VISIBLE);
                        }else{
                            for(int i=0; i<transactionModels.size(); i++){
                                transactionAdapter=new TransactionAdapter(transactionModels,Wallet.this);
                                tractionRecyclerview.setAdapter(transactionAdapter);
                                transactionAdapter.notifyDataSetChanged();
                            }
                        }


                    }else{
                        Toast.makeText(Wallet.this,"Error :-",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<MyTransactionResponse> call, Throwable t) {
                    Toast.makeText(Wallet.this,"onFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(Wallet.this,"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkWalletBalance();
        allTransaction();
    }
}