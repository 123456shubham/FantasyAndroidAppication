package com.aryan.stumps11.CreateTeam;

import static android.content.Context.MODE_PRIVATE;

import static com.aryan.stumps11.CreateTeam.CreateTeams.tvCreditPoints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.util.Log;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aryan.stumps11.Adapters.TabAdapter;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.OnClick;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamAdapter extends RecyclerView.Adapter<CreateTeamAdapter.ViewHolder> {
    Context cc;
    List<ModelClass> list;
    String mobile;
    static int crept;
    int hello, wk, bat, all, bwl, creditPoints;
    static int addCreditPoint;
    private SharedPreferences sp;
    OnClick onClick;
    int count = 0, full = 0, countrycount = 0;
    double creditcount;
    private db d;
    private String TAG = "sachin";


    public CreateTeamAdapter(Context cc, List<ModelClass> list, OnClick onClick) {
        this.cc = cc;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(cc);
        View view = layoutInflater.inflate(R.layout.playerlayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelClass mm = list.get(position);
        DataBase db = new DataBase(cc);
        db d = new db(cc);
        SharedPreferences mob = cc.getSharedPreferences("Mobile", MODE_PRIVATE);
        mobile = mob.getString("mKey", "0");
        String key = list.get(position).getId();
        Glide.with(cc).load(list.get(position).getImages());
        holder.ttcountry.setText(list.get(position).getTname());
        holder.ttcredits.setText(list.get(position).getCredits());
        holder.ttpts.setText(list.get(position).getPts());
        holder.ttstatus.setText(list.get(position).getStatus());
        holder.ttname.setText(list.get(position).getPname());
        //boolean check = Update(list.get(position).getPname());
        boolean check = SelectedData.getSelectedData().getPlayer(key);
        Log.e(TAG, "onBindViewHolder: " + position + " " + check);

        holder.cb.setChecked(check);

        if (holder.cb.isChecked()) {
            holder.rr.setBackgroundColor(ContextCompat.getColor(cc, R.color.PlayerSelected));
        } else {
            holder.rr.setBackgroundColor(Color.WHITE);
        }


    }


    @SuppressLint("NewApi")
    void putPlayer(String key, boolean b) {
        if (!(SelectedData.getSelectedData().putPlayer(key, b))) {
            Toast.makeText(cc, "Max 11 Players.", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    boolean playerCount(SelectedData.Role role, int status, String teamName) {
        boolean check = true;

        switch (role) {
            case WK:
                if (SelectedData.getSelectedData().getRoleCount("wk") < 4) {
                    SelectedData.setWicketKeeper((SelectedData.getWicketKeeper() + status));

                } else {
                    check = false;
                    Toast.makeText(cc, "Max 4 Wicket Keeper.", Toast.LENGTH_SHORT).show();
                }

                break;
            case BAT:
                if (SelectedData.getSelectedData().getRoleCount("bat") < 6) {
                    SelectedData.setBatsman((SelectedData.getBatsman() + status));
                } else {
                    check = false;
                    Toast.makeText(cc, "Max 6 Batsman.", Toast.LENGTH_SHORT).show();
                }

                break;
            case BOWL:
                if (SelectedData.getSelectedData().getRoleCount("bowl") < 6) {
                    SelectedData.setBowler((SelectedData.getBowler() + status));
                } else {
                    check = false;
                    Toast.makeText(cc, "Max 6 Bowler.", Toast.LENGTH_SHORT).show();
                }
                break;
            case ALL:
                if (SelectedData.getSelectedData().getRoleCount("wk") < 6) {
                    SelectedData.setAllRounder((SelectedData.getAllRounder() + status));
                } else {
                    check = false;
                    Toast.makeText(cc, "Max 6 all rounder.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return check;
    }


    public void Save(String Key, boolean value) {
        SharedPreferences sp = cc.getSharedPreferences("Preference", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp.edit();
        editor1.putBoolean(Key, value);
        editor1.apply();
    }


    public boolean Update(String Key) {
        SharedPreferences sp = cc.getSharedPreferences("Preference", MODE_PRIVATE);
        boolean kk = sp.getBoolean(Key, false);
        return kk;

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        if (holder.cb != null) {
            holder.cb.setOnClickListener(null);
        }
        super.onViewRecycled(holder);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ii;
        CheckBox cb;
        TextView ttname, ttstatus, ttpts, ttcredits, ttcountry;
        RelativeLayout rr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rr = itemView.findViewById(R.id.PlayerLayout);
            cb = itemView.findViewById(R.id.checkBox);
//            ii=itemView.findViewById(R.id.imageView22);
            ttname = itemView.findViewById(R.id.textView54);
            ttstatus = itemView.findViewById(R.id.textView64);
            ttpts = itemView.findViewById(R.id.textView65);
            ttcredits = itemView.findViewById(R.id.textView66);

            ttcountry = itemView.findViewById(R.id.textView68);
            cb.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            String key = "Hello" + list.get(getAdapterPosition()).getPname() + getAdapterPosition();
            boolean check = SelectedData.getSelectedData().getPlayer(key);
            updateCheckValues((!check), getAdapterPosition(), cb, rr);
        }
    }

    @SuppressLint("NewApi")
    void updateCheckValues(boolean b, int position, CheckBox cb, RelativeLayout rr) {

        Log.e(TAG, "onCheckedChanged: " + position);
        ModelClass mm = list.get(position);
        sp = cc.getSharedPreferences("Counts", Context.MODE_PRIVATE);


        String key = list.get(position).getPname();


        if (cb.isChecked()) {


            Log.e("mnkhkjjk",SelectedData.getSelectedData().getCreditPoints()+"");

//
            if (SelectedData.getSelectedData().getCreditPoints() > 100.0) {
                Toast.makeText(cc, "We can not add more than 100 Credit Points", Toast.LENGTH_SHORT).show();
                cb.setChecked(false);
                return;

            }


            if (SelectedData.getSelectedData().getPlayerCount() >= 11) {
                Toast.makeText(cc, "We can not add more than 11 player", Toast.LENGTH_SHORT).show();
                cb.setChecked(false);
                return;

            }

//
//                if(SelectedData.getSelectedData().getTeamCount(list.get(position).getTname())>=7){
//                    Toast.makeText(cc, "We can not add more than 7 player in a team", Toast.LENGTH_SHORT).show();
//                    cb.setChecked(false);
//                    return;
//
//                }
            if (list.get(position).getRole().equals("wk")) {
                //   int value = fun(d, 1, key, cb, "wk");
                if (playerCount(SelectedData.Role.WK, 1, list.get(position).getTname())) {
                    SelectedData.getSelectedData().getData().put(list.get(position).getId(), new SelectedData.data(list.get(position).getId(), list.get(position).getPname(), list.get(position).getTname(), list.get(position).getRole(), list.get(position).getCredits()));
                    rr.setBackgroundColor(ContextCompat.getColor(cc, R.color.PlayerSelected));

                } else {
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);

                }


            }

            if (list.get(position).getRole().equals("bat")) {
                if (playerCount(SelectedData.Role.BAT, 1, list.get(position).getTname())) {
                    SelectedData.getSelectedData().getData().put(list.get(position).getId(), new SelectedData.data(list.get(position).getId(), list.get(position).getPname(), list.get(position).getTname(), list.get(position).getRole(), list.get(position).getCredits()));
                    rr.setBackgroundColor(ContextCompat.getColor(cc, R.color.PlayerSelected));

                } else {
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);

                }
            }

            if (list.get(position).getRole().equals("all")) {
                if (playerCount(SelectedData.Role.ALL, 1, list.get(position).getTname())) {
                    rr.setBackgroundColor(ContextCompat.getColor(cc, R.color.PlayerSelected));

                    SelectedData.getSelectedData().getData().put(list.get(position).getId(), new SelectedData.data(list.get(position).getId(), list.get(position).getPname(), list.get(position).getTname(), list.get(position).getRole(), list.get(position).getCredits()));

                } else {
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);

                }
            }

            if (list.get(position).getRole().equals("bowl")) {

                if (playerCount(SelectedData.Role.BOWL, 1, list.get(position).getTname())) {
                    rr.setBackgroundColor(ContextCompat.getColor(cc, R.color.PlayerSelected));
                    SelectedData.getSelectedData().getData().put(list.get(position).getId(), new SelectedData.data(list.get(position).getId(), list.get(position).getPname(), list.get(position).getTname(), list.get(position).getRole(), list.get(position).getCredits()));
                } else {
                    cb.setChecked(false);
                    rr.setBackgroundColor(Color.WHITE);
                }
            }

        } else {
            cb.setChecked(false);
            SelectedData.getSelectedData().removePlayer(list.get(position).getId());
            rr.setBackgroundColor(Color.WHITE);

        }


        tvCreditPoints.setText(String.valueOf(SelectedData.getSelectedData().getCreditPoints()));

        Log.e("kljkhjk", SelectedData.getSelectedData().getCreditPoints() + "");

        SharedPreferences.Editor editor1 = sp.edit();
        editor1.putInt("Key", SelectedData.getSelectedData().getPlayerCount());
        editor1.putInt("wKey", SelectedData.getSelectedData().getRoleCount("wk"));
        editor1.putInt("bKey", SelectedData.getSelectedData().getRoleCount("bat"));
        editor1.putInt("aKey", SelectedData.getSelectedData().getRoleCount("all"));
        editor1.putInt("bwlKey", SelectedData.getSelectedData().getRoleCount("bowl"));
        editor1.putFloat("creditPoints", SelectedData.getSelectedData().getCreditPoints());
//                editor1.clear();
        editor1.apply();

    }
}


