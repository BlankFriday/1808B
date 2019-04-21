package com.example.lenovo.day3_geek.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NodeActivity extends BaseActivity<EmptyView,EmptyPresenter>implements EmptyView {
    private String mUrl = "https://www.v2ex.com";
    private String TAG = "NodeActivity";

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
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements elements = doc.select("div.cell");

                    for (Element element :elements) {
                        Elements select = element.select("table tbody tr td span.fade");
                        String text = select.text();
                        Log.e(TAG, "run: "+text);

                        Elements select1 = element.select("table tbody tr td a.href");
                        for (Element s :
                                select1) {
                            String text1 = s.text();
                            Log.d(TAG, "item: "+text1);
                        }


                    }
                    Elements inner = doc.select("div.inner").select("table tbody tr td span.fade");
                    String text = inner.text();
                    Log.e(TAG, "run: "+text);





                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
