package com.helloarron.bezier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by arron on 16/4/14.
 */
public class BezierView extends View {

    private static final float POINTWIDTH = 1.0f;
    private static final String TAG = "Bezier";
    private Paint mPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;

    // 辅助点
    private Point assistPoint;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPath = new Path();
        startPoint = new Point(150, 300);
        endPoint = new Point(450, 300);
        assistPoint = new Point(300, 450);
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画笔颜色
        mPaint.setColor(Color.BLUE);
        // 笔宽
        mPaint.setStrokeWidth(POINTWIDTH);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
        // 重置路径
        mPath.reset();
        // 起点
        mPath.moveTo(startPoint.x, startPoint.y);
        // 重要的就是这句
        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
        // 画路径
        canvas.drawPath(mPath, mPaint);
        // 画辅助点
        canvas.drawPoint(assistPoint.x, assistPoint.y, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
            case MotionEvent.ACTION_MOVE :
                assistPoint.x = (int) event.getX();
                assistPoint.y = (int) event.getY();
                Log.i(TAG, "assistPoint.x = " + assistPoint.x);
                Log.i(TAG, "assistPoint.Y = " + assistPoint.y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                assistPoint.set(300, 450);
                break;
        }
        return true;
    }
}
