package com.chris.viewpagerdemo;

import java.util.List;

import com.chris.viewpagerdemo.fragments.MyPagerFragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ViewPagerDemoActivity extends FragmentActivity
{
	protected static final String tag = "ViewPagerDemoActivity";
	HorizontalScrollView hsv;
	RadioGroup radioGroup;
	TextView tvLine;
	ViewPager viewPager;
	int lastPos = 0;
	float lastFrom = 0;
	boolean firstRunFlag = true;
	int[] picRes = new int[]
	{ R.drawable.lufei, R.drawable.namei, R.drawable.qiaoba, R.drawable.luobin, R.drawable.shenping, R.drawable.simoge, R.drawable.hankuke };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager_demo);
		initViews();
	}

	private void initViews()
	{
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		tvLine = (TextView) findViewById(R.id.tvLine);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOnPageChangeListener(vpPageChangeListener);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(rgCheckChangeListener);

		MyPagerAdapter adapter = new MyPagerAdapter(this.getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		
		//设置初始位置的colume字体为红色
		RadioButton initColume = (RadioButton) radioGroup.getChildAt(lastPos);
		initColume.setTextColor(Color.parseColor("#ffff4444"));
		
	}

	OnPageChangeListener vpPageChangeListener = new OnPageChangeListener()
	{
		int curPos;
		
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
		{
			RadioButton colume = (RadioButton) radioGroup.getChildAt(position);
			
			int width = colume.getWidth();
//			int startX = (viewPager.getWidth()-width)/2;
//			int deltaX = (int) (width*(position+positionOffset));
//			int x = deltaX-startX;
//			hsv.scrollTo(x, 0);
			
			//不用居中显示，模仿网易新闻，就顶格显示
			hsv.scrollTo((int)(colume.getLeft()+colume.getWidth()*positionOffset), 0);
			
			int [] location = new int [2];
			colume.getLocationInWindow(location);
			
			moveLine(lastFrom, location[0]+width*positionOffset);
		};

		public void onPageSelected(int position)
		{
			//把当前colume的字体设置成红色
			RadioButton curColume = (RadioButton) radioGroup.getChildAt(position);
			curColume.setTextColor(Color.parseColor("#ffff4444"));
			
			//把之前的colume设置回黑色
			RadioButton lastColume = (RadioButton) radioGroup.getChildAt(lastPos);
			lastColume.setTextColor(Color.parseColor("#626262"));
			
			lastPos = position;
		};

		public void onPageScrollStateChanged(int state)
		{
		};
	};
	
	private void moveLine(float fromX, float toX) {
		TranslateAnimation animation = new TranslateAnimation(fromX,toX, 0, 0);
		animation.setDuration(50);
		animation.setFillAfter(true);	//动画结束后固定位置
		tvLine.startAnimation(animation);
		lastFrom = toX;
	}

	OnCheckedChangeListener rgCheckChangeListener = new OnCheckedChangeListener()
	{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			Log.i(tag, "onCheckedChanged id = "+checkedId);
			switch (checkedId)
			{
			case R.id.lufei:
				viewPager.setCurrentItem(0, true);
				break;
			case R.id.namei:
				viewPager.setCurrentItem(1, true);
				break;
			case R.id.qiaoba:
				viewPager.setCurrentItem(2, true);
				break;
			case R.id.luobin:
				viewPager.setCurrentItem(3, true);
				break;
			case R.id.shenping:
				viewPager.setCurrentItem(4, true);
				break;
			case R.id.simoge:
				viewPager.setCurrentItem(5, true);
				break;
			case R.id.hankuke:
				viewPager.setCurrentItem(6, true);
				break;

			default:
				break;
			}
		}
	};

	private class MyPagerAdapter extends FragmentPagerAdapter
	{

		public MyPagerAdapter(FragmentManager fm)
		{
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0)
		{
			Bundle bundle = new Bundle();
			bundle.putInt("picRes", picRes[arg0]);
			MyPagerFragment fragment = new MyPagerFragment();
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount()
		{
			int cnt = radioGroup.getChildCount();
			return cnt;
		}

	}
}
