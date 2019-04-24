package com.example.lenovo.day3_geek.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.img_mater)
    ImageView imgMater;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String TAG = "MaterialActivity";

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_material;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        setSupportActionBar(toolbar);
        //滑动偏移监听事件/
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: "+verticalOffset);
            }
        });

        //标题必须在CollapsingToolbarLayout设置
       /* ctl.setTitle("条目介绍");*/
        //扩张时候的title颜色
        ctl.setExpandedTitleColor(getResources().getColor(R.color.c_blue));
        //收缩后显示title的颜色
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.c_white));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(DailyNewsBean.StoriesBean bean){
        ctl.setTitle(bean.getTitle());
        Glide.with(this).load(bean.getImages().get(0)).into(imgMater);
    }
}
