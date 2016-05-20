package com.helloarron.fragmentsample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.helloarron.fragmentsample.fragment.CommunicateFragment;

/**
 * Created by arron on 16/5/19.
 */
public class CommunicateActivity extends AppCompatActivity implements CommunicateFragment.CommunicateListener {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

        editText = (EditText) findViewById(R.id.edit_text);
        button = (Button) findViewById(R.id.send_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                CommunicateFragment fragment = new CommunicateFragment();

                Bundle bundle = new Bundle();
                bundle.putString("name", string);
                fragment.setArguments(bundle);

                FragmentManager manager = getFragmentManager();
                FragmentTransaction beginTransaction = manager.beginTransaction();
                beginTransaction.replace(R.id.layout, fragment, "fragment-communicate");
                beginTransaction.commit();

                Toast.makeText(CommunicateActivity.this, "向Fragment发送数据: "+string, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void thank(String code) {
        Toast.makeText(CommunicateActivity.this, "已成功接收到数据:"+code+", 客气了！", Toast.LENGTH_SHORT).show();
    }
}
