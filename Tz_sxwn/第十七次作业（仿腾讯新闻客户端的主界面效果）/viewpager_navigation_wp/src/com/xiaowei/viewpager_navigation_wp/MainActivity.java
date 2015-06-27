package com.xiaowei.viewpager_navigation_wp;

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

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener{
	HorizontalScrollView hsv;
	RadioGroup rg;
	ViewPager viewPager;
	int[] flags;
	int fromX;
	TextView tvLine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hsv  = (HorizontalScrollView) findViewById(R.id.hsv);
		rg  = (RadioGroup) findViewById(R.id.rg);
		rg.setOnCheckedChangeListener(this);
		tvLine = (TextView) findViewById(R.id.tvLine);
		viewPager  = (ViewPager) findViewById(R.id.viewpager);
		MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
		flags = new int[]{
				R.drawable.china,
				R.drawable.korea,
				R.drawable.nkorea,
				R.drawable.japan,
				R.drawable.american,
				R.drawable.india,
				R.drawable.tail
			};
		viewPager.setAdapter(myAdapter);
		viewPager.setOnPageChangeListener(this);
	}
	
	class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("path", flags[position]);//麻布袋
			bundle.putInt("position", position);//麻布袋
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
		}
		
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
			viewPager.setCurrentItem(3);
			break;
		case R.id.american:
			viewPager.setCurrentItem(4);
			break;
		case R.id.india:
			viewPager.setCurrentItem(5);
			break;
		case R.id.tail:
			viewPager.setCurrentItem(6);
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		int total = (int)(position + positionOffset)*rg.getChildAt(0).getWidth();
		int left = (viewPager.getWidth()  - rg.getChildAt(0).getWidth())/2;
		int scollX = total - left;
		hsv.scrollTo(scollX, 0);
		moveLine(position,positionOffset);
	}
	/**
	 * 滑动的线
	 * @param position
	 * @param positionOffset
	 */
	private void moveLine(int position, float positionOffset) {
		//position表示将来第几个选项被选中
		RadioButton rb  = (RadioButton) rg.getChildAt(position);
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		//利用位移动画滑到对应的按钮的位置
		TranslateAnimation animation = new TranslateAnimation(
				fromX, location[0]+positionOffset*rb.getWidth(), 0, 0);
		animation.setDuration(50);//持续时间,稍短一些
		animation.setFillAfter(true);//滑动后停下来
		fromX = (int)(location[0]+positionOffset*rb.getWidth());
		tvLine.startAnimation(animation);
	}

	@Override
	public void onPageSelected(int arg0) {
		
	}
}
