package com.aryan.stumps11.CreateTeam;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;

import java.util.List;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.ViewHolder> {
    Context cc;
    List<ModelClass>list;

    public GreenAdapter(Context cc, List<ModelClass> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.player,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     ModelClass mm=list.get(position);
     SharedPreferences mob=cc.getSharedPreferences("Mobile",cc.MODE_PRIVATE);
     String mobile=mob.getString("mKey","0");
     DataBase db=new DataBase(cc);
     holder.i26.setImageResource(R.drawable.image);
     holder.t102.setText(list.get(position).getPlayername());
     holder.t103.setText(list.get(position).getPcredit());
     holder.t104.setText(list.get(position).getCap());
     holder.t104.setText(list.get(position).getCap());
     holder.t105.setText(list.get(position).getVc());

     if(holder.t104.getText().toString().equals(list.get(position).getCap())){
         holder.card.setVisibility(View.VISIBLE);
     }
        if(holder.t105.getText().toString().equals(list.get(position).getVc())){
            holder.card.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(cc, ""+list.get(position).getPlayername(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView i26;
        TextView t102,t103,t104,t105;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card=itemView.findViewById(R.id.Card);
            i26=itemView.findViewById(R.id.imageView26);
            t102=itemView.findViewById(R.id.textView102);
            t103=itemView.findViewById(R.id.textView103);
            t104=itemView.findViewById(R.id.textView104);
            t105=itemView.findViewById(R.id.textView105);
        }
    }
}