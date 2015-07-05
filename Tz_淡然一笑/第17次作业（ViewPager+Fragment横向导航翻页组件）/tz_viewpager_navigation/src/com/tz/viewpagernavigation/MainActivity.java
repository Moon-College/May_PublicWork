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
	private int[] imagesId;
	private int fromX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initView();
		setListener();
	}

	private void initView() {
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		line = findViewById(R.id.line);

		imagesId = new int[] { R.drawable.gaoyuanyuan, R.drawable.fanbingbing, R.drawable.linzhilng,
				R.drawable.linqingxia, R.drawable.wangzuxain, R.drawable.yangmi, R.drawable.liuyifei,
				R.drawable.zhouxun };
	}

	private void setListener() {
		// ViewPager里面放fragment，此处可以直接在MyAdapter中返回就可以了，Fragment和Activity没关系
		// List<Fragment> fragments = new ArrayList<Fragment>();

		MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
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
			bundle.putInt("path", imagesId[arg0]);
			bundle.putInt("position", arg0);
			fragment.setArguments(bundle);
			return fragment;  //直接返回fragment，因为fragment有自己的生命周期
		}

		// 决定有多少页,和rb一样
		@Override
		public int getCount() {
			return rg.getChildCount();
		}
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_gaoyuanyuan:
			viewPager.setCurrentItem(0);
			break;
		case R.id.rb_fanbingbing:
			viewPager.setCurrentItem(1);
			break;
		case R.id.rb_linzhiling:
			viewPager.setCurrentItem(2);
			break;
		case R.id.rb_linqingxia:
			viewPager.setCurrentItem(3);
			break;
		case R.id.rb_wangzuxian:
			viewPager.setCurrentItem(4);
			break;
		case R.id.rb_yangmi:
			viewPager.setCurrentItem(5);
			break;
		case R.id.rb_liuyifei:
			viewPager.setCurrentItem(6);
			break;
		case R.id.rb_zhouxun:
			viewPager.setCurrentItem(7);
			break;

		default:
			break;
		}

	}

	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		int total = (int) ((position+positionOffset)*rg.getChildAt(0).getWidth());
		int left = (viewPager.getWidth()-rg.getChildAt(0).getWidth())/2;
		int scrollDx = total - left;
		hsv.scrollTo(scrollDx, 0);
		moveLine(position, positionOffset);
	}

	private void moveLine(int position, float positionOffset) {
		// position表示将来第几个选项被选中
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		// 利用位移动画滑到对应的按钮的位置
		TranslateAnimation animation = new TranslateAnimation(fromX, location[0] + positionOffset * rb.getWidth(), 0, 0);
		animation.setDuration(100); // 持续时间
		animation.setFillAfter(true); // 滑动后停下来
		fromX = (int) (location[0] + positionOffset * rb.getWidth());
//		line.setAnimation(animation);
		line.startAnimation(animation);  //注意此处要调用startAnimation，而不是设置setAnimation
	}

	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

	}
}
