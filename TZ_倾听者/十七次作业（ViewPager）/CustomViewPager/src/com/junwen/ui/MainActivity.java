package com.junwen.ui;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.customviewpager.R;
import com.junwen.adapter.CustomFragAdapter;
import com.junwen.util.June;
import com.junwen.view.CustomRadioGroup;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener, OnPageChangeListener {

	// ViewPager
	private ViewPager pager;
	// RadioGroup
	private CustomRadioGroup group;
	// 滑动条
	private View view;
	// 适配器
	private CustomFragAdapter adapter;
	private float fromX;
	private HorizontalScrollView horziontal;
	// 图片素材
	private int[] imgs = { R.drawable.anglebaby, R.drawable.fanbingbing,
			R.drawable.gaoyuanyuan, R.drawable.liuyan, R.drawable.liuyifei,
			R.drawable.yanmi, R.drawable.zhaoliyin, R.drawable.zhenshuang

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	/*
	 * 初始化数据
	 */
	private void initData() {
		adapter = new CustomFragAdapter(getSupportFragmentManager(), imgs);
		pager.setAdapter(adapter);
		view.getLayoutParams().width = June.getScreenWidth(this) / 3;
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		pager = (ViewPager) findViewById(R.id.viewpager);
		group = (CustomRadioGroup) findViewById(R.id.radiogroup);
		view = findViewById(R.id.current);
		horziontal = (HorizontalScrollView) findViewById(R.id.horizontal);
		group.setOnCheckedChangeListener(this);
		pager.setOnPageChangeListener(this);
	}

	/**
	 * 当RadioButton更改选中的时候触发
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int index = 0;
		switch (checkedId) {
		case R.id.baby:
			index = 0;
			break;
		case R.id.bingbing:
			index = 1;
			break;
		case R.id.yuanyuan:
			index = 2;
			break;
		case R.id.liuyan:
			index = 3;
			break;
		case R.id.yifei:
			index = 4;
			break;
		case R.id.yanmi:
			index = 5;
			break;
		case R.id.liying:
			index = 6;
			break;
		case R.id.zhenshuang:
			index = 7;
			break;
		default:
			break;
		}
		//跳转页面
		pager.setCurrentItem(index);
		
		//更改选中的颜色
		for (int i = 0; i < group.getChildCount(); i++) {
			RadioButton childAt = (RadioButton) group.getChildAt(i);
			if (childAt.isChecked()) {
				childAt.setTextColor(Color.GREEN);
			} else {
				childAt.setTextColor(Color.BLACK);
			}
		}
	}

	/**
	 * 正在滑动时
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		//获取要滑动到指定的页面的值
		int total = (int) ((position + positionOffset) * group.getChildAt(0)
				.getWidth());
		//计算他左边的那个按钮宽度
		int left = (June.getScreenWidth(MainActivity.this) - group
				.getChildAt(0).getWidth()) / 2;
		//计算出移动到目标左边
		int scrollX = total - left;
		//移动Horizonl
		horziontal.smoothScrollTo(scrollX, 0);
		//移动游标
		moveView(position, positionOffset);
	}

	/**
	 * 滑动游标
	 * 
	 * @param position
	 * @param positionOffset
	 */
	private void moveView(int position, float positionOffset) {
		// 获取当前页面的RadioButton的坐标
		RadioButton childAt = (RadioButton) group.getChildAt(position);
		//计算当前的页面按钮
		int[] viewForXY = June.getViewForXY(childAt);
		//计算要滑动的值
		float scrollX = 	viewForXY[0] + positionOffset * childAt.getWidth() ;
		//移动动画
		ViewHelper.setTranslationX(view, scrollX);
	}

	/**
	 * 滑动后
	 */
	@Override
	public void onPageSelected(int position) {
		RadioButton childAt = (RadioButton) group.getChildAt(position);
		childAt.setChecked(true);
	}

	/**
	 * 滑动状态改变时
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		
	}
}
