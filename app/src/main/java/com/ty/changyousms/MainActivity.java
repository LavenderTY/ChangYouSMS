package com.ty.changyousms;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;
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
    private TextView tv_tab_conversation;
    private TextView tv_tab_group;
    private TextView tv_tab_search;
    private MainPagerAdapter adapter;
    private LinearLayout ll_tab_conversation;
    private LinearLayout ll_tab_group;
    private LinearLayout ll_tab_search;
    private View v_indicate_line;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        //得到布局文件中的组件
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tv_tab_conversation = (TextView) findViewById(R.id.tv_tab_conversation);
        tv_tab_group = (TextView) findViewById(R.id.tv_tab_group);
        tv_tab_search = (TextView) findViewById(R.id.tv_tab_search);
        ll_tab_conversation = (LinearLayout) findViewById(R.id.ll_tab_conversation);
        ll_tab_group = (LinearLayout) findViewById(R.id.ll_tab_group);
        ll_tab_search = (LinearLayout) findViewById(R.id.ll_tab_search);
    }

    @Override
    public void initListener() {
        //viewPager 界面切换时会触发
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动过程不断调用
            //position 是前一个界面的索引值，只要一移动就算前一个界面的索引，滑动过程出现两个界面，position的值是前一个的
            //positionOffset 位置偏移量，是百分比
            //positionOffsetPixels  位置偏移量参数的值
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //计算红线位移的距离
                int distance = positionOffsetPixels / 3;
                //事件必须设置为0，因为onPageScrolled在不断调用，所以不应该延时
                //持续时间为0，立即生效，因为滑动线的移动需要与用户滑动同步
                //ViewPropertyAnimator 滑动动画师
                //distance + position * v_indicate_line.getWidth() 移动的位置与距离，红条的初始位置由position决定
                ViewPropertyAnimator.animate(v_indicate_line).translationX(distance + position * v_indicate_line.getWidth()).setDuration(0);
            }

            //切换完成后调用，传入的参数是切换后的界面索引
            @Override
            public void onPageSelected(int position) {
                textLightAndScale();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //给三个选项卡设置点击事件
        ll_tab_conversation.setOnClickListener(this);
        ll_tab_group.setOnClickListener(this);
        ll_tab_search.setOnClickListener(this);

        v_indicate_line = findViewById(R.id.v_indicate_line);
        //设置红线的宽度
        computeIndicateLineWidth();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_tab_conversation:
                //改变ViewPager的界面
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_tab_group:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_tab_search:
                viewPager.setCurrentItem(2);
                break;
        }
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
        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        //这里调用就是从开始就判断界面的索引，其实就是默认使第一个界面开始就为高亮显示
        textLightAndScale();
    }


    /**
     * 改变选项卡的文本颜色和大小
     */
    private void textLightAndScale() {
        //获取ViewPager当前显示界面的索引
        int item = viewPager.getCurrentItem();
        //在代码中要写透明度，不写的话就是全透明
        //根据viewPager的界面索引设置选项卡的颜色
        tv_tab_conversation.setTextColor(item == 0 ? Color.WHITE : 0xffa0a0a3);
        tv_tab_group.setTextColor(item == 1 ? Color.WHITE : 0xffa0a0a3);
        tv_tab_search.setTextColor(item == 2 ? Color.WHITE : 0xffa0a0a3);

        //animate的参数为要操作的对象    scaleX 表示改变宽度至指定值   setDuration 表示在规定时间(参数的值)内改变完成
        ViewPropertyAnimator.animate(tv_tab_conversation).scaleX(item == 0 ? 1.2f : 1).setDuration(200);
        ViewPropertyAnimator.animate(tv_tab_conversation).scaleY(item == 0 ? 1.2f : 1).setDuration(200);
        ViewPropertyAnimator.animate(tv_tab_group).scaleX(item == 1 ? 1.2f : 1).setDuration(200);
        ViewPropertyAnimator.animate(tv_tab_group).scaleY(item == 1 ? 1.2f : 1).setDuration(200);
        ViewPropertyAnimator.animate(tv_tab_search).scaleX(item == 2 ? 1.2f : 1).setDuration(200);
        ViewPropertyAnimator.animate(tv_tab_search).scaleY(item == 2 ? 1.2f : 1).setDuration(200);

    }

    /**
     * 设置滑动线的宽度，为屏幕的1/3
     */
    private void computeIndicateLineWidth() {
        //得到屏幕的宽度
        int width = getWindowManager().getDefaultDisplay().getWidth();
        v_indicate_line.getLayoutParams().width = width / 3;
    }
}
