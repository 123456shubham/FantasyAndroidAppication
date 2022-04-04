package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;

import java.util.List;

public class RecentTransactionsAdapter extends RecyclerView.Adapter<RecentTransactionsAdapter.ViewHolder> {
    Context cc;
    List<ModelClass> list;

    public RecentTransactionsAdapter(Context cc, List<ModelClass> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.transaction,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass mm=list.get(position);
        holder.tamt.setText(list.get(position).getTamt());
        holder.tid.setText(list.get(position).getTid());
        holder.tdate.setText(list.get(position).getTdate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tamt,tid,tdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tamt=itemView.findViewById(R.id.textView60);
            tid=itemView.findViewById(R.id.textView62);
            tdate=itemView.findViewById(R.id.textView63);
        }
    }
}
