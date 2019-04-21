package com.example.lenovo.day3_geek.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvWeChatAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.WeChatBean;
import com.example.lenovo.day3_geek.presenter.WeChatPresenter;
import com.example.lenovo.day3_geek.view.WeChatView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class WeChatFragment extends BaseFragment <WeChatView,WeChatPresenter> implements WeChatView{
    @BindView(R.id.recv_wechat)
    RecyclerView mRecv;
    @BindView(R.id.sml_refresh)
    SmartRefreshLayout mSl_refresh;
    private int page = 1;
    private RlvWeChatAdapter adapter;
    private ArrayList<WeChatBean.NewslistBean> newList;
    private String TAG = "WeChatFragment";

    private ArrayList<WeChatBean.NewslistBean> condList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected WeChatPresenter initPresenter() {
        return new WeChatPresenter();
    }

    @Override
    protected void initView() {
        //if (EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().register(this);

        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));

        newList = new ArrayList<>();
        condList = new ArrayList<>();
        adapter = new RlvWeChatAdapter(newList, getContext());
        mRecv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData(page);
    }

    @Override
    public void setData(WeChatBean bean) {
        newList.addAll(bean.getNewslist());
        adapter.setNewList(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCondData(WeChatBean bean) {
        condList.clear();
        newList.clear();
        if (bean!=null){
            condList.addAll(bean.getNewslist());
            adapter.setNewList(condList);
        }
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getWord(String query){
        Log.d(TAG, "getWord: "+query);
        if (query!=null){
            mPresenter.getCondData(page,query);
        }
    }

    @Override
    protected void initListener() {
        mSl_refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (newList!=null){
                    initData();
                }
                mSl_refresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                newList.clear();
                if (newList!=null){
                    initData();
                }
                mSl_refresh.finishRefresh();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
