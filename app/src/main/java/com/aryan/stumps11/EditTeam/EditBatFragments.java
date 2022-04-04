package com.aryan.stumps11.EditTeam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryan.stumps11.R;

public class EditBatFragments extends Fragment {

    private RecyclerView editRvBat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_bat_fragments, container, false);
        editRvBat=v.findViewById(R.id.edit_bat_recyclerview);
        EditPlayer pc=new EditPlayer();
        pc.EditTeamPlayersApi(editRvBat,getContext(),"bat");
        return v;
    }
}