package com.example.liplop.myapplication.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.support.v4.view.NestedScrollingChild;

/**
 * Created by hzj on 2018/5/18.
 */
public class NestedLinearLayout extends LinearLayout implements NestedScrollingChild {
    protected NestedScrollingChildHelper nestedScrollingChildHelper;

    public NestedLinearLayout(Context context) {
        super(context);
        initNestHelper();
    }

    public NestedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initNestHelper();
    }

    public NestedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initNestHelper();

    }

    private void initNestHelper(){nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        nestedScrollingChildHelper.setNestedScrollingEnabled(true);}


    @Override
    public boolean isNestedScrollingEnabled(){
        return nestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes){
        return nestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll(){
        nestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent(){
        return nestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow){
        return nestedScrollingChildHelper.dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow){
        return nestedScrollingChildHelper.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed){
        return nestedScrollingChildHelper.dispatchNestedFling(velocityX,velocityY,consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY){
        return nestedScrollingChildHelper.dispatchNestedPreFling(velocityX,velocityY);
    }
}
