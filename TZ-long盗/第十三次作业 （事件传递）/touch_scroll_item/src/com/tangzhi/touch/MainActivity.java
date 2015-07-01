package com.tangzhi.touch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tangzhi.touch.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private float touchY;
	private ListView listview;
	private ScrollView scroll;
	private List<Map<String, String>> date = new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		listview=(ListView)findViewById(R.id.touch_list);
		scroll=(ScrollView)findViewById(R.id.touch_scroll);
		initDate();
		SimpleAdapter adapter  = new SimpleAdapter(this,
				date, 
				layout.items, 
				new String[]{"item"},
				new int[]{R.id.name});
		listview.setAdapter(adapter);
		listview.setOnTouchListener(new lvOnTouchListener());
	}
	//加载数据
	private void initDate() {
		
		for (int i = 0; i < 30; i++) {
			Map<String , String> map = new HashMap<String, String>();
			map.put("item", "list"+i);
			date.add(map);
		}
		
	}
	private class lvOnTouchListener implements OnTouchListener{

		

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touchY=event.getY();
				Log.i("INFO", "ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("INFO", "ACTION_MOVE");
				float newY=event.getY();
				/**
				 * 1.如何当前滑到最底部，用户还在滑动时，交给上级scrollview处理
				 * 2.如果当前滑到最顶部，用户还在上滑时，交给上级scrollview处理
				 */
				if (listview.getLastVisiblePosition() == (listview.getCount()-1)
						&& newY <touchY) {
					scroll.requestDisallowInterceptTouchEvent(false);
				}else if(listview.getFirstVisiblePosition() == 0
						&& newY >touchY){
					scroll.requestDisallowInterceptTouchEvent(false);
				}else {
					scroll.requestDisallowInterceptTouchEvent(true);
				}
				touchY = newY;
				break;
			case MotionEvent.ACTION_UP:
				Log.i("INFO", "ACTION_UP");
				break;
			default:
				break;
			}
			return false;
		}
		
		
		
	}
	
}
