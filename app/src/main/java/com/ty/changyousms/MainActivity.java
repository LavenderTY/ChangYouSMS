package com.ty.changyousms;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ty.changyousms.adapter.MainPagerAdapter;
import com.ty.changyousms.base.BaseActivity;
import com.ty.changyousms.fragment.ConversationFragment;
import com.ty.changyousms.fragment.GroupFragment;
import com.ty.changyousms.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        //得到布局文件中的组件
        viewPager = (ViewPager) findViewById(R.id.viewpager);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        fragments = new ArrayList<Fragment>();
        //创建Fragment对象存入集合
        ConversationFragment fragmentC = new ConversationFragment();
        GroupFragment fragmentG = new GroupFragment();
        SearchFragment fragmentS = new SearchFragment();
        fragments.add(fragmentC);
        fragments.add(fragmentG);
        fragments.add(fragmentS);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void processClick(View v) {

    }
}
