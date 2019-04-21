package com.example.lenovo.day3_geek.model;


import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.LoginBean;
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

public class LoginModel extends BaseModel {
    public void login(String name, String psd, final ResultCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        final Observable<LoginBean> login = apiService.login(name, psd);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //切断观察者与被观察着之间的联系,找到合适的时机,界面退出的时候
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        callBack.onSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
