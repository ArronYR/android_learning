package com.helloarron.broadcastreceiversample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by arron on 16/7/3.
 */
public class BCRTestSticky extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i("BCReceiver", "收到消息: " + msg);
        Toast.makeText(context, "收到消息: " + msg, Toast.LENGTH_SHORT).show();
    }
}
