package com.example.lenovo.day3_geek.view;

import com.example.lenovo.day3_geek.base.BaseView;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;

public interface DailyNewsView extends BaseView {
    void setData(DailyNewsBean bean);
    void onFail(String s);
    void calendarData(DailyNewsBean bean);
}
