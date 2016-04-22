package com.helloarron.adaptersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btArrayAdapter, btSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btArrayAdapter = (Button) findViewById(R.id.btn_array_adapter);
        btSimpleAdapter = (Button) findViewById(R.id.btn_simple_adapter);

        btArrayAdapter.setOnClickListener(this);
        btSimpleAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_array_adapter:
                startActivity(new Intent(this, ArrayAdapterActivity.class));
                break;
            case R.id.btn_simple_adapter:
                startActivity(new Intent(this, SimpleAdapterActivity.class));
                break;
            default:
                break;
        }
    }
}
