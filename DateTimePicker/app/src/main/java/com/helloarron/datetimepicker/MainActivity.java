package com.helloarron.datetimepicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNormalType, btnDialogType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNormalType = (Button) findViewById(R.id.normal_type_btn);
        btnDialogType = (Button) findViewById(R.id.dialog_type_btn);

        btnNormalType.setOnClickListener(this);
        btnDialogType.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_type_btn:
                startActivity(new Intent(this, NormalTypeActivity.class));
                break;
            case R.id.dialog_type_btn:
                startActivity(new Intent(this, DialogTypeActivity.class));
                break;
        }
    }
}
