package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;
import com.example.lenovo.day3_geek.bean.SectBean;

public interface SectionsView extends BaseView {
    void setData(SectBean bean);
    void onFail(String s);
}
