package com.aryan.stumps11.NewUiData.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.aryan.stumps11.NewUiData.Activity.Model.ModelTeamScore;
import com.aryan.stumps11.R;

import java.util.List;

public class AdapterTeamScore extends RecyclerView.Adapter<AdapterTeamScore.SecondTeamScoreHolder> {

   private List<ModelTeamScore>listSecondTeamScore;
   private Context context;

    public AdapterTeamScore(List<ModelTeamScore> listSecondTeamScore, Context context) {
        this.listSecondTeamScore = listSecondTeamScore;
        this.context = context;
    }

    @NonNull
    @Override
    public SecondTeamScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_second_teamscore,parent,false);
      return new SecondTeamScoreHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondTeamScoreHolder holder, int position) {
        holder.tv_batsmanName.setText(listSecondTeamScore.get(position).getBatsmanName());
        holder.tv_battingStatus.setText(listSecondTeamScore.get(position).getBattingStatus());
        holder.tv_playerRun.setText(listSecondTeamScore.get(position).getPlayerRun());
        holder.tv_bowl.setText(listSecondTeamScore.get(position).getBowl());
        holder.tv_four.setText(listSecondTeamScore.get(position).getFour());
        holder.tv_six.setText(listSecondTeamScore.get(position).getSix());
        holder.tv_strikeRunRate.setText(listSecondTeamScore.get(position).getStrikeRunRate());
    }

    @Override
    public int getItemCount() {
        return listSecondTeamScore.size();
    }

    public class SecondTeamScoreHolder extends RecyclerView.ViewHolder {
       private TextView tv_batsmanName,tv_battingStatus,tv_playerRun,tv_bowl,tv_four,tv_six,tv_strikeRunRate;
        public SecondTeamScoreHolder(@NonNull View itemView) {
            super(itemView);

            tv_batsmanName=itemView.findViewById(R.id.tv_batsmanName);
            tv_battingStatus=itemView.findViewById(R.id.tv_battingStatus);
            tv_playerRun=itemView.findViewById(R.id.tv_playerRun);
            tv_bowl=itemView.findViewById(R.id.tv_bowl);
            tv_four=itemView.findViewById(R.id.tv_four);
            tv_six=itemView.findViewById(R.id.tv_six);
            tv_strikeRunRate=itemView.findViewById(R.id.tv_strikeRunRate);
        }
    }
}
