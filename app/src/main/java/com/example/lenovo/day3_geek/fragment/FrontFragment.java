package com.example.lenovo.day3_geek.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.adapter.RlvGankAdapter;
import com.example.lenovo.day3_geek.base.BaseFragment;
import com.example.lenovo.day3_geek.bean.GankBean;
import com.example.lenovo.day3_geek.presenter.GankPresenter;
import com.example.lenovo.day3_geek.utils.SystemUtil;
import com.example.lenovo.day3_geek.view.GankView;

import java.util.ArrayList;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class FrontFragment extends BaseFragment<GankView, GankPresenter> implements GankView {
    @BindView(R.id.img_tech_blur)
    ImageView imgTechBlur;
    @BindView(R.id.img_tech_origin)
    ImageView imgTechOrigin;
    @BindView(R.id.tv_tech)
    TextView tvTech;
    @BindView(R.id.gank_appBar)
    AppBarLayout gankAppBar;
    @BindView(R.id.recv_gank)
    RecyclerView mRecv;
    private int page = 1;
    private ArrayList<GankBean.ResultsBean> newList;
    private RlvGankAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_front;
    }

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    public void setData(GankBean bean) {
        newList.addAll(bean.getResults());
        adapter.setNewList(newList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView() {
        newList = new ArrayList<>();
        mRecv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RlvGankAdapter(newList, getContext());
        mRecv.setAdapter(adapter);

        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(R.mipmap.vp5).apply(options).into(imgTechBlur);
        Glide.with(getContext()).load(R.mipmap.vp5).into(imgTechOrigin);

        gankAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //两张图片,后面的是高斯模糊过的,前面的是原图,滑动过程中修改原图
                //的透明度,实现效果
                ///透明度值范围,0到1,0完全透明,1完全不透明
                int imgHeight = SystemUtil.dp2px(256);
                //verticalOffset 0到-768
                //verticalOffset / imgHeight(768)范围:0到-1
                float rate = 1+verticalOffset * 2.0f / imgHeight;
                if (rate>=0){
                    imgTechOrigin.setAlpha(rate);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData("前端",page);
    }

}
