package com.aryan.stumps11.EditTeam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.CreateTeam.CreateTeamAdapter;
import com.aryan.stumps11.CreateTeam.DataBase;
import com.aryan.stumps11.CreateTeam.SelectedData;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditTeamAdapter extends RecyclerView.Adapter<EditTeamAdapter.ViewHolder> {
    private Context context;
    private List<ModelClass> editTeamModels;


    static int crept;
    int hello,wk,bat,all,bwl,creditPoints;
    static int addCreditPoint;
    private SharedPreferences sp;

    public EditTeamAdapter(Context context, List<ModelClass> editTeamModels) {
        this.context = context;
        this.editTeamModels = editTeamModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_player,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelClass mm=editTeamModels.get(position);
        EditDatabase db=new EditDatabase(context);
        SharedPreferences mob=context.getSharedPreferences("Mobile",context.MODE_PRIVATE);
      String  mobile=mob.getString("mKey","0");
      
        String key = editTeamModels.get(position).getId();
//        Glide.with(cc).load(list.get(position).getImages());
//        holder.ttcountry.setText(editTeamModels.get(position).getEditTeamName());
       // boolean isVerified=editTeamModels.get(position).isCheck();
        boolean check = SelectedData.getSelectedData().getPlayer(key);
        holder.ttcredits.setText(editTeamModels.get(position).getCredits());
//        holder.ttpts.setText(editTeamModels.get(position).getPts());
        holder.ttstatus.setText(editTeamModels.get(position).getStatus());
        holder.ttname.setText(editTeamModels.get(position).getPname());
       // String key="Hello"+editTeamModels.get(position).getEditPlayerName()+position;
        holder.cb.setChecked(check);
        if (check==true){
          holder.rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

        }




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    boolean playerCount(SelectedData.Role role, int status, String teamName){
        boolean check=true;

        switch (role){
            case WK:
                if(SelectedData.getSelectedData().getRoleCount("wk") < 4){
                    SelectedData.setWicketKeeper((SelectedData.getWicketKeeper() + status));
                }
                else{
                    check =false;
                    Toast.makeText(context, "Max 4 Wicket Keeper.", Toast.LENGTH_SHORT).show();
                }

                break;
            case BAT:
                if(SelectedData.getSelectedData().getRoleCount("bat")  < 6){
                    SelectedData.setBatsman((SelectedData.getBatsman() + status));
                }
                else{
                    check =false;
                    Toast.makeText(context, "Max 6 Batsman.", Toast.LENGTH_SHORT).show();
                }

                break;
            case BOWL:
                if(SelectedData.getSelectedData().getRoleCount("bowl") < 6){
                    SelectedData.setBowler((SelectedData.getBowler() + status));
                }
                else{
                    check =false;
                    Toast.makeText(context, "Max 6 Bowler.", Toast.LENGTH_SHORT).show();
                }
                break;
            case ALL:
                if(SelectedData.getSelectedData().getRoleCount("wk") < 6){
                    SelectedData.setAllRounder((SelectedData.getAllRounder() + status));
                }
                else{
                    check =false;
                    Toast.makeText(context, "Max 6 all rounder.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return check;
    }


    @Override
    public int getItemCount() {
        return editTeamModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       private ImageView ii;
       private CheckBox cb;
       private TextView ttname,ttstatus,ttpts,ttcredits,ttcountry;
       private RelativeLayout rr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rr=itemView.findViewById(R.id.edit_PlayerLayout);
            cb=itemView.findViewById(R.id.edit_player_checkBox);
//            ii=itemView.findViewById(R.id.imageView22);
            ttname=itemView.findViewById(R.id.edit_player_name);
            ttstatus=itemView.findViewById(R.id.edit_player_status);
            ttcredits=itemView.findViewById(R.id.edit_player_credit_point);
//            ttcountry=itemView.findViewById(R.id.textView68);

            cb.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            String key = "Hello" + editTeamModels.get(getAdapterPosition()).getPname() + getAdapterPosition();
            boolean check = SelectedData.getSelectedData().getPlayer(key);
            updateCheckValues((!check),getAdapterPosition(),cb,rr);
        }
    }



    public void Save(String Key,boolean value){
        SharedPreferences sp=context.getSharedPreferences("Save",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1=sp.edit();
        editor1.putBoolean(Key,value);
        editor1.apply();
    }


    public boolean Update(String Key){
        SharedPreferences sp=context.getSharedPreferences("Save",Context.MODE_PRIVATE);
        boolean kk=sp.getBoolean(Key,false);
        return kk;
    }



    @SuppressLint("NewApi")
    void updateCheckValues(boolean b, int position, CheckBox cb, RelativeLayout rr){

        ModelClass mm=editTeamModels.get(position);
        sp=context.getSharedPreferences("Counts",Context.MODE_PRIVATE);


        String key = editTeamModels.get(position).getPname();


        if (cb.isChecked()) {

            if(SelectedData.getSelectedData().getPlayerCount()>=11){
                Toast.makeText(context, "We can not add more than 11 player", Toast.LENGTH_SHORT).show();
                cb.setChecked(false);
                return;

            }


//                if(SelectedData.getSelectedData().getTeamCount(list.get(position).getTname())>=7){
//                    Toast.makeText(cc, "We can not add more than 7 player in a team", Toast.LENGTH_SHORT).show();
//                    cb.setChecked(false);
//                    return;
//
//                }
            if (editTeamModels.get(position).getRole().equals("wk")) {
                //   int value = fun(d, 1, key, cb, "wk");
                if(playerCount(SelectedData.Role.WK,1,editTeamModels.get(position).getPname())) {
                    rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

                    SelectedData.getSelectedData().getData().put(editTeamModels.get(position).getId(), new SelectedData.data(editTeamModels.get(position).getId(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getRole(), editTeamModels.get(position).getPoints()));
                }
                else{
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);

                }


            }

            if (editTeamModels.get(position).getRole().equals("bat")) {
                if(playerCount(SelectedData.Role.BAT,1,editTeamModels.get(position).getTname())) {
                    SelectedData.getSelectedData().getData().put(editTeamModels.get(position).getId(), new SelectedData.data(editTeamModels.get(position).getId(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getRole(), editTeamModels.get(position).getPoints()));
                    rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

                }
                else{
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);

                }
            }

            if (editTeamModels.get(position).getRole().equals("all")) {
                if(playerCount(SelectedData.Role.ALL,1,editTeamModels.get(position).getTname())) {
                    rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));
                    SelectedData.getSelectedData().getData().put(editTeamModels.get(position).getId(), new SelectedData.data(editTeamModels.get(position).getId(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getRole(), editTeamModels.get(position).getPoints()));

                }
                else{
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);

                }
            }

            if (editTeamModels.get(position).getRole().equals("bowl")) {
                if(playerCount(SelectedData.Role.BOWL,1,editTeamModels.get(position).getTname())) {
                    rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));
                    SelectedData.getSelectedData().getData().put(editTeamModels.get(position).getId(), new SelectedData.data(editTeamModels.get(position).getId(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getPname(), editTeamModels.get(position).getRole(), editTeamModels.get(position).getPoints()));
                }
                else{
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);
                }
            }

        }else {
            cb.setChecked(false);
            SelectedData.getSelectedData().removePlayer(editTeamModels.get(position).getId());
            rr.setBackgroundColor(Color.WHITE);

        }

        SharedPreferences.Editor editor1=sp.edit();

        editor1.putInt("Key",SelectedData.getSelectedData().getPlayerCount());
        editor1.putInt("wKey",SelectedData.getSelectedData().getRoleCount("wk"));
        editor1.putInt("bKey",SelectedData.getSelectedData().getRoleCount("bat"));
        editor1.putInt("aKey",SelectedData.getSelectedData().getRoleCount("all"));
        editor1.putInt("bwlKey",SelectedData.getSelectedData().getRoleCount("bowl"));
        editor1.putFloat("creditPoints",SelectedData.getSelectedData().getCreditPoints());
//      editor1.clear();
    }

}
