package com.helloarron.progresssample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by arron on 16/5/12.
 */
public class CustomProgressBar extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private Button btnAdd, btnReduce, btnReset;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        progressBar = (ProgressBar) findViewById(R.id.custom_progressBar);
        tvResult = (TextView) findViewById(R.id.tv_custom_result);

        btnAdd = (Button) findViewById(R.id.custom_add);
        btnReduce = (Button) findViewById(R.id.custom_reduce);
        btnReset = (Button) findViewById(R.id.custom_reset);

        btnAdd.setOnClickListener(this);
        btnReduce.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        setTextViewContent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_add: {
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                setTextViewContent();
                break;
            }
            case R.id.custom_reduce: {
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                setTextViewContent();
                break;
            }
            case R.id.custom_reset: {
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(80);
                setTextViewContent();
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
