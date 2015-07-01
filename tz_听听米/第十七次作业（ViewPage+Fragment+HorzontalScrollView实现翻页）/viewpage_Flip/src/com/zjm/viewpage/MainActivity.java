package com.zjm.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zjm.viewpage.former.RotateDownPageTransformer;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener,OnCheckedChangeListener {
	HorizontalScrollView hscroll;
	ViewPager vp;
	private RadioGroup rg;
	View line;
	private int[] flags;
	private int rbWidth;// radioButton的宽度
	private int fromX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
	}

	/**
	 * 初始化界面
	 * 
	 * @author 邹继明
	 * @date 2015-7-1下午5:16:06
	 * @return void
	 */
	private void initView() {
		hscroll = (HorizontalScrollView) findViewById(R.id.hscroll);
		vp = (ViewPager) findViewById(R.id.vp);
		vp.setPageTransformer(true, new RotateDownPageTransformer());
		vp.addOnPageChangeListener(this);
		rg = (RadioGroup) findViewById(R.id.rg);
		rg.setOnCheckedChangeListener(this);
		line = findViewById(R.id.line);
		flags = new int[] { R.drawable.aries, R.drawable.taurus,
				R.drawable.gemini, R.drawable.cancer, R.drawable.leo,
				R.drawable.virgo, R.drawable.libra, R.drawable.scorpio,
				R.drawable.sagittarius, R.drawable.capricorn,
				R.drawable.aquarius, R.drawable.pisces };
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		rbWidth = rg.getChildAt(0).getWidth();
	}

	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			MyFragment myFragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("resid", flags[position]);
			// bundle.putInt("position", position);
			myFragment.setArguments(bundle);
			return myFragment;
		}

		@Override
		public int getCount() {
			return rg.getChildCount();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			Log.i("INFO", "第" + position + "个fragment被销毁");
			super.destroyItem(container, position, object);
		}

	}
	

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
		case R.id.aries:
			vp.setCurrentItem(0);
			break;
		case R.id.taurus:
			vp.setCurrentItem(1);
			break;
		case R.id.gemini:
			vp.setCurrentItem(2);
			break;
		case R.id.cancer:
			vp.setCurrentItem(3);
			break;
		case R.id.leo:
			vp.setCurrentItem(4);
			break;
		case R.id.virgo:
			vp.setCurrentItem(5);
			break;
		case R.id.libra:
			vp.setCurrentItem(6);
			break;
		case R.id.scorpio:
			vp.setCurrentItem(7);
			break;
		case R.id.sagittarius:
			vp.setCurrentItem(8);
			break;
		case R.id.capricorn:
			vp.setCurrentItem(9);
			break;
		case R.id.aquarius:
			vp.setCurrentItem(10);
			break;
		case R.id.pisces:
			vp.setCurrentItem(11);
			break;

		default:
			break;
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// 选中的radiobutton左边左边的长度
		int total = (int) ((position + positionOffset) * rbWidth);
		// 选中的radiobutton的x坐标,保持居中
		int left = (vp.getWidth() - rbWidth) / 2;
		// HorizontalScrollView移动的距离
		int scrollX = total - left;
		hscroll.scrollTo(scrollX, 0);
		moveLine(position, positionOffset);

	}

	/**
	 * 滑块的移动
	 * 
	 * @author 邹继明
	 * @date 2015-7-1下午6:59:26
	 * @param position
	 * @param positionOffset
	 * @return void
	 */
	private void moveLine(int position, float positionOffset) {
		// 被选中的RadioButton
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		// 获取被选中RadioButton的坐标
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		Log.i("INFO", "偏移："+positionOffset * rb.getWidth());
		TranslateAnimation tanimation = new TranslateAnimation(
				fromX,
				location[0] + positionOffset * rb.getWidth(), 
				0, 
				0);
		tanimation.setDuration(50);
		tanimation.setFillAfter(true);
		line.startAnimation(tanimation);
		fromX = (int) (location[0] + positionOffset * rb.getWidth());
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageSelected(int arg0) {

	}

}
