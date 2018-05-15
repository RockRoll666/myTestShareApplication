package com.example.liplop.myapplication.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.liplop.myapplication.R;
import com.example.liplop.myapplication.fragment.TestFragment;
import com.example.liplop.myapplication.view.HomePageScrollView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hzj on 2018/5/15.
 */
public class HomePageNewActivity extends AppCompatActivity {
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.ind_original)
    MagicIndicator indOriginal;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.hpsv_main)
    HomePageScrollView hpsvMain;

    private ArrayList<String> mTitles;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_new);
        ButterKnife.bind(this);
        mTitles = new ArrayList<>();
        mTitles.add("title1");
        mTitles.add("title2");
        mFragments = new ArrayList<>();
        TestFragment fragment1 = new TestFragment();
        TestFragment fragment2 = new TestFragment();
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        initViewPager();
        initIndicator();
    }

    private void initIndicator(){
        CommonNavigator commonNavigatorO = new CommonNavigator(this);
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
        indOriginal.setNavigator(commonNavigatorO);
        ViewPagerHelper.bind(indOriginal,vpContent);
    }

    private void initViewPager(){
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
    }
}
