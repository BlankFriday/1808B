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
import com.example.lenovo.day3_geek.bean.HotBean;
import com.example.lenovo.day3_geek.bean.SectBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvHotAdapter extends RecyclerView.Adapter<RlvHotAdapter.ViewHolder> {
    private ArrayList<HotBean.RecentBean> newList;
    private Context context;

    public RlvHotAdapter(ArrayList<HotBean.RecentBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_hot, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_hot.setText(newList.get(position).getTitle());
        Glide.with(context).load(newList.get(position).getThumbnail()).into(holder.img_hot);
    }

    public void setNewList(ArrayList<HotBean.RecentBean> newList) {
        this.newList = newList;
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_hot)
        ImageView img_hot;
        @BindView(R.id.tv_hot_title)
        TextView tv_hot;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
