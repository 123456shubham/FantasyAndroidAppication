package com.aryan.stumps11.NewUiData.Activity.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aryan.stumps11.NewUiData.Activity.Fragment.CommentaryFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.CompleteMatchFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.LiveMatchFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.MyConstsFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.MyTeamFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.ScoredFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.StatusFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.UpcomingMatchFragment;


public class AdapterViewPagerMatchDetails extends FragmentPagerAdapter {
    private int numOfTabs;
    public AdapterViewPagerMatchDetails(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new MyConstsFragment();

//            case 1:
//                return new MyTeamFragment();
//
//            case 2:
//                return new CommentaryFragment();
//            case 3:
//                return new ScoredFragment();
//
//            case 4:
//                return new StatusFragment();

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
