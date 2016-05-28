package com.helloarron.baseadapter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.helloarron.baseadapter.bean.ItemBean;

import java.util.List;

/**
 * Created by arron on 16/5/28.
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean> itemBeanList;

    public MyAdapter(List<ItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
