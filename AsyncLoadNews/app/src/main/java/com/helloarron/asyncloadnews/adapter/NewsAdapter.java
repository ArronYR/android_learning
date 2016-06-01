package com.helloarron.asyncloadnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.helloarron.asyncloadnews.ImageLoader;
import com.helloarron.asyncloadnews.R;
import com.helloarron.asyncloadnews.bean.NewsBean;

import java.util.List;

/**
 * Created by arron on 16/6/1.
 */
public class NewsAdapter extends BaseAdapter {

    private List<NewsBean> mList;
    private LayoutInflater mInflater;

    public NewsAdapter(Context context, List<NewsBean> newsList) {
        this.mInflater = LayoutInflater.from(context);
        this.mList = newsList;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_layout, null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        // 设置Tag，标志图片，以免图片缓存加载错误
        String url = mList.get(position).newsIconUrl;
        viewHolder.ivIcon.setTag(url);
        // 使用多线程加载图片
        // new ImageLoader().showImgByThread(viewHolder.ivIcon, url);
        // 使用异步加载图片
        new ImageLoader().showImgByAsync(viewHolder.ivIcon, url);
        viewHolder.tvTitle.setText(mList.get(position).newsTitle);
        viewHolder.tvContent.setText(mList.get(position).newsContent);

        return convertView;
    }

    class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle, tvContent;
    }
}
