package com.example.lenovo.day3_geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.day3_geek.base.BaseFragment;

import java.util.ArrayList;

public class VpGankAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> titles;

    public VpGankAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
