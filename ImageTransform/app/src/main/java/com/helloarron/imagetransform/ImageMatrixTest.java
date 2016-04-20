package com.helloarron.imagetransform;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.helloarron.imagetransform.view.ImageMatrixView;

/**
 * Created by arron on 16/4/18.
 */
public class ImageMatrixTest extends AppCompatActivity {

    private GridLayout mGridLayout;
    private ImageMatrixView mImageMatrixView;
    private int mEtWidth, mEtHeight;
    private float[] mImageMatrix = new float[9];
    private EditText[] mEts = new EditText[9];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_matrix);

        mGridLayout = (GridLayout) findViewById(R.id.grid_group);
        mImageMatrixView = (ImageMatrixView) findViewById(R.id.view);

        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGridLayout.getWidth() / 3;
                mEtHeight = mGridLayout.getHeight() / 3;
                addEts();
                initImageMatrix();
            }
        });
    }

    private void addEts() {
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(ImageMatrixTest.this);
            editText.setGravity(Gravity.CENTER);
            mEts[i] = editText;
            mGridLayout.addView(editText, mEtWidth, mEtHeight);
        }
    }
    
    private void initImageMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mEts[i].setText(String.valueOf(1));
            }else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    private void getImageMatrix() {
        for (int i = 0; i < 9; i++) {
            mImageMatrix[i] = Float.parseFloat(mEts[i].getText().toString());
        }
    }

    public void change(View view) {
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);
        // 系统API
        // matrix.setScale(2, 2);
        // matrix.postTranslate(100, 100);
        mImageMatrixView.setImageMatrix(matrix);
        mImageMatrixView.invalidate();
    }

    public void reset(View view) {
        initImageMatrix();
        change(view);
    }
}
