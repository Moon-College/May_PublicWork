package com.vincen.event_delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

public class TouchEventActivity extends Activity implements OnTouchListener{
	
	private ScrollView sv;
	private ListView listView ;
	private List<Map<String,String>> data ;
	private int firstItem;
	private int lastItem;
	private int downY;
	private boolean isTop = false ;
	private boolean isBottom = false ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scollerview_listview);
		
		sv = (ScrollView) findViewById(R.id.sv);
		sv.requestDisallowInterceptTouchEvent(true);
		listView = (ListView) findViewById(R.id.listView);
		initData();
		
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, 
				new String[]{"item"}, 
				new int[]{R.id.item});
		
		listView.setAdapter(adapter);
		
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				firstItem = firstVisibleItem;
				lastItem = firstVisibleItem + visibleItemCount;
			}
			
			
		});
		
		listView.setOnTouchListener(this);
	}

	private void initData() {
		data = new ArrayList<Map<String,String>>();
		for(int i=0;i<50;i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "item"+i);
			data.add(map);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("info", "listView down");
			downY = (int) event.getY();   //获取手指按下是Y的坐标
			break;
		case MotionEvent.ACTION_MOVE:
			
			if(firstItem == listView.getChildAt(0).getTop() && event.getY()>downY){
				sv.requestDisallowInterceptTouchEvent(false);
			}else if(lastItem == listView.getCount() && event.getY()<downY){
				sv.requestDisallowInterceptTouchEvent(false);
			}else{
				sv.requestDisallowInterceptTouchEvent(true);
			}
			Log.i("info", "listView move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("info", "listView up");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	
}
