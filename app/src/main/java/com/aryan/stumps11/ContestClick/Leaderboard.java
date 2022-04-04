package com.aryan.stumps11.ContestClick;

import static com.aryan.stumps11.ContestClick.ContestClick.TeamID;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.ContestClick.lederboard_details.LeadeboardData;
import com.aryan.stumps11.ContestClick.lederboard_details.LeaderBoardAdapter;
import com.aryan.stumps11.ContestClick.lederboard_details.LeaderBoardResponse;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Leaderboard extends Fragment {

    private List<LeadeboardData> list;
    private LeaderBoardAdapter leaderBoardAdapter;
    private RecyclerView recyclerViewRv;
    private String teamId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leaderboard, container, false);
        list=new ArrayList<>();
        teamId=TeamID;
        Toast.makeText(getContext(),"Team id"+teamId,Toast.LENGTH_SHORT).show();
        init(view);
        return view;
    }
    private void init(View view){

        recyclerViewRv=view.findViewById(R.id.leaderBoard_rv);
        recyclerViewRv.setHasFixedSize(true);
        recyclerViewRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;


        CheckConnection.api.getLeaderBoard(tokenName,"623049de528890c52d805e0d").enqueue(new Callback<LeaderBoardResponse>() {
            @Override
            public void onResponse(Call<LeaderBoardResponse> call, Response<LeaderBoardResponse> response) {
                if (response.isSuccessful()){
                 list=response.body().getData();
                    if (list.equals(0) || list.isEmpty() || list==null){

                        Toast.makeText(getContext(), "no more item", Toast.LENGTH_SHORT).show();

                    } else {
                        assert response.body() != null;
                        leaderBoardAdapter=new LeaderBoardAdapter(getContext(),list);
                        recyclerViewRv.setAdapter(leaderBoardAdapter);
                        leaderBoardAdapter.notifyDataSetChanged();
                    }
                }else{

                    Toast.makeText(getContext(),"Error : "+response.errorBody().toString(),Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LeaderBoardResponse> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}