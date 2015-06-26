/**
 * Project Name:lsn14_event
 * File Name:MainActivity.java
 * Package Name:com.zht.event
 * Date:2015-6-17下午3:54:43
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-17 下午3:54:43 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	private ScrollView sv;
	private ListView lv;
	private boolean isLvTop;
	private boolean isLvButtom;
	private SimpleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sv = (ScrollView) findViewById(R.id.sv);
		lv = (ListView) findViewById(R.id.lv);
		
		List<Map<String, String>> mData = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 20; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", String.valueOf(i));
			mData.add(map);
		}

		adapter = new SimpleAdapter(this, mData,
				android.R.layout.simple_list_item_1, new String[] { "item" },
				new int[] { android.R.id.text1 });
		lv.setAdapter(adapter);

		lv.setOnTouchListener(new OnTouchListener() {

			private float startY;
			private float curY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					curY = event.getY();
					//listView滑到最底部手指还继续往上滑 和 listView滑到最顶部手指还继续往下滑 这两种情况下scrollView拦截，其他情况不拦截
					if((isLvButtom && event.getY() < startY) || (isLvTop && event.getY() > startY)){
						sv.requestDisallowInterceptTouchEvent(false);
					}else{
						sv.requestDisallowInterceptTouchEvent(true);
					}
					startY = curY;
					break;
				case MotionEvent.ACTION_UP:
					break;
				default:
					break;
				}
				return onTouchEvent(event);
			}
		});

		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
                 switch (scrollState) {
				case SCROLL_STATE_IDLE://滑动停止
					//滑到顶部
					if(0 == view.getFirstVisiblePosition()){
						isLvTop = true;
					}else{
						isLvTop = false;
					}
					//滑到底部
					if(view.getCount()-1 == view.getLastVisiblePosition()){
						isLvButtom = true;
					}else{
						isLvButtom = false;
					}
					break;
				default:
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});

	}
}
