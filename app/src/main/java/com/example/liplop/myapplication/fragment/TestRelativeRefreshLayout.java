package com.example.liplop.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liplop.myapplication.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by hzj on 2018/5/21.
 */
public class TestRelativeRefreshLayout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment,null);
        ((SmartRefreshLayout)root.findViewById(R.id.rfl_test)).setEnableLoadMore(false);
        return root;
    }
}
