package com.aryan.stumps11.CreateTeam;

import static com.aryan.stumps11.CreateTeam.CreateTeams.MATCH_ID;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WK extends Fragment  {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_w_k, container, false);

        String matchid=MATCH_ID;

        Log.e("llkl",matchid+"");


       RecyclerView rr=view.findViewById(R.id.RecyclerView);
       PlayersClass pc=new PlayersClass();
       pc.PlayersApi(rr,getContext(),"wk");
       return view;
    }
}