package com.view.viewpagees;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.viewpagerradiogroup.R;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener, OnTouchListener{
	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private ViewPager viewPager;
	private TextView tvLine;
	private int[] flags;
	private int fromX;
	private Handler handler =new Handler() {
		public void handleMessage(android.os.Message msg) {
			LayoutParams lp = (LayoutParams) tvLine.getLayoutParams();
			lp.width =  msg.what;
			tvLine.setLayoutParams(lp);
		};
	};
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);
		init();
		
		MyRadioButton rt =new MyRadioButton(this);
		rt.setBack(this.getResources().getDrawable(R.drawable.radiobtn_bg));
		rt.setTextes("Qwe");
		rt.setBtn(false);
		rt.setPaddings(30);
		rt.setColors(Color.RED);
		rg.addView(rt);
		
		MyViewPager mvp = new MyViewPager(getSupportFragmentManager());
		viewPager.setAdapter(mvp);
		rg.setOnCheckedChangeListener(this);
		viewPager.setOnPageChangeListener(this);
		viewPager.setOnTouchListener(this);
		
	}
	class MyViewPager extends FragmentPagerAdapter{
		public MyViewPager(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			MyPager mp = new MyPager();
			Bundle bundle = new Bundle();
			bundle.putInt("img", flags[arg0]);
			mp.setArguments(bundle);
			return mp;
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
		}
		
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	float pf = 0;
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		int width = 0;
		if (position == 0) {
			width = (int) (rg.getChildAt(position).getWidth() * positionOffset);
		} else {
			for (int i = 0; i <= position - 1; i++) {
				width += rg.getChildAt(i).getWidth();
			}
			width += (int) (rg.getChildAt(position).getWidth() * positionOffset);;
		}
		int left = (viewPager.getWidth() - rg.getChildAt(position).getWidth())/2;
		hsv.scrollTo(width - left, 0);
		
		if (positionOffset - pf > 0) {
			pf = positionOffset;
			handler.sendEmptyMessage((int) (rg.getChildAt(position + 1).getWidth()));
		}
		if (positionOffset - pf < 0 || positionOffset == 0) {
			pf = positionOffset;
			handler.sendEmptyMessage((int) (rg.getChildAt(position).getWidth()));
		}
		
		RadioButton bt = (RadioButton) rg.getChildAt(position);
		int[] local = new int[2];
		bt.getLocationInWindow(local);
		TranslateAnimation tsa = new TranslateAnimation(fromX, local[0] + positionOffset * bt.getWidth(), 0, 0);
		tsa.setFillAfter(true);
		tsa.setDuration(100);
		fromX = (int) (local[0] + positionOffset * bt.getWidth());
		tvLine.startAnimation(tsa);
		
	}
	@Override
	public void onPageSelected(int arg0) {
		
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int postion = 0;
		for (int i = 0; i < rg.getChildCount(); i++) {
			if (group.getChildAt(i).getId() == checkedId) {
				postion = i;
			}
		}
		handler.sendEmptyMessage(rg.getChildAt(postion).getWidth());
		viewPager.setCurrentItem(postion);
	}
	private void init() {
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		tvLine = (TextView) findViewById(R.id.tvline);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		
		flags = new int[]{
        		R.drawable.china,
        		R.drawable.korea,
        		R.drawable.nkorea,
        		R.drawable.japan,
        		R.drawable.usa,
        		R.drawable.india,
        		R.drawable.tail,
        		R.drawable.usa
        };
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

}
