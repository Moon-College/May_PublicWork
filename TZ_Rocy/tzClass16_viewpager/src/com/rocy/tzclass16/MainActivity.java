package com.rocy.tzclass16;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.BoringLayout.Metrics;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity  implements  OnPageChangeListener {
	private int rId [] = {R.drawable.china,
			R.drawable.korea,
			R.drawable.nkorea,
			R.drawable.japan,
			R.drawable.usa,
			R.drawable.india,
			R.drawable.tail};
	private ViewPager viewPager;
	private HorizontalScrollView hsv;
	private TextView tv;
	private RadioGroup rg;
	private RadioButton radio;
    private int dispalywidth;
    private int statesation =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.vp);
		MyAdatper adapter =new MyAdatper(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		hsv = (HorizontalScrollView)findViewById(R.id.hsv);
		tv = (TextView)findViewById(R.id.line);
		rg = (RadioGroup)findViewById(R.id.rg);
		radio  = (RadioButton)findViewById(R.id.btn_1);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		dispalywidth = metrics.widthPixels;
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.btn_1:
					viewPager.setCurrentItem(0);
					break;
				case R.id.btn_2:
					viewPager.setCurrentItem(1);
					break;
				case R.id.btn_3:
					viewPager.setCurrentItem(2);
					break;
				case R.id.btn_4:
					viewPager.setCurrentItem(3);
					break;
				case R.id.btn_5:
					viewPager.setCurrentItem(4);
					break;
				case R.id.btn_6:
					viewPager.setCurrentItem(5);
					break;
				case R.id.btn_7:
					viewPager.setCurrentItem(6);
					break;

				default:
					break;
				}
			}
		});
		
	}
	
	
	//适配器
	class MyAdatper extends FragmentPagerAdapter{

		public MyAdatper(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			MyFragment fragement = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("rId", rId[arg0]);
			fragement.setArguments(bundle);
			return fragement;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return rId.length;
		}
		
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * arg0 所在的页面
	 * arg1 表示滑动的bilv
	 * arg2 滑动屏幕的坐标
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		Log.i("nf", "rocy"+arg0+","+arg1+","+arg2);
		RadioButton childAt = (RadioButton) rg.getChildAt(arg0);
		int width = radio.getWidth();
		int sWidth=(int) ((dispalywidth-width)/2);
		int mWidht=(int) (childAt.getX()+arg1*childAt.getX());
		
		hsv.smoothScrollTo(mWidht-sWidth, 0);
		//绝对位置
		int location[] =new int [2]; 
		childAt.getLocationOnScreen(location);
		TranslateAnimation  animation  = new TranslateAnimation(statesation, location[0]+arg1*childAt.getWidth(), 0, 0);
		animation.setDuration(30);
		animation.setFillAfter(true);
		tv.setAnimation(animation);
		statesation = (int) (location[0]+arg1*childAt.getWidth());
	}


	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
