package com.helloarron.progresssample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by arron on 16/5/10.
 */
public class ProgressDialogSample extends AppCompatActivity implements View.OnClickListener {

    private Button btnShowDialog;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodialog);

        btnShowDialog = (Button) findViewById(R.id.show_dialog_btn);
        btnShowDialog.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        /**
         * progressDialog 显示风格
         */
        // 设置显示风格
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置标题
        progressDialog.setTitle("进度条对话框");
        // 这支图标
        progressDialog.setIcon(R.mipmap.ic_launcher);
        // 设置对话框中得文字
        progressDialog.setMessage("学习使用进度条对话框");

        /**
         * 设置 progressBar 属性
         */
        // 设置最大进度值
        progressDialog.setMax(100);
        // 设定初始化已经增长到的进度
        progressDialog.incrementProgressBy(50);
        // 设置进度条是否明确显示进度
        progressDialog.setIndeterminate(false);

        /**
         * 设定对话框按钮
         */
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ProgressDialogSample.this, "欢迎大家的支持", Toast.LENGTH_SHORT).show();
            }
        });
        // 是否通过返回按钮退出对话框
        progressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_dialog_btn:
                // 显示进度条对话框
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}
