package com.aryan.stumps11.NewUiData.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryan.stumps11.NewUiData.Activity.Adapter.AdapterHighlightsCommentary;
import com.aryan.stumps11.NewUiData.Activity.Model.ModelHighlightCommentary;
import com.aryan.stumps11.R;

import java.util.ArrayList;
import java.util.List;

public class CommentaryFragment extends Fragment {

    private RecyclerView rv_highlightCommentary;
    private AdapterHighlightsCommentary adapterHighlightsCommentary;
    private List<ModelHighlightCommentary> commentaryList=new ArrayList<>();


    public CommentaryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_commentary, container, false);

        Initialization(view);

    return view;
    }

    private void Initialization(View view) {

        rv_highlightCommentary=view.findViewById(R.id.rv_highlightCommentary);

        loadCommentaryData();


    }

    private void loadCommentaryData() {
        commentaryList.clear();

        commentaryList.add(new ModelHighlightCommentary("5.3","6","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));
        commentaryList.add(new ModelHighlightCommentary("10.3","4","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));
        commentaryList.add(new ModelHighlightCommentary("15.3","w","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));
        commentaryList.add(new ModelHighlightCommentary("25.3","f","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));
        commentaryList.add(new ModelHighlightCommentary("28.3","H","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));
        commentaryList.add(new ModelHighlightCommentary("29.3","6","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));
        commentaryList.add(new ModelHighlightCommentary("50.3","6","Neesham to Rahul, SIX, you can't complain much with the bowler for this one. Was indeed close to a yorker, but Rahul was so smart to switch sides and reverse-guided it over short third man"));


        adapterHighlightsCommentary=new AdapterHighlightsCommentary(commentaryList, getActivity());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv_highlightCommentary.setHasFixedSize(true);
        rv_highlightCommentary.setAdapter(adapterHighlightsCommentary);
        rv_highlightCommentary.setLayoutManager(linearLayoutManager);
        adapterHighlightsCommentary.notifyDataSetChanged();

    }


}