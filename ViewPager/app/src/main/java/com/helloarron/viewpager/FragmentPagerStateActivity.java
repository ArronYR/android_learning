package com.helloarron.viewpager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.helloarron.viewpager.adapter.MyFragmentPagerAdapter;
import com.helloarron.viewpager.adapter.MyFragmentStatePagerAdapter;
import com.helloarron.viewpager.fragment.Fragment1;
import com.helloarron.viewpager.fragment.Fragment2;
import com.helloarron.viewpager.fragment.Fragment3;
import com.helloarron.viewpager.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arron on 16/5/21.
 */
public class FragmentPagerStateActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerTabStrip tab;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        // 初始化ViewPager及tab
        pager = (ViewPager) findViewById(R.id.pager);
        tab = (PagerTabStrip) findViewById(R.id.tab);

        // 设置PagerTabStrip的一些属性
        tab.setBackgroundColor(Color.TRANSPARENT);
        tab.setTextColor(Color.BLACK);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.BLUE);


        /**
         * 通过使用Fragment对象获取视图作为ViewPager的数据源
         */
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());

        /**
         * 为ViewPager页卡设置标题
         */
        titleList.add("FragmentState一");
        titleList.add("FragmentState二");
        titleList.add("FragmentState三");
        titleList.add("FragmentState四");

        // 建立PagerAdapter的数据适配器
        MyFragmentStatePagerAdapter pagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), fragmentList, titleList);

        // ViewPager加载适配器
        pager.setAdapter(pagerAdapter);
    }
}
