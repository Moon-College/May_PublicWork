package com.knight.scroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener {

    private ListView lv;
	private List<Map<String, String>> data;
	private ScrollView scv;
	private float starty;
	private float movey;
	private float upy;
	private int height;
	private float pointery;
	private boolean istop = false;
	private boolean isbotton = false;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv = (ListView)findViewById(R.id.lv);
        scv = (ScrollView)findViewById(R.id.scv);
        data = new ArrayList<Map<String,String>>();
        for (int i = 0; i < 60; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("item", "item"+i);
			data.add(map);
		}
        SimpleAdapter adapter = new SimpleAdapter(this, 
        		data, 
        		R.layout.list_item, 
        		new String[]{"item"}, 
        		new int[]{R.id.tv});
        lv.setAdapter(adapter);
        setListViewHight(lv);
        lv.setOnTouchListener(this);
        lv.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				scv.requestDisallowInterceptTouchEvent(true);
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
					if (firstVisibleItem + visibleItemCount ==totalItemCount) {
						isbotton = true;
						//滑动到底部
					}else {
						isbotton = false;
					}
					if (firstVisibleItem == 0) {
						//滑动到顶部
						istop = true;
					}else {
						istop = false;
					}
			}
		});
    }
	
	/**
	 * 重新计算ListView高度 解决scrollview嵌套listview 
	 * @Title: setListViewHight
	 * @Description: TODO
	 * @param lv
	 * @return: void
	 */
	private void setListViewHight(ListView lv){
		//获取listview对应的adapter
		ListAdapter listAdapter = lv.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0 ,len=listAdapter.getCount(); i < len; i++) {
			//listAdapter.getCount();返回数据项的数目
			View listItem = listAdapter.getView(i, null, lv);
			listItem.measure(0, 0);//计算子view宽高 ListView的每个Item必须是LinearLayout
			totalHeight += listItem.getMeasuredHeight();//统计所有子项的总高度
			ViewGroup.LayoutParams params = lv.getLayoutParams();
			//listview 控件高度 = item总高+listview总得间隔高度 /5
			params.height = (totalHeight +(lv.getDividerHeight()*(listAdapter.getCount()-1)))/5;
			lv.setLayoutParams(params);
			
		}
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()&event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			//手指按下的坐标
			starty = event.getY();
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			pointery = event.getY(event.getPointerCount()-1);
			break;
		case MotionEvent.ACTION_MOVE:
			//手指移动 判断方向 距离
			movey = event.getY();
			height = lv.getHeight();
			//判断手指滑动的方向和 listview是否滑动到了边缘
				if (movey - starty > 0) {
					//向下滑动
					if (istop) {
						//是否在顶部
						scv.requestDisallowInterceptTouchEvent(false);
					}else {
						scv.requestDisallowInterceptTouchEvent(true);
					}
				}else {
					//向下滚动
					if (isbotton) {
						//是否在底部
						scv.requestDisallowInterceptTouchEvent(false);
					}else {
						scv.requestDisallowInterceptTouchEvent(true);
					}
				}
			break;
		case MotionEvent.ACTION_UP:
			//手指抬起 
			upy = event.getY();
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}
