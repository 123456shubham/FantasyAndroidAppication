package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.ApiModel.profile.rank.RankDetails;
import com.aryan.stumps11.R;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private List<RankDetails> rankDetailsList;
    private Context context;


    public RankAdapter(List<RankDetails> rankDetailsList, Context context) {
        this.rankDetailsList = rankDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_wining_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RankDetails rankDetails=rankDetailsList.get(position);
        holder.tvRankNumber.setText("# "+String.valueOf(rankDetails.getRank()));
        holder.tvRankPrice.setText(String.valueOf(rankDetails.getPercent()));

    }

    @Override
    public int getItemCount() {
        return rankDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRankNumber,tvRankPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRankNumber=itemView.findViewById(R.id.custom_wining_rank);
            tvRankPrice=itemView.findViewById(R.id.custom_wining_price);

        }
    }
}
