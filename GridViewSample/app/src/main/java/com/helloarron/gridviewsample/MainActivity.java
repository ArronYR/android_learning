package com.helloarron.gridviewsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnShowSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowSample = (Button) findViewById(R.id.show_sample_btn);
        btnShowSample.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_sample_btn:
                startActivity(new Intent(this, GridViewActivity.class));
                break;
            default:
                break;
        }
    }
}
