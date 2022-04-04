package com.aryan.stumps11.NewUiData.Activity.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Adapters.HomeAdapter;
import com.aryan.stumps11.ApiModel.profile.elevenPlayer.ElevenPlayerContext;
import com.aryan.stumps11.ApiModel.profile.elevenPlayer.ElevenPlayerRes;
import com.aryan.stumps11.CreateTeam.ChooseCaptainandVC;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterUpComingMatches;
import com.aryan.stumps11.NewUiData.Activity.Adapter.CompleteMatchAdapter;
import com.aryan.stumps11.NewUiData.Activity.MatchDetailsActivity;
import com.aryan.stumps11.NewUiData.Activity.Model.modelupcomingMatch;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class CompleteMatchFragment extends Fragment {
    private CompleteMatchAdapter adapterUpComingMatches;
   private List<modelupcomingMatch> upcomingMatchesList;
    private RecyclerView rv_CompleteMatch;
    private RequestQueue rq;
    private ProgressDialog progressDialog;
    private ImageView imgCompleted;
//    private List<ElevenPlayerContext> elevenPlayerContexts;

    public CompleteMatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_complete_match, container, false);

        progressDialog=new ProgressDialog(getContext());

        Initialization(view);

   return view;
    }

    private void Initialization(View view) {

        rv_CompleteMatch=view.findViewById(R.id.rv_CompleteMatch);
        imgCompleted=view.findViewById(R.id.complete_match_no_match);
        rv_CompleteMatch.setHasFixedSize(true);
        rv_CompleteMatch.setLayoutManager(new LinearLayoutManager(getContext()));




        LoadCompleteMatchData();



    }
    private void LoadCompleteMatchData() {
//        upcomingMatchesList.clear();
        progressDialog.setTitle("Stump11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;



        try {


//
            upcomingMatchesList=new ArrayList<>();
            rq = Volley.newRequestQueue(getContext());
            String connection=CheckConnection.BASE_URL+"/api/user/team/temp/details/622af9eac3877f3cd7980ae3";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, connection, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if (status.equals("200")) {

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (jsonArray.length()==0){
                                imgCompleted.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }else{

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                                    JSONObject jsonObject3 = jsonObject2.getJSONObject("contest");

                                    String seriesname = jsonObject3.getString("title");
                                    String teamA=jsonObject3.getString("teamA_tittle");
                                    String teamB=jsonObject3.getString("teamB_tittle");

                                    modelupcomingMatch mm=new modelupcomingMatch();
                                    mm.setTeamAName(teamA);
                                    mm.setTeamBName(teamB);
                                    mm.setTourName(seriesname);
                                    upcomingMatchesList.add(mm);


                                    adapterUpComingMatches=new CompleteMatchAdapter(upcomingMatchesList,getActivity());
                                    rv_CompleteMatch.setAdapter(adapterUpComingMatches);
                                    adapterUpComingMatches.notifyDataSetChanged();
                                    progressDialog.dismiss();

                                }

                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Exception : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    //add params <key,value>
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("Authorization", tokenName);
                    return headers;
                }

            };


            rq.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }



//        adapterUpComingMatches=new AdapterUpComingMatches(upcomingMatchesList,getActivity());
//        rv_CompleteMatch.setAdapter(adapterUpComingMatches);
//        adapterUpComingMatches.notifyDataSetChanged();



    }



    private void selected11Player(){
        SharedPreferences preferences =getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

//        CheckConnection.api.getSelected11Player(tokenName,"").enqueue(new Callback<ElevenPlayerRes>() {
//            @Override
//            public void onResponse(Call<ElevenPlayerRes> call, Response<ElevenPlayerRes> response) {
//                if (response.isSuccessful()){
//
//                    assert response.body() != null;
////                    elevenPlayer11List=response.body().getElevenPlayerData().getElevenPlayerContext();
//                    elevenPlayerContexts=response.body().getElevenPlayerData().getElevenPlayerContext();
//
//                    if (elevenPlayer11List==null && elevenPlayer11List.equals(0)){
//                        Toast.makeText(getContext(), "No Item Here ", Toast.LENGTH_SHORT).show();
//                    }else{
//                        for (int i=0; i< elevenPlayer11List.size(); i++){
//                            //    CVCAdapter ca=new CVCAdapter(elevenPlayer11List,ChooseCaptainandVC.this);
//                            //  rr.setAdapter(ca);
//                        }
//
//                    }




//
//            @Override
//            public void onFailure(Call<ElevenPlayerRes> call, Throwable t) {
//
//            }
//        });
//    }


//}

    }
}