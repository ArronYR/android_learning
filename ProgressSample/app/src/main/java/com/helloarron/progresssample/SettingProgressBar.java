package com.helloarron.progresssample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by arron on 16/5/9.
 */
public class SettingProgressBar extends AppCompatActivity implements View.OnClickListener {

    /**
     * 关键属性:
     *
     * android:max="1000"                   最大显示进度
     * android:progress="80"                第一显示进度
     * android:secondaryProgress="200"      第二显示进度
     * android:indeterminate="true"         设置是否精确显示
     */

    /**
     * 关键方法：
     * <p/>
     * setProgress(int);                    设置第一进度
     * setSecondaryProgress(int);           设置第二进度
     * getProgress();                       获取第一进度
     * getSecondaryProgress();              获取第二进度
     * incrementProgressBy(int);            增加或减少第一进度
     * incrementSecondaryProgressBy(int);   增加或减少第二进度
     * getMax();                            获取最大进度
     */

    private ProgressBar progressBar;
    private Button btnAdd, btnReduce, btnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        progressBar = (ProgressBar) findViewById(R.id.pb_horizontal);
        btnAdd = (Button) findViewById(R.id.add);
        btnReduce = (Button) findViewById(R.id.reduce);
        btnReset = (Button) findViewById(R.id.reset);

        btnAdd.setOnClickListener(this);
        btnReduce.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                break;
            case R.id.reduce:
                break;
            case R.id.reset:
                break;
            default:
                break;
        }
    }
}
