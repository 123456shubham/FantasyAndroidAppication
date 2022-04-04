package com.aryan.stumps11.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.dataPassing.DataPassringInterface;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context cc;
    List<ModelClass> list;
    Long oldLong, newLong, diff;
    Calendar cal;
    private String machId;
    private String formattedElapsedTime;
    private DataPassringInterface dataPassringInterface;

    public HomeAdapter(Context cc, List<ModelClass> list, DataPassringInterface dataPassringInterface) {
        this.cc = cc;
        this.list = list;
        this.dataPassringInterface = dataPassringInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(cc);
        View view = layoutInflater.inflate(R.layout.cricketmatch, null);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelClass mm = list.get(position);
        machId = mm.getMatchid();
        SharedPreferences sp = cc.getSharedPreferences("MID", Context.MODE_PRIVATE);
        holder.tvMatchTitle.setText(list.get(position).getSname());
        holder.tvMatchTeamNameA.setText(list.get(position).getTname1());
        holder.tvMatchTeamNameB.setText(list.get(position).getTname2());


        String time = list.get(position).getMatchTiming();

        // converting mili seconds

        String miliseconds = String.valueOf(list.get(position).getMatchTiming());

        //Vivek Tripathi :: CountdownTimer
        try {
//
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime matchDateTime = LocalDateTime.parse(miliseconds,
                    dtf);

            //Find remaining time
            long millis = Duration.between(now, matchDateTime).toMillis();
            new CountDownTimer(millis, 1000) {
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long dd = hours / 24;
                    String time = dd + " days : " + hours % 24 + " hrs : " + minutes % 60 + " min : " + seconds % 60 + " sec";
                    holder.tvMatchStatus.setText(time);
                }

                public void onFinish() {
                    //Close the popup
                    String time = "0 days : 0 hrs : 0 min : 0 sec";
                    holder.tvMatchStatus.setText(time);
                }
            }.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


        String team1 = "https://cricket.entitysport.com/" + mm.getTimage2();

        Shimmer shimmer = new Shimmer.ColorHighlightBuilder().setBaseColor(Color.parseColor("#F3F3F3")).setBaseAlpha(1).setHighlightColor(Color.parseColor("#e7e7e7")).setHighlightAlpha(1).setDropoff(50).build();
        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);
        Glide.with(cc).load(list.get(position).getTimage1()).into(holder.matchTeamIcon1);
        Glide.with(cc).load(list.get(position).getTimage2()).into(holder.matchTeamIcon2);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(cc, HomePageClick.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(CommonData.Match_id, mm.getMatchid());
                intent.putExtra(CommonData.C_I_D, mm.getCid());

                intent.putExtra("TeamNameA", list.get(position).getTname1());
                intent.putExtra("TeamNameB", list.get(position).getTname2());
                intent.putExtra("teamImage1", list.get(position).getTimage1());
                intent.putExtra("teamImage2", list.get(position).getTimage2());
                intent.putExtra("time", time);

                cc.startActivity(intent);

                ((AppCompatActivity) cc).finish();


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMatchTitle;

        private TextView tvMatchTeamNameA, tvMatchTeamNameB;

        private TextView tvMatchStatus;

        private CircleImageView matchTeamIcon1, matchTeamIcon2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMatchTitle = itemView.findViewById(R.id.cricket_match_title);

            tvMatchStatus = itemView.findViewById(R.id.cricket_match_status);

            tvMatchTeamNameA = itemView.findViewById(R.id.circket_match_team_name_a);

            tvMatchTeamNameB = itemView.findViewById(R.id.circket_match_team_name_b);

            matchTeamIcon1 = itemView.findViewById(R.id.cricket_match_team_a_icon);

            matchTeamIcon2 = itemView.findViewById(R.id.cricket_match_team_b_icon);

            cal = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
            String Hello = simpleDateFormat.format(cal.getTime());
            tvMatchStatus.setText("22.01.2022, 17:00:00");
            String oldTime = Hello;
            String newTime = tvMatchStatus.getText().toString();
            Date olddate, newdate;

            try {
                olddate = simpleDateFormat.parse(oldTime);
                newdate = simpleDateFormat.parse(newTime);
                oldLong = olddate.getTime();
                newLong = newdate.getTime();
                diff = newLong - oldLong;
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            MyCount count = new MyCount(diff, 1000);
            count.start();
        }

        public class MyCount extends CountDownTimer {
            MyCount(long millisinfuture, long countdowninterval) {
                super(millisinfuture, countdowninterval);
            }

            @Override
            public void onFinish() {
//                tvMatchStatus.setText("Done");
            }

            @Override
            public void onTick(long l) {
                long millis = l;
                String hms = (TimeUnit.MILLISECONDS.toDays(millis)) + " D : " +
                        (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)) + " h : ") +
                        (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)) + " m : " +
                                (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)) + " s"));
                tvMatchStatus.setText(hms);
            }
        }

    }
}
