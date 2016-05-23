package com.helloarron.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
                return false;
            }
        });
    }
}
