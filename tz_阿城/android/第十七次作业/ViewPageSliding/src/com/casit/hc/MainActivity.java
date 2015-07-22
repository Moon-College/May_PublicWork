package com.casit.hc;

import android.app.Activity;
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
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
	
    /** Called when the activity is first created. */
  
	HorizontalScrollView hsv;
	RadioGroup rg;
	ViewPager viewpager;
	TextView textView;
	int[] flags;
	int fromX = 0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        hsv = (HorizontalScrollView) findViewById(R.id.hv);
        rg = (RadioGroup) findViewById(R.id.rg);
        viewpager = (ViewPager) findViewById(R.id.vp);
        textView = (TextView) findViewById(R.id.teline);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewpager.setAdapter(myAdapter);
        flags = new int[]{
        		R.drawable.china,
        		R.drawable.korea,
        		R.drawable.nkorea,
        		R.drawable.japan,
        		R.drawable.america,
        		R.drawable.india,
        		R.drawable.tail
        };
        rg.setOnCheckedChangeListener(this);
        viewpager.setOnPageChangeListener(this);
    }
	private class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			MyFragment myFragment = new MyFragment();
			Bundle bud = new Bundle();
			bud.putInt("path", flags[arg0]);
			bud.putInt("position", arg0);
			myFragment.setArguments(bud);
			return myFragment;
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
		case R.id.china:
			viewpager.setCurrentItem(0);
			break;
		case R.id.korea:
			viewpager.setCurrentItem(1);			
			break;
		case R.id.nkorea:
			viewpager.setCurrentItem(2);			
			break;
		case R.id.japan:
			viewpager.setCurrentItem(3);			
			break;
		case R.id.america:
			viewpager.setCurrentItem(4);			
			break;
		case R.id.india:
			viewpager.setCurrentItem(5);			
			break;
		case R.id.tail:
			viewpager.setCurrentItem(6);			
			break;
		default:
			break;
		}	
	}
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	public void onPageScrolled(int position, float arg1, int arg2) {
		// TODO Auto-generated method stub
		int total =(int) (( position+arg1)*rg.getChildAt(0).getWidth());
	    int left = (viewpager.getWidth() -rg.getChildAt(0).getWidth())/2;
	    int scrollX = total - left;
	    hsv.scrollTo(scrollX, 0);
	    moveLine(position,arg1);
	}
	private void moveLine(int position, float positionoffset){
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		TranslateAnimation animation = new TranslateAnimation(
				fromX, 
				location[0]+positionoffset*rb.getWidth(), 
				0, 
				0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX = (int) (location[0] + positionoffset*rb.getWidth());
		textView.startAnimation(animation);
	}
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}