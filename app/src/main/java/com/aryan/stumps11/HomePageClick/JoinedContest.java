package com.aryan.stumps11.HomePageClick;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.aryan.stumps11.Adapters.JoinedContestAdapter;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.MyMatches.Live;
import com.aryan.stumps11.NewUiData.Activity.Adapter.LiveAdapter;
import com.aryan.stumps11.NewUiData.Activity.Model.modelupcomingMatch;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.aryan.stumps11.joinContext.JoinContextResponse;
import com.aryan.stumps11.joinContextList.JoinContextAdapter;
import com.aryan.stumps11.joinContextList.JoinContextListRes;
import com.aryan.stumps11.joinContextList.ListContextModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;

import retrofit2.Call;
import retrofit2.Callback;


public class JoinedContest extends Fragment {

    private ImageView imgNOContext;
    private ProgressDialog progressDialog;
    private List<ListContextModel> upcomingMatchesList;
    private RequestQueue rq;
    private RecyclerView rr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_joined_contest, container, false);
        SharedPreferences sp=getActivity().getSharedPreferences("MID", MODE_PRIVATE);
        String Matchid=Match_id;
        SharedPreferences mob=getActivity().getSharedPreferences("Mobile",MODE_PRIVATE);
        String mobile=mob.getString("mKey","0");
         rr=view.findViewById(R.id.JoinedContestView);
        imgNOContext=view.findViewById(R.id.joined_context_image_view);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        rr.setLayoutManager(new LinearLayoutManager(getContext()));
        rr.setHasFixedSize(true);
        upcomingMatchesList=new ArrayList<>();

        progressDialog.dismiss();

        getAllList();

        return view;
    }
    private void getAllList() {


        try {

            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            String tokenName="Bearer "+retrivedToken;
//
            rq = Volley.newRequestQueue(getContext());
            String connection= CheckConnection.BASE_URL+"/api/user/team/list-view";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, connection, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if (status.equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (jsonArray.length()==0){

                                imgNOContext.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }else{
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                                    JSONObject jsonObject3 = jsonObject2.getJSONObject("contest");

                                    String seriesname = jsonObject3.getString("title");
                                    String teamA=jsonObject3.getString("teamA_tittle");
                                    String teamB=jsonObject3.getString("teamB_tittle");

                                    ListContextModel mm=new ListContextModel();
                                    mm.setTeamAName(teamA);
                                    mm.setTeamBName(teamB);
                                    mm.setTourName(seriesname);
                                    upcomingMatchesList.add(mm);
//                                 JoinedContestAdapter   adapterUpComingMatches=new JoinedContestAdapter(upcomingMatchesList,getActivity());
//                                    rr.setAdapter(adapterUpComingMatches);
//                                    adapterUpComingMatches.notifyDataSetChanged();
//                                    progressDialog.dismiss();

                                    JoinContextAdapter liveAdapter=new JoinContextAdapter(upcomingMatchesList,getContext());
                                    rr.setAdapter(liveAdapter);
                                    liveAdapter.notifyDataSetChanged();
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




//        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
//        String retrivedToken = preferences.getString("TOKEN", null);//second parameter default value.
//        String tokenName = "Bearer " + retrivedToken;
//        try{
//            CheckConnection.api.joinContextList(tokenName).enqueue(new Callback<JoinContextListRes>() {
//                @Override
//                public void onResponse(Call<JoinContextListRes> call, Response<JoinContextListRes> response) {
//                    if (response.isSuccessful()){
//
//                    }else{
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JoinContextListRes> call, Throwable t) {
//
//                    Toast.makeText(getContext(), "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }catch (Exception e){
//            Toast.makeText(getContext(), "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

    }
}