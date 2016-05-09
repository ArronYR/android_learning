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
        setProgressBarIndeterminateVisibility(false);

        // 对于带进度的进度条可设置进度 Max:10000,
        // 启用时需设置setProgressBarIndeterminateVisibility(false)
        // 设置为Max时，进度条消失。常用9999设置其最大且显示
        setProgress(9999);

    }
}
