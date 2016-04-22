package com.helloarron.adaptersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arron on 16/4/22.
 */
public class SimpleAdapterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private ListView lvSimpleListView;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        lvSimpleListView = (ListView) findViewById(R.id.simple_list_view);

        /**
         * 1. 新建一个适配器
         * SimpleAdapter(context, data, resource, from, to)
         *      context: 上下文
         *      data: 数据源 List<? extends Map<String, ?>> data 一个Map做组成的List集合
         *              每一个Map对应ListView中每一行
         *              每一个Map（键-值对）中的键必须包含在from中所指定的键
         * resource: 列表项的布局文件 R.id.item_layout
         * from: Map中的键名组成的数组 String{ 'pic', 'name'}
         * to: 视图文件中指定的id项数组 Int{ R.id.pic, R.id.name} 与from对应
         */
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.simple_adapter_item, new String[]{"pic", "text"}, new int[]{R.id.pic, R.id.text});
        lvSimpleListView.setAdapter(simp_adapter);

        // 设置事件监听器
        lvSimpleListView.setOnItemClickListener(this);
        lvSimpleListView.setOnScrollListener(this);
    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text", "simple adapter " + i);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = lvSimpleListView.getItemAtPosition(position) + "";
        Toast.makeText(this, "position:" + position + " text:" + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Log.i("Main", "用户手指在离开屏幕之前，由于用力滑动，视图仍依靠惯性滑动");

                // 增加数据
                Map<String, Object> map = new HashMap<>();
                map.put("pic", R.mipmap.ic_launcher);
                map.put("text", "新增加的项");
                dataList.add(map);
                // 同时adapter数据的更新
                simp_adapter.notifyDataSetChanged();

                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main", "视图已经停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main", "用户手指没有离开屏幕，视图正在滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
