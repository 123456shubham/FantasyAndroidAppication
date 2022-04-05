package com.aryan.stumps11.joinContextList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Adapters.JoinedContestAdapter;
import com.aryan.stumps11.R;

import org.w3c.dom.Text;

import java.util.List;

public class JoinContextAdapter extends RecyclerView.Adapter<JoinContextAdapter.ViewHolder> {
    private List<ListContextModel> list;
    private Context context;


    public JoinContextAdapter(List<ListContextModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_join_context__list,parent,false);
        ViewHolder viewHolder1=new ViewHolder(viewHolder);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListContextModel listContextModel=list.get(position);
        holder.tvTitle.setText(listContextModel.getTourName());
        holder.tvTeamA.setText(listContextModel.getTeamAName());
        holder.tvTeamB.setText(listContextModel.getTeamBName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvTeamA,tvTeamB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeamA=itemView.findViewById(R.id.custom_join_context_team_name_a);
            tvTeamB=itemView.findViewById(R.id.custom_join_context_team_name_b);
            tvTitle=itemView.findViewById(R.id.custom_join_context_title);

        }
    }
}
