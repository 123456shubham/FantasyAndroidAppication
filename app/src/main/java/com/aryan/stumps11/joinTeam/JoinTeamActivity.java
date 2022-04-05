package com.aryan.stumps11.joinTeam;

import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Adapters.MyTeamAdapter;
import com.aryan.stumps11.ApiModel.profile.DummyTeamResponse.MyTeamDummyModel;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinTeamActivity extends AppCompatActivity {
    private RecyclerView rvJoinTeam;
    private JoinTeamAdapter myTeamAdapter;
    private List<MyTeamDummyModel> myTeamDummyModelList;
    private ImageView imgNoMatch;
    private ProgressDialog progressDialog;
    private MyTeamDummyModel myTeamDummyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);
//        getSupportActionBar().hide();
        init();
    }
    private void init(){
        rvJoinTeam=findViewById(R.id.join_team_rv);
        rvJoinTeam.setHasFixedSize(true);
        rvJoinTeam.setLayoutManager(new LinearLayoutManager(JoinTeamActivity.this,LinearLayoutManager.VERTICAL,false));

        progressDialog=new ProgressDialog(JoinTeamActivity.this);
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String MATCHID=getIntent().getStringExtra("match_id");
        Log.e("opop",MATCHID+"");
        getDummyData();
    }

    private void getDummyData(){
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        SharedPreferences preferences = JoinTeamActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        String url= CheckConnection.BASE_URL+"/api/user/team/temp/list?match_id="+Match_id;
        myTeamDummyModel=new MyTeamDummyModel();
        myTeamAdapter=new JoinTeamAdapter(myTeamDummyModelList,JoinTeamActivity.this);
        try{
//            progressDialog.dismiss();
            myTeamDummyModelList=new ArrayList<>();
            RequestQueue rq= Volley.newRequestQueue(JoinTeamActivity.this);
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
                                myTeamAdapter=new JoinTeamAdapter(myTeamDummyModelList,JoinTeamActivity.this);
                                rvJoinTeam.setAdapter(myTeamAdapter);
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
                    Toast.makeText(JoinTeamActivity.this, "Error "+error, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(JoinTeamActivity.this, "Exception e"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}