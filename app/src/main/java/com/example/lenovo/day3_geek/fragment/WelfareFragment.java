package com.example.lenovo.day3_geek.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvWelfareadapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.FuLiBean;
import com.example.lenovo.day3_geek.presenter.WeChatPresenter;
import com.example.lenovo.day3_geek.presenter.WelfarePre;
import com.example.lenovo.day3_geek.view.WeChatView;
import com.example.lenovo.day3_geek.view.WelfareView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelfareFragment extends BaseFragment<WelfareView,WelfarePre> implements WelfareView{

    @BindView(R.id.recv_welfare)
    RecyclerView mRecv;
    @BindView(R.id.sml)
    SmartRefreshLayout mSml;
    private ArrayList<FuLiBean.ResultsBean> newist;
    private RlvWelfareadapter welfareadapter;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected WelfarePre initPresenter() {
        return new WelfarePre();
    }

    @Override
    protected void initData() {
        mPresenter.getData(page);
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        newist = new ArrayList<>();

        welfareadapter = new RlvWelfareadapter(newist, getContext());
        mRecv.setAdapter(welfareadapter);
        //防止图片在上下滑动的时候移动.
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecv.setLayoutManager(manager);


        mSml.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                newist.clear();
                initData();
            }
        });
    }

    @Override
    public void setData(FuLiBean bean) {
        newist.addAll(bean.getResults());
        welfareadapter.setNewList(newist);
        welfareadapter.setImageScale();

        finishLoadMoreAndRefresh();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        finishLoadMoreAndRefresh();
    }
    private void finishLoadMoreAndRefresh() {
        mSml.finishRefresh();
        mSml.finishLoadMore();
    }
}
