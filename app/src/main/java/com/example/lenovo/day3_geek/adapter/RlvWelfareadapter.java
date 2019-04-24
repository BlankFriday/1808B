package com.example.lenovo.day3_geek.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseApp;
import com.example.lenovo.day3_geek.bean.FuLiBean;
import com.example.lenovo.day3_geek.utils.SystemUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RlvWelfareadapter extends RecyclerView.Adapter {

    private ArrayList<FuLiBean.ResultsBean> newList;
    private Context context;

    public void setNewList(ArrayList<FuLiBean.ResultsBean> newList) {
        this.newList = newList;
    }

    public RlvWelfareadapter(ArrayList<FuLiBean.ResultsBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_welfare,null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FuLiBean.ResultsBean bean = newList.get(position);
        ViewHolder vh = (ViewHolder) holder;

        //在设置图片之前把ImageView的宽高重新设置一下
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vh.img.getLayoutParams();
        int imageWidth = BaseApp.mWidthPixels / 2 - SystemUtil.dp2px(8);
        layoutParams.width = imageWidth;
        if (bean.getScale() != 0){
            layoutParams.height = (int) (imageWidth/bean.getScale());
        }
        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
        vh.img.setLayoutParams(layoutParams);

        RequestOptions options = new RequestOptions()
                .transform(new RoundedCornersTransformation(SystemUtil.dp2px(6),0));
        Glide.with(context).load(bean.getUrl()).apply(options).into(vh.img);
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_welfare)
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    //计算图片的宽高比
    public void setImageScale() {
        for (final FuLiBean.ResultsBean bean :newList) {
            if (bean.getScale() == 0) {
                //未计算过宽高比
                Glide.with(context).load(bean.getUrl()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        //宽高比
                        float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                        bean.setScale(scale);
                        notifyDataSetChanged();
                    }
                });
            }else {
                notifyDataSetChanged();
            }
        }
    }
}
