package com.example.lenovo.day3_geek.fragment;
//卫晨旭  1808D
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.ZhiHuDailyNewsVpAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;

//卫晨旭  1808D
//142726200005181279
public class ZhiHuDailyNewsFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<Integer> tabs;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        initTabs();
        initFragment();

        ZhiHuDailyNewsVpAdapter vpAdapter = new ZhiHuDailyNewsVpAdapter(getChildFragmentManager(), fragments, getContext(), tabs);
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new DailyNewsFragment());
        fragments.add(new SectionsFragment());
        fragments.add(new HotFragment());
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add(R.string.dailyNews);
        tabs.add(R.string.sections);
        tabs.add(R.string.hot);
    }
}
