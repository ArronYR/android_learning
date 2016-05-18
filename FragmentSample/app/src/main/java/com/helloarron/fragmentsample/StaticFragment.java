package com.helloarron.fragmentsample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by arron on 16/5/18.
 */
public class StaticFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * layout布局文件转为View对象
         *     resource: Fragment需要加载的布局文件
         *     root: 加载布局文件layout到父ViewGroup
         *     attachToRoot: false-不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.static_fragment, container, false);

        TextView textView = (TextView) view.findViewById(R.id.text);

        textView.setText("静态加载Fragment");

        return view;
    }
}
