package com.example.lenovo.day3_geek.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvSectionAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.SectBean;
import com.example.lenovo.day3_geek.presenter.SectionsPresenter;
import com.example.lenovo.day3_geek.view.SectionsView;

import java.util.ArrayList;

import butterknife.BindView;

public class SectionsFragment extends BaseFragment <SectionsView,SectionsPresenter>implements SectionsView{
    @BindView(R.id.recv_sect)
    RecyclerView mRecv;
    private RlvSectionAdapter adapter;
    private ArrayList<SectBean.DataBean> newList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected SectionsPresenter initPresenter() {
        return new SectionsPresenter();
    }

    @Override
    protected void initView() {
        mRecv.setLayoutManager(new GridLayoutManager(getContext(),2));

        newList = new ArrayList<>();
        adapter = new RlvSectionAdapter(newList, getContext());
        mRecv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void setData(SectBean bean) {
        newList.addAll(bean.getData());
        adapter.setNewList(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {

    }
}
