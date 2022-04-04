package com.aryan.stumps11.NewUiData.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.NewUiData.Activity.Model.ModelBrowsePlayer;
import com.aryan.stumps11.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterBrowsePlayer extends RecyclerView.Adapter<AdapterBrowsePlayer.BrowsePlayerHolder> {
   private List<ModelBrowsePlayer>listTrendingPlayer;
   private Context context;

    public AdapterBrowsePlayer(List<ModelBrowsePlayer> listTrendingPlayer, Context context) {
        this.listTrendingPlayer = listTrendingPlayer;
        this.context = context;
    }

    @NonNull
    @Override
    public BrowsePlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_browse_player,parent,false);
       return new BrowsePlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowsePlayerHolder holder, int position) {

        Glide.with(context).load(listTrendingPlayer.get(position).getPlayerImageBrowsePlayer()).into(holder.iv_playerBrowsePlayer);
        holder.tv_playerNameBrowsePlayer.setText(listTrendingPlayer.get(position).getPlayerNameBrowsePlayer());
        holder.tv_playerCountryName.setText(listTrendingPlayer.get(position).getPlayerCountryName());



    }

    @Override
    public int getItemCount() {
        return listTrendingPlayer.size();
    }

    public class BrowsePlayerHolder extends RecyclerView.ViewHolder {
       private ImageView iv_playerBrowsePlayer;
       private TextView tv_playerNameBrowsePlayer,tv_playerCountryName;
        public BrowsePlayerHolder(@NonNull View itemView) {
            super(itemView);

            iv_playerBrowsePlayer=itemView.findViewById(R.id.iv_playerImageBrowsePlayer);
            tv_playerNameBrowsePlayer=itemView.findViewById(R.id.tv_playerNameBrowsePlayer);
            tv_playerCountryName=itemView.findViewById(R.id.tv_playerCountryName);
        }
    }
}
