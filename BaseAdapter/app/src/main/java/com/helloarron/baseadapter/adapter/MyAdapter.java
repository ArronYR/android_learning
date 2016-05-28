package com.helloarron.baseadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.helloarron.baseadapter.R;
import com.helloarron.baseadapter.bean.ItemBean;

import java.util.List;

/**
 * Created by arron on 16/5/28.
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<ItemBean> itemBeanList) {
        this.mList = itemBeanList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 方式一
         */
        /*
        View view = mInflater.inflate(R.layout.item, null);
        ImageView image = (ImageView) view.findViewById(R.id.iv_image);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView content = (TextView) view.findViewById(R.id.tv_content);
        ItemBean bean = mList.get(position);
        image.setImageResource(bean.ItemImageResId);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);
        return view;
        */

        /**
         * 方式二
         */
        /*
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item, null);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView content = (TextView) convertView.findViewById(R.id.tv_content);
        ItemBean bean = mList.get(position);
        image.setImageResource(bean.ItemImageResId);
        title.setText(bean.ItemTitle);
        content.setText(bean.ItemContent);
        return convertView;
        */

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item, null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean bean = mList.get(position);
        viewHolder.image.setImageResource(bean.ItemImageResId);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);

        return convertView;

    }

    class ViewHolder{
        public ImageView image;
        public TextView title;
        public TextView content;
    }
}
