package com.chris.eventconfilict;

import java.util.ArrayList;
import java.util.HashMap;
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
	private boolean firstItemTopFlag;
	private boolean lastItemBottomFlag;
	private float lastY = 0;
	private float curY = 0;
	private ArrayList<Map<String, String>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_confilict);
		firstItemTopFlag = true; //是否滑倒顶部
		lastItemBottomFlag = false; //是否滑倒底部
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
					lastY = curY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i(tag, "listview move");
					boolean tF = firstItemTopFlag;
					boolean bF = lastItemBottomFlag;
					curY = event.getY();
					//到底部并且继续向下滑动
					if ((curY - lastY > 0 && tF) || (curY - lastY < 0 && bF))
					{
						listview.requestDisallowInterceptTouchEvent(false);
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
				Log.d(tag, "onScrollStateChanged");
				switch (scrollState)
				{
				// 当不滚动时
				case OnScrollListener.SCROLL_STATE_IDLE:
					Log.d(tag, "SCROLL_STATE_IDLE");
					break;
				case OnScrollListener.SCROLL_STATE_FLING:
					Log.d(tag, "SCROLL_STATE_FLING");
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					Log.d(tag, "SCROLL_STATE_TOUCH_SCROLL");
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{
				Log.d(tag, "onScroll");
				Log.d(tag, "visibleItemCount = " + visibleItemCount + " totalItemCount = " + totalItemCount + " firstVisibleItem = " + firstVisibleItem);

				int first_index = view.getFirstVisiblePosition();
				int last_index = view.getLastVisiblePosition();
				Log.d(tag, "the first item index = " + first_index);
				Log.d(tag, "the last item index = " + last_index);

				int[] location = new int[2];
				view.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
				int y = location[1];
				int x = location[0];
				Log.d(tag, "location x = " + x + " location y = " + y);
				
				Log.d(tag, "ListView height = "+view.getHeight());
				
				if(0 == view.getFirstVisiblePosition())	//说明滑动到了顶部的list item
				{
					View first_list_item = view.getChildAt(0);	//获取可见的第一个list item
					if (first_list_item != null)
					{
						if(first_list_item.getTop() >= -1)
						{
							firstItemTopFlag = true;
							Log.d(tag, "change firstItemTopFlag to ture.!");
						}
						else
						{
							firstItemTopFlag = false;
							Log.d(tag, "change firstItemTopFlag to false.!");
						}
					}
				}
				
				if(view.getCount()-1 == view.getLastVisiblePosition())	//说明滑动到了最底部的list item
				{
					View last_list_item = view.getChildAt(visibleItemCount - 1);	//获取可见的最后一个list item

					if(last_list_item != null)
					{
						Log.d(tag, "last item top = " + last_list_item.getTop());
						Log.d(tag, "last item height = " + last_list_item.getHeight());
						Log.d(tag, "ListView height = "+view.getHeight());
						if(last_list_item.getTop()+last_list_item.getHeight() - view.getHeight() <= 1)
						{
							lastItemBottomFlag = true;
							Log.d(tag, "change lastItemBottomFlag to true.!");
						}
						else
						{
							lastItemBottomFlag = false;
							Log.d(tag, "change lastItemBottomFlag to false.!");
						}
					}
				}
			}
		});
	}
}
