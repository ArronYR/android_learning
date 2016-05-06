package com.helloarron.dropdownspinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnArrayAdapter, btnSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnArrayAdapter = (Button) findViewById(R.id.array_adapter_btn);
        btnSimpleAdapter = (Button) findViewById(R.id.simple_adapter_btn);

        btnArrayAdapter.setOnClickListener(this);
        btnSimpleAdapter.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.array_adapter_btn:
                startActivity(new Intent(this, AASpinnerActivity.class));
                break;
            case R.id.simple_adapter_btn:
                startActivity(new Intent(this, SASpinnerActivity.class));
                break;
            default:
                break;
        }
    }
}
