package com.helloarron.fragmentsample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.helloarron.fragmentsample.fragment.DynamicFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.first:
                startActivity(new Intent(MainActivity.this, LoadFragmentStaticActivity.class));
                break;
            case R.id.second:
                DynamicLoadFragment();
                break;
            case R.id.third:
                startActivity(new Intent(MainActivity.this, LifeCycleActivity.class));
                break;
            case R.id.fourth:
                break;
            default:
                break;
        }
    }

    private void DynamicLoadFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DynamicFragment fragment = new DynamicFragment();
        /**
         * fragmentTransaction.add(R.id.frame, fragment);
         * 这个每次点动态加载都会多一个fragment
         * 所以可以采用replace方法
         */
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
