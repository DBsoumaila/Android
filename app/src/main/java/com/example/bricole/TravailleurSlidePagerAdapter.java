package com.example.bricole;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class TravailleurSlidePagerAdapter extends FragmentStatePagerAdapter {

    //List containing all fragments
    private List<Fragment> trFragmentList;

    public TravailleurSlidePagerAdapter(FragmentManager fragmentManager, List<Fragment> trFragmentList) {
        super(fragmentManager);
        this.trFragmentList = trFragmentList;
    }

    //Get a specific fragment
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return trFragmentList.get(position);
    }

    //Number of fragments
    @Override
    public int getCount() {
        return trFragmentList.size();
    }


}
