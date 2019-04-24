package com.example.lenovo.day3_geek.model;

import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.FuLiBean;
import com.example.lenovo.day3_geek.net.ApiService;
import com.example.lenovo.day3_geek.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelFareModel extends BaseModel {
    public void getData(int page, final ResultCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.mUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FuLiBean> data = apiService.getData(page+"");
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuLiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(FuLiBean bean) {

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
