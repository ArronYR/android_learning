package com.helloarron.broadcastreceiversample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by arron on 16/7/3.
 */
public class BCRTestNormal2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i("BCReceiver", "测试2收到消息: "+msg);
        Toast.makeText(context, "测试2收到消息: "+msg, Toast.LENGTH_SHORT).show();
        // 普通广播是不能被截断的
        // abortBroadcast();

        Bundle bundle = getResultExtras(true);
        String test = bundle.getString("test");
        Log.i("BCReceiver", "截断得到的数据结果: "+test);
        Toast.makeText(context, "截断得到的数据结果: "+test, Toast.LENGTH_SHORT).show();
    }
}
