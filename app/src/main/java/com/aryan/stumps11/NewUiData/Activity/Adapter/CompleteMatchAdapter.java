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

import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.NewUiData.Activity.MatchDetailsActivity;
import com.aryan.stumps11.NewUiData.Activity.Model.modelupcomingMatch;
import com.aryan.stumps11.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CompleteMatchAdapter extends RecyclerView.Adapter<CompleteMatchAdapter.ViewHolder> {

    private List<modelupcomingMatch> matchList;
    private Context context;
    public CompleteMatchAdapter(List<modelupcomingMatch>matchList, Context context){
        this.context=context;
        this.matchList=matchList;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_completed_adapter,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        //https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU
//        Glide.with(context).load(matchList.get(position).getIv_teamA()).into(holder.iv_teamA);
//        Glide.with(context).load(matchList.get(position).getIv_teamB()).into(holder.iv_teamB);
        Glide.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU").into(holder.iv_teamA);
        Glide.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU").into(holder.iv_teamB);

//        ElevenPlayerContext elevenPlayerContext=elevenPlayerContexts.get(position);
//        ElevenPlayerData elevenPlayerId=elevenPlayerData.get(position);
//        String matchId=elevenPlayerId.getId();
//
//        String matchTitle=elevenPlayerContext.getTitle();
//        String matchTeamNameA=elevenPlayerContext.getTeamATittle();
//        String matchTeamNameB=elevenPlayerContext.getTeamBTittle();
//
//        holder.tv_teamAName.setText(matchTeamNameA);
//        holder.tv_teamBName.setText(matchTeamNameB);
//        holder.tv_TourName.setText(matchTitle);


        holder.tv_TourName.setText(matchList.get(position).getTourName());
        holder.tv_MatchStatus.setText(matchList.get(position).getMatchStatus());
        holder.tv_teamAName.setText(matchList.get(position).getTeamAName());
        holder.tv_teamBName.setText(matchList.get(position).getTeamBName());
        holder.tv_joinTeam.setText(matchList.get(position).getJoinTeam()+" "+"team");


        holder.ll_matchDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MatchDetailsActivity.class);
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


            tv_TourName=itemView.findViewById(R.id.custom_complete_match_tour_name);
            tv_MatchStatus=itemView.findViewById(R.id.complete_match_real_status);
            tv_teamAName=itemView.findViewById(R.id.custom_complete_match_team_name_a);
            tv_teamBName=itemView.findViewById(R.id.custom_complete_match_team_name_b);
            tv_joinTeam=itemView.findViewById(R.id.custom_complete_match_team_joined);

            iv_teamA=itemView.findViewById(R.id.complete_match_tour_team_image_a);
            iv_teamB=itemView.findViewById(R.id.complete_match_tour_team_image_b);

            ll_matchDetails=itemView.findViewById(R.id.custom_match_liner_layout);
        }
    }
}
