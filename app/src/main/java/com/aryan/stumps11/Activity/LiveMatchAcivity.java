package com.aryan.stumps11.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterViewPagerMatchDetails;
import com.aryan.stumps11.NewUiData.Activity.MatchDetailsActivity;
import com.aryan.stumps11.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class LiveMatchAcivity extends AppCompatActivity {


    private ImageView iv_backArrow;
    private TabLayout tabLayoutMatch;
    private ViewPager viewPager_MatchDetails;
    private AdapterViewPagerMatchDetails adapterViewPagerMatch;
    private TextView tvMatchDetailsStatus;
    private TextView tvMatchStatus;
    private TextView tvTeamAStatus;
    private TextView tvTeamBStatus;
    private TextView tvMatchNameA;
    private TextView tvMatchNameB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_match_acivity);
        Initialization();
        completeMatchStatus();
    }

    private void Initialization() {

//        tabLayoutMatch=findViewById(R.id.tabLayoutMatch);
//        viewPager_MatchDetails=findViewById(R.id.viewPager_MatchDetails);

        iv_backArrow=findViewById(R.id.live_match_activty_back_btn);
        tvMatchDetailsStatus=findViewById(R.id.live_match_score_board_status);
        tvMatchStatus=findViewById(R.id.live_match_status_match);
        tvTeamAStatus=findViewById(R.id.live_match_details_team_score_a);
        tvTeamBStatus=findViewById(R.id.live_match_details_team_score_b);
        tvMatchNameA=findViewById(R.id.live_match_team_name_one);
        tvMatchNameB=findViewById(R.id.live_match_team_name_two);


        iv_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



//        adapterViewPagerMatch = new AdapterViewPagerMatchDetails(getSupportFragmentManager(), tabLayoutMatch.getTabCount());
//        viewPager_MatchDetails.setAdapter(adapterViewPagerMatch);
//        viewPager_MatchDetails.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMatch));
//        tabLayoutMatch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                viewPager_MatchDetails.setCurrentItem((tab.getPosition()));
//                Log.e("TABBBB25897", tab.getPosition() + "");
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });



    }



    private void completeMatchStatus(){

//        scoredBoardModels=new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(LiveMatchAcivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://rest.entitysport.com/v2/matches/53401/newpoint2?token=de7cddfd6309f89136ca5c4f68aaff99", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    sl.stopShimmer();
//                    sl.setVisibility(View.INVISIBLE);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("ok")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("response");
                        String matchStatus=jsonObject1.getString("result");
                        tvMatchDetailsStatus.setText(matchStatus);
                        String liveStatus=jsonObject1.getString("status_str");
                        tvMatchStatus.setText(liveStatus);


                        JSONObject jsonObject2=jsonObject1.getJSONObject("competition");


                        JSONObject jsonObject3 = jsonObject1.getJSONObject("teama");
                        String teamShortNAme=jsonObject3.getString("short_name");
                        tvMatchNameA.setText(teamShortNAme);
                        String teamScoredBoardA=jsonObject3.getString("scores_full");
                        tvTeamAStatus.setText(teamScoredBoardA);


                        JSONObject jsonObject4=jsonObject1.getJSONObject("teamb");
                        String teamNameB=jsonObject4.getString("short_name");
                        tvMatchNameB.setText(teamNameB);

                        String teamScoredBoardB=jsonObject4.getString("scores_full");
                        tvTeamBStatus.setText(teamScoredBoardB);



                        // JSONArray jsonArray = jsonObject1.getJSONArray("items");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//                            String matchid=jsonObject2.getString("match_id");
//
//                            JSONObject jsonObject3 = jsonObject2.getJSONObject("competition");
//                            String seriesname = jsonObject3.getString("title");
//                            String cid=jsonObject3.getString("cid");
//////
//                            JSONObject jsonObject4 = jsonObject2.getJSONObject("teama");
//                            String teamname1 = jsonObject4.getString("name");
//                            String teamimage1 = jsonObject4.getString("logo_url");
//                            JSONObject jsonObject5 = jsonObject2.getJSONObject("teamb");
//                            String teamname2 = jsonObject5.getString("name");
//                            String teamimage2 = jsonObject5.getString("logo_url");








//                            ScoredBoardModel mm = new ScoredBoardModel();

//                            mm.setScoreBoardTotalTeamA(teamAScoredBoard);
//                            mm.setScoredBoardTotalTeamB(teamBScoredBoard);
//                            mm.setScoreBoardTotalTeamA(teamNameA);
//                            mm.setScoredBoardTotalTeamB(teamNameB);
//                            mm.setScoredBoardStatus(matchStatus);


//                            mm.setSname(seriesname);
//                            mm.setMatchid(matchid);
//                            mm.setCid(cid);
//                            mm.setTname1(teamname1);
//                            mm.setTname2(teamname2);
//                            mm.setTimage1(teamimage1);
//                            mm.setTimage2(teamimage2);
//                            mm.setMatchTiming(timer);
                        //   scoredBoardModels.add(mm);

//                            HomeAdapter ha = new HomeAdapter(HomePage.this, list,HomePage.this::sendData);
//                            comingSoonRecyclerView.setAdapter(ha);
                        // }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LiveMatchAcivity.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(stringRequest);


    }

}