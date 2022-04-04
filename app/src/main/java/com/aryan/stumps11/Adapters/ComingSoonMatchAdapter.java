package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.R;
import com.aryan.stumps11.comingMatch.Item;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComingSoonMatchAdapter extends RecyclerView.Adapter<ComingSoonMatchAdapter.ViewHolder> {

    private List<Item> itemList;

    private Context context;


    public ComingSoonMatchAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cricketmatch,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item= itemList.get(position);

        holder.tvMatchTitle.setText(item.getTitle());

        holder.tvMatchStatus.setText(item.getStatus());

        holder.tvMatchTeamNameA.setText(item.getTeama().getName());

        holder.tvMatchTeamNameB.setText(item.getTeamb().getName());

        Glide.with(context).load(item.getTeama().getLogoUrl()).into(holder.matchTeamIcon1);

        Glide.with(context).load(item.getTeamb().getLogoUrl()).into(holder.matchTeamIcon2);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMatchTitle;

        private TextView tvMatchTeamNameA,tvMatchTeamNameB;

        private TextView tvMatchStatus;

        private CircleImageView matchTeamIcon1,matchTeamIcon2;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvMatchTitle=itemView.findViewById(R.id.cricket_match_title);

            tvMatchStatus=itemView.findViewById(R.id.cricket_match_status);

            tvMatchTeamNameA=itemView.findViewById(R.id.circket_match_team_name_a);

            tvMatchTeamNameB=itemView.findViewById(R.id.circket_match_team_name_b);

            matchTeamIcon1=itemView.findViewById(R.id.cricket_match_team_a_icon);

            matchTeamIcon2=itemView.findViewById(R.id.cricket_match_team_b_icon);
        }
    }
}
