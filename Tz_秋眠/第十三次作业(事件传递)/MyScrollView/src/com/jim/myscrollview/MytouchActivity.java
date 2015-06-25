package com.jim.myscrollview;

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

public class MytouchActivity extends Activity implements OnTouchListener {

	private ScrollView scrollView;
	private GridView gridView;
	private Map<String, Object> map;
	private List<Map<String, Object>> datas;
	private float downY;
	private float moveY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_touch);
		initData();
		initViews();
	}

	private void initData() {
		// TODO Auto-generated method stub
		datas = new ArrayList<Map<String, Object>>();
		for (int i = 0; i <= 50; i++) {
			map = new HashMap<String, Object>();
			map.put("item", "textView" + i);
			datas.add(map);
		}
	}

	private void initViews() {
		// TODO Auto-generated method stub
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		scrollView.requestDisallowInterceptTouchEvent(true);// 设置父容器不拦截Touch事件
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setOnTouchListener(this);
		SimpleAdapter adapter = new SimpleAdapter(this, datas,
				R.layout.item_grid, new String[] { "item" },
				new int[] { R.id.tvGrid });
		gridView.setAdapter(adapter);
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			Log.i("INFO", "gridview==>>down" + downY);
			break;
		case MotionEvent.ACTION_MOVE:
			moveY = event.getY();
			Log.i("INFO", "gridview==>>down" + moveY);
			if (moveY > downY + 5) {// 向上
				if (gridView.getChildAt(0).getTop() == 0) {
					scrollView.requestDisallowInterceptTouchEvent(false);// 如果gridView已经在最顶部，则scrollView截取滑动事件
				} else {
					scrollView.requestDisallowInterceptTouchEvent(true);// 如果gridView不在最顶部，则scrollView不截取滑动事件
				}
			} else if (moveY + 5 < downY) {// 向下
				if (gridView.getChildAt(gridView.getChildCount() - 1)
						.getBottom() == gridView.getHeight()) {
					scrollView.requestDisallowInterceptTouchEvent(false);// 如果gridView已经在最底端，则scrollView截取滑动事件
				} else {
					scrollView.requestDisallowInterceptTouchEvent(true);// 如果gridView不在最底端，则scrollView不截取滑动事件
				}
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
