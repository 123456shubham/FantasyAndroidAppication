package com.aryan.stumps11.EditTeam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryan.stumps11.CreateTeam.PlayersClass;
import com.aryan.stumps11.R;

public class EditAllRounderFragments extends Fragment {

    private RecyclerView rcAllRounder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_all_rounder_fragments, container, false);
        rcAllRounder=v.findViewById(R.id.edit_all_rounder_fragments);
        EditPlayer pc=new EditPlayer();
        pc.EditTeamPlayersApi(rcAllRounder,getContext(),"all");
        return v;
    }
}