package com.aryan.stumps11.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.EnterReferCodeRequest;
import com.aryan.stumps11.ApiModel.profile.refercode.EnterReferCodeResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.GetMoneyResponse;
import com.aryan.stumps11.ApiModel.profile.refercode.GetReferCodeResponse;
import com.aryan.stumps11.Class.Class;
import com.aryan.stumps11.Model.MoreModel;
import com.aryan.stumps11.More.AboutUs;
import com.aryan.stumps11.More.FantasyPointsSystem;
import com.aryan.stumps11.More.HowToPlay;
import com.aryan.stumps11.More.KYC;
import com.aryan.stumps11.More.Legality;
import com.aryan.stumps11.More.Privacy;
import com.aryan.stumps11.More.Profile;
import com.aryan.stumps11.More.RecentTransactions;
import com.aryan.stumps11.More.ReferandEarn;
import com.aryan.stumps11.More.TermsandConditions;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder> {
    Context cc;
    List<MoreModel>list;
    private TextInputLayout rc1;
    private TextInputEditText rc;
    private MaterialButton code;
    private String climPrice;
    String referCodeMsg,appUrl,referCode;

    public MoreAdapter(Context cc, List<MoreModel> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.morelayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MoreModel mm=list.get(position);
        holder.tt.setText(list.get(position).getItemname());
        holder.ii.setImageResource(list.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (list.get(position).getItemname()){

                    case "Profile":
                        cc.startActivity(new Intent(cc, Profile.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "Update Account Details":
                        cc.startActivity(new Intent(cc, BankAccountActivity.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;


                    case "Recent Transactions":
                        cc.startActivity(new Intent(cc, RecentTransactions.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "KYC":
                        cc.startActivity(new Intent(cc, KYC.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "Refer & Earn":
                        cc.startActivity(new Intent(cc, ReferandEarn.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "Enter Referral Code":
                        SharedPreferences sp=cc.getSharedPreferences("ForCode",Context.MODE_PRIVATE);
                        String KEY=sp.getString("CKey","0");
                        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(cc);
                        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        bottomSheetDialog.setContentView(R.layout.enterreferralcode);
                        bottomSheetDialog.setCanceledOnTouchOutside(false);
                        bottomSheetDialog.show();

                         rc1=bottomSheetDialog.findViewById(R.id.code1);
                         rc=bottomSheetDialog.findViewById(R.id.code);
                         code = bottomSheetDialog.findViewById(R.id.Claim);

                         String enterCodeName=rc.getText().toString();

                        sendMoney();



                        code.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences.Editor editor=sp.edit();
                                if (enterCodeName.isEmpty()){
                                    rc.setError("Enter Refer Code");
                                    return;
                                }
                               // editor.putString("CKey",""+rc.getText().toString());
                               // editor.apply();
                               rc.setEnabled(true);
                                rc1.setEnabled(true);
                                enterReferCode();
                                code.setEnabled(true);
                          //      code.setBackgroundColor(ContextCompat.getColor(cc,R.color.Color2));
                            }
                        });
                        if(KEY!="0"){
                           rc.setEnabled(true);
                            rc1.setEnabled(true);
                            code.setEnabled(true);
                  //          rc.setText(KEY);
                   //         code.setBackgroundColor(ContextCompat.getColor(cc,R.color.Color2));
                        }
                        break;

//                    case "Share Stumps11!":
//                        Class cc1=new Class();
//                        DisplayReferralCode();
////                        DisplayProfile();
//                        cc1.Share(cc.getApplicationContext(),referCodeMsg+appUrl + referCode);
//                        break;

                    case "Follow Us on Social Media":
                        final BottomSheetDialog bottomSheetDialog5 = new BottomSheetDialog(cc);
                        bottomSheetDialog5.setContentView(R.layout.sociallinks);
                        bottomSheetDialog5.show();

                        RelativeLayout insta=bottomSheetDialog5.findViewById(R.id.insta);
                        MaterialButton fb = bottomSheetDialog5.findViewById(R.id.fb);
                        MaterialButton tele = bottomSheetDialog5.findViewById(R.id.telegram);
                        insta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        fb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        tele.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        break;

                    case "Contact Us":
                        final BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(cc);
                        bottomSheetDialog1.setContentView(R.layout.contactstumps11);
                        bottomSheetDialog1.show();
                        MaterialButton call = bottomSheetDialog1.findViewById(R.id.call);
                        MaterialButton mail = bottomSheetDialog1.findViewById(R.id.mail);
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String mobile = "+91 9599734137";
                                Intent in = new Intent(Intent.ACTION_DIAL);
                                in.setData(Uri.parse("tel:" + mobile));
                                cc.startActivity(in);
                            }
                        });
                        mail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String[] TOEMAIL1= {"stumps11thegame@gmail.com"};
                                Intent in=new Intent(Intent.ACTION_SENDTO);
                                in.setData(Uri.parse("mailto:"));
                                in.putExtra(Intent.EXTRA_EMAIL,TOEMAIL1);
                                cc.startActivity(in);
                            }
                        });
                        break;

                    case "Fantasy Points System":
                        cc.startActivity(new Intent(cc, FantasyPointsSystem.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "About Us":
                        cc.startActivity(new Intent(cc, AboutUs.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "Terms & Conditions":
                        cc.startActivity(new Intent(cc, TermsandConditions.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "How to Play?":
                        cc.startActivity(new Intent(cc, HowToPlay.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "Privacy & Policy":
                        cc.startActivity(new Intent(cc, Privacy.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;

                    case "Legality":
                        cc.startActivity(new Intent(cc, Legality.class));
                        CustomIntent.customType(cc,"left-to-right");
                        break;
                }
            }
        });
    }

    private void DisplayProfile(){

        SharedPreferences preferences = cc.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        CheckConnection.api.getProfile(tokenName).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(Profile.this,"Suucc"+response.body().getData().getUsername(),Toast.LENGTH_SHORT).show();


                     referCode=response.body().getData().getGetReferCode();

                }else {
                    Toast.makeText(cc,"Token Expire",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(cc.getApplicationContext(),"onFailure",Toast.LENGTH_SHORT).show();
            }
        });

//        RequestQueue rq=Volley.newRequestQueue(getApplicationContext());
//        Dialog dialog=new Dialog(Profile.this);
//        dialog.setContentView(R.layout.progressbar);
//        dialog.show();
//        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
//
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://www.stumps11.com/APIS/Apis.asmx/Display", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    dialog.hide();
//                    JSONArray jsonArray=new JSONArray(response);
//                    for(int i=0;i<jsonArray.length();i++){
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String tname=jsonObject.getString("userteamname");
//                        String name=jsonObject.getString("username");
//                        String email=jsonObject.getString("useremail");
//                        String dob=jsonObject.getString("userdob");
//
//                        u.setText(tname);
//                        n.setText(name);
//                        e.setText(email);
//                        d.setText(dob);
//                    }
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
//                HashMap<String,String>map=new HashMap<>();
//                map.put("mob",p.getText().toString());
//                return map;
//            }
//        };
//        rq.add(stringRequest);
    }


    private void sendMoney(){
        SharedPreferences preferences = cc.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        CheckConnection.api.sendMoney(tokenName).enqueue(new Callback<GetMoneyResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GetMoneyResponse> call, Response<GetMoneyResponse> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(cc.getApplicationContext(),"3454"+response.body().getData().getRefer().getReferPrice(),Toast.LENGTH_SHORT).show();
                    code.setText("Claim ₹ "+response.body().getData().getRefer().getReferPrice());
                }else{
                    Toast.makeText(cc.getApplicationContext(),"erorr",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetMoneyResponse> call, Throwable t) {

            }
        });
    }

    private void DisplayReferralCode(){
        Dialog dialog=new Dialog(cc.getApplicationContext());
        dialog.setContentView(R.layout.progressbar);
        dialog.show();
        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);

        try {
            SharedPreferences preferences = cc.getApplicationContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            String tokenName="Bearer "+retrivedToken;
            CheckConnection.api.getRefferCode(tokenName).enqueue(new Callback<GetReferCodeResponse>() {
                @Override
                public void onResponse(Call<GetReferCodeResponse> call, Response<GetReferCodeResponse> response) {
                    if(response.isSuccessful()){
//                        Toast.makeText(ReferandEarn.this,"Your error Code",Toast.LENGTH_SHORT).show();
                         referCodeMsg=response.body().getData().getMessage();
                        appUrl=response.body().getData().getUrl();

                    }else{
                        Toast.makeText(cc.getApplicationContext(),"Error : ",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetReferCodeResponse> call, Throwable t) {
                    Toast.makeText(cc.getApplicationContext(),"onFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(cc.getApplicationContext(),"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();


        }
//        RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://www.stumps11.com/APIS/Apis.asmx/Display?mob="+mkey+"", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    dialog.hide();
//                    JSONArray jsonArray=new JSONArray(response);
//                    for(int i=0;i<jsonArray.length();i++){
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String rcode1=jsonObject.getString("userrcode");
//                        t24.setText(rcode1);
//                    }
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        rq.add(stringRequest);
    }


    private void enterReferCode(){
        SharedPreferences preferences = cc.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        String refferCodeName=rc.getText().toString();
        EnterReferCodeRequest enterReferCodeRequest=new EnterReferCodeRequest();
        enterReferCodeRequest.setReferCode(refferCodeName);
//        enterReferCodeRequest.
        try{

            CheckConnection.api.enterReferCode(tokenName,enterReferCodeRequest).enqueue(new Callback<EnterReferCodeResponse>() {
                @Override
                public void onResponse(Call<EnterReferCodeResponse> call, Response<EnterReferCodeResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(cc.getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(cc.getApplicationContext(),"Invalid Refer Code : "+response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EnterReferCodeResponse> call, Throwable t) {

                    Toast.makeText(cc.getApplicationContext(),"OnFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(cc.getApplicationContext(),"wefr : "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ii;
        TextView tt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ii=itemView.findViewById(R.id.imageView7);
            tt=itemView.findViewById(R.id.textView7);
        }
    }
}
