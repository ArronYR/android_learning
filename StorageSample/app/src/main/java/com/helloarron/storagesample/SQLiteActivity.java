package com.helloarron.storagesample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.helloarron.storagesample.helper.DBOpenHelper;

/**
 * Created by arron on 16/6/4.
 */
public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TABLENAME = "user.db";
    private DBOpenHelper helper;
    private SQLiteDatabase database;

    private Button btnLogin, btnCancel;
    private EditText etUsername, etPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        helper = new DBOpenHelper(SQLiteActivity.this, TABLENAME);
        database = helper.getWritableDatabase();

        etUsername = (EditText) findViewById(R.id.et_username_sql);
        etPwd = (EditText) findViewById(R.id.et_pwd_sql);

        btnLogin = (Button) findViewById(R.id.sql_login_btn);
        btnCancel = (Button) findViewById(R.id.sql_cancel_btn);
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sql_login_btn: {
                String name = etUsername.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();

                Cursor cursor = database.query("user", null, "name = ? and pwd = ?", new String[]{name, pwd}, null, null, null);
                if (cursor != null) {
                    if (cursor.getCount() > 0){
                        Toast.makeText(SQLiteActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SQLiteActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SQLiteActivity.this, "一条数据都没有", Toast.LENGTH_SHORT).show();
                }
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
