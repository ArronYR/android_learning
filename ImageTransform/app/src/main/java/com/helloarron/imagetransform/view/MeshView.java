package com.helloarron.imagetransform.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.helloarron.imagetransform.R;

/**
 * Created by arron on 16/4/21.
 */
public class MeshView extends View {

    private int width = 200;
    private int height = 200;
    private int count = (width+1)*(height+1);
    // 保存所有焦点的坐标
    private float[] verts = new float[count*2];
    private float[] orign = new float[count*2];
    private Bitmap mBitmap;

    // 增加相位
    private float K = 1;

    public MeshView(Context context) {
        super(context);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        int index = 0;
        float bmWidth = mBitmap.getWidth();
        float bmHeight = mBitmap.getHeight();

        for (int i = 0; i < height + 1; i++) {
            float fy = bmHeight*i / height;
            for (int j = 0; j < width + 1; j++) {
                float fx = bmWidth*j / width;
                orign[index*2+0] = verts[index*2+0] = fx;
                orign[index*2+1] = verts[index*2+1] = fy + 100;
                index += 1;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 改变verts
        for (int i = 0; i < height + 1; i++) {
            for (int j = 0; j < width + 1; j++) {
                verts[(i*(width+1)+j)*2 + 0] += 0;
                float offsetY = (float) Math.sin((float)j/width*2*Math.PI + K*2*Math.PI);
                verts[(i*(width+1)+j)*2 + 1] = orign[(i*(width+1)+j)*2 + 1] + offsetY*50;
            }
        }
        K += 0.1f;
        canvas.drawBitmapMesh(mBitmap, width, height, verts, 0, null, 0, null);
        invalidate();
    }
}
