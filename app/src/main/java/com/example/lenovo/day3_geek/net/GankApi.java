package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.GankBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {
    //http://gank.io/api/data/Android/20/1
    public String Url = "http://gank.io/api/data/";
    @GET("{tech}/20/{page}")
    Observable<GankBean> getData(@Path("tech")String tech,@Path("page")int page);

}
