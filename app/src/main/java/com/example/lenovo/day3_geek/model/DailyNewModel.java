package com.example.lenovo.day3_geek.model;

import android.util.Log;

import com.example.lenovo.day3_geek.base.BaseModel;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.example.lenovo.day3_geek.net.DailyNewsApi;
import com.example.lenovo.day3_geek.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyNewModel extends BaseModel {
    public void getData(final ResultCallBack<DailyNewsBean> callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyNewsApi.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DailyNewsApi dailyNewsApi = retrofit.create(DailyNewsApi.class);
        Observable<DailyNewsBean> data = dailyNewsApi.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        if (dailyNewsBean!=null){
                            if (dailyNewsBean.getStories()!=null&&dailyNewsBean.getTop_stories()!=null){
                                callBack.onSuccess(dailyNewsBean);
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

    public void calendarDate(final ResultCallBack<DailyNewsBean> callBack, String s){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyNewsApi.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DailyNewsApi dailyNewsApi = retrofit.create(DailyNewsApi.class);
        Observable<DailyNewsBean> data = dailyNewsApi.calendarData(s);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        if (dailyNewsBean!=null){
                            if (dailyNewsBean.getStories()!=null){
                                Log.i("mm",dailyNewsBean.getStories().toString());
                                callBack.onSuccess(dailyNewsBean);
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
