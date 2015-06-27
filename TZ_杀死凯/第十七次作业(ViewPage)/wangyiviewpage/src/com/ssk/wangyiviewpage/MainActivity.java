package com.ssk.wangyiviewpage;

import com.ssk.fragment.MyFragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends FragmentActivity implements OnPageChangeListener {

	private View v;
	private int fromX;
	private ViewPager vp;
	private HorizontalScrollView hsv;
	private RadioGroup rg;
	private RadioButton headline,recreation,hot,sports,finance,science,picture,car,war;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	private void initView() {
		v = (View) findViewById(R.id.v);
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		vp=(ViewPager) findViewById(R.id.vp);
		rg=(RadioGroup) findViewById(R.id.rg);
		headline=(RadioButton) findViewById(R.id.headline);
		recreation=(RadioButton) findViewById(R.id.recreation);
		hot=(RadioButton) findViewById(R.id.hot);
		sports=(RadioButton) findViewById(R.id.sports);
		finance=(RadioButton) findViewById(R.id.finance);
		science=(RadioButton) findViewById(R.id.science);
		picture=(RadioButton) findViewById(R.id.picture);
		car=(RadioButton) findViewById(R.id.car);
		war=(RadioButton) findViewById(R.id.war);
		MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
		vp.setAdapter(adapter);
		//翻页事件
		vp.setOnPageChangeListener(this);
	}
	private class MyAdapter extends FragmentPagerAdapter{
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int position) {
			MyFragment fragment=new MyFragment();
			Bundle bundle=new Bundle();
			bundle.putInt("position", position);
			fragment.setArguments(bundle);//传参数
			return fragment;
		}
		@Override
		public int getCount() {
			return rg.getChildCount();
		}
		
	}
	@Override
	public void onPageScrollStateChanged(int State) {
		//State 状态
	}
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		//position条数
		//positionOffset 从右滑左0-1,停止为0
		//positionOffsetPixels X像素
		Log.i("INFO", "position="+position+"/positionOffset="+positionOffset+"/positionOffsetPixels="+positionOffsetPixels);
	    int total=(int) ((position+positionOffset)*headline.getWidth());
	    int mediate=(vp.getWidth()-headline.getWidth())/2;
	    //RadioButton当前位置-RadioButton居中后左边的位置
	    int dx=total-mediate;
	    hsv.smoothScrollTo(dx,0);
	    //下划线位置
		lineScroll(position, positionOffset);
	}
	@Override
	public void onPageSelected(int position) {
		//position当前条数
	}
	private void lineScroll(int position, float positionOffset) {
		RadioButton child= (RadioButton) rg.getChildAt(position);
		int [] cxy=new int[2];
		//获得控件在当前屏幕的XY坐标
		child.getLocationInWindow(cxy);
		//位移动画
		TranslateAnimation animation=new TranslateAnimation(
				fromX, 
				cxy[0]+(positionOffset*child.getWidth()), 
				0, 
				0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX=(int) (cxy[0]+(positionOffset*child.getWidth()));
		v.startAnimation(animation);
		Log.i("INFO",cxy[0]+"");
	}

}
