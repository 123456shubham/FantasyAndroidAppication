package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class JoinedContestAdapter extends RecyclerView.Adapter<JoinedContestAdapter.ViewHolder>{
    Context cc;
    List<ModelClass> list;

    public JoinedContestAdapter(Context cc, List<ModelClass> list) {
        this.cc = cc;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.joinedcontestlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass mm=list.get(position);
        holder.t26.setText(list.get(position).getMaxteams1());
        holder.t25.setText(list.get(position).getBonus1());
        holder.t27.setText(list.get(position).getPrizepool1());
        holder.bb.setText(list.get(position).getEntry1());
        holder.t37.setText(list.get(position).getJoined1());
        holder.t38.setText(list.get(position).getSpotstotal1());
        holder.t52.setText(list.get(position).getContesttype1());
        holder.t55.setText(list.get(position).getWinpercentage1());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t26,t25,t27,t37,t38,t52,t55;
        MaterialButton bb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t26=itemView.findViewById(R.id.textView26);
            t25=itemView.findViewById(R.id.textView25);
            t27=itemView.findViewById(R.id.textView27);
            bb=itemView.findViewById(R.id.EntryButton);
            t37=itemView.findViewById(R.id.textView37);
            t38=itemView.findViewById(R.id.textView38);
            t52=itemView.findViewById(R.id.textView52);
            t55=itemView.findViewById(R.id.textView55);
        }
    }
}
