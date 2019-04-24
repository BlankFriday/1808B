package com.example.lenovo.day3_geek.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.activity.CalendarActivity;
import com.example.lenovo.day3_geek.activity.MaterialActivity;
import com.example.lenovo.day3_geek.adapter.RlvDailyNewsAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.example.lenovo.day3_geek.bean.Student;
import com.example.lenovo.day3_geek.presenter.DailyNewsPresenter;
import com.example.lenovo.day3_geek.view.DailyNewsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class DailyNewsFragment extends BaseFragment <DailyNewsView,DailyNewsPresenter> implements DailyNewsView{
    private static final String TAG = "DailyNewsFragment";
    @BindView(R.id.recv)
    RecyclerView mRecv;
    @BindView(R.id.fab_bt)
    FloatingActionButton fab_bt;
    private RlvDailyNewsAdapter adapter;
    private ArrayList<DailyNewsBean.StoriesBean> newList;
    private ArrayList<DailyNewsBean.TopStoriesBean> banners;


    private ArrayList<DailyNewsBean.StoriesBean> newCalendarList;
    private ArrayList<DailyNewsBean.TopStoriesBean> calendarbanners;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected DailyNewsPresenter initPresenter() {
        return new DailyNewsPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this)==true) {
            EventBus.getDefault().register(this);
        }
//        EventBus.getDefault().register(this);
        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));
        newList = new ArrayList<>();
        banners = new ArrayList<>();

        newCalendarList = new ArrayList<>();
        calendarbanners = new ArrayList<>();

        adapter = new RlvDailyNewsAdapter(newList, banners, getContext());
        mRecv.setAdapter(adapter);

        fab_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CalendarActivity.class));
            }
        });

        adapter.setItemOnClick(new RlvDailyNewsAdapter.ItemOnClick() {
            @Override
            public void onClick(int position, DailyNewsBean.StoriesBean bean) {
                startActivity(new Intent(getContext(),MaterialActivity.class));
                EventBus.getDefault().postSticky(bean);
            }
        });
    }

    @Override
    public void setData(DailyNewsBean bean) {
        adapter.setTitle("今日新闻");
        newList.addAll(bean.getStories());
        banners.addAll(bean.getTop_stories());
        adapter.setBanners(banners);
        adapter.setNewList(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void calendarData(DailyNewsBean bean) {
        calendarbanners.clear();
        newCalendarList.clear();
        banners.clear();
        newList.clear();
        if (bean!=null&&bean.getStories()!=null) {
            if (bean.getTop_stories()!=null) {

                calendarbanners.addAll(bean.getTop_stories());
                adapter.setBanners(calendarbanners);
            }
            String date = bean.getDate();
            int i = Integer.parseInt(date);
            adapter.setTitle(i+1+"");
            newCalendarList.addAll(bean.getStories());

            adapter.setNewList(newCalendarList);

            adapter.notifyDataSetChanged();
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void bibi(Student str){
        Log.e(TAG, "bibi: "+str.name.toString() );
        mPresenter.calendarData("before/"+str.name);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop:");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        EventBus.getDefault().unregister(this);
    }
}
