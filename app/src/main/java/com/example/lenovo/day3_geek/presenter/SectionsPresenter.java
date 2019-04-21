package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.SectBean;
import com.example.lenovo.day3_geek.model.SectionsModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.view.SectionsView;

public class SectionsPresenter extends BasePresenter<SectionsView> {

    private SectionsModel sectionsModel;

    @Override
    protected void initModel() {
        sectionsModel = new SectionsModel();
        models.add(sectionsModel);
    }

    public void getData() {
        if (sectionsModel != null) {
            sectionsModel.getData(new ResultCallBack<SectBean>() {
                @Override
                public void onSuccess(SectBean bean) {
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
