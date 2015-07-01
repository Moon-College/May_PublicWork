package com.lin.mytzwork;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lin.mytzwork.ui.MyFragment;
import com.lin.mytzwork.util.L;
import com.lin.mytzwork.util.ReflectUtil;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private Activity activity;
    private int[] imags = new int[]{
            R.mipmap.china,
            R.mipmap.korea,
            R.mipmap.nkorea,
            R.mipmap.japan,
            R.mipmap.usa,
            R.mipmap.india,
            R.mipmap.tail};
    private MyFragmentPagerAdapter adapter;
    private RadioGroup rg;
    private TextView tvline;
    private HorizontalScrollView hsv;
    private int fromX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ReflectUtil.initView(activity);
        initView();
    }

    private void initView() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        rg.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.china:
                //中国被选中
                viewPager.setCurrentItem(0);
                break;
            case R.id.korea:
                //韩国被选中
                viewPager.setCurrentItem(1);
                break;
            case R.id.nkorea:
                //朝鲜被选中
                viewPager.setCurrentItem(2);
                break;
            case R.id.japan:
                //日本被选中
                viewPager.setCurrentItem(3);
                break;
            case R.id.america:
                viewPager.setCurrentItem(4);
                break;
            case R.id.india:
                viewPager.setCurrentItem(5);
                break;
            case R.id.tail:
                viewPager.setCurrentItem(6);
                //中国被选中
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /**
         * 滑动过程中
         * position 当前滑动是第几页
         * positionOffset   页面滑动到百分之几  0~0.99999  屏幕的比例
         * positionOffsetPixels 滑动了当页的像素  0 ~ 页面的宽度          屏幕的像素
         */
        L.i("info", "position:" + position + " positionOffset:" + positionOffset + " positionOffsetPixels:" + positionOffsetPixels);


        /**
         * 居中 当前 选择的条目
         */

        int total = (int) ((position + positionOffset) * rg.getChildAt(0).getWidth());//当前控件离原点的总长度
        int left = (viewPager.getWidth() - rg.getChildAt(0).getWidth()) / 2; //当前控件里屏幕左边的原点的长度
        int scrollX = total - left; //HorizontalScrollView需要滑动到的距离
        hsv.scrollTo(scrollX, 0);

        moveLine(position, positionOffset);

    }

    private void moveLine(int position, float positionOffset) {

        /*拿到当前RadioButton的宽度 以及坐标*/
        RadioButton radioButton = (RadioButton) rg.getChildAt(position);
        int[] ints = new int[2];
        radioButton.getLocationInWindow(ints);


        TranslateAnimation animation = new TranslateAnimation(
                fromX //从0开始移动
                , ints[0] + positionOffset * radioButton.getWidth(), //移动到当前RadioButton的左上角的X位置再加上移动当页的百分比
                0,
                0);
        animation.setDuration(50);//持续时间，稍短一点
        animation.setFillAfter(true);//滑动后停下来
        fromX = (int) (ints[0] + positionOffset * radioButton.getWidth());
        tvline.startAnimation(animation);

    }

    @Override
    public void onPageSelected(int position) {
        /**
         * 页面滑动停下里，页面是第几页
         */
        L.i("info", "position:" + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        /*页面状态改变
        * 0  按下去开始滑动
        * 1  滑动状态
        * 2  松开的一瞬间
        * 3  当到某一页停下来
        * */
        L.i("info", "state:" + state);

    }


    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return imags.length;
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment fragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putInt("image", imags[position]);
            fragment.setArguments(args);
            return fragment;
        }
    }

}
