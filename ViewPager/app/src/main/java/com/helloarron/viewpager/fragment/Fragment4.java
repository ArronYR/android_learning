package com.helloarron.viewpager.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helloarron.viewpager.R;

/**
 * Created by arron on 16/5/21.
 */
public class Fragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_4, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Main", "我被销毁了");
    }
}
