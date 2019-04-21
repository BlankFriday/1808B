package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.example.lenovo.day3_geek.model.DailyNewModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.view.DailyNewsView;

public class DailyNewsPresenter extends BasePresenter<DailyNewsView> {

    private DailyNewModel dailyNewModel;

    @Override
    protected void initModel() {
        dailyNewModel = new DailyNewModel();
        models.add(dailyNewModel);
    }
    public void getData(){
        if (dailyNewModel!=null){
            dailyNewModel.getData(new ResultCallBack<DailyNewsBean>() {
                @Override
                public void onSuccess(DailyNewsBean bean) {
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

    public void calendarData(String date){
        if (dailyNewModel!=null){
            dailyNewModel.calendarDate(new ResultCallBack<DailyNewsBean>() {
                @Override
                public void onSuccess(DailyNewsBean bean) {
                    if (mView!=null){
                        mView.calendarData(bean);
                    }
                }

                @Override
                public void onFail(String s) {
                    if (mView!=null){
                        mView.onFail(s);
                    }
                }
            },date);
        }
    }
}
