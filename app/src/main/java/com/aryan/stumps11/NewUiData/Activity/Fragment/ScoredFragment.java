package com.aryan.stumps11.NewUiData.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterBowlerList;
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterFallOfWickets;
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterTeamScore;
import com.aryan.stumps11.NewUiData.Activity.MatchDetailsActivity;
import com.aryan.stumps11.NewUiData.Activity.Model.ModelBowlerList;
import com.aryan.stumps11.NewUiData.Activity.Model.ModelFallOffWickets;
import com.aryan.stumps11.NewUiData.Activity.Model.ModelTeamScore;
import com.aryan.stumps11.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ScoredFragment extends Fragment {
    private ImageView iv_firstTeamMore,iv_firstTeamLess,iv_SecondTeamMore,iv_SecondTeamless;
    private LinearLayout ll_firstTeamScore,ll_secondTeamScore;


    private RecyclerView rv_secondTeamScore;
    private AdapterTeamScore adapterTeamScore;
    private List<ModelTeamScore> listSecondTeamScore=new ArrayList<>();

    private RecyclerView rv_bowlerList;
    private AdapterBowlerList adapterBowlerList;
    private List<ModelBowlerList>bowlerList=new ArrayList<>();


    private RecyclerView rv_fallOfWicketsSecondTeamScore;
    private AdapterFallOfWickets adapterFallOfWickets;
    private List<ModelFallOffWickets>listFallOffWickets=new ArrayList<>();



    private RecyclerView rv_FirstTeamScore,rv_firstTeamBowlerList,rv_firstTeamFallOfWickets;
    private List<ModelTeamScore>listFirstTeamScore=new ArrayList<>();
    private List<ModelBowlerList>listFirstTeamBowlerList=new ArrayList<>();
    private List<ModelFallOffWickets>listFirstTeamFallOffWickets=new ArrayList<>();



    public ScoredFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_scored, container, false);


        initialize(view);

    return view;
    }

    private void initialize(View view) {
        iv_firstTeamMore =view.findViewById(R.id.iv_firstTeamMore);
        iv_firstTeamLess =view.findViewById(R.id.iv_firstTeamLess);
        iv_SecondTeamMore =view.findViewById(R.id.iv_SecondTeamMore);
        iv_SecondTeamless =view.findViewById(R.id.iv_SecondTeamless);
        ll_firstTeamScore=view.findViewById(R.id.ll_firstTeamScore);
        ll_secondTeamScore=view.findViewById(R.id.ll_secondTeamScore);

        rv_secondTeamScore=view.findViewById(R.id.rv_secondTeamScore);
        rv_bowlerList=view.findViewById(R.id.rv_bowlerList);
        rv_fallOfWicketsSecondTeamScore=view.findViewById(R.id.rv_fallOfWicketsSecondTeamScore);
        rv_FirstTeamScore=view.findViewById(R.id.rv_FirstTeamScore);
        rv_firstTeamBowlerList=view.findViewById(R.id.rv_firstTeamBowlerList);
        rv_firstTeamFallOfWickets=view.findViewById(R.id.rv_firstTeamFallOfWickets);


        loadFirstTeamData();
        loadFirstTeamScore();
        loadFirstTeamBowlerList();
        loadFirstTeamFallOffWickets();


        loadSecondTeamData();
        loadSecondTeamScore();
        loadBowlerListData();
        loadFallOffWicketsData();

    }


    private void loadFirstTeamData() {
        iv_firstTeamMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_firstTeamScore.setVisibility(View.VISIBLE);
                ll_secondTeamScore.setVisibility(View.GONE);
                iv_firstTeamLess.setVisibility(View.VISIBLE);
                iv_firstTeamMore.setVisibility(View.GONE);
                iv_SecondTeamMore.setVisibility(View.VISIBLE);
                iv_SecondTeamless.setVisibility(View.GONE);
            }
        });
        iv_firstTeamLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_firstTeamScore.setVisibility(View.GONE);
                iv_firstTeamMore.setVisibility(View.VISIBLE);
                iv_firstTeamLess.setVisibility(View.GONE);
            }
        });

    }
    private void loadSecondTeamData() {
        iv_SecondTeamless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_secondTeamScore.setVisibility(View.GONE);
                iv_SecondTeamless.setVisibility(View.GONE);
                iv_SecondTeamMore.setVisibility(View.VISIBLE);
            }
        });
        iv_SecondTeamMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_secondTeamScore.setVisibility(View.VISIBLE);
                iv_SecondTeamMore.setVisibility(View.GONE);
                iv_SecondTeamless.setVisibility(View.VISIBLE);
                ll_firstTeamScore.setVisibility(View.GONE);
                iv_firstTeamMore.setVisibility(View.VISIBLE);
                iv_firstTeamLess.setVisibility(View.GONE);
            }
        });
    }

    private void loadFirstTeamScore() {
        listFirstTeamScore.clear();





        listFirstTeamScore.add(new ModelTeamScore("Guptill","b Chahal","66","46","6","4","143.48"));
        listFirstTeamScore.add(new ModelTeamScore("Henry Nicholls","c Rahul b SN Thakur","80","103","9","0","77.67"));
        listFirstTeamScore.add(new ModelTeamScore("Williamson","b Chahal","56","46","0","0","143.48"));
        listFirstTeamScore.add(new ModelTeamScore("Ross Tayler","b Chahal","16","46","0","0","143.48"));
        listFirstTeamScore.add(new ModelTeamScore("Henry Nicholls","b Chahal","26","46","0","0","143.48"));
        listFirstTeamScore.add(new ModelTeamScore("James Neesham","Batting","15","46","1","1","143.48"));
        listFirstTeamScore.add(new ModelTeamScore("de Grandhomme","Batting","15","46","1","1","143.48"));



        //------------------------------------------------------------------------------------------------------------------------------------------






//        scoredBoardModels=new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(getContext());
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
                        String liveStatus=jsonObject1.getString("status_str");


                        JSONObject jsonObject2=jsonObject1.getJSONObject("points");


                        JSONObject jsonObject3 = jsonObject2.getJSONObject("teama");

                       // String teamShortNAme=jsonObject3.getString("short_name");
                       // String teamScoredBoardA=jsonObject3.getString("scores_full");


                      //  JSONObject jsonObject4=jsonObject1.getJSONObject("playing11");

                  //      String teamNameB=jsonObject4.getString("short_name");

                    //    String teamScoredBoardB=jsonObject4.getString("scores_full");



                        JSONArray jsonArray = jsonObject3.getJSONArray("playing11");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject4 = jsonArray.getJSONObject(i);
                            String teamAPname=jsonObject4.getString("name");
                            String teamAPrun=jsonObject4.getString("run");
                            String teamAPFour=jsonObject.getString("four");
                            String teamAPSix=jsonObject.getString("six");
                            String teamAPSR=jsonObject.getString("sr");


                            JSONObject jsonObject5 = jsonObject2.getJSONObject("teamb");
                            JSONArray jsonArray1=jsonObject5.getJSONArray("playing11");








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
                         }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(stringRequest);





















        //--------------------------------------------------------------------------------------------------------------------------------------------------










        adapterTeamScore =new AdapterTeamScore(listFirstTeamScore,getActivity());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_FirstTeamScore.setLayoutManager(linearLayoutManager);
        rv_FirstTeamScore.setHasFixedSize(true);
        rv_FirstTeamScore.setAdapter(adapterTeamScore);

    }
    private void loadFirstTeamBowlerList() {
        listFirstTeamBowlerList.clear();

        listFirstTeamBowlerList.add(new ModelBowlerList("Jasprit Bumrah","7","0","34","0","4.9"));
        listFirstTeamBowlerList.add(new ModelBowlerList("Navdeep Saini","7","0","59","0","8.4"));
        listFirstTeamBowlerList.add(new ModelBowlerList("Yuzvendra Chahal","9","1","35","3","3.9"));
        listFirstTeamBowlerList.add(new ModelBowlerList("Shardul Thakur","7","0","48","1","6.9"));
        listFirstTeamBowlerList.add(new ModelBowlerList("Ravindra Jadeja","10","0","45","1","4.5"));

        adapterBowlerList=new AdapterBowlerList(listFirstTeamBowlerList,getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_firstTeamBowlerList.setAdapter(adapterBowlerList);
        rv_firstTeamBowlerList.setHasFixedSize(true);
        rv_firstTeamBowlerList.setLayoutManager(linearLayoutManager);
        adapterBowlerList.notifyDataSetChanged();
    }
    private void loadFirstTeamFallOffWickets() {
        listFirstTeamFallOffWickets.clear();

        listFirstTeamFallOffWickets.add(new ModelFallOffWickets("Martin Guptill","106-1","16.3"));
        listFirstTeamFallOffWickets.add(new ModelFallOffWickets("Williamson","159-2","27.1"));
        listFirstTeamFallOffWickets.add(new ModelFallOffWickets("Ross Taylor","186-3","31.5"));
        listFirstTeamFallOffWickets.add(new ModelFallOffWickets("Henry Nicholls","189-4","32.5"));
        listFirstTeamFallOffWickets.add(new ModelFallOffWickets("James Neesham","220-5","39.3"));

        adapterFallOfWickets=new AdapterFallOfWickets(listFallOffWickets,getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_firstTeamFallOfWickets.setLayoutManager(linearLayoutManager);
        rv_firstTeamFallOfWickets.setHasFixedSize(true);
        rv_firstTeamFallOfWickets.setAdapter(adapterFallOfWickets);
        adapterFallOfWickets.notifyDataSetChanged();
    }



    private void loadSecondTeamScore() {
        listSecondTeamScore.clear();


        listSecondTeamScore.add(new ModelTeamScore("K Rahul","c Santner b Bennett","112","113","8","2","98"));
        listSecondTeamScore.add(new ModelTeamScore("V Kohli","b Santner","58","75","8","2","96"));
        listSecondTeamScore.add(new ModelTeamScore("M Panday","run out Bennett","18","35","1","2","76"));
        listSecondTeamScore.add(new ModelTeamScore("Prithvi Shaw","batting","28","15","0","1","86"));
        listSecondTeamScore.add(new ModelTeamScore("Ravindra Jadeja","b Jamieson","48","75","4","2","86"));
        listSecondTeamScore.add(new ModelTeamScore("SN Thakur","batting","5","15","1","0","36"));
        listSecondTeamScore.add(new ModelTeamScore("Navdeep Saini","b Jamieson","18","25","1","1","86"));
        listSecondTeamScore.add(new ModelTeamScore("Mayank Agarwal","b Jamieson","8","5","2","0","116"));


        adapterTeamScore =new AdapterTeamScore(listSecondTeamScore,getContext());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_secondTeamScore.setAdapter(adapterTeamScore);
        rv_secondTeamScore.setHasFixedSize(true);
        rv_secondTeamScore.setLayoutManager(linearLayoutManager);
        adapterTeamScore.notifyDataSetChanged();
    }

    private void loadBowlerListData() {
        bowlerList.clear();

        bowlerList.add(new ModelBowlerList("Southee","9","1","59","0","6.6"));
        bowlerList.add(new ModelBowlerList("Jamieson","10","0","53","1","5.3"));
        bowlerList.add(new ModelBowlerList("Bennett","10","1","64","4","6.4"));
        bowlerList.add(new ModelBowlerList("de Grandhomme","3","0","10","0","3.3"));
        bowlerList.add(new ModelBowlerList("Neesham","8","0","50","1","6.2"));
        bowlerList.add(new ModelBowlerList("Santner","10","0","59","0","5.9"));



        adapterBowlerList =new AdapterBowlerList(bowlerList,getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_bowlerList.setLayoutManager(linearLayoutManager);
        rv_bowlerList.setHasFixedSize(true);
        rv_bowlerList.setAdapter(adapterBowlerList);
        adapterBowlerList.notifyDataSetChanged();

    }
    private void loadFallOffWicketsData() {
        listFallOffWickets.clear();

        listFallOffWickets.add(new ModelFallOffWickets("Mayank Agarwal","8-1","1.6"));
        listFallOffWickets.add(new ModelFallOffWickets("V Kohli","32-2","6.4"));
        listFallOffWickets.add(new ModelFallOffWickets("Prithvi Shaw","62-3","12.1"));
        listFallOffWickets.add(new ModelFallOffWickets("Shreyas Iyer","162-4","30.3"));
        listFallOffWickets.add(new ModelFallOffWickets("Rahul","269-5","46.4"));
        listFallOffWickets.add(new ModelFallOffWickets("Manish Pandey","269-6","46.5"));
        listFallOffWickets.add(new ModelFallOffWickets("SN Thakur","280-7","48.2"));

        adapterFallOfWickets =new AdapterFallOfWickets(listFallOffWickets,getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_fallOfWicketsSecondTeamScore.setAdapter(adapterFallOfWickets);
        rv_fallOfWicketsSecondTeamScore.setHasFixedSize(true);
        rv_fallOfWicketsSecondTeamScore.setLayoutManager(linearLayoutManager);
        adapterFallOfWickets.notifyDataSetChanged();



    }






    private void completeMatchStatus(){

//        scoredBoardModels=new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(getContext());
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
                        String liveStatus=jsonObject1.getString("status_str");


                        JSONObject jsonObject2=jsonObject1.getJSONObject("competition");


                        JSONObject jsonObject3 = jsonObject1.getJSONObject("teama");
                        String teamShortNAme=jsonObject3.getString("short_name");
                        String teamScoredBoardA=jsonObject3.getString("scores_full");


                        JSONObject jsonObject4=jsonObject1.getJSONObject("teamb");
                        String teamNameB=jsonObject4.getString("short_name");

                        String teamScoredBoardB=jsonObject4.getString("scores_full");



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

                Toast.makeText(getContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(stringRequest);


    }








}