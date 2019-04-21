package com.example.lenovo.day3_geek.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.bean.GoldShowBean;
import com.example.lenovo.day3_geek.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvShowAdapter extends RecyclerView.Adapter implements TouchCallBack {
    private ArrayList<GoldShowBean> newList;

    public void setNewList(ArrayList<GoldShowBean> newList) {
        this.newList = newList;
    }

    public RlvShowAdapter(ArrayList<GoldShowBean> newList) {
        this.newList = newList;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        final GoldShowBean bean = newList.get(position);
        vh.mTv.setText(bean.getTitle());
        vh.mSc.setChecked(bean.isChecked);
        vh.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(newList,fromPosition,toPosition);
        //局部刷新,索引混乱
        notifyItemMoved(fromPosition,toPosition);

    }

    @Override
    public void onItemDelete(int position) {
        newList.remove(position);
        //局部刷新,索引混乱,越界
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView mTv;
        @BindView(R.id.sc)
        SwitchCompat mSc;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
