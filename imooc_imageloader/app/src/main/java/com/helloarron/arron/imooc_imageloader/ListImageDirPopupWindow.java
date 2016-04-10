package com.helloarron.arron.imooc_imageloader;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.helloarron.arron.imooc_imageloader.bean.FolderBean;

import java.util.List;

/**
 * Created by arron on 16/4/10.
 */
public class ListImageDirPopupWindow extends PopupWindow {
    private int mWidth;
    private int mHeight;
    private View mConvertView;
    private ListView mListView;

    private List<FolderBean> mDatas;

    public ListImageDirPopupWindow(Context context, List<FolderBean> mDatas) {
        super(context);
        this.mDatas = mDatas;

        calWidthAndHeight(context);
        mConvertView = LayoutInflater.from(context).inflate(R.layout.item_popup_main, null);

        setContentView(mConvertView);
        setWidth(mWidth);
        setHeight(mHeight);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE){
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        
        initViews();
        initEvent();
    }

    private void initViews() {
        mListView = (ListView) mConvertView.findViewById(R.id.id_list_dir);
    }

    private void initEvent() {
    }

    /**
     * 计算popupWindow的宽度和高度
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mWidth = outMetrics.widthPixels;
        mHeight = (int) (outMetrics.widthPixels * 0.7);
    }
}
