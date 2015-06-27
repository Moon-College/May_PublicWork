package com.tz.navigation;

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

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
    HorizontalScrollView hsv;
    RadioGroup rg;
    ViewPager viewPager;
    TextView tvLine;
    
    int [] flags; //国旗图片资源ID数组
    int fromX;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        rg = (RadioGroup) findViewById(R.id.rg);
        tvLine = (TextView) findViewById(R.id.tvline);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        
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
    
    private class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		//决定每一页是什么样
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			MyFragment fragment = new MyFragment();
			
			Bundle bundle = new Bundle();
			bundle.putInt("path", flags[arg0]);//麻布袋，放置图片资源ID
			bundle.putInt("position", arg0); //第几张图片
			
			fragment.setArguments(bundle);
			
			return fragment;
		}

		//决定有多少页
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

	//position表示将来第几个选项被选中（比如中国那一页，position=0）
	//positionOffset表示页面已经划过去多少比例（0到1）
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total = (int) ((position+positionOffset)*rg.getChildAt(0).getWidth());
		int left = (viewPager.getWidth()-rg.getChildAt(0).getWidth())/2; //此值为固定值，不会变化
		int scrollX = total - left;
		hsv.scrollTo(scrollX, 0); //滑动hsv组件
		
		moveLine(position,positionOffset); //滑动TextView组件
	}

	
	private void moveLine(int position, float positionOffset) {
		// TODO Auto-generated method stub
		//position表示将来第几个选项被选中（比如中国那一页，position=0）
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		//坐标
		int [] location = new int [2];
		rb.getLocationInWindow(location);
		//利用位移动画滑到对应的按钮的位置
		TranslateAnimation animation = new TranslateAnimation(
				fromX
				,location[0]+positionOffset*rb.getWidth(), 
				0, 
				0);
		animation.setDuration(50);//持续时间，稍短一点
		animation.setFillAfter(true);//滑动后停下来
		fromX = (int) (location[0]+positionOffset*rb.getWidth());
		tvLine.startAnimation(animation);
	}

	//当一个新页被选中时
	public void onPageSelected(int position) {
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		//rb.setTextColor(R.drawable.blue);
		rb.setTextSize(40.0f);
		//rb.setBackgroundColor(android.R.color.background_light);
		for (int i = 0; i < rg.getChildCount(); i++) {
			if (i != position) {
				RadioButton rbutton = (RadioButton) rg.getChildAt(i);
				//rbutton.setTextColor(R.drawable.white);
				rbutton.setTextSize(20.0f);
			}
		}
	}

	//页面滑动的状体改变时（摁下去，滑动，松开手页面停下来）
	public void onPageScrollStateChanged(int state) {
		
	}
}