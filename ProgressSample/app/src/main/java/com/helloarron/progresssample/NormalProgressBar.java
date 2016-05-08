package com.helloarron.progresssample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by arron on 16/5/8.
 */
public class NormalProgressBar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 启动窗口特征，启用带进度的和不带进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_normal);

        // 显示两种进度条
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
    }
}
