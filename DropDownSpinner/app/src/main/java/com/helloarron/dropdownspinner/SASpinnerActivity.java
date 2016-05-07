package com.helloarron.dropdownspinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arron on 16/5/6.
 */
public class SASpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView selectedContent;
    private Spinner spinner;

    private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sasa);

        selectedContent = (TextView) findViewById(R.id.tv_selected);
        spinner = (Spinner) findViewById(R.id.sp_dropdown);

        // 1.数据源
        getData();

        // 2.新建数据适配器
        simpleAdapter = new SimpleAdapter(this, dataList, R.layout.dropdown_item, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});

        // 3.给ArrayAdapter设置一个下拉样式
        simpleAdapter.setDropDownViewResource(R.layout.dropdown_item);

        // 4.spinner加载数据适配器
        spinner.setAdapter(simpleAdapter);

        // 5.spinner设置监听器
        spinner.setOnItemSelectedListener(this);

    }

    private void getData() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.mipmap.ic_launcher);
        map1.put("text", "北京");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.mipmap.ic_launcher);
        map2.put("text", "上海");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.mipmap.ic_launcher);
        map3.put("text", "深圳");

        Map<String, Object> map4 = new HashMap<>();
        map4.put("image", R.mipmap.ic_launcher);
        map4.put("text", "广州");

        Map<String, Object> map5 = new HashMap<>();
        map5.put("image", R.mipmap.ic_launcher);
        map5.put("text", "贵州");

        dataList.add(map1);
        dataList.add(map2);
        dataList.add(map3);
        dataList.add(map4);
        dataList.add(map5);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Map map = (Map) simpleAdapter.getItem(position);
        selectedContent.setText("您当前选择的城市是:"+ (String) map.get("text"));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
