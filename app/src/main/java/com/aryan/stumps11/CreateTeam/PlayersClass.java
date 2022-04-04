package com.aryan.stumps11.CreateTeam;

import static com.aryan.stumps11.CreateTeam.CreateTeams.MATCH_ID;
import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;
import static com.aryan.stumps11.HomePageClick.HomePageClick.cid;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.api_integration.OnClick;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayersClass  {

    private String url,Matchid;
    private String c_i_d;




    public void PlayersApi(RecyclerView rr, Context cc,String role){
        List<ModelClass> list;
        rr.setHasFixedSize(true);
        rr.setLayoutManager(new LinearLayoutManager(cc));
        list=new ArrayList<>();
        RequestQueue rq= Volley.newRequestQueue(cc);
        Matchid=Match_id;
        c_i_d=cid;


//        https://rest.entitysport.com/v2/competitions/123282/squads/53271?token={{sportToken}}

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://rest.entitysport.com/v2/competitions/"+cid+"/squads/"+Matchid+"?token=de7cddfd6309f89136ca5c4f68aaff99", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String status=jsonObject.getString("status");
                    if(status.equals("ok")){
                        JSONObject jsonObject1=jsonObject.getJSONObject("response");
                        JSONArray jsonArray=jsonObject1.getJSONArray("squads");
//                        JSONObject team=jsonObject.getJSONObject("team");
//                        String teamA=team.getString("logo_url");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject2=jsonArray.getJSONObject(i);
                            JSONArray jsonArray1=jsonObject2.getJSONArray("players");
                            for(int j=0;j<jsonArray1.length();j++){
                                JSONObject jsonObject3=jsonArray1.getJSONObject(j);
                                String title=jsonObject3.getString("title");
                                String shortname=jsonObject3.getString("short_name");
                                String country=jsonObject3.getString("country");
                                String id=jsonObject3.getString("pid");
                                String role1=jsonObject3.getString("playing_role");
                                String pts=jsonObject3.getString("recent_appearance");
                                String Credits=jsonObject3.getString("fantasy_player_rating");

                                if(role1.equals(role)){
                                    ModelClass mm=new ModelClass();
                                    mm.setTname(country);
                                    mm.setPts(pts);
                                    mm.setId(id);
                                    mm.setRole(role1);
                                    mm.setPname(shortname);
                                    mm.setCredits(Credits);
                                    mm.setStatus("");


                                    JSONArray jsonArray2=jsonObject2.getJSONArray("last_match_played");
                                    for(int k=0;k<jsonArray2.length();k++){
                                        JSONObject jsonObject4=jsonArray2.getJSONObject(k);
                                        String titl=jsonObject4.getString("title");

                                        if(titl.equals(title)){
                                            mm.setStatus("Played Last Match");
                                        }
                                    }
                                    list.add(mm);
                                    CreateTeamAdapter ca=new CreateTeamAdapter(cc, list, new OnClick() {
                                        @Override
                                        public void onClick() {

                                        }
                                    });
                                    rr.setAdapter(ca);
                                }
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
        });
        rq.add(stringRequest);
    }


}
