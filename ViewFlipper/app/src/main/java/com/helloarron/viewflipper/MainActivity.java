package com.helloarron.viewflipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStatic, btnDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStatic = (Button) findViewById(R.id.static_btn);
        btnDynamic = (Button) findViewById(R.id.dynamic_btn);

        btnStatic.setOnClickListener(this);
        btnDynamic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.static_btn:
                startActivity(new Intent(MainActivity.this, StaticVFActivity.class));
                break;
            case R.id.dynamic_btn:
                break;
            default:
                break;
        }
    }
}
