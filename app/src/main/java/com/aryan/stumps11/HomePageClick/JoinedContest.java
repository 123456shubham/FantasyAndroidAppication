package com.aryan.stumps11.HomePageClick;

import android.app.ProgressDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Adapters.JoinedContestAdapter;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;


public class JoinedContest extends Fragment {

    private ImageView imgNOContext;
    private ProgressDialog progressDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_joined_contest, container, false);
        SharedPreferences sp=getActivity().getSharedPreferences("MID", MODE_PRIVATE);
       // String Matchid=sp.getString("MatchID","0");
        String Matchid=Match_id;

        Log.e("mkmk",Matchid+"");
        SharedPreferences mob=getActivity().getSharedPreferences("Mobile",MODE_PRIVATE);
        String mobile=mob.getString("mKey","0");

        RecyclerView rr=view.findViewById(R.id.JoinedContestView);
        imgNOContext=view.findViewById(R.id.joined_context_image_view);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        rr.setLayoutManager(new LinearLayoutManager(getContext()));
        rr.setHasFixedSize(true);
        List<ModelClass>list=new ArrayList<>();
        RequestQueue rq= Volley.newRequestQueue(getContext());

        progressDialog.dismiss();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://www.stumps11.com/APIS/Apis.asmx/displayjc", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    if (jsonArray.length() == 0) {

                        imgNOContext.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();


                    } else {
                        progressDialog.dismiss();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Type = jsonObject.getString("jctype");
                        String pool = jsonObject.getString("jcontestpool");
                        String entry = jsonObject.getString("jcontestentry");
                        String max = jsonObject.getString("jcontestmaxentries");
                        String total = jsonObject.getString("jcontesttotalspots");
                        String joined = jsonObject.getString("jcontestwinnerpercentage");
                        String winner = jsonObject.getString("jcontestjoinedspots");
                        String mid = jsonObject.getString("jmatchid");
                        String usermob = jsonObject.getString("userteamname");

                        if (mid.equals(Matchid) && mobile.equals(usermob)) {
                            ModelClass mm = new ModelClass();
                            mm.setMaxteams1(max);
                            mm.setBonus1("Bonus 10%");
                            mm.setPrizepool1(pool);
                            mm.setEntry1(entry);
                            mm.setJoined1(joined);
                            mm.setSpotstotal1(total);
                            mm.setContesttype1(Type);
                            mm.setWinpercentage1(winner);
                            list.add(mm);
                            JoinedContestAdapter jc = new JoinedContestAdapter(getActivity(), list);
                            rr.setAdapter(jc);
                            progressDialog.dismiss();
                        }
                    }
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String ,String> map=new HashMap<>();
                map.put("jmatchid",Matchid);
                map.put("userteamname",mobile);
                return map;
            }
        };
        rq.add(stringRequest);
        return view;
    }
}