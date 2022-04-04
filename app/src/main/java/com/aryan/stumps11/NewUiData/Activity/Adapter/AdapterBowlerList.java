package com.aryan.stumps11.NewUiData.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.aryan.stumps11.NewUiData.Activity.Model.ModelBowlerList;
import com.aryan.stumps11.R;

import java.util.List;

public class AdapterBowlerList extends RecyclerView.Adapter<AdapterBowlerList.BowlerListHolder> {
  private List<ModelBowlerList>listBowlerList;
   private Context context;

    public AdapterBowlerList(List<ModelBowlerList> listBowlerList, Context context) {
        this.listBowlerList = listBowlerList;
        this.context = context;
    }

    @NonNull
    @Override
    public BowlerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bowler_list_secondteam_score,parent,false);

        return new BowlerListHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull BowlerListHolder holder, int position) {

          holder.tv_BowlerName.setText(listBowlerList.get(position).getBowlerName());
        holder.tv_bowlerOver.setText(listBowlerList.get(position).getBowlerOver());
        holder.tv_maidenOver.setText(listBowlerList.get(position).getMaidenOver());
        holder.tv_totalRun.setText(listBowlerList.get(position).getTotalRun());
        holder.tv_totalWickets.setText(listBowlerList.get(position).getTotalWickets());
        holder.tv_overRunRate.setText(listBowlerList.get(position).getOverRunRate());
    }

    @Override
    public int getItemCount() {
        return listBowlerList.size();
    }

    public class BowlerListHolder extends RecyclerView.ViewHolder {
        private TextView tv_BowlerName,tv_bowlerOver,tv_maidenOver,tv_totalRun,tv_totalWickets,tv_overRunRate;
        public BowlerListHolder(@NonNull View itemView) {
            super(itemView);
            tv_BowlerName=itemView.findViewById(R.id.tv_BowlerName);
            tv_bowlerOver=itemView.findViewById(R.id.tv_bowlerOver);
            tv_maidenOver=itemView.findViewById(R.id.tv_maidenOver);
            tv_totalRun=itemView.findViewById(R.id.tv_totalRun);
            tv_totalWickets=itemView.findViewById(R.id.tv_totalWickets);
            tv_overRunRate=itemView.findViewById(R.id.tv_overRunRate);




        }
    }
}
