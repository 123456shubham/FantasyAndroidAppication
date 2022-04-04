package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.MyMatches.MyMatches;
import com.aryan.stumps11.MyMatchesClick.MyMatchesClickEvent;
import com.aryan.stumps11.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyMatchesAdapter extends RecyclerView.Adapter<MyMatchesAdapter.ViewHolder> {
    Context cc;
    List<ModelClass> list;

    public MyMatchesAdapter(Context cc, List<ModelClass> list) {
        this.cc = cc;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.mymatcheslayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass mm=list.get(position);
        holder.tsname.setText(list.get(position).getSmname());
        holder.tname1.setText(list.get(position).getTmname1());
        holder.tname2.setText(list.get(position).getTmname2());
        holder.timage1.setImageResource(list.get(position).getTmimage1());
        holder.timage1.setImageResource(list.get(position).getTmimage2());
        holder.tstatus8.setText(list.get(position).getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.tstatus8.getText().toString().equals("Upcoming")){
                    cc.startActivity(new Intent(cc, HomePageClick.class));
                    ((AppCompatActivity)cc).finish();
                }
                else {
                    cc.startActivity(new Intent(cc, MyMatchesClickEvent.class));
                    ((AppCompatActivity)cc).finish();
                }
            }
        });


        holder.progressBar2.setProgress(5*100/(11000/1000));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tsname,tname1,tname2,tstatus8;
        CircleImageView timage1,timage2;
        private ProgressBar progressBar2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tsname=itemView.findViewById(R.id.textView2);
            tname1=itemView.findViewById(R.id.textView4);
            tname2=itemView.findViewById(R.id.textView5);
            timage1=itemView.findViewById(R.id.team1);
            timage2=itemView.findViewById(R.id.team2);
            tstatus8=itemView.findViewById(R.id.textView8);
            progressBar2=itemView.findViewById(R.id.progressBar2);
        }
    }
}
