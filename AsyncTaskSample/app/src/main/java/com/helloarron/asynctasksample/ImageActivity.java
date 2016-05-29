package com.helloarron.asynctasksample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * Created by arron on 16/5/28.
 */
public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;

    private static String URL = "http://www.gratisography.com/pictures/234_1.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // 调用异步任务
        new MyAsyncTask().execute(URL);
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            // 获取传入的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;

            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                // 人为制造时间延迟
                Thread.sleep(2000);
                // 通过decodeStream解析输入流
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 返回获取的Bitmap
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 将隐藏的ProgressBar显示出来
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }
    }
}
