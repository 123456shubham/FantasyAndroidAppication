package com.aryan.stumps11.ContestClick.lederboard_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.ContestClick.ContestClick;
import com.aryan.stumps11.R;

import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {
    private Context context;
    private List<LeadeboardData> list;

    public LeaderBoardAdapter(Context context, List<LeadeboardData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_leaderboard_details,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LeadeboardData leadeboardData=list.get(position);
        holder.tvLeaderBoardPlayerName.setText(leadeboardData.getUsername());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLeaderBoardPlayerName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeaderBoardPlayerName=itemView.findViewById(R.id.custom_leader_player_name);

        }
    }
}
