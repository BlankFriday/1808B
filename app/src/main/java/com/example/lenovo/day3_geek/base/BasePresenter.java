package com.example.lenovo.day3_geek.base;

import com.example.lenovo.day3_geek.model.LoginModel;

import java.util.ArrayList;

public abstract class BasePresenter <V extends BaseView>{
    protected V mView;
    protected ArrayList<BaseModel> models = new ArrayList<>();
    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    public void bind(V view){
        this.mView = view;
    }
    public void onDestory(){
        mView = null;
        if (models.size()>0){
            for (BaseModel model: models) {
                model.onDestory();
            }
        }
    }
}
