package com.example.lenovo.day3_geek.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.VpGankAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;

public class GankListFragment extends BaseFragment<EmptyView,EmptyPresenter> implements EmptyView {
    @BindView(R.id.tab_gank)
    TabLayout mTab;
    @BindView(R.id.vp_gank)
    ViewPager mVp;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> titles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ganl_list;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        fragments.add(new AndriodFragment());
        fragments.add(new IOSFragment());
        fragments.add(new FrontFragment());
        fragments.add(new WelfareFragment());

        titles.add("Android");
        titles.add("iOS");
        titles.add("前端");
        titles.add("福利");

        VpGankAdapter adapter = new VpGankAdapter(getChildFragmentManager(), fragments, titles);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);

    }
}
