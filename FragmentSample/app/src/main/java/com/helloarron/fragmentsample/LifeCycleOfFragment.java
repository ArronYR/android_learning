package com.helloarron.fragmentsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by arron on 16/5/19.
 */
public class LifeCycleOfFragment extends Fragment {

    private TextView textView;

    // 启动Fragment -> 屏幕锁屏 -> 屏幕解锁 -> 切换到其他Fragment -> 回到桌面 -> 回到应用 -> 退出Fragment

    /**
     * 每次创建都会绘制Fragment的View组件时回调该方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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
        textView.setText("第一个Fragment");

        Log.i("Main", "Fragment-1--onCreateView");
        return view;
    }

    /**
     * 当fragment被添加到activity时会回调该方法，并且只调用一次
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Main", "Fragment-1--onAttach");
    }

    /**
     * 创建fragment时会回调，并且只调用一次
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Main", "Fragment-1--onCreate");
    }

    /**
     * 当fragment所在的activity启动完成之后调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Main", "Fragment-1--onActivityCreated");
    }

    /**
     * 启动fragment
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.i("Main", "Fragment-1--onStart");
    }

    /**
     * 回复fragment时会回调该方法，调用onStart()方法后一定会调用onResume()方法
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.i("Main", "Fragment-1--onResume");
    }

    /**
     * 暂停fragment
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.i("Main", "Fragment-1--onPause");
    }

    /**
     * 停止fragment
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.i("Main", "Fragment-1--onStop");
    }

    /**
     * 销毁fragment所包含的View组件时调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Main", "Fragment-1--onDestroyView");
    }

    /**
     * 销毁fragment时会回调
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Main", "Fragment-1--onDestroy");
    }

    /**
     * fragment从activity中删除时会回调，并且只调用一次
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Main", "Fragment-1--onDetach");
    }
}
