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
		scrollView.requestDisallowInterceptTouchEvent(true);// ���ø�����������Touch�¼�
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
			if (moveY > downY + 5) {// ����
				if (gridView.getChildAt(0).getTop() == 0) {
					scrollView.requestDisallowInterceptTouchEvent(false);// ���gridView�Ѿ����������scrollView��ȡ�����¼�
				} else {
					scrollView.requestDisallowInterceptTouchEvent(true);// ���gridView�����������scrollView����ȡ�����¼�
				}
			} else if (moveY + 5 < downY) {// ����
				if (gridView.getChildAt(gridView.getChildCount() - 1)
						.getBottom() == gridView.getHeight()) {
					scrollView.requestDisallowInterceptTouchEvent(false);// ���gridView�Ѿ�����׶ˣ���scrollView��ȡ�����¼�
				} else {
					scrollView.requestDisallowInterceptTouchEvent(true);// ���gridView������׶ˣ���scrollView����ȡ�����¼�
				}
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
