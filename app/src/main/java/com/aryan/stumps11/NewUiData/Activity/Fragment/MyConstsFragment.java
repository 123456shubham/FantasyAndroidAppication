package com.aryan.stumps11.NewUiData.Activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterMyContas;
import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterUpComingMatches;
import com.aryan.stumps11.NewUiData.Activity.Model.modelupcomingMatch;
import com.aryan.stumps11.R;

import java.util.ArrayList;
import java.util.List;


public class MyConstsFragment extends Fragment {
   private AdapterMyContas adapterMyContas;
   List<modelupcomingMatch>upcomingMatchesList=new ArrayList<>();
   private RecyclerView rv_UpComingMatch;

    public MyConstsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upcoming_match, container, false);
    
    Initialization(view);
    
        return  view;
    
    }

    private void Initialization(View view) {

        rv_UpComingMatch=view.findViewById(R.id.rv_UpComingMatch);



        LoadUpComingMatchData();



    }

    private void LoadUpComingMatchData() {
        upcomingMatchesList.clear();


        upcomingMatchesList.add(new modelupcomingMatch("Pakistan One Day","UpComing","SL","Pak","1","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU","https://image.shutterstock.com/image-illustration/pakistan-flag-waving-fabric-texture-260nw-1405892870.jpg"));
        upcomingMatchesList.add(new modelupcomingMatch("Pakistan One Day","UpComing","SL","Pak","1","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU","https://image.shutterstock.com/image-illustration/pakistan-flag-waving-fabric-texture-260nw-1405892870.jpg"));
        upcomingMatchesList.add(new modelupcomingMatch("Pakistan One Day","UpComing","SL","Pak","1","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU","https://image.shutterstock.com/image-illustration/pakistan-flag-waving-fabric-texture-260nw-1405892870.jpg"));
        upcomingMatchesList.add(new modelupcomingMatch("Pakistan One Day","UpComing","SL","Pak","1","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY_hVLYK-HmPeDhUaEu_F4oQ1OTzGj3AHshw&usqp=CAU","https://image.shutterstock.com/image-illustration/pakistan-flag-waving-fabric-texture-260nw-1405892870.jpg"));




        adapterMyContas=new AdapterMyContas(upcomingMatchesList,getActivity());
        rv_UpComingMatch.setAdapter(adapterMyContas);
        adapterMyContas.notifyDataSetChanged();



    }
}