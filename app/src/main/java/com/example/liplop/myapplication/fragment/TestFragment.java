package com.example.liplop.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.liplop.myapplication.R;
import com.example.liplop.myapplication.TextAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/5/14.
 */
public class TestFragment extends Fragment {
    private static final String[] strDatas = new String[] {
            "first", /*"second", "third", "fourth", "fifth","1231","1223","112","123","123123"*/
    };
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rfl_main)
    SmartRefreshLayout rfl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, null);
        ButterKnife.bind(this,rootView);
        initView();
        initSmartRefreshLayout();
        return rootView;
    }

    private void initSmartRefreshLayout(){
        rfl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        rfl.setEnableLoadMore(false);
//        rfl.setEnableRefresh(false);
    }

    private void initView(){
        TextAdapter adapter = new TextAdapter(Arrays.asList(strDatas));
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContent.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
