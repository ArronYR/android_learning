package com.helloarron.storagesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSharedPreferences, btnSqlite, btnContentProvider, btnFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSharedPreferences = (Button) findViewById(R.id.sp_btn);
        btnSqlite = (Button) findViewById(R.id.sql_btn);
        btnContentProvider = (Button) findViewById(R.id.cp_btn);
        btnFile = (Button) findViewById(R.id.file_btn);

        btnSharedPreferences.setOnClickListener(this);
        btnSqlite.setOnClickListener(this);
        btnContentProvider.setOnClickListener(this);
        btnFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sp_btn:
                startActivity(new Intent(MainActivity.this, SPLoginActivity.class));
                break;
            case R.id.sql_btn:
                startActivity(new Intent(MainActivity.this, SQLiteActivity.class));
                break;
            case R.id.file_btn:
                startActivity(new Intent(MainActivity.this, FileActivity.class));
                break;
            case R.id.cp_btn:
                break;
            default:
                break;
        }
    }
}
