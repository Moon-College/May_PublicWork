/**
 * Project Name:lsn18_viewpager_navigation
 * File Name:MainActivity.java
 * Package Name:com.zht.viewpagernavigation
 * Date:2015-6-29上午10:56:41
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.viewpagernavigation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-29 上午10:56:41 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends FragmentActivity {
	private TextView line;
	private ViewPager vp;
	private RadioGroup rg;
	private int[] flags;
	private HorizontalScrollView hsv;
	int fromX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		initData();
	}

	private void initView() {
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		line = (TextView) findViewById(R.id.line);
		vp = (ViewPager) findViewById(R.id.viewpager);
	}

	private void initData() {
		flags = new int[] { R.drawable.china, R.drawable.korea,
				R.drawable.nkorea, R.drawable.japan, R.drawable.usa,
				R.drawable.india, R.drawable.tail };
		MyViewPagerAdapter adapter = new MyViewPagerAdapter(
				getSupportFragmentManager());
		vp.setAdapter(adapter);
		rg.setOnCheckedChangeListener(new MyCheckedChangeListener());
		vp.setOnPageChangeListener(new MyPageChangeListener());
	}

	// ViewPager监听
	private class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
           int total = (int) ((position + positionOffset)*rg.getChildAt(0).getWidth());
           int left = (vp.getWidth()-rg.getChildAt(0).getWidth())/2;
   		   int scrollX = total - left;
   		   hsv.scrollTo(scrollX, 0);
   		   moveLine(position,positionOffset);
		}
		//设置下滑线的滑动动画
		private void moveLine(int position, float positionOffset) {
			//position表示将来第几个选项被选中
			RadioButton rb = (RadioButton) rg.getChildAt(position);
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
			line.startAnimation(animation);
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub

		}

	}

	// RadioButton监听
	private class MyCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.china:
				vp.setCurrentItem(0);
				break;
			case R.id.korea:
				vp.setCurrentItem(1);
				break;
			case R.id.nkorea:
				vp.setCurrentItem(2);
				break;
			case R.id.japan:
				vp.setCurrentItem(3);
				break;
			case R.id.america:
				vp.setCurrentItem(4);
				break;
			case R.id.india:
				vp.setCurrentItem(5);
				break;
			case R.id.tail:
				vp.setCurrentItem(6);
				break;
			default:
				break;
			}
		}

	}

	private class MyViewPagerAdapter extends FragmentPagerAdapter {

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		// 决定每一页是什么样的
		@Override
		public Fragment getItem(int arg0) {
			ViewPagerFragment fragment = new ViewPagerFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("path", flags[arg0]);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
		}

	}
}
