package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.SectBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface SectionsAPI {
    //http://news-at.zhihu.com/api/4/sections
    public String Url = "http://news-at.zhihu.com/api/4/";

    @GET("sections")
    Observable<SectBean> getData();
}
