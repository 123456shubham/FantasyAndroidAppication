package com.aryan.stumps11.EditTeam;

import static com.aryan.stumps11.EditTeam.EditTeamActivity.teamId;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryan.stumps11.R;


public class EditWkFargment extends Fragment {

    private RecyclerView rvWk;
    public   static String teamId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_wk_fargment, container, false);
        rvWk=v.findViewById(R.id.edit_wkt_recycleview);
        EditPlayer pc=new EditPlayer();
        pc.EditTeamPlayersApi(rvWk,getContext(),"wk");
        return v;
    }
}