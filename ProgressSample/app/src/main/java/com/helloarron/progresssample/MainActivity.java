package com.helloarron.progresssample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSysNormal, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSysNormal = (Button) findViewById(R.id.sys_normal_btn);
        btnSettings = (Button) findViewById(R.id.setting_btn);

        btnSysNormal.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sys_normal_btn:
                startActivity(new Intent(this, NormalProgressBar.class));
                break;
            case R.id.setting_btn:
                startActivity(new Intent(this, SettingProgressBar.class));
                break;
            default:
                break;
        }
    }
}
