package com.helloarron.datetimepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by arron on 16/4/23.
 */
public class DialogTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDate, btnTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_type);

        btnDate = (Button) findViewById(R.id.date_btn);
        btnTime = (Button) findViewById(R.id.time_btn);

        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_btn:
                break;
            case R.id.time_btn:
                break;
        }
    }
}
