package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.GankBean;
import com.example.lenovo.day3_geek.model.GankModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.view.GankView;
import com.example.lenovo.day3_geek.view.ZhiHuDailyNewsView;

public class GankPresenter extends BasePresenter<GankView> {

    private GankModel gankModel;

    @Override
    protected void initModel() {
        gankModel = new GankModel();
        models.add(gankModel);
    }
    public void getData(String tech,int page){
        gankModel.getData(tech, page, new ResultCallBack<GankBean>() {
            @Override
            public void onSuccess(GankBean bean) {
                if (mView!=null){
                    mView.setData(bean);
                }
            }

            @Override
            public void onFail(String s) {
                if (mView!=null){
                    mView.onFail(s);
                }
            }
        });
    }
}
