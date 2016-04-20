package com.helloarron.imagetransform.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.helloarron.imagetransform.R;

/**
 * Created by arron on 16/4/20.
 */
public class RoundRectXfermodeView extends View {

    private Bitmap mBitmap, mOut;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundRectXfermodeView(Context context) {
        super(context);
        initView();
    }

    public RoundRectXfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoundRectXfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        // 禁用硬件加速
        setLayerType(LAYER_TYPE_HARDWARE, null);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mOut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mOut);

        // 首先绘制圆角矩形
        // API 21
        // canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 50f, 50f, mPaint);
        // 兼容其他
        canvas.drawRoundRect(new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight()), 50, 50, mPaint);

        // 使用Xfermode
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mOut, 0 , 0, null);
    }
}
