package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Winners.WinnersActivity;
import com.aryan.stumps11.Winners.winnerRes.WinnerData;

import java.util.List;

public class WinnersAdapter extends RecyclerView.Adapter<WinnersAdapter.ViewHolder> {
    Context cc;
    List<WinnerData> list;

    public WinnersAdapter(Context cc, List<WinnerData> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.winnerlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WinnerData mm=list.get(position);
        holder.winnerstate.setText(list.get(position).getPlace());
        holder.winnerusername.setText(list.get(position).getUsername());
        holder.winneramt.setText(String.valueOf("₹ "+list.get(position).getWinnerPrice()));
        holder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ii;
        TextView winneramt,winnerusername,winnerstate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ii=itemView.findViewById(R.id.image);
            winneramt=itemView.findViewById(R.id.textView17);
            winnerusername=itemView.findViewById(R.id.textView18);
            winnerstate=itemView.findViewById(R.id.textView16);
        }
    }
}
