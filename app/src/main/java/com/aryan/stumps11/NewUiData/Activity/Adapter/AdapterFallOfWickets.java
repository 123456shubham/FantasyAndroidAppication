package com.aryan.stumps11.NewUiData.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.aryan.stumps11.NewUiData.Activity.Model.ModelFallOffWickets;
import com.aryan.stumps11.R;

import java.util.List;

public class AdapterFallOfWickets extends RecyclerView.Adapter<AdapterFallOfWickets.FallOffWicketsHolder> {
   private List<ModelFallOffWickets>listFallOffWickets;
   private Context context;

    public AdapterFallOfWickets(List<ModelFallOffWickets> listFallOffWickets, Context context) {
        this.listFallOffWickets = listFallOffWickets;
        this.context = context;
    }

    @NonNull
    @Override
    public FallOffWicketsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fall_ofwickets_secondteam_score,parent,false);
        return new FallOffWicketsHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull FallOffWicketsHolder holder, int position) {
      holder.tv_playerName.setText(listFallOffWickets.get(position).getPlayerName());
        holder.tv_secondTeamScore.setText(listFallOffWickets.get(position).getSecondTeamScore());
        holder.tv_secondTeamOverNo.setText(listFallOffWickets.get(position).getSecondTeamOverNo());
    }

    @Override
    public int getItemCount() {
        return listFallOffWickets.size();
    }

    public class FallOffWicketsHolder extends RecyclerView.ViewHolder {
        private TextView tv_playerName,tv_secondTeamScore,tv_secondTeamOverNo;
        public FallOffWicketsHolder(@NonNull View itemView) {
            super(itemView);

            tv_playerName=itemView.findViewById(R.id.tv_playerName);
            tv_secondTeamScore=itemView.findViewById(R.id.tv_secondTeamScore);
            tv_secondTeamOverNo=itemView.findViewById(R.id.tv_secondTeamOverNo);

        }
    }
}
