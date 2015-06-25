package com.tz.requestallow;

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
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

import com.tz.requestallow.R.layout;

public class MainActivity extends Activity implements OnTouchListener {
	private GridView gv;
	List<Map<String, String>> data;
	private ScrollView sv;

	private boolean isTop = false;
	private boolean isBootom = false;
	private int down_y;
	private int move_y;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gv = (GridView) findViewById(R.id.gv);
		sv = (ScrollView) findViewById(R.id.sv);
		gv.setNumColumns(3);
		data = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 50; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "item" + i);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data, layout.grid_item,
				new String[] { "item" }, new int[] { R.id.tv1 });
		gv.setAdapter(adapter);
		gv.setOnTouchListener(this);

		gv.setOnScrollListener(new AbsListView.OnScrollListener() {

			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
				
				if (firstVisibleItem == 0) {
					isTop = true;
				}

				if (visibleItemCount + firstVisibleItem == totalItemCount) {
					isBootom = true;
				}
			}
		});
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "gridView down");
			down_y = (int )event.getY();
			
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "gridView move");
			
			move_y = (int) event.getY();
			if( (isBootom && (move_y - down_y) <0) || (isTop && (move_y - down_y) >0)){
				sv.requestDisallowInterceptTouchEvent(false);// true表示放行
			}else{
				sv.requestDisallowInterceptTouchEvent(true);// true表示放行
			}
			
			isTop = false;
			isBootom = false;
			
			
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "gridView up");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}