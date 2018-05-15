package com.example.liplop.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.liplop.myapplication.R;
import com.example.liplop.myapplication.fragment.TestFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/5/14.
 */
public class NestedScrollActivity extends AppCompatActivity {
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.ind_original)
    MagicIndicator indOriginal;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.abl_main)
    AppBarLayout ablMain;
    @BindView(R.id.rfl_main)
    SmartRefreshLayout rflMain;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.ind_below_title)
    MagicIndicator indBelowTitle;

    private ArrayList<String> mTitles;
    private ArrayList<Fragment> mFragments;
    private int mHeadHeight;
    private int mTitleHeight;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 11){
                ((RefreshLayout) msg.obj).finishRefresh();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestscroll);
        ButterKnife.bind(this);
        rlHead.post(new Runnable() {
            @Override
            public void run() {
                mHeadHeight = rlHead.getHeight();
            }
        });
        llTitle.post(new Runnable() {
            @Override
            public void run() {
                mTitleHeight = llTitle.getHeight();
            }
        });
        mTitles = new ArrayList<>();
        mTitles.add("title1");
        mTitles.add("title2");
        mFragments = new ArrayList<>();
        TestFragment fragment1 = new TestFragment();
        TestFragment fragment2 = new TestFragment();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        initSmartRefreshLayout();
        initViewPager();
        initAppBarLayout();
        initIndicator();
    }

    private void initSmartRefreshLayout(){
        rflMain.setEnableLoadMore(false);
        rflMain.setOnMultiPurposeListener(new OnMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                if (isDragging){
                    if (0 < offset){
                        llTitle.setVisibility(View.INVISIBLE);
                    }else {
                        llTitle.setVisibility(View.VISIBLE);
                    }
                }else {
                    if (offset == 1){
                        llTitle.setVisibility(View.VISIBLE);
                    }
                }

                Log.d("onHeaderMoving","isdragging:"+isDragging+",offset:"+offset);
            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Message message = mHandler.obtainMessage();
                message.what = 11;
                message.obj = refreshLayout;
                mHandler.sendMessageDelayed(message,2000);
            }

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

            }
        });
    }

    private void initAppBarLayout(){
        llTitle.getBackground().mutate().setAlpha(0);
        ablMain.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < mTitleHeight - mHeadHeight){
                    indBelowTitle.setVisibility(View.VISIBLE);
                    llTitle.getBackground().mutate().setAlpha(255);
                }else {
                    indBelowTitle.setVisibility(View.GONE);
                    Log.d("onOffsetChanged","mTitleHeight:"+ mTitleHeight+",mHeadHeight:"+mHeadHeight+",verticalOffset"+verticalOffset);
                    if (0 != mTitleHeight - mHeadHeight){
                        llTitle.getBackground().mutate().setAlpha(255*verticalOffset/(mTitleHeight - mHeadHeight));
                    }
                }
            }
        });
    }


    private void initIndicator(){
        CommonNavigator commonNavigatorO = new CommonNavigator(this);
        CommonNavigator commonNavigatorB = new CommonNavigator(this);
        CommonNavigatorAdapter adapter = new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitles == null ? 0 : mTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(mTitles.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpContent.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        };
        commonNavigatorO.setAdapter(adapter);
        commonNavigatorB.setAdapter(adapter);
        indOriginal.setNavigator(commonNavigatorO);
        ViewPagerHelper.bind(indOriginal,vpContent);
        indBelowTitle.setNavigator(commonNavigatorB);
        ViewPagerHelper.bind(indBelowTitle,vpContent);
    }

    private void initViewPager(){
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(),mFragments);
        vpContent.setAdapter(adapter);
    }


    class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            // TODO Auto-generated constructor stub
            mFragments=fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return mFragments.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mFragments.size();
        }

    }
}
