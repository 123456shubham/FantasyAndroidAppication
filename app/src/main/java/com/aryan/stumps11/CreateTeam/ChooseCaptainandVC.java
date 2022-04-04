package com.aryan.stumps11.CreateTeam;

import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateReqData;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamReq;
import com.aryan.stumps11.ApiModel.profile.createTeam.CreateTeamResponse;
import com.aryan.stumps11.ApiModel.profile.dummyCreateRes.DummyPlayer11;
import com.aryan.stumps11.ApiModel.profile.dummyCreateRes.DummyResponse;
import com.aryan.stumps11.ApiModel.profile.elevenPlayer.ElevenPlayer11;
import com.aryan.stumps11.ApiModel.profile.elevenPlayer.ElevenPlayerRes;
import com.aryan.stumps11.Extra.CommonData;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.HomePageClick.Contests;
import com.aryan.stumps11.HomePageClick.HomePageClick;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Wallet.Wallet;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCaptainandVC extends AppCompatActivity {
    private MaterialButton bb,next;
    private String pname,prole,C,vc,pid,credit;
    private ImageView imgBacKBtn;
    private  String mobile;
    private DataBase db;
    private Cursor cc;
    private List<ModelClass> list;

    private List<CreateReqData> createReqData1;
    private String ppts;
    public static String MATCH_ID;
    private int addCreditPoint;
    private List<Integer> addCredit;
    private String userID;
    private List<ElevenPlayer11> elevenPlayer11List;
    private RecyclerView rr;
    private String captainName,viceCaptain;
    private String captainNameTrue,viceCaptainTrue;
    private static String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_captainand_v_c);
        next=findViewById(R.id.Next);
        bb=findViewById(R.id.Preview);
        imgBacKBtn=findViewById(R.id.cvcs_back_btn);

        rr=findViewById(R.id.CVC);
        rr.setHasFixedSize(false);
        rr.setLayoutManager(new LinearLayoutManager(ChooseCaptainandVC.this));
        list=new ArrayList<>();
        createReqData1=new ArrayList<>();
        elevenPlayer11List=new ArrayList<>();


        Intent intent=getIntent();
        if (intent.hasExtra(CommonData.Match_id)){

            MATCH_ID=intent.getStringExtra(CommonData.Match_id);
            Log.e("lmmklkkk",MATCH_ID+"");

        }


        if (intent.hasExtra(CommonData.C_I_D)){
            cid=intent.getStringExtra(CommonData.C_I_D);
            Log.e("cid>>>",cid);
        }


        userID=getIntent().getStringExtra("id");


        imgBacKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseCaptainandVC.this,GreenBackground.class));
                overridePendingTransition(0,0);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addPlayer11();
            }
        });




        SharedPreferences mob=getSharedPreferences("Mobile",MODE_PRIVATE);
        mobile=mob.getString("mKey","0");
        SharedPreferences sp=getSharedPreferences("MID", Context.MODE_PRIVATE);
        String mid=sp.getString("MatchID","0");
        RecyclerView rr=findViewById(R.id.CVC);
        rr.setHasFixedSize(false);
        rr.setLayoutManager(new LinearLayoutManager(ChooseCaptainandVC.this));
        list=new ArrayList<>();
        createReqData1=new ArrayList<>();
        //createReqData = new CreateReqData();

        for(SelectedData.data data:SelectedData.getSelectedData().getData().values()){
            ModelClass mm=new ModelClass();
            mm.setId(data.getId());
            mm.setPname1(data.getName());
            mm.setRole1(data.getRole());
            mm.setTname1(data.getTeam());
            mm.setPts1(data.getPoints());
            list.add(mm);
        }

        CVCAdapter ca=new CVCAdapter(list,ChooseCaptainandVC.this);
        rr.setAdapter(ca);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ChooseCaptainandVC.this, CreateTeams.class));
        CustomIntent.customType(ChooseCaptainandVC.this,"right-to-left");
        finish();
    }




    private void addPlayer11(){
        SharedPreferences preferences1 = ChooseCaptainandVC.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken1  = preferences1.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken1;

        CreateTeamReq createTeamReq=new CreateTeamReq();


        createReqData1.clear();
        for(SelectedData.data data:SelectedData.getSelectedData().getData().values()){
             CreateReqData createReqData;
             createReqData=new CreateReqData();
                createReqData.setPid(data.getId());
                createReqData.setCredit(data.getPoints());
                createReqData.setName(data.getName());
                createReqData.setRole(data.getRole());
                createReqData.setCaptain(data.isCaption());
                createReqData.setVcaptain(data.isVcaption());
                createReqData1.add(createReqData);



            }





            createTeamReq.setPlayer11(createReqData1);



        SharedPreferences CN=ChooseCaptainandVC.this.getSharedPreferences("CName",MODE_PRIVATE);

        captainName=CN.getString("CNAME",null);
        SharedPreferences VCN=ChooseCaptainandVC.this.getSharedPreferences("VCName",MODE_PRIVATE);

        viceCaptain=VCN.getString("VCName",null);
//
//        Toast.makeText(this, "sgsf "+C, Toast.LENGTH_SHORT).show();





        createTeamReq.setTeamId(MATCH_ID);
        createTeamReq.setCid(cid);


    //    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();





        try {
            CheckConnection.api.creteTeamTempData(tokenName,createTeamReq).enqueue(new Callback<DummyResponse>() {
                @Override
                public void onResponse(Call<DummyResponse> call, Response<DummyResponse> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(ChooseCaptainandVC.this, " "+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(ChooseCaptainandVC.this, HomePage.class));
                        finish();
//                             db.removeOldId();
                        db.deleteReminder();
                    }else if(response.code()==500){
                        Toast.makeText(ChooseCaptainandVC.this, "Server Error 500"+response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    }else if(response.code()==422){
                        Toast.makeText(ChooseCaptainandVC.this, "Error 422", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ChooseCaptainandVC.this, "ewrfdvn", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DummyResponse> call, Throwable t) {
                    Toast.makeText(ChooseCaptainandVC.this, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
//            Toast.makeText(this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }




        //----------------------------------------------------------------------------------------------------------------------------------------




        Log.e("lllll",createReqData1+"");


            }
        }


