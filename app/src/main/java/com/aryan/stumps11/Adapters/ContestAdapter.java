package com.aryan.stumps11.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyTeamDummyModel;
import com.aryan.stumps11.ApiModel.profile.context.ContextData;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateReqData;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamReq;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamResponse;
import com.aryan.stumps11.ApiModel.profile.wallet.WalletResponse;
import com.aryan.stumps11.ContestClick.ContestClick;
import com.aryan.stumps11.CreateTeam.CVCAdapter;
import com.aryan.stumps11.CreateTeam.ChooseCaptainandVC;
import com.aryan.stumps11.CreateTeam.DataBase;
import com.aryan.stumps11.CreateTeam.SelectedData;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.Wallet.Wallet;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.aryan.stumps11.joinContext.JoinContextResponse;
import com.aryan.stumps11.joinTeam.JoinTeamActivity;
import com.aryan.stumps11.joinTeam.JoinTeamAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

import static com.aryan.stumps11.HomePageClick.HomePageClick.userId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.ViewHolder> {
    Context cc;
    List<ContextData> list;
    private String entryFess;
    private List<MyTeamDummyModel> models;
    private CreateReqData createReqData;
    private   List<CreateReqData> createReqData1;
    private String ppts;
    private String pname,prole,C,vc,pid,credit;
    private  String mobile;
    private DataBase db;
    private Cursor cursor;
    private String walletBalance;
    private String match_id;
    private int balance;
    private String id;

    public ContestAdapter(Context cc, List<ContextData> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.contestlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContextData mm=list.get(position);
        SharedPreferences sp=cc.getSharedPreferences("MID", MODE_PRIVATE);
        String Matchid=sp.getString("MatchID","0");
        SharedPreferences mob=cc.getSharedPreferences("Mobile",MODE_PRIVATE);
        String mobile=mob.getString("mKey","0");
        holder.tvContextLeaugeName.setText(mm.getContestType());
        entryFess=String.valueOf(mm.getEntryPrice());
        holder.tvContextWinningPrice.setText("₹"+String.valueOf(mm.getWinningPrice()));
        holder.bb.setText("₹ "+ entryFess);
        holder.tvcontext_spots.setText(mm.getTotalMember()+" spots");

        String _id= mm.getId();

        id =userId;
//        models=new ArrayList<>();
//        MyTeamDummyModel myTeamDummyModel=models.get(position);
//      String userId=  myTeamDummyModel.getUserId();

        SharedPreferences a = cc.getSharedPreferences("_ID", Context.MODE_PRIVATE);

        String  key  = a.getString("ID",null);//second parameter default value.

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(cc,ContestClick.class);
                intent.putExtra(CommonData.TEAM_ID,mm.getId());

                cc.startActivity(intent);

           //     cc.startActivity(new Intent(cc, ContestClick.class));
                ((AppCompatActivity)cc).finish();
            }
        });

        holder.bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(cc);
                bottomSheetDialog.setContentView(R.layout.contestjoindialog);
                bottomSheetDialog.show();
                TextView tvEntryFee=bottomSheetDialog.findViewById(R.id.custom_join_dialog_entry_fees);
                TextView tvWalletBalance=bottomSheetDialog.findViewById(R.id.custom_join_dialog_wallet_balance);
                tvEntryFee.setText("₹ "+entryFess);
                SharedPreferences preferences = cc.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
                String tokenName="Bearer "+retrivedToken;
                CheckConnection.api.getWalletBalance(tokenName).enqueue(new Callback<WalletResponse>() {
                    @Override
                    public void onResponse(Call<WalletResponse> call, retrofit2.Response<WalletResponse> response) {
                        if (response.isSuccessful()){
                             balance=response.body().getData().getWallet();
//                    Toast.makeText(Wallet.this,"Balance : "+response.body().getData().getWallet(),Toast.LENGTH_SHORT).show();
                            tvWalletBalance.setText(String.valueOf("₹ "+balance));
                        }else{
                            Toast.makeText(cc,"ERR : ",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<WalletResponse> call, Throwable t) {
                        Toast.makeText(cc,"on Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });



                MaterialButton button=bottomSheetDialog.findViewById(R.id.Pay);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        if (mm.getEntryPrice()<=balance){



                            //----------------------------

//                            match_id=Matchid;
//                            try {
//                                CheckConnection.api.joinContext(tokenName,"62383ff7082eb0d6e9dfbb4b", String.valueOf(mm.getEntryPrice())).enqueue(new Callback<JoinContextResponse>() {
//                                    @Override
//                                    public void onResponse(Call<JoinContextResponse> call, Response<JoinContextResponse> response) {
//                                        if (response.isSuccessful()){
//                                            cc.startActivity(new Intent(cc, HomePageClick.class));
////                                        finish();
////                db.removeOldId();
//                                            Toast.makeText(cc, " "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                        }else if(response.code()==500){
//                                            Toast.makeText(cc, "Server Error 500"+response.errorBody().toString(), Toast.LENGTH_SHORT).show();
//                                        }else{
//                                            Toast.makeText(cc, "Some thing went wrong ", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<JoinContextResponse> call, Throwable t) {
//                                        Toast.makeText(cc, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                                    }
//                                });
//
//                            }catch (Exception e){
//                                Toast.makeText(cc, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//

                            Intent selectTeam=new Intent(cc, JoinTeamActivity.class);
                            selectTeam.putExtra("match_id",Matchid);
                            SelectedData.walletBalance=String.valueOf(balance);
                            SelectedData.joinAmount=String.valueOf(mm.getEntryPrice());
                            cc.startActivity(selectTeam);



                        }else {
                            Intent walletIntent=new Intent(cc,Wallet.class);
                            cc.startActivity(walletIntent);
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContextLeaugeName, tvContextWinningPrice,tvcontext_spots;

        MaterialButton bb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            bb=itemView.findViewById(R.id.EntryButton);
            tvContextLeaugeName=itemView.findViewById(R.id.context_league_title);
            tvContextWinningPrice=itemView.findViewById(R.id.context_wining_price);
            tvcontext_spots=itemView.findViewById(R.id.context_spots);

        }
    }


}
