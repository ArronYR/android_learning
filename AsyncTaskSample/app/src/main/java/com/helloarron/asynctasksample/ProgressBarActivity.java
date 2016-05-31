package com.helloarron.asynctasksample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by arron on 16/5/31.
 */
public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        mProgressBar = (ProgressBar) findViewById(R.id.pg_progress);
        mTask = new MyAsyncTask();
        mTask.execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            // 模拟进度更新
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                try {
                    // 延缓进度
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
        }
    }
}
