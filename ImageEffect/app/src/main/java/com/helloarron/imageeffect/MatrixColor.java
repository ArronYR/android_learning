package com.helloarron.imageeffect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by arron on 16/4/18.
 */
public class MatrixColor extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private GridLayout mGroup;
    private Button mBtnChange, mBtnReset;
    private Bitmap bitmap;
    private TextView[] mEts = new TextView[20];
    private float[] mColorMatrix = new float[20];

    private int mEtWidth, mEtHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_color);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mGroup = (GridLayout) findViewById(R.id.group);
        mBtnChange = (Button) findViewById(R.id.btnChange);
        mBtnReset = (Button) findViewById(R.id.btnReset);

        mImageView.setImageBitmap(bitmap);
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });

        mBtnChange.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(MatrixColor.this);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
            mEts[i].setGravity(Gravity.CENTER);
        }
    }

    private void changeMatrix() {
        getMatrix();
        setImageMatrix();
    }

    private void resetMatrix() {
        initMatrix();
        changeMatrix();
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnChange:
                changeMatrix();
                break;
            case R.id.btnReset:
                resetMatrix();
                break;
        }
    }
}
