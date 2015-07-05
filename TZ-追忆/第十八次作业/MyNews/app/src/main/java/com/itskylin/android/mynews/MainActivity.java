package com.itskylin.android.mynews;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.itskylin.android.mynews.utils.ReflectionUtil;
import com.itskylin.android.mynews.view.MyFragment;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private static final int VIEWPAGER_PAGE_MOVE = 1;
    private RadioGroup rgMenu;
    private ViewPager viewPager;
    private HorizontalScrollView hsvMenu;
    private int[] flags;
    private View radioLine;
    private MyAdapter myAdapter;
    private DisplayMetrics screen;
    private int fromX;
    private RadioButton rdTop, rdNews, rdHot, rdSz, rdCar, rdLife, rdPhoto, rdTrip, rdHistory, rdFashion, rdGame;
    private int rdMinWidth;
    private String TAG = "MainActivity";

    private Handler handel = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VIEWPAGER_PAGE_MOVE:
                    viewPager.setCurrentItem(msg.arg1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(screen);
        setContentView(R.layout.activity_main);
        ReflectionUtil.initView(this);
        init();
        myAdapter = new MyAdapter(getSupportFragmentManager());
        rgMenu.setOnCheckedChangeListener(this);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void init() {
//        rdMinWidth = (screen.widthPixels - 80) / 5;
//        radioLine.setMinimumWidth(rdMinWidth);
//        rdTop.setMinimumWidth(rdMinWidth);
//        rdNews.setMinimumWidth(rdMinWidth);
//        rdHot.setMinimumWidth(rdMinWidth);
//        rdSz.setMinimumWidth(rdMinWidth);
//        rdCar.setMinimumWidth(rdMinWidth);
//        rdLife.setMinimumWidth(rdMinWidth);
//        rdPhoto.setMinimumWidth(rdMinWidth);
//        rdTrip.setMinimumWidth(rdMinWidth);
//        rdHistory.setMinimumWidth(rdMinWidth);
//        rdFashion.setMinimumWidth(rdMinWidth);
//        rdGame.setMinimumWidth(rdMinWidth);
        flags = new int[]{
                R.drawable.china,
                R.drawable.china2,
                R.drawable.china4,
                R.drawable.china6,
                R.drawable.chin3,
                R.drawable.chin5,
                R.drawable.chin8,
                R.drawable.japan,
                R.drawable.japan2,
                R.drawable.japan3,
                R.drawable.japan4
        };
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.i(TAG, "radio group:" + checkedId);
        Log.i(TAG, "checkedId:" + checkedId);
        int i = 0;
        switch (checkedId) {
            case R.id.rdTop:
                i = 0;
                break;
            case R.id.rdNews:
                i = 1;
                break;
            case R.id.rdHot:
                i = 2;
                break;
            case R.id.rdSz:
                i = 3;
                break;
            case R.id.rdCar:
                i = 4;
                break;
            case R.id.rdLife:
                i = 5;
                break;
            case R.id.rdPhoto:
                i = 6;
                break;
            case R.id.rdTrip:
                i = 7;
                break;
            case R.id.rdFashion:
                i = 8;
                break;
            case R.id.rdHistory:
                i = 9;
                break;
            case R.id.rdGame:
                i = 10;
                break;
            default:
                break;
        }
        Message msg = new Message();
        msg.what = VIEWPAGER_PAGE_MOVE;
        msg.arg1 = i;
        handel.sendMessage(msg);
    }


    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("path", flags[position]);
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return rgMenu.getChildCount();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.i(TAG, "Position:" + position);
//        Log.i(TAG, "positionOffset:" + positionOffset);
        Log.i(TAG, "positionOffsetPixels:" + positionOffsetPixels);

        int total = (int) ((position + positionOffset) * rgMenu.getChildAt(0).getWidth());
        int left = (viewPager.getWidth() - rgMenu.getChildAt(0).getWidth()) / 2;
        int scrollX = total - left;
        hsvMenu.scrollTo(scrollX, 0);
        moveLine(position, positionOffset);
    }


    private void moveLine(final int position, float positionOffset) {
        //position表示将来第几个选项被选中
        final RadioButton rb = (RadioButton) rgMenu.getChildAt(position);
        //坐标
        final int[] location = new int[2];
        rb.getLocationInWindow(location);
        //利用位移动画滑到对应的按钮的位置
        fromX = (int) (location[0] + positionOffset * rb.getWidth());
        ValueAnimator value = ValueAnimator.ofFloat(0, positionOffset);
        value.setCurrentPlayTime(50);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewHelper.setTranslationX(radioLine, fromX);
            }
        });
        value.start();
    }


    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}