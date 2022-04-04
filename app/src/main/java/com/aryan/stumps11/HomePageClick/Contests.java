package com.aryan.stumps11.HomePageClick;

import static com.aryan.stumps11.HomePageClick.HomePageClick.Match_id;

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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.Adapters.ContestAdapter;
import com.aryan.stumps11.ApiModel.profile.context.ContextData;
import com.aryan.stumps11.ApiModel.profile.context.ContextResponse;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.aryan.stumps11.dataPassing.DataPassringInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contests extends Fragment  {
//    String Matchid;
    private String url,Matchid;
    private RecyclerView rr;
    private List<ContextData> contextDataList;
    private ContestAdapter contestAdapter;
    private ImageView imgNoContext;
    private ProgressDialog progressDialog;


    public Contests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contests, container, false);

        imgNoContext=view.findViewById(R.id.context_image_view);
        progressDialog=new ProgressDialog(getContext());

        Matchid=Match_id;

        if (Matchid!=null){

            rr=view.findViewById(R.id.ContestView);
            rr.setHasFixedSize(true);
            rr.setLayoutManager(new LinearLayoutManager(getContext()));
//        List<ModelClass>list=new ArrayList<>();
            contextDataList=new ArrayList<>();

            contextList();

            Log.e("jfgkjk",Matchid+"");

        }
        return view;
    }

    private void contextList(){
        progressDialog.setTitle("Stumps11");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        Log.e("fjgn",tokenName+"");

       // String match_id  = preferences.getString("MATCH_ID",null);//second parameter default value.
     //   Toast.makeText(getContext(), "match "+match_id, Toast.LENGTH_SHORT).show();

        try{
           // CheckConnection.api.getContest(tokenName,"53281").enqueue(new Callback<ContextResponse>() {
           // CheckConnection.api.getContest(tokenName,"52094").enqueue(new Callback<ContextResponse>() {
           CheckConnection.api.getContest(tokenName,Matchid).enqueue(new Callback<ContextResponse>() {
                @Override
                public void onResponse(Call<ContextResponse> call, Response<ContextResponse> response) {

                    if (response.isSuccessful()){
                        contextDataList=response.body().getContextData();

                        if (contextDataList.isEmpty() || contextDataList.equals(0) || contextDataList==null){
                            imgNoContext.setVisibility(View.VISIBLE);
                            progressDialog.dismiss();


                        }else{
                            contestAdapter=new ContestAdapter(getContext(),contextDataList);
                            rr.setAdapter(contestAdapter);
                            progressDialog.dismiss();


                        }


           //             Toast.makeText(getContext(), "Sucees "+response.body().getContextData(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error ", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ContextResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Toast.makeText(getContext(), "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}