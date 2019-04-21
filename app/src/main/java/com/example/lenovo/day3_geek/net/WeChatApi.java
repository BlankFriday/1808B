package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.WeChatBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WeChatApi {
    //http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
    public String Url = "http://api.tianapi.com/wxnew/";
    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10")
    Observable<WeChatBean> getData(@Query("page")int page);

    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10")
    Observable<WeChatBean> getCondData(@QueryMap Map<String,Object> map);
}
