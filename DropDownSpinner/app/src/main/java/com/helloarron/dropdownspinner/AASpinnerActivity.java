package com.helloarron.dropdownspinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arron on 16/5/6.
 */
public class AASpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView selectedContent;
    private Spinner spinner;

    private List<String> list = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aasa);

        selectedContent = (TextView) findViewById(R.id.tv_selected);
        spinner = (Spinner) findViewById(R.id.sp_dropdown);

        // 1.数据源
        list.add("北京");
        list.add("上海");
        list.add("深圳");
        list.add("广州");
        list.add("贵州");

        // 2.新建数据适配器
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);

        // 3.给ArrayAdapter设置一个下拉样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 4.spinner加载数据适配器
        spinner.setAdapter(arrayAdapter);

        // 5.spinner设置监听器
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cityName = (String) arrayAdapter.getItem(position);
        String name = list.get(position);

        selectedContent.setText("您当前选择的城市是"+cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
