package com.example.liplop.myapplication.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hzj on 2018/5/15.
 */
public class HomePageRecycleView extends RecyclerView {
    public HomePageRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        //需要在绘制前重新计算高度，原高度加上可以上推的高度（head高度-搜索栏高度）
        setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight() + 360 - 96);
    }
}
