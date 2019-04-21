package com.example.lenovo.day3_geek.widget;

/**
 * @author xts
 *         Created by asus on 2019/4/19.
 */

public interface TouchCallBack {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);

    //删除条目
    void onItemDelete(int position);
}
