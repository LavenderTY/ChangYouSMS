package com.ty.changyousms.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ty.changyousms.R;
import com.ty.changyousms.base.BaseFragment;

/**
 * Created by TDing on 2016/4/29.
 */
public class SearchFragment extends BaseFragment{
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //填充布局文件，返回View对象
        View view = inflater.inflate(R.layout.fragment_search,null);
        return view;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
