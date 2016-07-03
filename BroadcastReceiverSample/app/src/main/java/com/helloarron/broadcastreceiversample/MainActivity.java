package com.helloarron.broadcastreceiversample;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNormal, btnOrdered, btnAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNormal = (Button) findViewById(R.id.normal_btn);
        btnOrdered = (Button) findViewById(R.id.ordered_btn);
        btnAsync = (Button) findViewById(R.id.async_btn);

        btnNormal.setOnClickListener(this);
        btnOrdered.setOnClickListener(this);
        btnAsync.setOnClickListener(this);

        /**
         * 动态注册级别高于在Manifest中的静态注册
         * 将Manifest中intentFilter的priority属性去掉观察效果
         */
        // IntentFilter intentFilter = new IntentFilter("BCR_Normal");
        // BCRTestNormal2 test2 = new BCRTestNormal2();
        // registerReceiver(test2, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_btn: {
                Intent intent = new Intent();
                intent.putExtra("msg", "这是一条普通广播");
                intent.setAction("BCR_Normal");
                sendBroadcast(intent);
                break;
            }
            case R.id.ordered_btn: {
                break;
            }
            case R.id.async_btn: {
                break;
            }
            default:
                break;
        }
    }
}
