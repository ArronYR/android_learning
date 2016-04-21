package com.helloarron.imagetransform;

import android.content.Intent;
import android.graphics.BitmapShader;
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
                startActivity(new Intent(this, RoundRectXfermodeTest.class));
                break;
            case R.id.shader:
                startActivity(new Intent(this, BitmapShaderTest.class));
                break;
            case R.id.reflect:
                startActivity(new Intent(this, ReflectTest.class));
                break;
            case R.id.mesh:
                startActivity(new Intent(this, MeshTest.class));
                break;
        }
    }
}
