package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.WeChatBean;
import com.example.lenovo.day3_geek.model.WeChatModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.view.WeChatView;
import com.example.lenovo.day3_geek.view.ZhiHuDailyNewsView;

public class WeChatPresenter extends BasePresenter<WeChatView> {

    private WeChatModel weChatModel;

    @Override
    protected void initModel() {
        weChatModel = new WeChatModel();
        models.add(weChatModel);
    }

    public void getData(int page){
        if (weChatModel!=null){
            weChatModel.getData(new ResultCallBack<WeChatBean>() {
                @Override
                public void onSuccess(WeChatBean bean) {
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
            },page);
        }
    }
    public void getCondData(int page,String word){
        if (weChatModel!=null){
            weChatModel.getCondData(page, word, new ResultCallBack<WeChatBean>() {
                @Override
                public void onSuccess(WeChatBean bean) {
                    if (mView!=null){
                        mView.setCondData(bean);
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
}
