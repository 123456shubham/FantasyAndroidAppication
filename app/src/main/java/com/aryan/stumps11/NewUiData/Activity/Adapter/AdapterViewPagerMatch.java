package com.aryan.stumps11.NewUiData.Activity.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aryan.stumps11.NewUiData.Activity.Fragment.CompleteMatchFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.LiveMatchFragment;
import com.aryan.stumps11.NewUiData.Activity.Fragment.UpcomingMatchFragment;


public class AdapterViewPagerMatch extends FragmentPagerAdapter {
    private int numOfTabs;
    public AdapterViewPagerMatch(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new UpcomingMatchFragment();

            case 1:
                return new LiveMatchFragment();

            case 2:
                return new CompleteMatchFragment();

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
