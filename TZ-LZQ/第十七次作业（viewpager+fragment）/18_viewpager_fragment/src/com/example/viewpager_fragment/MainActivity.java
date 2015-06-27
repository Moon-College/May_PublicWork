package com.example.viewpager_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener, OnPageChangeListener {

	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private ViewPager viewPager;
	private int[] flags;
	private TextView tvline;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		tvline = (TextView) findViewById(R.id.tvline);

		flags = new int[] { R.drawable.china, R.drawable.korea,
				R.drawable.nkorea, R.drawable.japan, R.drawable.usa,
				R.drawable.india, R.drawable.tail };

		viewPager = (ViewPager) findViewById(R.id.viewpager);

		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

		rg.setOnCheckedChangeListener(this);

		viewPager.setOnPageChangeListener(this);
	}

	private class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {

			MyFragment fragment = new MyFragment();
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

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case R.id.china:
			// 中国被选中
			viewPager.setCurrentItem(0);
			break;
		case R.id.korea:
			// 韩国被选中
			viewPager.setCurrentItem(1);
			break;
		case R.id.nkorea:
			// 朝鲜被选中
			viewPager.setCurrentItem(2);
			break;
		case R.id.japan:
			// 日本被选中
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
			// 中国被选中
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

		// Log.i("sss", "position:" + position + " , positionOffset:"
		// + positionOffset + " , positionOffsetPixels:"
		// + positionOffsetPixels);

		int total = (int) ((position + positionOffset) * rg.getChildAt(0)
				.getWidth());
		int left = (viewPager.getWidth() - rg.getChildAt(0).getWidth()) / 2;

		int scrollx = total - left;

		hsv.scrollTo(scrollx, 0);

		moveLine(position, positionOffset);
	}

	private void moveLine(int position, float positionOffset) {

		RadioButton rb = (RadioButton) rg.getChildAt(position);

		int[] location = new int[2];

		rb.getLocationInWindow(location);

		int location_X = location[0];

		TranslateAnimation animation = new TranslateAnimation(index, location_X
				+ positionOffset * rb.getWidth(), 0, 0);
		animation.setDuration(30);
		animation.setFillAfter(true);
		tvline.startAnimation(animation);
		index = (int) (location_X + positionOffset * rb.getWidth());
	}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

}
