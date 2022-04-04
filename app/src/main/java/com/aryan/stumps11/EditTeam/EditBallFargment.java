package com.aryan.stumps11.EditTeam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryan.stumps11.R;

public class EditBallFargment extends Fragment {

    private RecyclerView rvBall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_ball_fargment, container, false);
        rvBall=view.findViewById(R.id.edit_ball_recyclerview);
        EditPlayer pc=new EditPlayer();
        pc.EditTeamPlayersApi(rvBall,getContext(),"bowl");
        return view;
    }
}