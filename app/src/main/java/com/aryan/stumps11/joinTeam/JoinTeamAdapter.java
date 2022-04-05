package com.aryan.stumps11.joinTeam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Adapters.MyTeamAdapter;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyTeamDummyModel;
import com.aryan.stumps11.CreateTeam.SelectedData;
import com.aryan.stumps11.EditTeam.EditTeamActivity;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.aryan.stumps11.joinContext.JoinContextResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinTeamAdapter extends RecyclerView.Adapter<JoinTeamAdapter.ViewHolder> {

    private List<MyTeamDummyModel> modelClasses;
    private Context contests;
    private String walletBalance;
    private String joinAmount;

    public JoinTeamAdapter(List<MyTeamDummyModel> modelClasses, Context contests) {
        this.modelClasses = modelClasses;
        this.contests = contests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_join_team,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyTeamDummyModel myDummyTeamPlayer11=modelClasses.get(position);
        holder.tvCaptinName.setText(myDummyTeamPlayer11.getcName());
        holder.tvVCName.setText(myDummyTeamPlayer11.getVcName());
        holder.tvMyTeamId.setText("My Match ID : "+myDummyTeamPlayer11.getTeamID());
        holder.tvTotalBowl.setText("BOWL "+myDummyTeamPlayer11.getTotalBwl());
        holder.tvTotalBat.setText("BAT "+myDummyTeamPlayer11.getTotalBat());
        holder.tvTotalWk.setText("WK "+myDummyTeamPlayer11.getTotalWk());
        holder.tvTotalAll.setText("AR "+myDummyTeamPlayer11.getTotalAR());

        walletBalance= SelectedData.walletBalance;
        joinAmount=SelectedData.joinAmount;


        holder.tvJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(contests, "Click ", Toast.LENGTH_SHORT).show();

                // calling api
try{

    SharedPreferences preferences = contests.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
    String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
    String tokenName="Bearer "+retrivedToken;
    //----------------------------

    CheckConnection.api.joinContext(tokenName, myDummyTeamPlayer11.getUserId(),joinAmount).enqueue(new Callback<JoinContextResponse>() {
        @Override
        public void onResponse(Call<JoinContextResponse> call, Response<JoinContextResponse> response) {
            if (response.isSuccessful()){
                contests.startActivity(new Intent(contests, HomePageClick.class));
                Toast.makeText(contests, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }else if (response.code()==500){
                Toast.makeText(contests, "Server Error ", Toast.LENGTH_SHORT).show();

            }else if(response.code()==404){
                Toast.makeText(contests,"Not Fount ",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<JoinContextResponse> call, Throwable t) {

            Toast.makeText(contests, "on Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });

}catch (Exception e){
    Toast.makeText(contests, "Exception" +e.getMessage(), Toast.LENGTH_SHORT).show();
}


            }
        });



    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTeamNameA,tvTeamNameB,tvTeamTotalPlayerA,tvTeamTotalPlayerB,tvCaptinName,tvVCName;
        private ImageView imgCaptain,imgVCNAme,imageViewEditTeam;
        private TextView tvMyTeamId;
        private TextView tvTotalWk,tvTotalBowl,tvTotalBat,tvTotalAll,tvJoinTeam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCaptinName=itemView.findViewById(R.id.custom_join_team_c_name);
            tvVCName=itemView.findViewById(R.id.custom_join_team_vc_name);
            tvMyTeamId=itemView.findViewById(R.id.custom_join_team_id);
            tvTotalWk=itemView.findViewById(R.id.custom_join_team_tt_wk);
            tvTotalBat=itemView.findViewById(R.id.custom_join_team_tt_bat);
            tvTotalBowl=itemView.findViewById(R.id.custom_join_team_tt_bowl);
            tvTotalAll=itemView.findViewById(R.id.custom_join_team_tt_all);
            tvJoinTeam=itemView.findViewById(R.id.custom_join_team_selected_team);
        }
    }
}
