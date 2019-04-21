package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;

public interface LoginView extends BaseView {
    void setData(String s);
    String getUserName();
    String getPsd();
    void showToast(String msg);
}
