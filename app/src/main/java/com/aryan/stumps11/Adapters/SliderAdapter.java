package com.aryan.stumps11.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aryan.stumps11.ApiModel.profile.banner.BannerList;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.More.ReferandEarn;
import com.aryan.stumps11.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>{

    List<BannerList>list;
    Context cc;

    public SliderAdapter(List<BannerList>list,Context cc){
        this.list = list;
        this.cc=cc;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        String iimage="http://13.235.92.159:4228"+list.get(position).getImages();

        Picasso.get().load(iimage).into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==2){
                    //Insta
                    gotourl("https://www.facebook.com/help/2053403608222571");
                }
               else if(position==3){
                   //FB
                    gotourl("https://www.facebook.com/help/2053403608222571");
                }
               else if(position==4){
                   //Telegram
                    gotourl("https://www.facebook.com/help/2053403608222571");
                }
               else if(position==5){
                    cc.startActivity(new Intent(cc, ReferandEarn.class));
                    ((AppCompatActivity)cc).finish();
                }
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
    private void gotourl(String s) {
        Uri uri = Uri.parse(s);
        cc.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
