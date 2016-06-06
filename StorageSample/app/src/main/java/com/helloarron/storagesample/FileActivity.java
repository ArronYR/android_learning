package com.helloarron.storagesample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by arron on 16/6/6.
 */
public class FileActivity extends AppCompatActivity {

    private EditText etContent;
    private Button btnSave;
    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        etContent = (EditText) findViewById(R.id.et_file);
        btnSave = (Button) findViewById(R.id.file_save_btn);
        tvContent = (TextView) findViewById(R.id.tv_content);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString().trim();
                writeFiles(content);
                tvContent.setText(readFile());
            }
        });
    }

    /**
     * 写入内容到文件
     *
     * @param content
     */
    public void writeFiles(String content) {
        try {
            FileOutputStream fos = openFileOutput("user.txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件读取内容
     * @return
     */
    public String readFile() {
        String content = "";
        FileInputStream fis = null;
        try {
            fis = openFileInput("user.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            content = baos.toString();
            fis.close();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
