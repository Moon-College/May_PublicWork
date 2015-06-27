package com.zjm.key.touch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zjm.key.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;

@SuppressLint("ClickableViewAccessibility")
public class ScollTouchActivity extends Activity implements OnTouchListener {
	
	private ScrollView sv;
	private GridView gv;
	private HashMap<String, Object> map ;
	private List<HashMap<String, Object>> data ;
	private float firstY;
	private float thenY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoll_touch);
		initDate();
		initView();
	}

	/**
	 * 初始化数据
	 *@author 邹继明
	 *@date 2015-6-16上午1:23:51
	 *@return void
	 */
	private void initDate() {
		data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 50; i++) {
			map = new HashMap<>();
			map.put("item", "item"+i);
			data.add(map);
		}
		
	}

	/**
	 * 初始化控件
	 *@author 邹继明
	 *@date 2015-6-16上午1:23:40
	 *@return void
	 */
	private void initView() {
		sv = (ScrollView) findViewById(R.id.sv);
		sv.requestDisallowInterceptTouchEvent(true);//父容器不拦截Touch事件
		gv = (GridView) findViewById(R.id.gv);
		gv.setOnTouchListener(this);
		SimpleAdapter adapter = new SimpleAdapter(this, 
									data, 
									R.layout.grid_item, 
									new String[]{"item"}, 
									new int[]{R.id.item});
		gv.setAdapter(adapter);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			thenY = event.getY();
			if(thenY>firstY+5){
				//向上
				if(gv.getChildAt(0).getTop() == 0){
					sv.requestDisallowInterceptTouchEvent(false);
				}else{
					sv.requestDisallowInterceptTouchEvent(true);//父容器不拦截Touch事件
				}
				//Log.d("TZ","Top:"+gv.getChildAt(0).getTop());
			}else if(thenY+5<firstY){
				//向下
				if(gv.getChildAt(gv.getChildCount()-1).getBottom() == gv.getHeight()){
					sv.requestDisallowInterceptTouchEvent(false);
				}else{
					sv.requestDisallowInterceptTouchEvent(true);//父容器不拦截Touch事件
				}
				//Log.d("TZ","bottom:"+gv.getChildAt(gv.getChildCount()-1).getBottom()+"gv_bottom:"+gv.getHeight());
				
			}
			break;
		case MotionEvent.ACTION_UP:
			
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

}
