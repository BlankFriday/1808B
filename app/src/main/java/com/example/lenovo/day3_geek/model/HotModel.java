package com.example.lenovo.day3_geek.model;

import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.HotBean;
import com.example.lenovo.day3_geek.bean.SectBean;
import com.example.lenovo.day3_geek.net.HotApi;
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

public class HotModel extends BaseModel {
    public void getData(final ResultCallBack<HotBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HotApi.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HotApi hotApi = retrofit.create(HotApi.class);
        Observable<HotBean> data = hotApi.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        if (hotBean!=null){
                            if (hotBean.getRecent()!=null&&hotBean.getRecent().size()>0){
                                callBack.onSuccess(hotBean);
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
