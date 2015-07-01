package com.yl.viewpage;

import com.tz.navigation.R;

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

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener, OnPageChangeListener {
	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private ViewPager vp;
	private TextView tv;
	int[] flags;
	int fromX;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		tv = (TextView) findViewById(R.id.tvline);
		vp = (ViewPager) findViewById(R.id.viewpager);
		MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
		flags = new int[] { R.drawable.duomingyao, R.drawable.feiyannv,
				R.drawable.gujinling, R.drawable.humeiren,
				R.drawable.jianxiake, R.drawable.jumowang,
				R.drawable.longzhanjiang, R.drawable.mengzhuangshi,
				R.drawable.shentianbing, R.drawable.xiaomanyao };
		vp.setAdapter(adapter);
		rg.setOnCheckedChangeListener(this);
		vp.setOnPageChangeListener(this);
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
			// TODO Auto-generated method stub
			return rg.getChildCount();
		}

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.duomingyao:
			vp.setCurrentItem(0);
			break;
		case R.id.feiyannv:
			vp.setCurrentItem(1);
			break;
		case R.id.gujinling:
			vp.setCurrentItem(2);
			break;
		case R.id.humeiren:
			vp.setCurrentItem(3);
			break;
		case R.id.jianxiake:
			vp.setCurrentItem(4);
			break;
		case R.id.jumowang:
			vp.setCurrentItem(5);
			break;
		case R.id.longzhanjiang:
			vp.setCurrentItem(6);
			break;
		case R.id.mengzhuangshi:
			vp.setCurrentItem(7);
			break;
		case R.id.shentianbing:
			vp.setCurrentItem(8);
			break;
		case R.id.xiaomanyao:
			vp.setCurrentItem(9);
			break;
		default:
			break;
		}
	}

	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total = (int) ((position + positionOffset) * rg.getChildAt(0)
				.getWidth());
		int left = (vp.getWidth() - rg.getChildAt(0).getWidth()) / 2;
		int scrollX = total - left;
		hsv.scrollTo(scrollX, 0);
		getMove(position, positionOffset);
	}
	/**
	 * line»¬¶¯
	 * @param position
	 * @param positionOffset
	 */
	private void getMove(int position, float positionOffset) {
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		// ×ø±ê
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		TranslateAnimation animation = new TranslateAnimation(fromX,
				location[0] + positionOffset * rb.getWidth(), 0, 0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX = (int) (location[0] + positionOffset * rb.getWidth());
		tv.startAnimation(animation);
	}

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}