package com.main;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.listscroll.R;

public class MainActivity extends Activity implements OnTouchListener {

	private String IN = "event";
	private ListView listview;
	private TextView txt1, txt2;
	private ScrollView root;
	private MyLinear linear;
	private ArrayList<String> data = new ArrayList<String>();

	private int start = 0, showcount = 0, totalcount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview = (ListView) findViewById(R.id.listview);

		root = (ScrollView) findViewById(R.id.root);
		txt1 = (TextView) findViewById(R.id.txt1);
		txt2 = (TextView) findViewById(R.id.txt2);
		linear = (MyLinear) findViewById(R.id.linear);

		initData();
		listview.setOnTouchListener(this);
		listview.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {

			}

			@Override
			public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
				start = arg1;
				showcount = arg2;
				totalcount = arg3;
			}
		});

		txt1.setOnTouchListener(new mytouch());
		linear.setOnTouchListener(new mylinear());
	}

	class mytouch implements OnTouchListener {
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			switch (arg1.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.i(IN, "textView_Down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i(IN, "textView_Move");
				break;
			case MotionEvent.ACTION_UP:
				Log.i(IN, "textView_Up");
				break;
			default:
				break;
			}
			return false;
		}
	}

	class mylinear implements OnTouchListener {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			switch (arg1.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.i(IN, "linearView_Down");
				//更改Intercept 的返回值 并重绘
				linear.setIntercept(false);
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i(IN, "linearView_Move");
				break;
			case MotionEvent.ACTION_UP:
				Log.i(IN, "linearView_Up");
				break;
			default:
				break;
			}
			return false;
		}

	}

	private void initData() {
		for (int i = 0; i < 30; i++) {
			data.add("qqqqq" + i);
		}
		listview.setAdapter(new ArrayAdapter(this,
				android.R.layout.simple_expandable_list_item_1, data));
	}

	private int CurrentY = 0;

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_DOWN:
			root.requestDisallowInterceptTouchEvent(true);
			CurrentY = (int) arg1.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveY = (int) arg1.getY();
			int dis = moveY - CurrentY;
			if (dis > 0 && start == 0) {
				root.requestDisallowInterceptTouchEvent(false);
				break;
			}
			if (dis < 0 && (start + showcount) == totalcount) {
				root.requestDisallowInterceptTouchEvent(false);
				break;
			}
			break;
		case MotionEvent.ACTION_UP:

			break;
		default:
			break;
		}
		return super.onTouchEvent(arg1);
	}

}
