package com.example.lenovo.day3_geek.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.bean.GoldShowBean;

import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 *         在viewpager不需要的Fragment需要销毁时,生命周期不一样,
 *         FragmentPagerAdapter:onDestoryView()
 *         FragmentStatePagerAdapter:onDetach();取消关联
 */

public class VpGoldAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldShowBean> mTitles;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public VpGoldAdapter(FragmentManager fm,
                         ArrayList<BaseFragment> fragments,
                         ArrayList<GoldShowBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;

        for (int i = 0; i < mTitles.size(); i++) {
            GoldShowBean bean = mTitles.get(i);
            if (bean.isChecked){
                mNewTitles.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
