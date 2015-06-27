package com.knight.viewpager;

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

public class MainActivity extends FragmentActivity implements OnPageChangeListener, OnCheckedChangeListener {

	private ViewPager viewPager;
	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private View lineView;
	private HorizontalScrollView hScrollView;
	private int[] flags;
	private int formX;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MyFragPager adapter = new MyFragPager(getSupportFragmentManager());
        flags = new int[]{
        	R.drawable.landscape,
        	R.drawable.goose,
        	R.drawable.craft,
        	R.drawable.panda,
        	R.drawable.golaming,
        	R.drawable.boxter
        };
        viewPager.setAdapter(adapter);
    }


    private void initView() {
    	viewPager = (ViewPager) findViewById(R.id.viewpager);
    	hScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
    	radioGroup = (RadioGroup) findViewById(R.id.rad_group);
    	lineView = findViewById(R.id.line);
    	viewPager.setOnPageChangeListener(this);
    	radioGroup.setOnCheckedChangeListener(this);
	}


	private class MyFragPager extends FragmentPagerAdapter{

		public MyFragPager(FragmentManager fm) {
			super(fm);
			
		}
		//决定viewpager长什么样
		@Override
		public Fragment getItem(int arg0) {
			PageFragment fragment = new PageFragment();
			//把要显示的视图内容存储在bundle麻布袋中
			Bundle bundle = new Bundle();
			bundle.putInt("page", flags[arg0]);
			bundle.putInt("position", arg0);
			//通过fragment的setarguments把数据传递到fragment中
			fragment.setArguments(bundle);
			return fragment;
		}

		//决定这个viewpager有多少页
		@Override
		public int getCount() {
			//由于viewpager的页面数量跟RadioGroup中的RadioButton数量一致
			return radioGroup.getChildCount();
		}
    	
    }
    
    //页面滑动状态改变
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//求出滑动的距离
		int total = (int) ((arg0 + arg1)*radioGroup.getChildAt(0).getWidth());
		//每个Radiobutton居中距离父容器X方向距离
		int leftX = (viewPager.getWidth() - radioGroup.getChildAt(0).getWidth())/2;
		int scrollX = total - leftX;
		hScrollView.scrollTo(scrollX, 0);
		changeLine(arg0,arg1);
	}

	private void changeLine(int arg0, float arg1) {
		//arg0表示第几个按钮被选中
		radioButton = (RadioButton) radioGroup.getChildAt(arg0);
		//计算控件在屏幕中的坐标
		int[] location = new int[2];
		radioButton.getLocationInWindow(location);
		TranslateAnimation tAnimation = new TranslateAnimation(formX, 
				location[0]+arg1*radioButton.getWidth(), 0, 0);
		tAnimation.setDuration(50);//设置动画持续时间 50ms
		tAnimation.setFillAfter(true);//动画保持滑动后的状态
		formX = (int) (location[0]+arg1*radioButton.getWidth());
		lineView.startAnimation(tAnimation);
		
	}


	@Override
	public void onPageSelected(int arg0) {
		
	}

	//监听RadioGroup内的RadioButton的改变
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.landscape:
			//风景被选中
			viewPager.setCurrentItem(0);
			break;
		case R.id.goose:
			//家禽被选中
			viewPager.setCurrentItem(1);
			break;
		case R.id.craft:
			//艺术被选中
			viewPager.setCurrentItem(2);
			break;
		case R.id.panda:
			//熊猫被选中
			viewPager.setCurrentItem(3);
			break;
		case R.id.golaming:
			//黄昏被选中
			viewPager.setCurrentItem(4);
			break;
		case R.id.boxster:
			//名车被选中
			viewPager.setCurrentItem(5);
			break;
		default:
			break;
		}
		
	}



}
