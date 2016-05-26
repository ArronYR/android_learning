package com.helloarron.gallerysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;

import com.helloarron.gallerysample.adapter.ImageAdapter;

public class MainActivity extends AppCompatActivity {

    private int[] res = {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4, R.drawable.item5, R.drawable.item6, R.drawable.item7, R.drawable.item8, R.drawable.item9, R.drawable.item10, R.drawable.item11, R.drawable.item12};

    private Gallery gallery;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery = (Gallery) findViewById(R.id.gallery);
        // 加载数据适配器
        imageAdapter = new ImageAdapter(res, this);
        gallery.setAdapter(imageAdapter);
    }
}
