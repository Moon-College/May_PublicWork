package com.tz.viewpagernavigation;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {

	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private ViewPager viewPager;
	private View line;
	private int[] images;
	private int fromX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		line = findViewById(R.id.line);

		MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
		
		images = new int[] { 
				R.drawable.china,
				R.drawable.korea, 
				R.drawable.nkorea, 
				R.drawable.japan, 
				R.drawable.usa, 
				R.drawable.india,
				R.drawable.tail 
			};

		// ViewPager里面放fragmnet
//		 List<Fragment> fragments = new ArrayList<Fragment>();
		
		viewPager.setAdapter(adapter);

		rg.setOnCheckedChangeListener(this);
		viewPager.setOnPageChangeListener(this);
	}
	

	// ViewPager适配器
	class MyAdapter extends FragmentPagerAdapter {
		
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		// 决定每一页是什么样
		@Override
		public Fragment getItem(int arg0) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("path", images[arg0]);
			bundle.putInt("position", arg0);
			fragment.setArguments(bundle);
			return fragment;
		}

		// 决定有多少页,和rb一样
		@Override
		public int getCount() {
			return rg.getChildCount();
		}
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_china:
			viewPager.setCurrentItem(0);
			break;
		case R.id.rb_korea:
			viewPager.setCurrentItem(1);
			break;
		case R.id.rb_japan:
			viewPager.setCurrentItem(2);
			break;
		case R.id.rb_usa:
			viewPager.setCurrentItem(3);
			break;
		case R.id.rb_north_korea:
			viewPager.setCurrentItem(4);
			break;
		case R.id.rb_india:
			viewPager.setCurrentItem(5);
			break;
		case R.id.rb_tail:
			viewPager.setCurrentItem(6);
			break;

		default:
			break;
		}

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		int total = (int) ((position + positionOffset) * rg.getChildAt(0).getWidth());
		int left = (viewPager.getWidth() - rg.getChildAt(0).getWidth())/2;
		int scrollDx = total - left;
		hsv.scrollTo(scrollDx, 0);
		moveLine(position, positionOffset);
	}
	
	private void moveLine(int position, float positionOffset){
		//position表示将来第几个选项被选中
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		//利用位移动画滑到对应的按钮的位置
		TranslateAnimation ta = new TranslateAnimation(fromX, location[0]+positionOffset*rb.getWidth(), 0, 0);
		ta.setDuration(100);	//持续时间
		ta.setFillAfter(true); //滑动后停下来
		fromX = (int) (location[0]+positionOffset*rb.getWidth());
		line.setAnimation(ta);
	}
	
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub 

	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

	}
}
