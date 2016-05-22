package com.helloarron.viewflipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by arron on 16/5/22.
 */
public class DynamicVFActivity extends AppCompatActivity {

    private ViewFlipper flipper;
    private int[] resId = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_vf);

        flipper = (ViewFlipper) findViewById(R.id.dm_flipper);

        // 动态导入的方式为ViewFlipper添加子View
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }

        // 为ViewFlipper添加动画效果
        flipper.setInAnimation(this, R.anim.left_in);
        flipper.setOutAnimation(this, R.anim.left_out);
        // 设置ViewFlipper动画时间
        flipper.setFlipInterval(3000);
        // 开始动画
        flipper.startFlipping();
    }

    private ImageView getImageView(int id) {
        ImageView image = new ImageView(this);
        // 大小随图片大小
        // image.setImageResource(id);
        image.setBackgroundResource(id);

        return image;
    }

}
