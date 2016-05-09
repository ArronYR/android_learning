package com.helloarron.progresssample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        progressBar = (ProgressBar) findViewById(R.id.pb_horizontal);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btnAdd = (Button) findViewById(R.id.add);
        btnReduce = (Button) findViewById(R.id.reduce);
        btnReset = (Button) findViewById(R.id.reset);

        btnAdd.setOnClickListener(this);
        btnReduce.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        setTextViewContent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add: {
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            }
            case R.id.reduce: {
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            }
            case R.id.reset: {
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(80);
                break;
            }
            default:
                break;
        }
    }

    private void setTextViewContent() {
        int first = progressBar.getProgress();
        int second = progressBar.getSecondaryProgress();
        int max = progressBar.getMax();

        tvResult.setText("第一进度百分比: " + (int) (first / (float) max * 100) + "% ,第二进度百分比: " + (int) (second / (float) max * 100) + "%");
    }
}
