package com.example.lenovo.day3_geek.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvShowAdapter;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.base.Constants;
import com.example.lenovo.day3_geek.bean.GoldShowBean;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;
import com.example.lenovo.day3_geek.widget.SimpleTouchHelperCallBack;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

public class ShowActivity extends BaseActivity<EmptyView,EmptyPresenter>implements EmptyView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recv_show)
    RecyclerView mRecv;
    private ArrayList<GoldShowBean> mList;
    private RlvShowAdapter adapter;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mList = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);

        mToolbar.setTitle(R.string.title);
        mToolbar.setNavigationIcon(R.mipmap.ic_close);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mRecv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvShowAdapter(mList);
        mRecv.setAdapter(adapter);
        mRecv.addItemDecoration(new DividerItemDecoration(ShowActivity.this,DividerItemDecoration.VERTICAL));

        //拖拽移动和侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack =
                new SimpleTouchHelperCallBack(adapter);
        simpleTouchHelperCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);
        helper.attachToRecyclerView(mRecv);
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,mList);
        setResult(200,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAct();
    }
}
