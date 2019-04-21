package com.example.lenovo.day3_geek.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.day3_geek.base.BaseFragment;

import java.util.ArrayList;

public class ZhiHuDailyNewsVpAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> fragments;
    private Context context;
    private ArrayList<Integer> tabs;

    public ZhiHuDailyNewsVpAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, Context context, ArrayList<Integer> tabs) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
        this.tabs = tabs;
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
        return context.getResources().getString(tabs.get(position));
    }
}
