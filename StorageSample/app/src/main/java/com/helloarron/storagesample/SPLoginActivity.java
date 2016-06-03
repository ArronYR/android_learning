package com.helloarron.storagesample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by arron on 16/6/3.
 */
public class SPLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername, etPwd;
    private CheckBox checkBox;
    private Button btnLogin, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        btnLogin = (Button) findViewById(R.id.login_btn);
        btnCancel = (Button) findViewById(R.id.cancel_btn);

        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                break;
            case R.id.cancel_btn:
                break;
            default:
                break;
        }
    }
}
