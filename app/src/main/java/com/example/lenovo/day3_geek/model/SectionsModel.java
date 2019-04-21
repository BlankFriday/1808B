package com.example.lenovo.day3_geek.model;

import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.example.lenovo.day3_geek.bean.SectBean;
import com.example.lenovo.day3_geek.net.DailyNewsApi;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.net.SectionsAPI;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SectionsModel extends BaseModel {
    public void getData(final ResultCallBack<SectBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SectionsAPI.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SectionsAPI sectionsAPI = retrofit.create(SectionsAPI.class);
        Observable<SectBean> data = sectionsAPI.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SectBean sectBean) {
                        if (sectBean!=null){
                            if (sectBean.getData()!=null&&sectBean.getData().size()>0){
                                callBack.onSuccess(sectBean);
                            }else {
                                callBack.onFail("失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
