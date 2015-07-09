package com.xigua.guideviewpager;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
	
	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private ViewPager viewPager;
	private TextView tvLine;
	private int [] pics;
	private int fromX;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		MyPageAdapter mypageAdapter = new MyPageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mypageAdapter);
        rg.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
        
	}
	private void init() {
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        rg = (RadioGroup) findViewById(R.id.rgroup);
        tvLine = (TextView) findViewById(R.id.tvline);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pics = new int[]{
        		R.drawable.naruto,
        		R.drawable.onepiece,
        		R.drawable.tennis,
        		R.drawable.basketball,
        		R.drawable.bakuman,
        		R.drawable.dragonball,
        };
	}

	class MyPageAdapter extends FragmentPagerAdapter{

		public MyPageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
		}


		@Override
		public android.support.v4.app.Fragment getItem(int arg0) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("pic", pics[arg0]);
			bundle.putInt("position", arg0);
			fragment.setArguments(bundle);
			return fragment;
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//��arg0��viewpager��λ�����һ��viewpagerλ�õľ���
		int total = (int) ((arg0+arg1)*rg.getChildAt(0).getWidth());
		//��ȡRadiogroup�Ŀ�ȵ�һ�룬��Ϊ����match_parent����ֱ��ȡviewpager�Ŀ��
		int left = (viewPager.getWidth()-rg.getChildAt(0).getWidth())/2;
		//horizontalsrcollview��Ҫ�����ľ���
		int scrollX = total - left;
		//�������Ϊ��
		hsv.scrollTo(scrollX, 0);
		moveLine(arg0,arg1);
	}
	private void moveLine(int arg0, float arg1) {
		//arg0��ʾ�����ڼ���ѡ�ѡ��
		RadioButton rb = (RadioButton) rg.getChildAt(arg0);
		//��ȡ��һ��RadioButton����Ļ�е�λ��
		int [] location = new int [2];
		rb.getLocationInWindow(location);
		//����λ�ƶ���������Ӧ�İ�ť��λ��
		TranslateAnimation animation = new TranslateAnimation(
				fromX,
				location[0]+arg1*rb.getWidth(), 
				0, 
				0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX = (int) (location[0]+arg1*rb.getWidth());
		tvLine.startAnimation(animation);		
	}
	
	@Override
	public void onPageSelected(int arg0) {
		
	}
	
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.naruto:
			viewPager.setCurrentItem(0);
			break;
		case R.id.onepiece:
			viewPager.setCurrentItem(1);
			break;
		case R.id.tennis:
			viewPager.setCurrentItem(2);
			break;
		case R.id.basketball:
			viewPager.setCurrentItem(3);
			break;
		case R.id.bakuman:
			viewPager.setCurrentItem(4);
			break;
		case R.id.dragonball:
			viewPager.setCurrentItem(5);
			break;
		default:
			break;
		}
	}

}
