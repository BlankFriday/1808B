package com.example.lenovo.day3_geek.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.activity.ShowActivity;
import com.example.lenovo.day3_geek.adapter.VpGoldAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.base.Constants;
import com.example.lenovo.day3_geek.bean.GoldShowBean;
import com.example.lenovo.day3_geek.presenter.GoldPresenter;
import com.example.lenovo.day3_geek.presenter.WeChatPresenter;
import com.example.lenovo.day3_geek.view.GoldView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GoldFragment extends BaseFragment<GoldView,GoldPresenter> implements GoldView {

    @BindView(R.id.vp_gold)
    ViewPager mVp;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.img_gold)
    ImageView mImg;
    private ArrayList<GoldShowBean> titles;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected void initView() {
        initTitles();
        setFragment();
    }

    private void setFragment() {
        initFragment();
        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(),
                fragments, titles);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            GoldShowBean goldShowBean = titles.get(i);
            if (goldShowBean.isChecked){
                fragments.add(new GoldDetailFragment(goldShowBean.title));
            }
        }
    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add(new GoldShowBean("前端",true));
        titles.add(new GoldShowBean("后端",true));
        titles.add(new GoldShowBean("Android",true));
        titles.add(new GoldShowBean("IOS",true));
        titles.add(new GoldShowBean("设计",true));
        titles.add(new GoldShowBean("产品",true));
        titles.add(new GoldShowBean("阅读",true));
        titles.add(new GoldShowBean("工具资源",true));
    }
    @OnClick({R.id.img_gold})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.img_gold:
                goShowActivity();
                break;
        }
    }

    private void goShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,titles);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null){
            if (requestCode == 100 && resultCode == 200){
                titles = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                //刷新界面
                setFragment();
            }
        }
    }
}
