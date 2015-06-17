package com.rocy.tzclass13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity implements OnTouchListener {

	private ListView lv ;
	private ScrollView sc;
	private boolean isUp ;
	private int index;
	private int srcoll ;
	float downY = 0;
	float moveY = 0;
	float getY = 0;
	private float moveY2;
	private float downY2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sc = (ScrollView)findViewById(R.id.sv);
		lv =(ListView)findViewById(R.id.lv);
		List<Map<String, String>> list =new ArrayList<Map<String,String>>();
		for (int i = 0; i < 20; i++) {
			Map<String, String> map =new HashMap<String, String>();
			map.put("name", "小明"+i);
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.listview, new String[]{"name"}, new int[]{R.id.tv});
		lv.setAdapter(adapter);
		lv.setOnTouchListener(this);
		lv.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				index=arg1;
				srcoll = arg2;
				Log.i("INFO", "ACTION_onScroll"+arg1+","+arg2);
			}
		});
		
		
	}
	
	
	public void goNext(View v){
		
	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
		switch (arg0.getId()) {
		case R.id.lv:
			//捕获触摸动作
			switch (arg1.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//按下去的时候LV的位置
				downY2=arg1.getRawY();
				downY=lv.getChildAt(0).getY();
				Log.i("INFO", "ACTION_DOWN"+getY+","+downY);
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("INFO", "ACTION_UP");
				moveY = lv.getChildAt(0).getY();
				moveY2 = arg1.getRawY();
				if(downY >= moveY && downY2 > moveY2  ){
					//向下
					isUp = false;
				}else{
					isUp = true ;
				}
				if(index == 0 && srcoll == 1 && isUp ){
					sc.requestDisallowInterceptTouchEvent(false);
				}else if(index == lv.getCount()-1 && !isUp && srcoll == 1){
					sc.requestDisallowInterceptTouchEvent(false);
				}
				else{
					sc.requestDisallowInterceptTouchEvent(true);
				}
				Log.i("INFO", "ACTION_MOVE"+moveY);
				
				break;
			case MotionEvent.ACTION_UP:
			
				break;

			default:
				break;
			}
			
			
			
			

		default:
			break;
		
		}
		return super.onTouchEvent(arg1);
	}
}
