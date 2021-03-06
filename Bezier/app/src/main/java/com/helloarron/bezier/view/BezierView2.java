package com.helloarron.bezier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arron on 16/4/14.
 */
public class BezierView2 extends View {

    private static final String TAG = "BIZIER";
    private static final int LINEWIDTH = 5;
    private static final int POINTWIDTH = 10;

    private Context mContext;
    /** 即将要穿越的点集合 */
    private List<Point> mPoints = new ArrayList<>();
    /** 中点集合 */
    private List<Point> mMidPoints = new ArrayList<>();
    /** 中点的中点集合 */
    private List<Point> mMidMidPoints = new ArrayList<>();
    /** 移动后的点集合(控制点) */
    private List<Point> mControlPoints = new ArrayList<>();

    private int mScreenWidth;
    private int mScreenHeight;

    private Paint mPaint;
    private Path mPath;

    public BezierView2(Context context) {
        this(context, null);
    }

    public BezierView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mPaint = new Paint();
        mPath = new Path();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);

        getScreenParams();
        initPoints();
        initMidPoints(this.mPoints);
        initMidMidPoints(this.mMidPoints);
        initControlPoints(this.mPoints, this.mMidPoints , this.mMidMidPoints);
    }

    /**
     * 添加即将要穿越的点
     */
    private void initPoints() {
        int pointWidthSpace = mScreenWidth / 5;
        int pointHeightSpace = 100;
        for (int i = 0; i < 5; i++) {
            Point point;
            // 一高一低五个点
            if (i%2 != 0) {
                point = new Point((int) (pointWidthSpace*(i + 0.5)), mScreenHeight/2 - pointHeightSpace);
            } else {
                point = new Point((int) (pointWidthSpace*(i + 0.5)), mScreenHeight/2);
            }
            mPoints.add(point);
        }
    }

    /**
     * 初始化中点集合
     * @param points
     */
    private void initMidPoints(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            Point midPoint = null;
            if (i == points.size()-1){
                return;
            }else {
                midPoint = new Point((points.get(i).x + points.get(i + 1).x)/2, (points.get(i).y + points.get(i + 1).y)/2);
            }
            mMidPoints.add(midPoint);
        }
    }

    /**
     * 初始化中点的中点集合
     * @param midPoints
     */
    private void initMidMidPoints(List<Point> midPoints) {
        for (int i = 0; i < midPoints.size(); i++) {
            Point midMidPoint = null;
            if (i == midPoints.size()-1){
                return;
            }else {
                midMidPoint = new Point((midPoints.get(i).x + midPoints.get(i + 1).x)/2, (midPoints.get(i).y + midPoints.get(i + 1).y)/2);
            }
            mMidMidPoints.add(midMidPoint);
        }
    }

    /**
     * 初始化控制点集合
     * @param points
     * @param midPoints
     * @param midMidPoints
     */
    private void initControlPoints(List<Point> points, List<Point> midPoints, List<Point> midMidPoints) {
        for (int i = 0; i < points.size(); i ++){
            if (i ==0 || i == points.size()-1){
                continue;
            }else{
                Point before = new Point();
                Point after = new Point();
                before.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i - 1).x;
                before.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i - 1).y;
                after.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i).x;
                after.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i).y;
                mControlPoints.add(before);
                mControlPoints.add(after);
            }
        }
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenParams() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // ***********************************************************
        // *************  贝塞尔进阶--曲滑穿越已知点  *********************
        // ***********************************************************

        // 画原始点
        drawPoints(canvas);
        // 画穿越原始点的折线
        drawCrossPointsBrokenLine(canvas);
        // 画中间点
        drawMidPoints(canvas);
        // 画中间点的中间点
        drawMidMidPoints(canvas);
        // 画控制点
        drawControlPoints(canvas);
        // 画贝塞尔曲线
        drawBezier(canvas);
    }

    /**
     * 画原始点
     * @param canvas
     */
    private void drawPoints(Canvas canvas) {
        mPaint.setStrokeWidth(POINTWIDTH);
        for (int i = 0; i < mPoints.size(); i++) {
            canvas.drawPoint(mPoints.get(i).x, mPoints.get(i).y, mPaint);
        }
    }

    /**
     * 画穿越原始点的折线
     * @param canvas
     */
    private void drawCrossPointsBrokenLine(Canvas canvas) {
        mPaint.setStrokeWidth(LINEWIDTH);
        mPaint.setColor(Color.RED);
        // 重置路径
        mPath.reset();
        // 画穿越原始点的折线
        mPath.moveTo(mPoints.get(0).x, mPoints.get(0).y);
        for (int i = 0; i < mPoints.size(); i++) {
            mPath.lineTo(mPoints.get(i).x, mPoints.get(i).y);
        }
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 画中间点
     * @param canvas
     */
    private void drawMidPoints(Canvas canvas) {
        mPaint.setStrokeWidth(POINTWIDTH);
        mPaint.setColor(Color.BLUE);
        for (int i = 0; i < mMidPoints.size(); i++) {
            canvas.drawPoint(mMidPoints.get(i).x, mMidPoints.get(i).y, mPaint);
        }
    }

    /**
     * 画中间点的中间点
     * @param canvas
     */
    private void drawMidMidPoints(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        for (int i = 0; i < mMidMidPoints.size(); i++) {
            canvas.drawPoint(mMidMidPoints.get(i).x, mMidMidPoints.get(i).y, mPaint);
        }
    }

    /**
     * 画控制点
     * @param canvas
     */
    private void drawControlPoints(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        // 画控制点
        for (int i = 0; i < mControlPoints.size(); i++) {
            canvas.drawPoint(mControlPoints.get(i).x, mControlPoints.get(i).y, mPaint);
        }
    }

    /**
     * 画贝塞尔曲线
     * @param canvas
     */
    private void drawBezier(Canvas canvas) {
        mPaint.setStrokeWidth(LINEWIDTH);
        mPaint.setColor(Color.BLACK);
        // 重置路径
        mPath.reset();
        for (int i = 0; i < mPoints.size(); i++){
            if (i == 0){
                // 第一条为二阶贝塞尔
                mPath.moveTo(mPoints.get(i).x, mPoints.get(i).y);// 起点
                mPath.quadTo(mControlPoints.get(i).x, mControlPoints.get(i).y,// 控制点
                        mPoints.get(i + 1).x,mPoints.get(i + 1).y);
            }else if(i < mPoints.size() - 2){// 三阶贝塞尔
                mPath.cubicTo(mControlPoints.get(2*i-1).x,mControlPoints.get(2*i-1).y,// 控制点
                        mControlPoints.get(2*i).x,mControlPoints.get(2*i).y,// 控制点
                        mPoints.get(i+1).x,mPoints.get(i+1).y);// 终点
            }else if(i == mPoints.size() - 2){// 最后一条为二阶贝塞尔
                mPath.moveTo(mPoints.get(i).x, mPoints.get(i).y);// 起点
                mPath.quadTo(mControlPoints.get(mControlPoints.size()-1).x,mControlPoints.get(mControlPoints.size()-1).y,
                        mPoints.get(i+1).x,mPoints.get(i+1).y);// 终点
            }
        }
        canvas.drawPath(mPath,mPaint);
    }
}
