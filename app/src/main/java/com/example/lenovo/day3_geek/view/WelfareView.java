package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;
import com.example.lenovo.day3_geek.bean.FuLiBean;

public interface WelfareView extends BaseView {
    void setData(FuLiBean bean);
    void onFail(String s);
}
