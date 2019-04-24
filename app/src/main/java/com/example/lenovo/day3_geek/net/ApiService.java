package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.FuLiBean;
import com.example.lenovo.day3_geek.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(@Field("username")String name,
                                @Field("password")String psd);
    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    String mUrl = "http://gank.io/api/data/福利/20/";
    @GET
    Observable<FuLiBean> getData(@Url String page);

}
