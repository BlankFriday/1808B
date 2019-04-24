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
import com.example.lenovo.day3_geek.bean.GankBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvGankAdapter extends RecyclerView.Adapter {


    private ArrayList<GankBean.ResultsBean> newList;
    private Context context;

    public void setNewList(ArrayList<GankBean.ResultsBean> newList) {
        this.newList = newList;
    }

    public RlvGankAdapter(ArrayList<GankBean.ResultsBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_gank, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GankBean.ResultsBean bean = newList.get(position);
        VH vh = (VH) holder;
        vh.tvAuthor.setText(bean.getWho());
        vh.tvDesc.setText(bean.getDesc());
        String[] ts = bean.getPublishedAt().split("T");
        vh.tvTime.setText(ts[0]);
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
