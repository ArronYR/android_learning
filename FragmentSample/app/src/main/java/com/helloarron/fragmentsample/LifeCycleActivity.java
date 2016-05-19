package com.helloarron.fragmentsample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.helloarron.fragmentsample.fragment.LifeCycleOfFragmentFirst;
import com.helloarron.fragmentsample.fragment.LifeCycleOfFragmentSecond;

/**
 * Created by arron on 16/5/19.
 */
public class LifeCycleActivity extends AppCompatActivity {

    private Button button;
    private boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        initView();

        button = (Button) findViewById(R.id.change_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction beginTransaction = manager.beginTransaction();
                if (flag){
                    LifeCycleOfFragmentSecond fragmentSecond = new LifeCycleOfFragmentSecond();
                    beginTransaction.replace(R.id.layout, fragmentSecond);
                    flag = false;
                }else {
                    LifeCycleOfFragmentFirst fragmentFirst = new LifeCycleOfFragmentFirst();
                    beginTransaction.replace(R.id.layout, fragmentFirst);
                    flag = true;
                }
                beginTransaction.commit();
            }
        });
    }

    private void initView() {
        LifeCycleOfFragmentFirst fragment = new LifeCycleOfFragmentFirst();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.replace(R.id.layout, fragment);
        beginTransaction.commit();
    }
}
