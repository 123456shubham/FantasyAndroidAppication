package com.aryan.stumps11.EditTeam;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.CreateTeam.DataBase;
import com.aryan.stumps11.CreateTeam.SelectedData;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class EditCVCAdapter extends RecyclerView.Adapter<EditCVCAdapter.ViewHolder> {
    List<ModelClass> list;
    Context cc;
    int index=-1,index2=-1;

    //
    public EditCVCAdapter(List<ModelClass> list, Context cc) {
        this.list = list;
        this.cc = cc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_edit_cvc,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

            c = itemView.findViewById(R.id.edit_Captain);
            vc = itemView.findViewById(R.id.edit_ViceCaptain);
//            i22 = itemView.findViewById(R.id.imageView22);
            t54 = itemView.findViewById(R.id.edit_cvc_name);
            t55 = itemView.findViewById(R.id.edit_cvc_type);
            tt61 = itemView.findViewById(R.id.edit_cvc_points);

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
