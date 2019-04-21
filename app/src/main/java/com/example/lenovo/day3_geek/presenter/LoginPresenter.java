package com.example.lenovo.day3_geek.presenter;

import android.text.TextUtils;

import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.LoginBean;
import com.example.lenovo.day3_geek.model.LoginModel;
import com.example.lenovo.day3_geek.net.ResultCallBack;
import com.example.lenovo.day3_geek.utils.Logger;
import com.example.lenovo.day3_geek.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {
    private String TAG = "LoginPresenter";
    private LoginModel loginModel;
    public void getData(){
        //获取网络数据
        String data = "网络请求的数据";
        if (mView != null){
            //每次转换类型麻烦
            mView.setData(data);
        }
    }
    public void login(){
        String userName = mView.getUserName();
        String psd = mView.getPsd();
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(psd)){
            mView.showToast("账号或密码不能为空");
            return;
        }

        loginModel.login(userName, psd, new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (bean!=null){
                    Logger.logD(TAG,bean.toString());
                    if (bean.getCode() == 200){
                        //防止页面销毁,数据返回后设置页面的空指针
                        if (mView!=null){
                            mView.showToast("登录成功");
                        }
                    }else {
                        if (mView!=null){
                            mView.showToast("登陆失败");
                        }
                    }
                }
            }

            @Override
            public void onFail(String s) {
                Logger.logD(TAG,s);
                if (mView!=null){
                    mView.showToast("登录失败");
                }
            }
        });
    }

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
        models.add(loginModel);
    }
}
