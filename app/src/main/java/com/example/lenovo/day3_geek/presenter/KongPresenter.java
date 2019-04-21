package com.example.lenovo.day3_geek.presenter;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.model.KongModel;
import com.example.lenovo.day3_geek.view.KongView;

public class KongPresenter extends BasePresenter<KongView> {

    private KongModel kongModel;

    @Override
    protected void initModel() {
        kongModel = new KongModel();
        models.add(kongModel);
    }
}
