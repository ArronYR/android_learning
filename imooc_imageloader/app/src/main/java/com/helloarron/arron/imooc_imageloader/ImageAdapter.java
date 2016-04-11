package com.helloarron.arron.imooc_imageloader;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.helloarron.arron.imooc_imageloader.util.ImageLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 图片适配器
 */
public class ImageAdapter extends BaseAdapter {

    private static Set<String> mSelectedImgs = new HashSet<String>();

    private String mDirPath;
    private List<String> mImagePaths;
    private LayoutInflater mInflater;

    public ImageAdapter(Context context, List<String> mDatas, String dirPath){
        this.mDirPath = dirPath;
        this.mImagePaths = mDatas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mImagePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return mImagePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImg = (ImageView) convertView.findViewById(R.id.id_item_image);
            viewHolder.mSelect = (ImageButton) convertView.findViewById(R.id.id_item_select);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 重置状态
        viewHolder.mImg.setImageResource(R.drawable.pictures_no);
        viewHolder.mSelect.setImageResource(R.drawable.picture_unselected);

        ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(mDirPath+"/"+mImagePaths.get(position), viewHolder.mImg);

        final String filePath = mDirPath+"/"+mImagePaths.get(position);
        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedImgs.contains(filePath)){
                    mSelectedImgs.remove(filePath);
                    viewHolder.mImg.setColorFilter(null);
                    viewHolder.mSelect.setImageResource(R.drawable.picture_unselected);
                }else{
                    mSelectedImgs.add(filePath);
                    viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
                    viewHolder.mSelect.setImageResource(R.drawable.pictures_selected);
                }
                // notifyDataSetChanged();
            }
        });

        if (mSelectedImgs.contains(filePath)){
            viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
            viewHolder.mSelect.setImageResource(R.drawable.pictures_selected);
        }else{
            viewHolder.mImg.setColorFilter(null);
        }

        return convertView;
    }

    private class ViewHolder {
        private ImageView mImg;
        private ImageButton mSelect;
    }

}