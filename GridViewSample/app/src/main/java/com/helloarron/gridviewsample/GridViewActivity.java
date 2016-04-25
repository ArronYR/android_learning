package com.helloarron.gridviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arron on 16/4/25.
 */
public class GridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

    private int[] drawable = { R.drawable.address_book, R.drawable.calendar,
            R.drawable.camera, R.drawable.clock, R.drawable.games_control,
            R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
            R.drawable.speech_balloon, R.drawable.weather,
            R.drawable.world, R.drawable.youtube };
    private String[] iconName = { "联系人", "日历", "照相机", "时钟", "游戏", "短信", "铃声", "设置",
            "语音", "天气", "浏览器", "Youtube" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gridview);

        mGridView = (GridView) findViewById(R.id.gv_container);
        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.gridview_item, new String[]{"pic", "name"}, new int[]{R.id.pic, R.id.name});
        mGridView.setAdapter(simpleAdapter);
        mGridView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < drawable.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", drawable[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        Log.i("Main", "size="+dataList.size());
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map item = (Map) mGridView.getItemAtPosition(position);
        String name = item.get("name").toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
