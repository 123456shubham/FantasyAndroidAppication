package com.aryan.stumps11.NewUiData.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.aryan.stumps11.NewUiData.Activity.Model.ModelHighlightCommentary;
import com.aryan.stumps11.R;

import java.util.List;

public class AdapterHighlightsCommentary extends RecyclerView.Adapter<AdapterHighlightsCommentary.CommentaryHolder> {
   private List<ModelHighlightCommentary>commentaryList;
   private Context context;

    public AdapterHighlightsCommentary(List<ModelHighlightCommentary> commentaryList, Context context) {
        this.commentaryList = commentaryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_highlights,parent,false);
        return new CommentaryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentaryHolder holder, int position) {

        holder.tv_overNo.setText(commentaryList.get(position).getOverNo());
        holder.tv_runType.setText(commentaryList.get(position).getRunType());
        holder.tv_commentary.setText(commentaryList.get(position).getCommentary());

        if (commentaryList.get(position).getRunType().equalsIgnoreCase("w")){
            holder.tv_runType.setBackgroundResource(R.drawable.circle_red);
        }
        if (commentaryList.get(position).getRunType().equalsIgnoreCase("4")){
            holder.tv_runType.setBackgroundResource(R.drawable.circle_yallow);
        }
        if (commentaryList.get(position).getRunType().equalsIgnoreCase("6")){
            holder.tv_runType.setBackgroundResource(R.drawable.circle_light_green);
        }
        if (commentaryList.get(position).getRunType().equalsIgnoreCase("f")){
            holder.tv_runType.setBackgroundResource(R.drawable.circle_purpal_background);
        }
        if (commentaryList.get(position).getRunType().equalsIgnoreCase("h")){
            holder.tv_runType.setBackgroundResource(R.drawable.circle_purpal_background);
        }

    }

    @Override
    public int getItemCount() {
        return commentaryList.size();
    }

    public class CommentaryHolder extends RecyclerView.ViewHolder {
        private TextView tv_overNo,tv_runType,tv_commentary;
        public CommentaryHolder(@NonNull View itemView) {
            super(itemView);

            tv_overNo=itemView.findViewById(R.id.tv_overNo);
            tv_runType=itemView.findViewById(R.id.tv_runType);
            tv_commentary=itemView.findViewById(R.id.tv_commentary);

        }
    }
}
