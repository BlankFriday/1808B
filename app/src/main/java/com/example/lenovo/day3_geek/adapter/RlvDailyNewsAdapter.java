package com.example.lenovo.day3_geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {

    private ArrayList<DailyNewsBean.StoriesBean> newList;
    private ArrayList<DailyNewsBean.TopStoriesBean> banners;
    private Context context;

    private String date;

    private int TYPE_BANNER = 0;
    private int TYPE_DATE = 1;
    private int TYPE_NEWS = 2;

    public RlvDailyNewsAdapter(ArrayList<DailyNewsBean.StoriesBean> newList, ArrayList<DailyNewsBean.TopStoriesBean> banners, Context context) {
        this.newList = newList;
        this.banners = banners;
        this.context = context;
    }

    public void setNewList(ArrayList<DailyNewsBean.StoriesBean> newList) {
        this.newList = newList;
    }

    public void setBanners(ArrayList<DailyNewsBean.TopStoriesBean> banners) {
        this.banners = banners;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_BANNER){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
            holder = new BannerHolder(inflate);
        }else if (viewType == TYPE_DATE){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_date, parent, false);
            holder = new DateHolder(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            holder = new NewsHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_BANNER){
            final BannerHolder bannerHolder = (BannerHolder) holder;
            bannerHolder.banner.setImages(banners)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(context).load(bean.getImage()).into(imageView);
                        }
                    }).start();
        }else if (viewType == TYPE_DATE){
            DateHolder dateHolder = (DateHolder) holder;
            dateHolder.tv.setText(date);
        }else {
            int newPoisition = position-1;
            NewsHolder newsHolder = (NewsHolder) holder;
            if (banners.size()>0){
                newPoisition -= 1;
            }
            newsHolder.tv_title.setText(newList.get(newPoisition).getTitle());
            Glide.with(context).load(newList.get(newPoisition).getImages().get(0)).into(newsHolder.img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (banners.size()>0){
            if (position == 0){
                return TYPE_BANNER;
            }else if (position == 1){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (banners.size()>0){
            return newList.size()+1+1;
        }else {
            return newList.size()+1;
        }
    }

    public void setTitle(String date) {
        this.date = date;
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;
        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class DateHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tv;
        public DateHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_title)
        TextView tv_title;
        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
