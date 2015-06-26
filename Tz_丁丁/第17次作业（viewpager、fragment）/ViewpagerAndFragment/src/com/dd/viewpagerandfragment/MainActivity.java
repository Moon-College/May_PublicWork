package com.dd.viewpagerandfragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnCheckedChangeListener {
	HorizontalScrollView hsv;
    RadioGroup rg;
    ViewPager viewPager;
    TextView tvLine;
    int [] flags;
    int fromX;
	private List<Fragment> fragments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        rg = (RadioGroup) findViewById(R.id.rg);
        tvLine = (TextView) findViewById(R.id.tvline);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //根据屏幕宽度适配组件宽带
        int[] screenWH = getScreenWH(MainActivity.this);
        for (int i = 0; i < rg.getChildCount(); i++) {
        	RadioButton childAt = (RadioButton) rg.getChildAt(i);
        	childAt.getLayoutParams().width = screenWH[0] / 4;
		}
        tvLine.getLayoutParams().width = screenWH[0] / 4;
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
//        fragments = new ArrayList<Fragment>();
//        MyFragment china = new MyFragment();
//        MyFragment2 china2 = new MyFragment2();
//        MyFragment3 china3 = new MyFragment3();
//        MyFragment4 china4 = new MyFragment4();
//        fragments.add(china);
//        fragments.add(china2);
//        fragments.add(china3);
//        fragments.add(china4);
        flags = new int[]{
        		R.drawable.china,
        		R.drawable.korea,
        		R.drawable.nkorea,
        		R.drawable.japan,
        		R.drawable.usa,
        		R.drawable.india,
        		R.drawable.tail
        };
        viewPager.setAdapter(adapter);
        rg.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
	}
	/**
	 * 获取屏幕的宽高
	 * @param context
	 * @return
	 */
	private int[] getScreenWH(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		return new int[] { outMetrics.widthPixels, outMetrics.heightPixels };
	}
	private class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}
		//决定每一页什么样
		@Override
		public Fragment getItem(int arg0) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			Log.v("home", "arg0"+arg0);
			bundle.putInt("id", flags[arg0]);
			bundle.putInt("position", arg0);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
//			return fragments.size();
		}
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total = (int) ((position+positionOffset)*rg.getChildAt(0).getWidth());
		int left = (viewPager.getWidth() - rg.getChildAt(0).getWidth())/2;
		int scrollX = total - left;
		hsv.scrollTo(scrollX, 0);
		moveLine(position,positionOffset);
	}
	private void moveLine(int position, float positionOffset) {
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		int [] loaction = new int [2];
		rb.getLocationInWindow(loaction);
		TranslateAnimation animation = new TranslateAnimation(fromX, loaction[0]+rb.getWidth()*positionOffset, 0, 0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX = (int) (loaction[0]+rb.getWidth()*positionOffset);
		tvLine.startAnimation(animation);
	}
	//改变选中项目颜色
	@Override
	public void onPageSelected(int arg0) {
		for (int i = 0; i < rg.getChildCount(); i++) {
			rg.getChildAt(i).setBackgroundColor(0xffffffff);
		}
		rg.getChildAt(arg0).setBackgroundColor(0xffff0000);
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

}
