package com.example.lenovo.day3_geek.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.activity.NodeActivity;
import com.example.lenovo.day3_geek.adapter.V2exVpAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.V2exBean;
import com.example.lenovo.day3_geek.bean.V2exTabBean;
import com.example.lenovo.day3_geek.presenter.V2exPresenter;
import com.example.lenovo.day3_geek.presenter.WeChatPresenter;
import com.example.lenovo.day3_geek.view.V2exView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

public class V2exFragment extends BaseFragment<V2exView,V2exPresenter>implements V2exView, View.OnClickListener {

    private String mUrl = "https://www.v2ex.com";
    private String TAG = "V2exFragment";
    @BindView(R.id.tab_v2ex)
    TabLayout mTab;
    @BindView(R.id.vp_v2ex)
    ViewPager mVp;
    @BindView(R.id.img_node)
    ImageView img_node;
    private ArrayList<V2exTabBean> tabList;
    private ArrayList<Fragment> fragments;
    private V2exVpAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            private String commentCount;
            private String commentPeople;
            private String author;
            private String str;
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素,因为只有一个,直接调用了first()
                    Element tabs = document.select("div#Tabs").first();
                    //查找带有href属性的a元素
                    Elements allTab = tabs.select("a[href]");

                    for (Element element : allTab){
                        String linkHref = element.attr("href");
                        String tab = element.text();
                        Log.d("tag", "linkHref: "+linkHref+",tab:"+tab);
                        tabList.add(new V2exTabBean(linkHref,tab));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (V2exTabBean bean : tabList) {
                                fragments.add(new V2exListFragment(bean.getLink()));
                                mTab.addTab(mTab.newTab().setText(bean.getTab()));
                                Log.e(TAG, "run: " +bean.toString());
                            }
                            adapter.notifyDataSetChanged();
                            mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    mVp.setCurrentItem(tab.getPosition());
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                            mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initView() {
        tabList = new ArrayList<>();
        fragments = new ArrayList<>();
        adapter = new V2exVpAdapter(getChildFragmentManager(), fragments);
        mVp.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        img_node.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_node:
                startActivity(new Intent(getContext(),NodeActivity.class));
                break;
        }
    }
}
