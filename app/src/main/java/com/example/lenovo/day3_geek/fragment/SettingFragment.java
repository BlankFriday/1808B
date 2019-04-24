package com.example.lenovo.day3_geek.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.activity.MainActivity;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.Constants;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.utils.SpUtil;
import com.example.lenovo.day3_geek.utils.UIModeUtil;
import com.example.lenovo.day3_geek.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SettingFragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView, View.OnClickListener {
    @BindView(R.id.cb_cache)
    CheckBox cbCache;
    @BindView(R.id.cb_img)
    CheckBox cbImg;
    @BindView(R.id.cb_night)
    CheckBox cbNight;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;
    @BindView(R.id.ll_clear)
    LinearLayout llClear;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected void initView() {
        int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO){
            //判断当前是日间模式
            cbNight.setChecked(false);
        }else {
            cbNight.setChecked(true);
        }
    }

    @Override
    protected void initListener() {
        cbNight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_night:
                if (cbNight.isChecked()){
                    //切换并保存模式
                    UIModeUtil.changeModeUI((MainActivity) getActivity());
                    //保存当前碎片type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,MainActivity.TYPE_SETTING);
                }
                break;
        }
    }
}
