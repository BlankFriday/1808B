package com.example.lenovo.day3_geek.model;

import android.util.Log;

import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.WeChatBean;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.net.WeChatApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatModel extends BaseModel {

    public void getData(final ResultCallBack<WeChatBean> callBack, int page){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeChatApi.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeChatApi weChatApi = retrofit.create(WeChatApi.class);
        Observable<WeChatBean> data = weChatApi.getData(page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WeChatBean weChatBean) {

                        if (weChatBean!=null){
                            if (weChatBean.getCode() == 200){
                                callBack.onSuccess(weChatBean);
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
    public void getCondData( int page,String word,final ResultCallBack<WeChatBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeChatApi.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeChatApi weChatApi = retrofit.create(WeChatApi.class);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("word",word);
        Observable<WeChatBean> data = weChatApi.getCondData(map);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WeChatBean weChatBean) {

                        if (weChatBean!=null){
                            if (weChatBean.getCode() == 200){
                                callBack.onSuccess(weChatBean);
                                Log.d("tag", "onNext: "+weChatBean.toString());
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
