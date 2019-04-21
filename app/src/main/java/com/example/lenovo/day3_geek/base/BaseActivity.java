package com.example.lenovo.day3_geek.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.day3_geek.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            //直接强转不对,因为BaseActivity不作为页面展示,展示的都是他的子类,
            // 而子类必须实现BaseMvpView
            mPresenter.bind((V)this);
        }
        //找控件
        initView();
        //监听
        initListener();
        //数据
        initData();
    }

    protected abstract P initPresenter();

    protected  void initData(){

    }

    protected void initListener(){

    }

    protected void initView(){

    };

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切断v层和p层联系
        mPresenter.onDestory();
        mPresenter = null;
    }
}
