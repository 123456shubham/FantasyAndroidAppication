package com.aryan.stumps11.Winners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.Adapters.WinnersAdapter;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.More.MoreActivity;
import com.aryan.stumps11.MyMatches.MyMatches;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.Winners.winnerRes.WinnerData;
import com.aryan.stumps11.Winners.winnerRes.WinnerResponse;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WinnersActivity extends AppCompatActivity {
    RecyclerView wv;
    private List<WinnerData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);

        API();
        Bottom();
    }

    private void Bottom(){
        BottomNavigationView bbn=findViewById(R.id.bottomnav);
        bbn.setSelectedItemId(R.id.Winners);
        bbn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.HomePage:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.MyMatches:
                        startActivity(new Intent(getApplicationContext(), MyMatches.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.Winners:
                        return true;

                    case R.id.More:
                        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }


                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomePage.class));
        overridePendingTransition(0,0);
        finish();
    }

    private void API(){
        wv=findViewById(R.id.WinnerView);
        wv.setLayoutManager(new GridLayoutManager(WinnersActivity.this,2));
        wv.setHasFixedSize(true);

        Dialog dialog=new Dialog(WinnersActivity.this);
        dialog.setContentView(R.layout.progressbar);
        dialog.show();
        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
       // List<ModelClass>list=new ArrayList<>();

        SharedPreferences preferences = WinnersActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        //String tokenName="Bearer "+retrivedToken;
        String tokenName="bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MjE4NzIwYjgzOTgyYzU5YjIxMzhhMzgiLCJwaG9uZSI6Ijg1NzEwNTY0MjYiLCJpYXQiOjE2NDY5MDM3NTIsImV4cCI6MTY1MjEwOTY1Mn0.vfTzxZAaz_QePf16h-he637vqs3-tpM15kqNmWskcjM";


        list=new ArrayList<>();

        try{
            CheckConnection.api.getWinnerList(tokenName).enqueue(new Callback<WinnerResponse>() {
                @Override
                public void onResponse(Call<WinnerResponse> call, Response<WinnerResponse> response) {
                    if (response.isSuccessful()){
                        dialog.hide();
                        list=response.body().getWinnerData();

                        if (list.isEmpty() || list.equals(0) || list==null){


                            Toast.makeText(WinnersActivity.this,"No Data Found  ", Toast.LENGTH_LONG).show();

                        }else{
                            WinnersAdapter winnersAdapter=new WinnersAdapter(WinnersActivity.this,list);
                            winnersAdapter.notifyDataSetChanged();
                            wv.setAdapter(winnersAdapter);

                        }
                    }else{

                        Toast.makeText(WinnersActivity.this,"Auth Token Error ", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<WinnerResponse> call, Throwable t) {

                    Toast.makeText(WinnersActivity.this, "onFailure " +t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){

            e.printStackTrace();

            Toast.makeText(WinnersActivity.this,"Exception "+e.getMessage(),Toast.LENGTH_LONG).show();

        }








//
//        RequestQueue rq= Volley.newRequestQueue(WinnersActivity.this);
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://www.stumps11.com/APIS/Apis.asmx/Winners", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                dialog.hide();
//                try {
//                    JSONArray jsonArray=new JSONArray(response);
//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject=jsonArray.getJSONObject(i);
//                    String wamt=jsonObject.getString("wineramount");
//                    String wusername=jsonObject.getString("winnerusername");
//                    String wstate=jsonObject.getString("winnerstate");
//                    ModelClass mm=new ModelClass();
//                    list.add(mm);
//                    mm.setWinneramount(wamt);
//                    mm.setWinnerState(wstate);
//                    mm.setWinnerUsername(wusername);
//                    WinnersAdapter wa=new WinnersAdapter(WinnersActivity.this,list);
//                    wv.setAdapter(wa);
//                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        rq.add(stringRequest);
    }
}