package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.DailyNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DailyNewsApi {
    //http://news-at.zhihu.com/api/4/news/latest
    public String Url = "http://news-at.zhihu.com/api/4/news/";
    @GET("latest")
    Observable<DailyNewsBean> getData();

    @GET
    Observable<DailyNewsBean> calendarData(@Url String url);
}
