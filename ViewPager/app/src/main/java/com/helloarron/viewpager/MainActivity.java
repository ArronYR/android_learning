package com.helloarron.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnView, btnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = (Button) findViewById(R.id.view_btn);
        btnFragment = (Button) findViewById(R.id.fragment_btn);

        btnView.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_btn:
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                break;
            case R.id.fragment_btn:
                startActivity(new Intent(MainActivity.this, FragmentPagerActivity.class));
                break;
            default:
                break;
        }
    }
}
