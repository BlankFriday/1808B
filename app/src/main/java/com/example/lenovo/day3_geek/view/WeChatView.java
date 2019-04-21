package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;
import com.example.lenovo.day3_geek.bean.SectBean;
import com.example.lenovo.day3_geek.bean.WeChatBean;

public interface WeChatView extends BaseView {
    void setData(WeChatBean bean);
    void onFail(String s);

    void setCondData(WeChatBean bean);
}
