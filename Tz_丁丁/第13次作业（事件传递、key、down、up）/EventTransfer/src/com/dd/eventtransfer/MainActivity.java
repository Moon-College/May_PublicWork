package com.dd.eventtransfer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{
	private ListView mLv;
	private ScrollView mSv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mLv = (ListView) findViewById(R.id.lv);
		mSv = (ScrollView) findViewById(R.id.sv);
		svTouch touch = new svTouch();
		mSv.setOnTouchListener(touch);
		mLv.setOnTouchListener(this);
		List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        data.add("测试数据4");
        mLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,data));
		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this, "第几个"+position, Toast.LENGTH_SHORT).show();
			}
		});
		mLv.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem==0){
                    Log.e("log", "滑到顶部");
                    mSv.requestDisallowInterceptTouchEvent(false);
                }else {
                	Log.e("log", "no滑到顶部");
                	mSv.requestDisallowInterceptTouchEvent(true);
				}
                if(visibleItemCount+firstVisibleItem==totalItemCount){
                    Log.e("log", "滑到底部");
                    mSv.requestDisallowInterceptTouchEvent(false);
                }else {
                	Log.e("log", "no滑到底部");
                	mSv.requestDisallowInterceptTouchEvent(true);
				}
			}
		});
	}
	class svTouch implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.i("home", "sv down");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("home", "sv down");
				break;
			case MotionEvent.ACTION_UP:
				Log.i("home", "sv down");
				break;

			default:
				break;
			}
			return false;
		}}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("home", "gridView down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("home", "gridView down");
			mSv.requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_UP:
			Log.i("home", "gridView down");
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	

}
