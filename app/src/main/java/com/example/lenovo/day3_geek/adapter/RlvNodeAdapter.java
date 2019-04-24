package com.example.lenovo.day3_geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.bean.FlowBean;
import com.example.lenovo.day3_geek.widget.FlowLayout;

import java.util.ArrayList;

public class RlvNodeAdapter extends RecyclerView.Adapter<RlvNodeAdapter.MyVH> {
    private Context mContext;
    private ArrayList<FlowBean> list;
    private String data;

    public RlvNodeAdapter(Context mContext, ArrayList<FlowBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void setList(ArrayList<FlowBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_label, parent, false);
        return new MyVH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        if (list != null && list.size() > 0) {
            holder.fl.removeAllViews();
            TextView label = (TextView) View.inflate(mContext, R.layout.item_content, null);
            data = list.get(position).getContent();
            label.setText(data);
            holder.fl.addView(label);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {

        private final FlowLayout fl;

        public MyVH(View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.fl);
        }
    }
}
