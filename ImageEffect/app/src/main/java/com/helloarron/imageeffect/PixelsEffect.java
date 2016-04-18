package com.helloarron.imageeffect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.helloarron.imageeffect.util.ImageHelper;

/**
 * Created by arron on 16/4/18.
 */
public class PixelsEffect extends AppCompatActivity {

    private ImageView imageView1,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);

        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        imageView3 = (ImageView) findViewById(R.id.imageview3);
        imageView4 = (ImageView) findViewById(R.id.imageview4);

        imageView1.setImageBitmap(bitmap);
        imageView2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        imageView3.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
        imageView4.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
    }
}
