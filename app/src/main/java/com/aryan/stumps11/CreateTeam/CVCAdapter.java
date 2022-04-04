package com.aryan.stumps11.CreateTeam;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.ApiModel.profile.dummyCreateRes.DummyPlayer11;
import com.aryan.stumps11.ApiModel.profile.elevenPlayer.ElevenPlayer11;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class CVCAdapter extends RecyclerView.Adapter<CVCAdapter.ViewHolder> {
//    List<ElevenPlayer11>list;
//    Context cc;
//    int index=-1,index2=-1;

//    List<ModelClass>list;
//    Context cc;
//    int index=-1,index2=-1;

    List<ModelClass>list;
    Context cc;
    int index=-1,index2=-1;

//
    public CVCAdapter(List<ModelClass> list, Context cc) {
        this.list = list;
        this.cc = cc;
    }

//    public CVCAdapter(List<ElevenPlayer11> list, Context cc) {
//        this.list = list;
//        this.cc = cc;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(cc);
        View view=layoutInflater.inflate(R.layout.cvclayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelClass mm=list.get(position);
        SharedPreferences mob=cc.getSharedPreferences("Mobile",cc.MODE_PRIVATE);
//        String mobile=mob.getString("mKey","0");
        String mobile=list.get(position).getId();
        DataBase db=new DataBase(cc);
//        holder.i22.setImageResource(R.drawable.image);
        holder.t54.setText(list.get(position).getPname1());
        holder.t55.setText(list.get(position).getTname1()+" | "+list.get(position).getRole1());

        holder.tt61.setText(list.get(position).getPts1());
        holder.c.setChecked(position==index);
        holder.vc.setChecked(position==index2);


        holder.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

               if (compoundButton.isChecked()){
                   if (b==true){

                       SelectedData.getSelectedData().setCaption(mobile,true);




                   //    db.Update("true",mobile);
//                       db.Update(String.valueOf(list.get(holder.getAdapterPosition())+"true"),mobile);
                       ;


////                       String CName=  list.get(position).getPname1();
//                       String CName=  list.get(holder.getAdapterPosition()).getPname1();
////                       String CName= mm.getPname1();
//                       SharedPreferences preferences = cc.getSharedPreferences("CName",Context.MODE_PRIVATE);
//                       preferences.edit().putString("CNAME",CName).apply();
                    //   Toast.makeText(cc, "postion :"+list.get(holder.getAdapterPosition()).getPname1(), Toast.LENGTH_SHORT).show();

                   }else{
                       SelectedData.getSelectedData().setCaption(mobile,false);

                   }
               }

            }
        });
        holder.vc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if (compoundButton.isChecked()){
                    if (b==true){
                        SelectedData.getSelectedData().setViceCaption(mobile,true);


                      //  db.Updatevc("true",mobile);
//                        notifyDataSetChanged();
//                        notifyDataSetChanged();

//                        String vcName=list.get(holder.getAdapterPosition()).getPname1();
//
//                        SharedPreferences preferences = cc.getSharedPreferences("VCName",Context.MODE_PRIVATE);
//                        preferences.edit().putString("VCName",vcName).apply();
                     //   Toast.makeText(cc, "postion :"+list.get(holder.getAdapterPosition()).getPname1(), Toast.LENGTH_SHORT).show();

                    }else{
                        SelectedData.getSelectedData().setViceCaption(mobile,false);

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView i22;
        TextView t54,t55,tt61;
        Chip c,vc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            c = itemView.findViewById(R.id.Captain);
            vc = itemView.findViewById(R.id.ViceCaptain);
//            i22 = itemView.findViewById(R.id.imageView22);
            t54 = itemView.findViewById(R.id.cvc_name);
            t55 = itemView.findViewById(R.id.cvc_type);
            tt61 = itemView.findViewById(R.id.cvc_points);

            c.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 index = getAdapterPosition();
                 notifyDataSetChanged();
                 index2 = getAdapterPosition();
                 notifyDataSetChanged();
             }
         });

         vc.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 index2 = getAdapterPosition();
                 notifyDataSetChanged();

             }
         });
         }
    }
}