package com.helloarron.viewflipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by arron on 16/5/22.
 */
public class StaticVFActivity extends AppCompatActivity {

    private ViewFlipper flipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_vf);

        flipper = (ViewFlipper) findViewById(R.id.st_flipper);

        // 为ViewFlipper添加动画效果
        flipper.setInAnimation(this, R.anim.left_in);
        flipper.setOutAnimation(this, R.anim.left_out);

        // 设置ViewFlipper动画时间
        flipper.setFlipInterval(3000);

        // 开始动画
        flipper.startFlipping();
    }
}
