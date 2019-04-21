package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.HotBean;
import com.example.lenovo.day3_geek.model.HotModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.view.HotView;

public class HotPresenter extends BasePresenter<HotView> {

    private HotModel hotModel;

    @Override
    protected void initModel() {
        hotModel = new HotModel();
        models.add(hotModel);

    }
    public void getData(){
        if (hotModel!=null){
            hotModel.getData(new ResultCallBack<HotBean>() {
                @Override
                public void onSuccess(HotBean bean) {
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
}
