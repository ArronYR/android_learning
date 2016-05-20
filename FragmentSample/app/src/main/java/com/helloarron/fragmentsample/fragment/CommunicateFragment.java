package com.helloarron.fragmentsample.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.helloarron.fragmentsample.R;

/**
 * Created by arron on 16/5/20.
 */
public class CommunicateFragment extends Fragment implements View.OnClickListener {

    private TextView textView;
    private Button button;

    public CommunicateListener listener;
    public interface CommunicateListener{
        void thank(String code);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);
        textView = (TextView) view.findViewById(R.id.text);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setText("回复");

        // 接收activity中传过来的数据并显示
        String name = getArguments().get("name") + "";
        textView.setText(name);

        Toast.makeText(getActivity(), "已成功接收到数据: "+name, Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (CommunicateListener) activity;
        super.onAttach(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String code = "谢谢";
                Toast.makeText(getActivity(), "Fragment向Activity发送数据:"+code, Toast.LENGTH_SHORT).show();
                listener.thank(code);
                break;
            default:
                break;
        }
    }
}
