package com.example.lenovo.day3_geek.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvNodeAdapter;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.bean.FlowBean;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;

public class NodeActivity extends BaseActivity<EmptyView,EmptyPresenter>implements EmptyView {
    private String mUrl = "https://www.v2ex.com";
    private String TAG = "NodeActivity";

    private ArrayList<FlowBean> newList;

    @BindView(R.id.recv_node)
    RecyclerView mRecv;
    private RlvNodeAdapter adapter;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_node;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {

            private FlowBean flowBean;

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements elements = doc.select("div.cell");

                    for (Element element :elements) {
                        Elements select = element.select("table tbody tr td span.fade");
                        String text = select.text();

                        Elements select1 = element.select("td > a[href]");
                        String text1 = select1.text();
                        if (text.length()>0&&text1.length()>3){
                            flowBean = new FlowBean(text,text1);
                            Log.d(TAG, "r: "+text1);
                            newList.add(flowBean);
                        }

                    }

                    Elements inner = doc.select("div.inner").select("table tbody tr td span.fade");
                    String text = inner.text();
                    Log.e(TAG, "run: "+text);

                    Elements select = doc.select("div.inner").select("table tbody tr td a[href]");
                    String text1 = select.text();
                    Log.d(TAG, "run: "+text1);
                    FlowBean flowBean = new FlowBean(text,text1);
                    newList.add(flowBean);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NormalDecoration decoration = new NormalDecoration() {
                                @Override
                                public String getHeaderName(int i) {
                                    return newList.get(i).getTitle();
                                }
                            };
                            mRecv.addItemDecoration(decoration);
                            adapter.setList(newList);
                            adapter.notifyDataSetChanged();
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
        newList = new ArrayList<>();

        mRecv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvNodeAdapter(this, newList);
        mRecv.setAdapter(adapter);
    }

}
