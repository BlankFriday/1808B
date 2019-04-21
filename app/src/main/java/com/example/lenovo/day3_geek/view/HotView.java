package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;
import com.example.lenovo.day3_geek.bean.HotBean;

public interface HotView extends BaseView {
    void setData(HotBean bean);
    void onFail(String s);
}
