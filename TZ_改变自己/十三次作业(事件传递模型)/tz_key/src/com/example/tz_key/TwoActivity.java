package com.example.tz_key;

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
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

public class TwoActivity extends Activity implements OnTouchListener {

	private ScrollView scrollView;
	private GridView gridView;
	private List<Map<String, String>> data;
	private int down_y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		//秘书放行
		scrollView.requestDisallowInterceptTouchEvent(true);
		gridView = (GridView) findViewById(R.id.gv);
		gridView.setNumColumns(3);
		data = new ArrayList<Map<String, String>>();

		for (int i = 0; i < 100; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "item" + i);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
				new String[] { "item" }, new int[] { R.id.item_tv });

		gridView.setAdapter(adapter);
		gridView.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.e("wdj","onTouch");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("wdj","ACTION_DOWN");
			down_y = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("wdj","ACTION_MOVE");
			//让scrollView乡下传递事件
			int cur_y=(int) event.getY();
			if(gridView.getChildAt(0).getTop() == 0 && cur_y - down_y >= 0) {
				scrollView.requestDisallowInterceptTouchEvent(false);
			} else if(gridView.getChildAt(gridView.getChildCount() - 1).getBottom() == gridView.getHeight()
					&& cur_y - down_y <= 0) {
				scrollView.requestDisallowInterceptTouchEvent(false);
			} else {
				scrollView.requestDisallowInterceptTouchEvent(true);
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.e("wdj","ACTION_UP");
			break;

		default:
			break;
		}
		return false;
	}
}
