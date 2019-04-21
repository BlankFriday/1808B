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
import com.example.lenovo.day3_geek.bean.WeChatBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvWeChatAdapter extends RecyclerView.Adapter<RlvWeChatAdapter.ViewHolder> {
    private ArrayList<WeChatBean.NewslistBean> newList;
    private Context context;

    public RlvWeChatAdapter(ArrayList<WeChatBean.NewslistBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_wechat, parent, false);
        return new ViewHolder(inflate);
    }

    public void setNewList(ArrayList<WeChatBean.NewslistBean> newList) {
        this.newList = newList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeChatBean.NewslistBean bean = newList.get(position);
        Glide.with(context).load(bean.getPicUrl()).into(holder.img_wechat);
        holder.tv_ctime.setText(bean.getCtime());
        holder.tv_description.setText(bean.getDescription());
        holder.tv_title.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wechat_title)
        TextView tv_title;
        @BindView(R.id.img_wechat)
        ImageView img_wechat;
        @BindView(R.id.tv_ctime)
        TextView tv_ctime;
        @BindView(R.id.tv_description)
        TextView tv_description;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
