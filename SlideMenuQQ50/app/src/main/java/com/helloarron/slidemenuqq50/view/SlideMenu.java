package com.helloarron.slidemenuqq50.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.helloarron.slidemenuqq50.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by arron on 16/4/12.
 */
public class SlideMenu extends HorizontalScrollView {

    private LinearLayout mWrapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;
    private int mMenuWidth;

    // dp
    private int mMenuRightPadding = 50;

    private boolean once = false;
    private boolean isOpen = false;

    public SlideMenu(Context context) {
        this(context, null);
    }

    /**
     * 未使用自定义属性时，调用
     * @param context
     * @param attrs
     */
    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 当使用了自定义属性时，调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlideMenu, defStyleAttr, 0);

        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.SlideMenu_rightPadding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
    }

    /**
     * 设置子View的宽和高
     * 设置自己的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once){
            mWrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWrapper.getChildAt(0);
            mContent = (ViewGroup) mWrapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;

            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量，将menu隐藏
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed){
            this.scrollTo(mMenuWidth, 0);
        }
    }

    /**
     * touch事件
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX >= mMenuWidth/2){
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                }else {
                    this.smoothScrollTo(0,0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动发生时
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        float scale = l*1.0f / mMenuWidth;
        float rightScale = 0.7f + 0.3f * scale;
        float leftScale = 1.0f - 0.3f * scale;
        float leftAlpha = 1.0f - 0.4f * scale;

        // 调用属性动画，设置TranslationX
        ViewHelper.setTranslationX(mMenu, l*0.7f);
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);
        ViewHelper.setAlpha(mMenu, leftAlpha);

        // 设置内容区域缩放中心店，缩放
        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight()/2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);

        // 设置菜单区域缩放
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen) return;
        this.smoothScrollTo(0,0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (!isOpen) return;
        this.smoothScrollTo(mMenuWidth,0);
        isOpen = false;
    }

    /**
     * 菜单切换
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        }else {
            openMenu();
        }
    }
}
