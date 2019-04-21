package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.example.lenovo.day3_geek.bean.HotBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HotApi {
    //http://news-at.zhihu.com/api/4/news/latest
    public String Url = "http://news-at.zhihu.com/api/4/";
    @GET("news/hot")
    Observable<HotBean> getData();
}
