package com.chris.eventconfilict;

import java.security.acl.LastOwnerException;
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
import android.widget.TextView;

public class EventConfilictActivity extends Activity
{
	private static final String tag = "EventConfilictActivity";
	private TextView tv_above, tv_below;
	private ListView listview;
	private ScrollView sv;
	private boolean topFlag, bottomFlag;
	private float lastY= 0;
	private ArrayList<Map<String, String>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_confilict);
		topFlag = true;	//是否滑倒顶部
		bottomFlag = false;	//是否滑倒底部
		//初始化相关控件
		initView();
	}
	
	private void initView()
	{
		tv_above = (TextView) findViewById(R.id.tv_above);
		tv_below = (TextView) findViewById(R.id.tv_below);
		listview = (ListView) findViewById(R.id.listview);
		sv = (ScrollView) findViewById(R.id.sv);
		
		//初始化listview并设置相关Listenner
		initListView();
		//初始化sv并设置相关Listenner
		initScrollView();
	}

	private void initScrollView()
	{
		sv.setOnTouchListener(new OnTouchListener()
		{

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				switch (event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					Log.i(tag, "sv down");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i(tag, "sv move");
					break;
				case MotionEvent.ACTION_UP:
					Log.i(tag, "sv up");
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

	private void initListView()
	{
		data = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 20; i++)
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "list:" + i);
			data.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listview_layout, new String[]
		{ "item" }, new int[]
		{ R.id.listtext });

		listview.setAdapter(adapter);
		
		//给ListView setOnTouchListener
		listview.setOnTouchListener(new OnTouchListener()
		{

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{

				switch (event.getAction())
				{
				case MotionEvent.ACTION_DOWN:
					Log.i(tag, "listview down");
					listview.requestDisallowInterceptTouchEvent(true);
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i(tag, "listview move");
					boolean tF = topFlag;
					boolean bF = bottomFlag;
					float curY = event.getY();
					//到底部并且继续向下滑动
					if((curY-lastY>0 && true == tF)
							||curY-lastY<0 && true == bF)
					{
						listview.requestDisallowInterceptTouchEvent(false);
					}
					else
					{
						listview.requestDisallowInterceptTouchEvent(true);
					}
					lastY = curY;
					break;
				case MotionEvent.ACTION_UP:
					Log.i(tag, "listview up");
					listview.requestDisallowInterceptTouchEvent(false);
					break;
				default:
					break;
				}
				return false;
			}
		});
		
		//setOnScrollListener判断滑动到顶部还是底部
		listview.setOnScrollListener(new OnScrollListener()
		{

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{
				Log.d(tag,"onScrollStateChanged");
				switch (scrollState)
				{
				// 当不滚动时
				case OnScrollListener.SCROLL_STATE_IDLE:
					Log.d(tag,"SCROLL_STATE_IDLE");
					// 判断滚动到顶部
					if (view.getFirstVisiblePosition() == 0)
					{
						Log.e("log", "1 滑到顶部");
						topFlag = true;
					}
					else
					{
						topFlag = false;
					}
					// 判断滚动到底部
					if (view.getLastVisiblePosition() == (view.getCount() - 1))
					{
						Log.e("log", "2 滑到底部");
						bottomFlag = true;
					}
					else
					{
						bottomFlag = false;
					}
					break;
				case OnScrollListener.SCROLL_STATE_FLING:
					Log.d(tag,"SCROLL_STATE_FLING");
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					Log.d(tag,"SCROLL_STATE_TOUCH_SCROLL");
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				Log.d(tag,"onScroll");
			}
		});
	}
}
