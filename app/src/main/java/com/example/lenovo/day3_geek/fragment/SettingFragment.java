package com.example.lenovo.day3_geek.fragment;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;

public class SettingFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new EmptyPresenter();
    }
}
