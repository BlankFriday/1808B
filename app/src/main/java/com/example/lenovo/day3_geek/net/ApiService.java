package com.example.lenovo.day3_geek.net;

import com.example.lenovo.day3_geek.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(@Field("username")String name,
                                @Field("password")String psd);
}
