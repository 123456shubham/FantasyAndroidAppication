package com.aryan.stumps11.NewUiData.Activity.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterUpComingMatches;
import com.aryan.stumps11.NewUiData.Activity.Adapter.LiveAdapter;
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


public class LiveMatchFragment extends Fragment {
    private LiveAdapter adapterUpComingMatches;
    private List<modelupcomingMatch> upcomingMatchesList=new ArrayList<>();
    private RecyclerView rv_LiveMatch;
    private RequestQueue rq;
    private ImageView imgLiveMatch;
    private ProgressDialog progressDialog;



    public LiveMatchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_live_match, container, false);

        Initialization(view);
    return view;
    }

    private void Initialization(View view) {

        rv_LiveMatch=view.findViewById(R.id.rv_LiveMatch);
        imgLiveMatch=view.findViewById(R.id.live_match_image);
        progressDialog=new ProgressDialog(getContext());




        LoadLiveMatchData();



    }
    private void LoadLiveMatchData() {
//        upcomingMatchesList.clear();
//
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            String tokenName="Bearer "+retrivedToken;

            try {


//
                upcomingMatchesList=new ArrayList<>();
                rq = Volley.newRequestQueue(getContext());
                String connection= CheckConnection.BASE_URL+"/api/user/my-team/live";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, connection, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");



                            if (status.equals("200")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                if (jsonArray.length()==0){

                                    imgLiveMatch.setVisibility(View.VISIBLE);
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
                                        adapterUpComingMatches=new LiveAdapter(upcomingMatchesList,getActivity());
                                        rv_LiveMatch.setAdapter(adapterUpComingMatches);
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



//
//            adapterUpComingMatches=new LiveAdapter(upcomingMatchesList,getActivity());
//            rv_LiveMatch.setAdapter(adapterUpComingMatches);
//            adapterUpComingMatches.notifyDataSetChanged();







    }


}