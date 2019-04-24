package com.example.lenovo.day3_geek.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvV2exAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.base.BasePresenter;
import com.example.lenovo.day3_geek.bean.V2exBean;
import com.example.lenovo.day3_geek.presenter.EmptyPresenter;
import com.example.lenovo.day3_geek.view.EmptyView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class V2exListFragment extends BaseFragment<EmptyView,EmptyPresenter> implements EmptyView {
    private static final String TAG = "V2exListFragment";
    private String mUrl = "https://www.v2ex.com";
    private ArrayList<V2exBean> newList;

    private String linkHref;

    @BindView(R.id.recv_v2ex)
    RecyclerView mRecv;
    private RlvV2exAdapter adapter;
    private String text;

    @SuppressLint("ValidFragment")
    public V2exListFragment(String linkHref) {
        this.linkHref = linkHref;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex_list;
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
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

                Document document = null;
                try {
                    document = Jsoup.connect(mUrl+linkHref).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements items = document.select("div.cell.item");
                for (Element element: items) {
                    //图片
                    Element img = element.select("table tbody tr td > a > img.avatar").first();
                    String src = img.attr("src");
                    //Log.i(TAG, "图片: "+src);

                    //评论,有可能没有,需要判空
                    Element comment = element.select("table tbody tr td > a.count_livid").first();
                    if (comment!=null){
                        commentCount = comment.text();
                        String href = comment.attr("href");
                        //Log.i(TAG, "评论数量: "+ commentCount +",href"+href);
                    }

                    //新闻主题信息
                    Element titleEl = element.select("table tbody tr td span.item_title > a").first();
                    if (titleEl!=null){
                        //标题
                        text = titleEl.text();
                        // Log.i(TAG, "标题: "+text);
                    }

                    //评论信息
                    Elements topicEl = element.select("table tbody tr td span.topic_info");
                    String topic = topicEl.text();
                   // Log.i(TAG, "评论信息: "+topic);
                    String[] split = topic.split("•");
                    if (split.length == 4){
                        str = split[2]+"•"+split[3];
                    }
                    //Log.e(TAG, "run: "+str);

                    Element secondTab = topicEl.select("a.node").first();
                    String tab = secondTab.text();//二类tab
                   // Log.i(TAG, "二类tab: "+text1);

                    Elements people = topicEl.select("strong > a");
                    //作者
                    if (people.size()>0){
                        Element authorEl = people.get(0);
                        author = authorEl.text();
                        //Log.i(TAG, "作者: "+ author);
                    }
                    //评论人
                    if (people.size()>1){
                        Element commentPeopleEl = people.get(1);
                        commentPeople = commentPeopleEl.text();
                        //Log.i(TAG, "评论人: "+ commentPeople);
                    }
                    V2exBean v2exBean = new V2exBean(src, author, text, commentCount, tab, str);
                    newList.add(v2exBean);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setNewList(newList);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void initView() {
        //数据源
        newList = new ArrayList<>();

        adapter = new RlvV2exAdapter(newList, getContext());
        mRecv.setAdapter(adapter);
        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
