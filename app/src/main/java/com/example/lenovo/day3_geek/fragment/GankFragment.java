package com.example.lenovo.day3_geek.fragment;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.GankPresenter;
import com.example.lenovo.day3_geek.presenter.WeChatPresenter;

public class GankFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new GankPresenter();
    }
}
