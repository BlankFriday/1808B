package com.example.lenovo.day3_geek.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.Student;
import com.example.lenovo.day3_geek.presenter.KongPresenter;
import com.example.lenovo.day3_geek.view.KongView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class CalendarActivity extends BaseActivity<KongView,KongPresenter> implements KongView, View.OnClickListener {

    private static final String TAG = "CalendarActivity";
    @BindView(R.id.img_back)
    ImageView img_Back;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.mcv)
    MaterialCalendarView mCalendar;
    private CalendarDay mDate;
    private String s;
    private int[] arr;
    private String newMonth;
    private String newDay;

    @Override
    protected KongPresenter initPresenter() {
        return new KongPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }


    @Override
    protected void initListener() {
        super.initListener();
        img_Back.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
        mCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });

    }

    private void setDate() {
        int year = mDate.getYear();
        int month = mDate.getMonth()+1;
        int day = mDate.getDay();
        //arr = new int[]{year, month, day};
        if (month < 10) {
            newMonth = "0" + month;
        } else {
            newMonth = month+"";
        }
        if (day<10){
            newDay = "0"+day;
        }else {
            newDay = day+"";
        }
        String date = year+newMonth+newDay;
        EventBus.getDefault().postSticky(new Student(date));
        Log.e(TAG, "onClick: "+date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_ok:
                setDate();
                finish();
                break;
        }
    }
}
