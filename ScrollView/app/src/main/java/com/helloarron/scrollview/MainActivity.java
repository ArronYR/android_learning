package com.helloarron.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUp, btnDown;
    private TextView content;
    private ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUp = (Button) findViewById(R.id.up);
        btnDown = (Button) findViewById(R.id.down);
        content = (TextView) findViewById(R.id.content);
        scroll = (ScrollView) findViewById(R.id.scrollView);

        content.setText(getResources().getString(R.string.content));
        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        /**
                         * getScrollY() -- 滚动条滑动的距离
                         * getMeasuredHeight() -- 获取控件的总高度
                         * getHeight() -- 获取控件在屏幕中的高度
                         */

                        // 顶部状态
                        if (scroll.getScrollY() <= 0) {
                            Log.i("Main", "顶部");
                        }

                        // 底部状态
                        if (scroll.getChildAt(0).getMeasuredHeight() <= scroll.getScrollY() + scroll.getHeight()) {
                            Log.i("Main", "滑动到底部");
                            content.append(getResources().getString(R.string.content));
                        }
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up:
                break;
            case R.id.down:
                break;
            default:
                break;
        }
    }
}
