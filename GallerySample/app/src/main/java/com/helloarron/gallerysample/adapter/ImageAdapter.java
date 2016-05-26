package com.helloarron.gallerysample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by arron on 16/5/26.
 */
public class ImageAdapter extends BaseAdapter {

    private int[] res;
    private Context context;

    public ImageAdapter(int[] res, Context context) {
        this.res = res;
        this.context = context;
    }

    /**
     * 返回已定义的数据源的总数量
     *
     * @return
     */
    @Override
    public int getCount() {
        // return res.length;
        // 循环播放
        return Integer.MAX_VALUE;
    }

    /**
     * 告诉适配器目前容器中的数据对象
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return res[position];
    }

    /**
     * 告诉适配器目前容器中的数据ID
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 取得目前欲显示的图像View，传入数据ID使之读取与成像
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = new ImageView(context);
        // image.setBackgroundResource(res[position]);
        // 循环播放
        image.setBackgroundResource(res[position % res.length]);
        image.setLayoutParams(new Gallery.LayoutParams(800, 600));
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        return image;
    }
}
