package com.helloarron.imagetransform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMatrix, mXfermode, mShader, mReflect, Mesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMatrix = (Button) findViewById(R.id.matrix);
        mXfermode = (Button) findViewById(R.id.xfermode);
        mShader = (Button) findViewById(R.id.shader);
        mReflect = (Button) findViewById(R.id.reflect);
        Mesh = (Button) findViewById(R.id.mesh);

        mMatrix.setOnClickListener(this);
        mXfermode.setOnClickListener(this);
        mShader.setOnClickListener(this);
        mReflect.setOnClickListener(this);
        Mesh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.matrix:
                startActivity(new Intent(this, ImageMatrixTest.class));
                break;
            case R.id.xfermode:
                break;
            case R.id.shader:
                break;
            case R.id.reflect:
                break;
            case R.id.mesh:
                break;
        }
    }
}
