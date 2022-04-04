package com.aryan.stumps11.CreateTeam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class EditTabAdapter extends FragmentPagerAdapter {

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Fragment>arrayList1=new ArrayList<>();
    public EditTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public int getCount() {
        return arrayList1.size();
    }
    public void EditFrag(String tittle,Fragment fragment){
        arrayList1.add(fragment);
        arrayList.add(tittle);
        //arrayList.add(MatchId);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
