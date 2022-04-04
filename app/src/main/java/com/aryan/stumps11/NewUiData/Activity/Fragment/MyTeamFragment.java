package com.aryan.stumps11.NewUiData.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterBrowsePlayer;
import com.aryan.stumps11.NewUiData.Activity.Model.ModelBrowsePlayer;
import com.aryan.stumps11.R;

import java.util.ArrayList;
import java.util.List;


public class MyTeamFragment extends Fragment {

    private RecyclerView rv_trendingPlayer;
    private AdapterBrowsePlayer adapterBrowsePlayer;
    private List<ModelBrowsePlayer> listTrendingPlayer=new ArrayList<>();


    public MyTeamFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_team, container, false);


       Initialization(view);

    return view;
    }

    private void Initialization(View view) {

        rv_trendingPlayer=view.findViewById(R.id.rv_trendingPlayer);

        loadTrendingPlayerData();
    }

    private void loadTrendingPlayerData() {
        listTrendingPlayer.clear();

        listTrendingPlayer.add(new ModelBrowsePlayer("https://timesofindia.indiatimes.com/photo/69257289.cms","Rohit Sharma","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/11/28/883063-virat-kohli.jpg","Virat Kohli","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("http://p.imgci.com/db/PICTURES/CMS/263500/263541.1.jpg","Warner","Australia"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://www.jagranimages.com/images/newimg/11022020/11_02_2020-kl_rahul_100_20020530.jpg","Kl rahul","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://www.thestatesman.com/wp-content/uploads/2019/07/Mohammed-Shami.jpg","Mohammed Shami","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://i.cricketcb.com/stats/img/faceImages/9311.jpg","Jasprit Bumrah","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQEJcew_ysQM7J5E6kCuWYfaYujM5cm9ne6B-TOMiBlbYhHztfm","Andre Russell ","West indies"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://timesofindia.indiatimes.com/photo/69257289.cms","Rohit Sharma","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("http://p.imgci.com/db/PICTURES/CMS/263500/263541.1.jpg","Warner","Australia"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://www.jagranimages.com/images/newimg/11022020/11_02_2020-kl_rahul_100_20020530.jpg","Kl rahul","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://www.thestatesman.com/wp-content/uploads/2019/07/Mohammed-Shami.jpg","Mohammed Shami","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://i.cricketcb.com/stats/img/faceImages/9311.jpg","Jasprit Bumrah","India"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQEJcew_ysQM7J5E6kCuWYfaYujM5cm9ne6B-TOMiBlbYhHztfm","Andre Russell ","West indies"));
        listTrendingPlayer.add(new ModelBrowsePlayer("https://timesofindia.indiatimes.com/photo/69257289.cms","Rohit Sharma","India"));

        adapterBrowsePlayer=new AdapterBrowsePlayer(listTrendingPlayer,getActivity());
        rv_trendingPlayer.setHasFixedSize(true);
        rv_trendingPlayer.setAdapter(adapterBrowsePlayer);
        adapterBrowsePlayer.notifyDataSetChanged();

    }



}