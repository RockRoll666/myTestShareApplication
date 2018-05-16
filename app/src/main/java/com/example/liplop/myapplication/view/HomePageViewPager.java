package com.example.liplop.myapplication.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by hzj on 2018/5/15.
 */
public class HomePageViewPager extends ViewPager {
    public HomePageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //需要在绘制前重新计算高度，原高度加上可以上推的高度（head高度-搜索栏高度）
//        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }
}
