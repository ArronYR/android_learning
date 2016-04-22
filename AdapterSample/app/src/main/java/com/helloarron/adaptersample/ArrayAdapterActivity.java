package com.helloarron.adaptersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by arron on 16/4/22.
 */
public class ArrayAdapterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvArrayListView;
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        lvArrayListView = (ListView) findViewById(R.id.array_list_view);

        /**
         * 1. 新建一个数据适配器
         * ArrayAdapter<String>(上下文，当前listView加载的每一个数据项对应的布局文件xml文件，数据源)
         * 2. 加载数据源
         * 3. 视图加载适配器 listView.setAdapter(adapter);
         */
        String[] arr_data = {"ArrayAdapter学习1", "ArrayAdapter学习2", "ArrayAdapter学习3"};
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        lvArrayListView.setAdapter(arr_adapter);

        lvArrayListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String data = (String) lvArrayListView.getItemAtPosition(position);
        String itemId = String.valueOf(lvArrayListView.getItemIdAtPosition(position));
        Toast.makeText(this, "点击了："+data+" id："+itemId, Toast.LENGTH_SHORT).show();
    }
}
