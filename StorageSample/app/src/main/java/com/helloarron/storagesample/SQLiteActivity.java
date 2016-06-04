package com.helloarron.storagesample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by arron on 16/6/4.
 */
public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        btnLogin = (Button) findViewById(R.id.sql_login_btn);
        btnCancel = (Button) findViewById(R.id.sql_cancel_btn);
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sql_login_btn: {
                break;
            }
            case R.id.sql_cancel_btn: {
                this.finish();
                break;
            }
            default:
                break;
        }
    }
}
