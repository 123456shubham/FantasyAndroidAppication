package com.aryan.stumps11.EditTeam;

import static com.aryan.stumps11.EditTeam.EditTeamActivity.ID;
import static com.aryan.stumps11.EditTeam.EditTeamActivity.comId;
import static com.aryan.stumps11.EditTeam.EditTeamActivity.teamId;
import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;
import static com.aryan.stumps11.HomePageClick.HomePageClick.cid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Adapters.MyTeamAdapter;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyTeamDummyModel;
import com.aryan.stumps11.CreateTeam.CreateTeamAdapter;
import com.aryan.stumps11.CreateTeam.SelectedData;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.api_integration.CheckConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditPlayer {
    public static String wkQ="";
    private String url, Matchid;
    private String c_i_d;
    private String id;
    private JSONArray findData;
    public static int totalWk = 0;
    private int totalBastan = 0;
    private int totalBwl = 0;
    private int totalAr = 0;
    private EditTeamModel mm;


    public void EditTeamPlayersApi(RecyclerView rr, Context cc, String role) {
        List<ModelClass> list;
        rr.setHasFixedSize(true);
        rr.setLayoutManager(new LinearLayoutManager(cc));
        list = new ArrayList<>();
        RequestQueue rq = Volley.newRequestQueue(cc);
        Matchid = teamId;
        c_i_d = comId;
        id = ID;
        Toast.makeText(cc, ">>>" + id, Toast.LENGTH_SHORT).show();

        SharedPreferences preferences = cc.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("TOKEN", null);//second parameter default value.
        String tokenName = "Bearer " + retrivedToken;

        getDummyData(cc, role);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://rest.entitysport.com/v2/competitions/" + cid + "/squads/" + Matchid + "?token=de7cddfd6309f89136ca5c4f68aaff99", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("ok")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("response");
                        JSONArray jsonArray = jsonObject1.getJSONArray("squads");
//                        JSONObject team=jsonObject.getJSONObject("team");
//                        String teamA=team.getString("logo_url");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            JSONArray jsonArray1 = jsonObject2.getJSONArray("players");
                            for (int j = 0; j < jsonArray1.length(); j++) {
                                JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                                String title = jsonObject3.getString("title");
                                String shortname = jsonObject3.getString("short_name");
                                String country = jsonObject3.getString("country");
                                String id = jsonObject3.getString("pid");
                                boolean check = findPlayer(findData, id);
                                String role1 = jsonObject3.getString("playing_role");
                                String pts = jsonObject3.getString("recent_appearance");
                                String Credits = jsonObject3.getString("fantasy_player_rating");


                                if(role1.equals(role)){
                                    ModelClass mm=new ModelClass();
                                    mm.setTname(country);
                                    mm.setPts(pts);
                                    mm.setId(id);
                                    mm.setRole(role1);
                                    mm.setPname(shortname);
                                    mm.setCredits(Credits);
                                    mm.setStatus("");

                                    // EditDatabase editDatabase=new EditDatabase(cc);

                                    JSONArray jsonArray2 = jsonObject2.getJSONArray("last_match_played");
                                    for (int k = 0; k < jsonArray2.length(); k++) {
                                        JSONObject jsonObject4 = jsonArray2.getJSONObject(k);
                                        String titl = jsonObject4.getString("title");

                                        if (titl.equals(title)) {
                                            mm.setStatus("Played Last Match");
                                        }
                                    }
                                    list.add(mm);
                                    EditTeamAdapter ca = new EditTeamAdapter(cc, list);
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
                Toast.makeText(cc, "Error :-" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
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


    }

    private void getDummyData(Context context, String role) {


        SharedPreferences preferences = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("TOKEN", null);//second parameter default value.
        String tokenName = "Bearer " + retrivedToken;
        String url = CheckConnection.BASE_URL + "/api/user/team/temp/details/" + id;

        try {

            RequestQueue rq = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
//                        Toast.makeText(getContext(), "dd"+status, Toast.LENGTH_SHORT).show();


                        if (status.equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");


                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                                JSONArray findData = jsonObject2.getJSONArray("player11");

                                JSONObject totalCount = jsonObject2.getJSONObject("role_count");
                                String twk = totalCount.getString("wk");
                                String tBat = totalCount.getString("bat");
                                String tall = totalCount.getString("all");
                                String tBowl = totalCount.getString("bowl");
                                mm = new EditTeamModel();

                                mm.setWk(Integer.parseInt(twk));
                                mm.setBat(Integer.parseInt(tBat));
                                mm.setAr(Integer.parseInt(tall));
                                mm.setBwl(Integer.parseInt(tBowl));


                                for (int j = 0; j < findData.length(); j++) {
                                    JSONObject jsonObject3 = findData.getJSONObject(j);

                                    String playerID = jsonObject3.getString("pid");
                                    String playername = jsonObject3.getString("name");
                                    String playerRole = jsonObject3.getString("role");
                                    String credit = jsonObject3.getString("credit");
                                    String ppt = jsonObject3.getString("point");
                                    String captin = jsonObject3.getString("captain");
                                    String vc = jsonObject3.getString("vcaptain");
                                    String teamName = "";
                                    SelectedData.getSelectedData().getData().put(playerID, new SelectedData.data(playerID, playername, teamName, playerRole, credit));

                                }


                            }

                            SharedPreferences sp = context.getSharedPreferences("Counts", Context.MODE_PRIVATE);






                            SharedPreferences.Editor editor1 = sp.edit();
                            editor1.putInt("Key", SelectedData.getSelectedData().getPlayerCount());
                            editor1.putInt("wKey", SelectedData.getSelectedData().getRoleCount("wk"));
                            editor1.putInt("bKey", SelectedData.getSelectedData().getRoleCount("bat"));
                            editor1.putInt("aKey", SelectedData.getSelectedData().getRoleCount("all"));
                            editor1.putInt("bwlKey", SelectedData.getSelectedData().getRoleCount("bowl"));
                            editor1.putFloat("creditPoints", SelectedData.getSelectedData().getCreditPoints());
//               editor1.clear();
                            editor1.apply();


                            wkQ=String.valueOf(SelectedData.getSelectedData().getRoleCount("wk"));


                            Log.e("kjkk",SelectedData.getSelectedData().getRoleCount("bowl")+"");
                            Log.e("kjkk",wkQ+"");


                        }
                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, "Error " + error, Toast.LENGTH_SHORT).show();


                }
            }) {
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
            Toast.makeText(context, "Exception e" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    private boolean findPlayer(JSONArray jsonArray1, String playerID1) {
        boolean hasMatch = false;
        try {
            for (int j = 0; j < jsonArray1.length(); j++) {
                JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                String playerID = jsonObject3.getString("pid");
                if (playerID1.equals(playerID)) {
                    hasMatch = true;
                    break;
                }
            }

        } catch (Exception ex) {
        }
        return hasMatch;
    }


}
