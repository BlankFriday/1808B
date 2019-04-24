package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;
import com.example.lenovo.day3_geek.bean.GankBean;

public interface GankView extends BaseView {
    void setData(GankBean bean);
    void onFail(String s);
}
