package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.LoginBean;

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String s);
}
