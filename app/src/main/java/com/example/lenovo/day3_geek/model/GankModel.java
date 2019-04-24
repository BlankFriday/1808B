package com.example.lenovo.day3_geek.model;

import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.GankBean;
import com.example.lenovo.day3_geek.net.GankApi;
import com.example.lenovo.day3_geek.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankModel extends BaseModel {
    public void getData(String tech, int page, final ResultCallBack<GankBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApi.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GankApi gankApi = retrofit.create(GankApi.class);
        Observable<GankBean> data = gankApi.getData(tech, page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GankBean bean) {

                        if (bean!=null){
                            if (!bean.isError()){
                                callBack.onSuccess(bean);
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
