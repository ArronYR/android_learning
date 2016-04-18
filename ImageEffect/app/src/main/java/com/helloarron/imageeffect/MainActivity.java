package com.helloarron.imageeffect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPrimaryColor, mPixelsEffect, mColorMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrimaryColor = (Button) findViewById(R.id.btnPrimaryColor);
        mPixelsEffect = (Button) findViewById(R.id.btnPixelsEffect);
        mColorMatrix = (Button) findViewById(R.id.btnColorMatrix);

        mPrimaryColor.setOnClickListener(this);
        mPixelsEffect.setOnClickListener(this);
        mColorMatrix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPrimaryColor:
                startActivity(new Intent(this, PrimaryColor.class));
                break;
            case R.id.btnPixelsEffect:
                startActivity(new Intent(this, PixelsEffect.class));
                break;
            case R.id.btnColorMatrix:
                startActivity(new Intent(this, MatrixColor.class));
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
