package com.helloarron.broadcastreceiversample;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_btn: {

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
