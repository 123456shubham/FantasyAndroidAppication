package com.aryan.stumps11.EditTeam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.ViewHolder> {
    private Context context;
    private List<EditTeamModel> editTeamModels;


    static int crept;
    int hello, wk, bat, all, bwl, creditPoints;
    static int addCreditPoint;
    private SharedPreferences sp;

    public SelectedAdapter(Context context, List<EditTeamModel> editTeamModels) {
        this.context = context;
        this.editTeamModels = editTeamModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_player, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        EditTeamModel mm = editTeamModels.get(position);
        EditDatabase db = new EditDatabase(context);
        SharedPreferences mob = context.getSharedPreferences("Mobile", context.MODE_PRIVATE);
        String mobile = mob.getString("mKey", "0");

//        Glide.with(cc).load(list.get(position).getImages());
//        holder.ttcountry.setText(editTeamModels.get(position).getEditTeamName());
        holder.ttcredits.setText(editTeamModels.get(position).getEditPlayerCreditPoint());
//        holder.ttpts.setText(editTeamModels.get(position).getEditPoints());
        holder.ttstatus.setText(editTeamModels.get(position).getEditPlayerRole());
        holder.ttname.setText(editTeamModels.get(position).getEditPlayerName());
        String key = "Hello" + editTeamModels.get(position).getEditPlayerName() + position;
        holder.cb.setChecked(Update(key));


        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hello == 11 || hello >= 11) {
                    Toast.makeText(context, "Player Length is greater than 11", Toast.LENGTH_SHORT).show();
                } else {
                    sp = context.getSharedPreferences("Counts", Context.MODE_PRIVATE);

                    hello = sp.getInt("Key", 0);

                    wk = sp.getInt("wKey", 0);
                    bat = sp.getInt("bKey", 0);
                    all = sp.getInt("aKey", 0);
                    bwl = sp.getInt("bwlKey", 0);
                    creditPoints = sp.getInt("creditPoints", crept);

                }


                if (holder.cb.isChecked()) {
                    Save(key, true);
//                    db.AddPlayer(list.get(position).getId(),mobile,list.get(position).getPname(),list.get(position).getTname(),list.get(position).getRole(),list.get(position).getPts(),list.get(position).getCredits(),null,null);
                    //    Toast.makeText(cc, "Player "+list.get(position).getPname(), Toast.LENGTH_SHORT).show();


                    if (editTeamModels.get(position).getEditPlayerRole().equals("wk")) {
                        if (wk == 4) {
                            Toast.makeText(context, "only four wicket keeper select", Toast.LENGTH_SHORT).show();
//                            holder.cb.setBackgroundColor(Color.WHITE);
                            holder.rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

//                            h
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
                            //    wk=sp.getInt("wKey",0);

                            //Toast.makeText(cc, "Player "+list.get(position), Toast.LENGTH_SHORT).show();
                        } else if (hello == 11 || hello >= 11) {

                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
                            Toast.makeText(context, "You Does't Select More Than 11 Player  " + hello, Toast.LENGTH_SHORT).show();

                        } else {
                            db.EditAddPlayer(editTeamModels.get(position).getEditPlayerId(), mobile, editTeamModels.get(position).getEditPlayerName(), editTeamModels.get(position).getEditPlayerCountryName(), editTeamModels.get(position).getEditPlayerRole(), editTeamModels.get(position).getEditPoints(), editTeamModels.get(position).getEditPlayerCreditPoint(), null, null);

                            hello++;
                            wk++;
                            creditPoints++;


                            try {

                                String cp = editTeamModels.get(position).getEditPlayerCreditPoint();
                                crept = Integer.parseInt(cp);
                                ArrayList<Integer> addCredit = new ArrayList<Integer>();
                                addCredit.add(crept);

                                for (int totalCredit : addCredit) {
                                    addCreditPoint += totalCredit;
//                                    Toast.makeText(cc, "i<<<"+addCreditPoint, Toast.LENGTH_SHORT).show();


                                }

                                Intent intent = new Intent("custom-message");
                                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                                intent.putExtra("c", addCreditPoint);
//                                    intent.putExtra("item",ItemName);
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //                                Toast.makeText(cc, "You select "+hello, Toast.LENGTH_SHORT).show();


//                            holder.rr.setBackgroundColor(Color.WHITE);

                        }

                    }


                    // bast man logic
                    if (editTeamModels.get(position).getEditPlayerRole().equals("bat")) {
                        if (bat == 6) {
                            holder.rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

                            Toast.makeText(context, "only four bastman select", Toast.LENGTH_SHORT).show();
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);

                        } else if (hello == 11 || hello >= 11) {
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
//                                Toast.makeText(cc, "You Does't Select More Than 11 Player  "+hello, Toast.LENGTH_SHORT).show();

                        } else {
                            db.EditAddPlayer(editTeamModels.get(position).getEditPlayerId(), mobile, editTeamModels.get(position).getEditPlayerName(), editTeamModels.get(position).getEditPlayerCountryName(), editTeamModels.get(position).getEditPlayerRole(), editTeamModels.get(position).getEditPoints(), editTeamModels.get(position).getEditPlayerCreditPoint(), null, null);

                            hello++;
                            bat++;
                            creditPoints++;

//                            Toast.makeText(cc, "You select "+hello, Toast.LENGTH_SHORT).show();


                        }


                    }


                    if (editTeamModels.get(position).getEditPlayerRole().equals("all")) {

                        if (all == 4) {
                            holder.rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

//                            Toast.makeText(cc,"only 2  all rounder  select",Toast.LENGTH_SHORT).show();
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
                        } else if (hello == 11 || hello >= 11) {
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
//                                Toast.makeText(cc, "You Does't Select More Than 11 Player  "+hello, Toast.LENGTH_SHORT).show();


                        } else {
                            db.EditAddPlayer(editTeamModels.get(position).getEditPlayerId(), mobile, editTeamModels.get(position).getEditPlayerName(), editTeamModels.get(position).getEditPlayerCountryName(), editTeamModels.get(position).getEditPlayerRole(), editTeamModels.get(position).getEditPoints(), editTeamModels.get(position).getEditPlayerCreditPoint(), null, null);

                            hello++;
                            all++;
                            creditPoints++;

//                            Toast.makeText(cc, "You select "+hello, Toast.LENGTH_SHORT).show();


                        }

                    }
                    if (editTeamModels.get(position).getEditPlayerRole().equals("bowl")) {
                        if (bwl == 6) {
                            holder.rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));

                            Toast.makeText(context, "only 2  all rounder  select", Toast.LENGTH_SHORT).show();
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
                        } else if (hello == 11 || hello >= 11) {
                            holder.cb.setChecked(false);
                            holder.rr.setClickable(false);
                            Toast.makeText(context, "You Does not  Select More Than 11 Player  " + hello, Toast.LENGTH_SHORT).show();

                        } else {
                            db.EditAddPlayer(editTeamModels.get(position).getEditPlayerId(), mobile, editTeamModels.get(position).getEditPlayerName(), editTeamModels.get(position).getEditPlayerCountryName(), editTeamModels.get(position).getEditPlayerRole(), editTeamModels.get(position).getEditPoints(), editTeamModels.get(position).getEditPlayerCreditPoint(), null, null);

                            hello++;
                            bwl++;
                            creditPoints++;

//                            Toast.makeText(cc, "You select "+hello, Toast.LENGTH_SHORT).show();

                        }


                    }


                } else {
                    Save(key, false);
                    holder.rr.setBackgroundColor(Color.WHITE);
                    db.EditRemovePlayer(mobile, editTeamModels.get(position).getEditPlayerId());
                    hello--;
                    if (editTeamModels.get(position).getEditPlayerRole().equals("wk")) {

                        wk--;
                        creditPoints--;
                        Toast.makeText(context, "" + wk, Toast.LENGTH_SHORT).show();
                    }
                    if (editTeamModels.get(position).getEditPlayerRole().equals("bat")) {
                        bat--;
                        creditPoints--;
                        Toast.makeText(context, "" + bat, Toast.LENGTH_SHORT).show();
                    }
                    if (editTeamModels.get(position).getEditPlayerRole().equals("all")) {
                        all--;
                        creditPoints--;
                        Toast.makeText(context, "" + all, Toast.LENGTH_SHORT).show();
                    }
                    if (editTeamModels.get(position).getEditPlayerRole().equals("bowl")) {
                        bwl--;
                        creditPoints--;
                        Toast.makeText(context, "" + bwl, Toast.LENGTH_SHORT).show();
                    }
                }
                SharedPreferences.Editor editor1 = sp.edit();
                editor1.putInt("Key", hello);
                editor1.putInt("wKey", wk);
                editor1.putInt("bKey", bat);
                editor1.putInt("aKey", all);
                editor1.putInt("bwlKey", bwl);
                editor1.putInt("creditPoints", creditPoints);
                editor1.commit();
//                editor1.clear();
//                editor1.apply();
            }
        });
        if (holder.cb.isChecked()) {
            holder.rr.setBackgroundColor(ContextCompat.getColor(context, R.color.PlayerSelected));
        } else {
            holder.rr.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return editTeamModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ii;
        private CheckBox cb;
        private TextView ttname, ttstatus, ttpts, ttcredits, ttcountry;
        private RelativeLayout rr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rr = itemView.findViewById(R.id.edit_PlayerLayout);
            cb = itemView.findViewById(R.id.edit_player_checkBox);
//            ii=itemView.findViewById(R.id.imageView22);
            ttname = itemView.findViewById(R.id.edit_player_checkBox);
            ttstatus = itemView.findViewById(R.id.edit_player_status);
            ttcredits = itemView.findViewById(R.id.edit_player_credit_point);
//            ttcountry=itemView.findViewById(R.id.textView68);


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
}
