package com.example.lenovo.day3_geek.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvHotAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.HotBean;
import com.example.lenovo.day3_geek.presenter.HotPresenter;
import com.example.lenovo.day3_geek.view.HotView;

import java.util.ArrayList;

import butterknife.BindView;

public class HotFragment extends BaseFragment<HotView,HotPresenter> implements HotView{
    @BindView(R.id.recv_hot)
    RecyclerView mRecv;
    private RlvHotAdapter adapter;
    private ArrayList<HotBean.RecentBean> newList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected void initView() {
        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));

        newList = new ArrayList<>();
        adapter = new RlvHotAdapter(newList, getContext());
        mRecv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void setData(HotBean bean) {
        newList.addAll(bean.getRecent());
        adapter.setNewList(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
