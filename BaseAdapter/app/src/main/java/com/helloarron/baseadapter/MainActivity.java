package com.helloarron.baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.helloarron.baseadapter.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据源
        List<ItemBean> itemBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemBeanList.add(new ItemBean(R.mipmap.ic_launcher, "Title"+i, "Content"+i));
        }

        listView = (ListView) findViewById(R.id.lv_list);
    }
}
