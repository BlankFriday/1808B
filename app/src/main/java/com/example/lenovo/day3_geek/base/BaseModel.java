package com.example.lenovo.day3_geek.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseModel {
    //容器
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void onDestory(){
        //观察者与被观察者断开联系
        compositeDisposable.clear();
    }
}
