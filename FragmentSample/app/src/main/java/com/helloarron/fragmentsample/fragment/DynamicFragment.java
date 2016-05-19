package com.helloarron.fragmentsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.helloarron.fragmentsample.R;

/**
 * Created by arron on 16/5/18.
 */
public class DynamicFragment extends Fragment {

    private TextView textView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         * layout布局文件转为View对象
         *     resource: Fragment需要加载的布局文件
         *     root: 加载布局文件layout到父ViewGroup
         *     attachToRoot: false-不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.fragment, container, false);

        textView = (TextView) view.findViewById(R.id.text);
        textView.setText("动态加载Fragment");

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("TextView改变了");
            }
        });
        
        return view;
    }
}
