package com.tz.slidetab;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager mViewPager;

    private RadioGroup radioGroup;

    private MyVpAdapter adapter;

    private TextView tvLine;

    private ArrayList mList;

    private HorizontalScrollView mHorizontalScrollView;

    int fromX;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvLine = (TextView) findViewById(R.id.tvline);
        tvLine.setWidth(200);

        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.addOnPageChangeListener(this);
        mList = new ArrayList();

        for (int i = 0; i < 20; i++) {
            mList.add(i);
        }
        adapter = new MyVpAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(adapter);

        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);
        setButtons();

    }

    private void setButtons() {
        for (int i = 0; i < mList.size(); i++) {
            RadioButton button = new RadioButton(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setPadding(0, 20, 0, 20);
            button.setText(i + "");
            button.setGravity(Gravity.CENTER);
            button.setButtonDrawable(android.R.color.transparent);
            radioGroup.addView(button);
        }
    }

    private void moveLine(int position, float positionOffset) {
        // TODO Auto-generated method stub
        //position表示将来第几个选项被选中
        RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
        //坐标
        int [] location = new int [2];
        rb.getLocationInWindow(location);
        //利用位移动画滑到对应的按钮的位置
        TranslateAnimation animation = new TranslateAnimation(
                fromX
                ,location[0]+positionOffset*rb.getWidth(),
                0,
                0);
        animation.setDuration(50);//持续时间，稍短一点
        animation.setFillAfter(true);//滑动后停下来
        fromX = (int) (location[0]+positionOffset*rb.getWidth());
        tvLine.startAnimation(animation);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int total = (int) ((position+positionOffset)*radioGroup.getChildAt(0).getWidth());
        int left = (mViewPager.getWidth()-radioGroup.getChildAt(0).getWidth())/2;
        int scrollX = total - left;
        mHorizontalScrollView.scrollTo(scrollX, 0);
        moveLine(position,positionOffset);
    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        mViewPager.setCurrentItem(i-1);
    }
}
