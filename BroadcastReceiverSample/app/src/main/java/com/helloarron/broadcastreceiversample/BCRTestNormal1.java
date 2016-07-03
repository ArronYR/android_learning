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
public class BCRTestNormal1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i("BCReceiver", "测试1收到消息: " + msg);
        Toast.makeText(context, "测试1收到消息: " + msg, Toast.LENGTH_SHORT).show();

        // 广播截断，向后发送数据
        Bundle bundle = new Bundle();
        bundle.putString("test", "广播截断加入数据");
        setResultExtras(bundle);
    }
}
