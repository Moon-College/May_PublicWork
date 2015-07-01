package com.tzmichael.activity;

import com.nineoldandroids.view.ViewHelper;
import com.tzmichael.fragment.MyFragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class NewsClentFramworkTestActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
	
	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private TextView tv_item;
	private ViewPager vp;
	private float fromXDelta=0.0f;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        rg.setOnCheckedChangeListener(this);
        vp.setOnPageChangeListener(this);
    }

	private void findViews() {
		hsv=(HorizontalScrollView) findViewById(R.id.hsv);
		rg=(RadioGroup) findViewById(R.id.rg);
		tv_item=(TextView) findViewById(R.id.tv_item);
		vp=(ViewPager) findViewById(R.id.vp);
	}
	
	class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			MyFragment fg=new MyFragment();
			Bundle bundle=new Bundle();
			bundle.putInt("position", position);
			fg.setArguments(bundle);
			return fg;
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
		}
		
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb1:
			vp.setCurrentItem(0, true);
			break;
		case R.id.rb2:
			vp.setCurrentItem(1, true);
			break;
		case R.id.rb3:
			vp.setCurrentItem(2, true);
			break;
		case R.id.rb4:
			vp.setCurrentItem(3, true);
			break;
		case R.id.rb5:
			vp.setCurrentItem(4, true);
			break;
		case R.id.rb6:
			vp.setCurrentItem(5, true);
			break;
		case R.id.rb7:
			vp.setCurrentItem(6, true);
			break;
		default:
			break;
		}
	}

	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		Log.i("vp--", position+"---"+positionOffset+"===="+positionOffsetPixels);
		int total=(int) ((positionOffset+position)*rg.getChildAt(0).getWidth());
		int leftX=(vp.getWidth()-rg.getChildAt(0).getWidth())/2;
		hsv.smoothScrollTo((int) (total-leftX), 0);
//		scrollItem(position,positionOffset);
		scrollItemByAttributAnimation(position,positionOffset);
	}

	/**
	 * 滑动选择条--使用补间动画
	 * @param position
	 * @param positionOffset
	 */
	private void scrollItem(int position,float positionOffset) {
		int[] loc=new int[2];
		rg.getChildAt(position).getLocationInWindow(loc);
		TranslateAnimation ta=new TranslateAnimation(
				fromXDelta,
				loc[0]+positionOffset*rg.getChildAt(0).getWidth(),
				0,
				0);
		ta.setDuration(50);
		ta.setFillAfter(true);
		tv_item.startAnimation(ta);
		fromXDelta=loc[0]+positionOffset*rg.getChildAt(0).getWidth();
	}

	/**
	 * 使用属性动画
	 * @param position
	 * @param positionOffset
	 */
	private void scrollItemByAttributAnimation(int position,float positionOffset){
		int[] loc=new int[2];
		rg.getChildAt(position).getLocationInWindow(loc);
		ViewHelper.setTranslationX(tv_item, loc[0]+positionOffset*rg.getChildAt(0).getWidth());
	}
	
	public void onPageSelected(int position) {
		
	}

	public void onPageScrollStateChanged(int state) {
		
	}
}