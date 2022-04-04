package com.aryan.stumps11.HomePageClick;

import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.Adapters.MyTeamAdapter;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyDummyTeamData;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyDummyTeamPlayer11;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyDummyTeamRes;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyTeamDummyModel;
import com.aryan.stumps11.CreateTeam.ChooseCaptainandVC;
import com.aryan.stumps11.CreateTeam.DataBase;
import com.aryan.stumps11.HomePageClick.db.MyTeamModel;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.firebase.messaging.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


public class MyTeams extends Fragment {

    private RecyclerView myRecyclerview;
    private DataBase myTeamDb;
    private Cursor myTeamCursor;
    private List<MyDummyTeamPlayer11> myTeamList;
    private String mobile;
    private String pname,prole,C,vc,pid,matchId,credit;
    private MyTeamAdapter myTeamAdapter;
    private List<MyTeamDummyModel> myTeamDummyModelList;
    private ImageView imgNoMatch;
    private ProgressDialog progressDialog;
    private MyTeamDummyModel myTeamDummyModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_teams, container, false);
        myRecyclerview=view.findViewById(R.id.my_team_recyclerview);
        imgNoMatch=view.findViewById(R.id.my_teams_no_match);
        myRecyclerview.setHasFixedSize(true);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        myTeamList=new ArrayList<>();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String MATCHID=Match_id;
        Log.e("opop",MATCHID+"");
        getDummyData();

    return view;
    }

    private void getDummyData(){
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        String url=CheckConnection.BASE_URL+"/api/user/team/temp/list?match_id="+Match_id;
        myTeamDummyModel=new MyTeamDummyModel();
        myTeamAdapter=new MyTeamAdapter(myTeamDummyModelList,getContext());
        try{
//            progressDialog.dismiss();
            myTeamDummyModelList=new ArrayList<>();
            RequestQueue rq= Volley.newRequestQueue(getContext());
            StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
//                        Toast.makeText(getContext(), "dd"+status, Toast.LENGTH_SHORT).show();
                        if (status.equals("200")){
                            JSONArray jsonArray= jsonObject.getJSONArray("data1");
                            if(jsonArray.length()==0){
                                imgNoMatch.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                                String teamId= jsonObject2.getString("teamId");
                                String userId=jsonObject2.getString("_id");
                                String cid=jsonObject2.getString("cid");
                                myTeamDummyModel.setTeamID(teamId);
                                myTeamDummyModel.setUserId(userId);
                                myTeamDummyModel.setCid(cid);
                                JSONObject rule=jsonObject2.getJSONObject("roles");
                                String totalWk=rule.getString("wk");
                                String totalBat=rule.getString("bat");
                                String totalAll=rule.getString("all");
                                String totalBowl=rule.getString("bowl");
                                myTeamDummyModel.setTotalWk(totalWk);
                                myTeamDummyModel.setTotalBat(totalBat);
                                myTeamDummyModel.setTotalBwl(totalBowl);
                                myTeamDummyModel.setTotalAR(totalAll);
                                JSONArray jsonArray1=jsonObject2.getJSONArray("vcaptain");
                                for (int j=0; j<jsonArray1.length(); j++) {
                                    JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                                    String vcName = jsonObject3.getString("name");
                                    myTeamDummyModel.setVcName(vcName);
                                }
                                JSONArray jsonArray2=jsonObject2.getJSONArray("captain");
                                for (int k=0; k<jsonArray2.length(); k++){
                                    JSONObject object=jsonArray2.getJSONObject(k);
                                    String cName=object.getString("name");
                                    myTeamDummyModel.setcName(cName);
                                }

                                myTeamDummyModelList.add(myTeamDummyModel);
                                myTeamAdapter=new MyTeamAdapter(myTeamDummyModelList,getContext());
                                myRecyclerview.setAdapter(myTeamAdapter);
                                progressDialog.dismiss();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Error "+error, Toast.LENGTH_SHORT).show();
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


        }catch (Exception e){
            Toast.makeText(getContext(), "Exception e"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}