package com.helloarron.storagesample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by arron on 16/6/3.
 */
public class SPLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername, etPwd;
    private CheckBox checkBox;
    private Button btnLogin, btnCancel;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();

        etUsername = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        btnLogin = (Button) findViewById(R.id.login_btn);
        btnCancel = (Button) findViewById(R.id.cancel_btn);

        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        String userName = preferences.getString("userName", "");
        if (userName == null) {
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
            etUsername.setText(userName);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn: {
                String name = etUsername.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                if ("admin".equals(name) && "123456".equals(pwd)) {
                    if (checkBox.isChecked()) {
                        editor.putString("userName", name);
                        editor.commit();
                    } else {
                        editor.remove("userName");
                        editor.commit();
                    }
                    Toast.makeText(SPLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SPLoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.cancel_btn:{
                if (!checkBox.isChecked()){
                    editor.remove("userName");
                    editor.commit();
                }
                this.finish();
                break;
            }
            default:
                break;
        }
    }
}
