package com.aryan.stumps11.NewUiData.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Activity.LiveMatchAcivity;
import com.aryan.stumps11.MyMatches.Live;
import com.aryan.stumps11.NewUiData.Activity.MatchDetailsActivity;
import com.aryan.stumps11.NewUiData.Activity.Model.modelupcomingMatch;
import com.aryan.stumps11.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    private List<modelupcomingMatch> matchList;
    private Context context;

    public LiveAdapter(List<modelupcomingMatch> matchList, Context context) {
        this.matchList = matchList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_live_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU").into(holder.iv_teamA);
        Glide.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU").into(holder.iv_teamB);

        holder.tv_TourName.setText(matchList.get(position).getTourName());
        holder.tv_MatchStatus.setText(matchList.get(position).getMatchStatus());
        holder.tv_teamAName.setText(matchList.get(position).getTeamAName());
        holder.tv_teamBName.setText(matchList.get(position).getTeamBName());
        holder.tv_joinTeam.setText(matchList.get(position).getJoinTeam()+" "+"team");


        holder.ll_matchDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LiveMatchAcivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_TourName,tv_MatchStatus,tv_teamAName,tv_teamBName,tv_joinTeam;
        private ImageView iv_teamA,iv_teamB;
        private LinearLayout ll_matchDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_TourName=itemView.findViewById(R.id.live_match_tour_name);
            tv_MatchStatus=itemView.findViewById(R.id.live_match_status);
            tv_teamAName=itemView.findViewById(R.id.live_match_team_name_a);
            tv_teamBName=itemView.findViewById(R.id.live_match_team_name_b);
            tv_joinTeam=itemView.findViewById(R.id.live_match_total_team_join);

            iv_teamA=itemView.findViewById(R.id.live_match_tour_name_image_a);
            iv_teamB=itemView.findViewById(R.id.live_match_tour_name_image_b);

            ll_matchDetails=itemView.findViewById(R.id.live_match_details);
        }
    }
}
