package com.helloarron.imageeffect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.helloarron.imageeffect.util.ImageHelper;

/**
 * Created by arron on 16/4/18.
 */
public class PrimaryColor extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView mImageView;
    private SeekBar mSeekBarHue, mSeekBarSatuartion, mSeekBarLum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
    private float mHue, mSaturation, mLum;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promary_color);

        // 初始化
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test3);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mSeekBarHue = (SeekBar) findViewById(R.id.seekbarHue);
        mSeekBarSatuartion = (SeekBar) findViewById(R.id.seekbarSaturation);
        mSeekBarLum = (SeekBar) findViewById(R.id.seekbarLum);

        mSeekBarHue.setOnSeekBarChangeListener(this);
        mSeekBarSatuartion.setOnSeekBarChangeListener(this);
        mSeekBarLum.setOnSeekBarChangeListener(this);

        mSeekBarHue.setMax(MAX_VALUE);
        mSeekBarSatuartion.setMax(MAX_VALUE);
        mSeekBarLum.setMax(MAX_VALUE);
        mSeekBarHue.setProgress(MID_VALUE);
        mSeekBarSatuartion.setProgress(MID_VALUE);
        mSeekBarLum.setProgress(MID_VALUE);
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekbarHue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seekbarSaturation:
                mSaturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seekbarLum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
