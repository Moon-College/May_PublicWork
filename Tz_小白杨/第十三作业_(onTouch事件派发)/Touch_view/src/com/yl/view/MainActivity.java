package com.yl.view;

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

public class MainActivity extends Activity implements OnTouchListener {
	private ScrollView sv;
	private GridView gv;
	List<Map<String, String>> data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sv = (ScrollView) findViewById(R.id.sv);
		sv.requestDisallowInterceptTouchEvent(true);
		gv = (GridView) findViewById(R.id.gv);
		gv.setOnTouchListener(this);
		init();
	}

	private void init() {
		data = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 80; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "item" + i);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.line,
				new String[] { "item" }, new int[] { R.id.tv });
		gv.setNumColumns(4);
		gv.setAdapter(adapter);
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "down");
			sv.requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "up");
			sv.requestDisallowInterceptTouchEvent(true);
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}