package com.aryan.stumps11.CreateTeam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;

import java.util.ArrayList;
import java.util.List;

public class GreenBackground extends AppCompatActivity {
RecyclerView wk,bat,all,bwl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_background);

            SharedPreferences mob = getSharedPreferences("Mobile", MODE_PRIVATE);
            String mobile = mob.getString("mKey", "0");
            DataBase db = new DataBase(GreenBackground.this);
            wk = findViewById(R.id.WK);
            bat = findViewById(R.id.Bat);
            all = findViewById(R.id.ALl);
            bwl = findViewById(R.id.Bwl);
            List<ModelClass> list = new ArrayList<>();
            List<ModelClass> list2 = new ArrayList<>();
            List<ModelClass> list3 = new ArrayList<>();
            List<ModelClass> list4 = new ArrayList<>();

        for(SelectedData.data data:SelectedData.getSelectedData().getData().values()){
            ModelClass mm=new ModelClass();
            mm.setId(data.getId());
            mm.setPname1(data.getName());
            mm.setRole1(data.getRole());
            mm.setTname1(data.getTeam());
            mm.setPts1(data.getPoints());
            if (data.getRole().equals("wk")) {
                    ModelClass mm1 = new ModelClass();
                    mm1.setPlayername(data.getName());
                    mm1.setPcredit(data.getPoints() + " cr");
//                    if(data.equals(cap)){
//                        mm1.setCap("C");
//                    }
//                    else {
//                        mm1.setCap(null);
//                    }
//
//                    if(pname.equals(VC)){
//                        mm1.setVc("V");
//                    }
//                    else {
//                        mm1.setVc(null);
//                    }
                    list.add(mm1);
                    Method(wk, 4, list);
                }
                if (data.getRole().equals("bat")) {
                    ModelClass mm1 = new ModelClass();
                    mm1.setPlayername(data.getName());
                    mm1.setPcredit(data.getPoints() + " cr");
//                    if(pname.equals(cap)){
//                        mm1.setCap("C");
//                    }
//                    else {
//                        mm1.setCap(null);
//                    }
//                    if(pname.equals(VC)){
//                        mm1.setVc("V");
//                    }
//                    else {
//                        mm1.setVc(null);
//                    }
                    list2.add(mm1);
                    Method(bat, 6, list2);
                }
                if (data.getRole().equals("all")) {
                    ModelClass mm1 = new ModelClass();
                    mm1.setPlayername(data.getName());
                    mm1.setPcredit(data.getPoints() + " cr");
//                    if(pname.equals(cap)){
//                        mm1.setCap("C");
//                    }
//                    else {
//                        mm1.setCap(null);
//                    }
//                    if(pname.equals(VC)){
//                        mm1.setVc("V");
//                    }
//                    else {
//                        mm1.setVc(null);
//                    }
                    list3.add(mm1);
                    Method(all, 4, list3);
                }
                if (data.getRole().equals("bowl")) {
                    ModelClass mm1 = new ModelClass();
                    mm1.setPlayername(data.getName());
                    mm1.setPcredit(data.getPoints() + " cr");
//                    if(pname.equals(cap)){
//                        mm1.setCap("C");
//                    }
//                    else {
//                        mm1.setCap(null);
//                    }
//                    if(pname.equals(VC)){
//                        mm1.setVc("V");
//                    }
//                    else {
//                        mm1.setVc(null);
//                    }
                    list4.add(mm1);
                    Method(bwl, 6, list4);
                }


        }



    }
   public void Method(RecyclerView rr,int count,List<ModelClass>list){
        rr.setLayoutManager(new GridLayoutManager(GreenBackground.this,count));
        rr.setHasFixedSize(true);
        GreenAdapter ga=new GreenAdapter(GreenBackground.this,list);
        rr.setAdapter(ga);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}