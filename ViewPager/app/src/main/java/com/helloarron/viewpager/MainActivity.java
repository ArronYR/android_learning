package com.helloarron.viewpager;

import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerTabStrip tab;

    private List<View> viewList = new ArrayList<View>();
    private List<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化ViewPager及tab
        pager = (ViewPager) findViewById(R.id.pager);
        tab = (PagerTabStrip) findViewById(R.id.tab);

        // 设置PagerTabStrip的一些属性
        tab.setBackgroundColor(Color.TRANSPARENT);
        tab.setTextColor(Color.BLACK);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.BLUE);


        /**
         * 通过使用View对象获取视图作为ViewPager的数据源
         */
        View view1 = View.inflate(this, R.layout.viewpager_1, null);
        View view2 = View.inflate(this, R.layout.viewpager_2, null);
        View view3 = View.inflate(this, R.layout.viewpager_3, null);
        View view4 = View.inflate(this, R.layout.viewpager_4, null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        /**
         * 为ViewPager页卡设置标题
         */
        titleList.add("标题一");
        titleList.add("标题二");
        titleList.add("标题三");
        titleList.add("标题四");

        // 建立PagerAdapter的数据适配器
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(viewList, titleList);

        // ViewPager加载适配器
        pager.setAdapter(pagerAdapter);
    }
}
