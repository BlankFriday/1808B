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
import com.example.lenovo.day3_geek.bean.SectBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvSectionAdapter extends RecyclerView.Adapter<RlvSectionAdapter.ViewHolder> {
    private ArrayList<SectBean.DataBean> newList;
    private Context context;

    public RlvSectionAdapter(ArrayList<SectBean.DataBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_sections, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_desc.setText(newList.get(position).getDescription());
        holder.tv_name.setText(newList.get(position).getName());
        Glide.with(context).load(newList.get(position).getThumbnail()).into(holder.img_set);
    }

    public void setNewList(ArrayList<SectBean.DataBean> newList) {
        this.newList = newList;
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_set)
        ImageView img_set;
        @BindView(R.id.tv_desc)
        TextView tv_desc;
        @BindView(R.id.tv_name)
        TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
