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
import com.example.lenovo.day3_geek.bean.V2exBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvV2exAdapter extends RecyclerView.Adapter {
    private ArrayList<V2exBean> newList;
    private Context context;

    public void setNewList(ArrayList<V2exBean> newList) {
        this.newList = newList;
    }

    public RlvV2exAdapter(ArrayList<V2exBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_v2ex, parent, false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        V2exBean bean = newList.get(position);
        VH vh = (VH) holder;
        vh.tv_author.setText(bean.getAuthor());
        vh.tv_comment.setText(bean.getCommentPeople());
        vh.tv_count.setText(bean.getCommentCount());
        vh.tv_tab.setText(bean.getTab());
        vh.tv_topic.setText(bean.getTopic());
        Glide.with(context).load("https:"+bean.getImg()).into(vh.img);
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }
    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.img_v2ex)
        ImageView img;
        @BindView(R.id.tv_author)
        TextView tv_author;
        @BindView(R.id.tv_comment)
        TextView tv_comment;
        @BindView(R.id.tv_commentCount)
        TextView tv_count;
        @BindView(R.id.tv_tab)
        TextView tv_tab;
        @BindView(R.id.tv_topic)
        TextView tv_topic;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
