package com.aryan.stumps11.ContestClick;

import static com.aryan.stumps11.ContestClick.ContestClick.TeamID;

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
import android.widget.Toast;

import com.aryan.stumps11.Adapters.RankAdapter;
import com.aryan.stumps11.ApiModel.profile.rank.RankData;
import com.aryan.stumps11.ApiModel.profile.rank.RankDetails;
import com.aryan.stumps11.ApiModel.profile.rank.RankResponse;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Winnings extends Fragment {

    private RecyclerView recyclerView;
    private List<RankDetails> rankDetails;
    private RankAdapter rankAdapter;
    private String url,Matchid,teamId;




    public Winnings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_winnings, container, false);
        recyclerView=view.findViewById(R.id.wining_recyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rankDetails=new ArrayList<>();
//        Matchid=MATCH_ID;
        teamId=TeamID;

        getAllRank();
        return view;
    }

    private void getAllRank(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;

        try{

//            CheckConnection.api.getRankResponse(tokenName,"6220602ead14667ac9ed8a49").enqueue(new Callback<List<RankData>>() {
//                @Override
//                public void onResponse(Call<List<RankData>> call, Response<List<RankData>> response) {
//                    if (response.isSuccessful()){
//                        Toast.makeText(getContext(), "su", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getContext(), "err", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<RankData>> call, Throwable t) {
//                    Toast.makeText(getContext(), "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });

            Log.e("Shubham Match Id ",teamId);

            CheckConnection.api.getRankResponse(tokenName,teamId).enqueue(new Callback<RankResponse>() {
                @Override
                public void onResponse(Call<RankResponse> call, Response<RankResponse> response) {
                    if (response.isSuccessful()){

                        assert response.body() != null;
//                        Toast.makeText(getContext(),"123e "+response.body().getRankData().getId().toString(),Toast.LENGTH_LONG).show();


                        rankDetails=response.body().getRankData().getRankDetailsList();
                        if (rankDetails==null){
                            Toast.makeText(getContext(),"No Item Here ",Toast.LENGTH_LONG).show();

                        }else{

                            rankAdapter=new RankAdapter(rankDetails,getContext());
                            rankAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(rankAdapter);

//                            Toast.makeText(getContext(),"SUCCESS",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getContext(),"Error :-",Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<RankResponse> call, Throwable t) {
                    Toast.makeText(getContext(),"OnFailure : "+t.getMessage(),Toast.LENGTH_LONG).show();

                }
            });

        }catch(Exception e){
            Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }
}