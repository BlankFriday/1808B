package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.FuLiBean;
import com.example.lenovo.day3_geek.model.WelFareModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.view.WelfareView;

public class WelfarePre extends BasePresenter <WelfareView>{

    private WelFareModel welFareModel;

    @Override
    protected void initModel() {
        welFareModel = new WelFareModel();
        models.add(welFareModel);

    }
    public void getData(int page){
        welFareModel.getData(page, new ResultCallBack<FuLiBean>() {
            @Override
            public void onSuccess(FuLiBean bean) {
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
