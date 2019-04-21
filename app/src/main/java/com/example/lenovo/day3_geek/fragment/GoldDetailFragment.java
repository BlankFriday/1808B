package com.example.lenovo.day3_geek.fragment;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class GoldDetailFragment extends BaseFragment <EmptyView,EmptyPresenter>implements EmptyView{

    private String title;
    @BindView(R.id.tv)
    TextView mTv;
    @SuppressLint("ValidFragment")
    public GoldDetailFragment(String title) {
        this.title = title;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected void initView() {
        mTv.setText(title);
    }
}
