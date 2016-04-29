package com.ty.changyousms.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }
    //方法要在MainActivity中实现，因此定义为抽象的
    //初始化控件
    public abstract void initView();
    //初始化监听器
    public abstract void initListener();
    //初始化数据
    public abstract void initData();
    //处理点击事件
    public abstract void processClick(View v);

    @Override
    public void onClick(View v) {
        processClick(v);
    }
}
