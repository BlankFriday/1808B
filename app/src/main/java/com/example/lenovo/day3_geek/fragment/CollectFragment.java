package com.example.lenovo.day3_geek.fragment;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.presenter.WeChatPresenter;

public class CollectFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new EmptyPresenter();
    }
}
