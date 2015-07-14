package com.cn.test;

import android.graphics.Color;
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

import com.cn.test.R;
import com.cn.test.fragments.MyPagerFragment;

public class ViewPagerctivity extends FragmentActivity {
	HorizontalScrollView hs;
	RadioGroup radioGroup;
	TextView tvline;
	ViewPager viewPager;
	int lastPos = 0;
	float lastFrom = 0;
	boolean firstRunFlag = true;
	int[] picRes = new int[] { R.drawable.china, R.drawable.korea,
			R.drawable.nkorea, R.drawable.japan, R.drawable.usa,
			R.drawable.india, R.drawable.tail };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager_demo);
		initViews();
	}

	private void initViews() {
		hs = (HorizontalScrollView) findViewById(R.id.hsv);
		tvline = (TextView) findViewById(R.id.tvLine);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOnPageChangeListener(vpPageChangeListener);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(rgCheckChangeListener);

		MyPagerAdapter adapter = new MyPagerAdapter(
				this.getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		RadioButton initColume = (RadioButton) radioGroup.getChildAt(lastPos);
		initColume.setTextColor(Color.parseColor("#ffff4444"));

	}

	OnPageChangeListener vpPageChangeListener = new OnPageChangeListener() {
		int curPos;

		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			RadioButton colume = (RadioButton) radioGroup.getChildAt(position);

			int width = colume.getWidth();
			hs.scrollTo((int) (colume.getLeft() + colume.getWidth()
					* positionOffset), 0);

			int[] location = new int[2];
			colume.getLocationInWindow(location);

			moveLine(lastFrom, location[0] + width * positionOffset);
		};

		public void onPageSelected(int position) {
			RadioButton curColume = (RadioButton) radioGroup
					.getChildAt(position);
			curColume.setTextColor(Color.parseColor("#ffff4444"));

			RadioButton lastColume = (RadioButton) radioGroup
					.getChildAt(lastPos);
			lastColume.setTextColor(Color.parseColor("#626262"));

			lastPos = position;
		};

		public void onPageScrollStateChanged(int state) {
		};
	};

	private void moveLine(float fromX, float toX) {
		TranslateAnimation animation = new TranslateAnimation(fromX, toX, 0, 0);
		animation.setDuration(50);
		tvline.startAnimation(animation);
		lastFrom = toX;
	}

	OnCheckedChangeListener rgCheckChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			switch (checkedId) {
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

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		// 决定每一页是�?���?
		@Override
		public Fragment getItem(int arg0) {
			Bundle bundle = new Bundle();
			bundle.putInt("picRes", picRes[arg0]);
			MyPagerFragment fragment = new MyPagerFragment();
			fragment.setArguments(bundle);// 麻布�?
			return fragment;
		}

		// 决定有多少页
		@Override
		public int getCount() {
			int cnt = radioGroup.getChildCount();
			return cnt;
		}

	}
}
