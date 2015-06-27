package com.example.touchdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity implements OnTouchListener, OnScrollListener {

	private ListView lv;
	private GridView grid;
	private ScrollView scroll;
	private List<Map<String, String>> maps;
	private boolean isButton = false;
	private int lastposition;
	private SimpleAdapter adapter;
	private float downY;
	private int topposition;
	private boolean istop= false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		for (int i = 0; i < 50; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", "姓名：" + i + "号");
			maps.add(map);
		}

		adapter = new SimpleAdapter(MainActivity.this, maps,
				R.layout.item_layout, new String[] { "title" },
				new int[] { R.id.textview1 });
		lv.setAdapter(adapter);
		grid.setAdapter(adapter);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.listview);
		grid = (GridView) findViewById(R.id.gridview);
		maps = new ArrayList<Map<String, String>>();
		scroll = (ScrollView) findViewById(R.id.scroll);
		scroll.requestDisallowInterceptTouchEvent(true);
		lv.setOnTouchListener(this);
		grid.setOnTouchListener(this);
		lv.setOnScrollListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(isButton == true){
				scroll.requestDisallowInterceptTouchEvent(false);
			}else{
				scroll.requestDisallowInterceptTouchEvent(true);
			}
			downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			scroll.requestDisallowInterceptTouchEvent(true);
			if(isButton == false && event.getY() < downY ){
				//向上划
				System.out.println("dd");
				scroll.requestDisallowInterceptTouchEvent(true);
			}else if(isButton == true &&  event.getY() < downY ){
				System.out.println("ww");
				//往下划
				scroll.requestDisallowInterceptTouchEvent(false);
				System.out.println("move"+event.getY());
			}else if ( istop == true ) {
				System.out.println("ee");
				scroll.requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(adapter.getCount() == lastposition && scrollState == SCROLL_STATE_IDLE){
			isButton = true;
		}else{
			isButton = false;
		}
		if(topposition==0 && scrollState == SCROLL_STATE_IDLE){
			istop = true;
		}else{
			istop = false;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		lastposition = firstVisibleItem + visibleItemCount;
		topposition = firstVisibleItem;
	}

}
