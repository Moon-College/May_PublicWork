package com.tz.michael.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ConflictTryActivity extends Activity implements OnTouchListener {
	
	TextView tv_top;
	ScrollView scrollview;
	GridView gridview;
	
	private List<Map<String,String>> ll=new ArrayList<Map<String,String>>();
	private int down_y;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv_top=(TextView) findViewById(R.id.tv_top);
        scrollview=(ScrollView) findViewById(R.id.scrollview);
        scrollview.requestDisallowInterceptTouchEvent(true);
        gridview=(GridView) findViewById(R.id.gridview);
        initData();
        SimpleAdapter adapter=new SimpleAdapter(this,
        		ll, 
        		R.layout.grid_item,
        		new String[]{"key"},
        		new int[]{R.id.tv_item});
        gridview.setAdapter(adapter);
        gridview.setOnTouchListener(this);
        tv_top.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(ConflictTryActivity.this,SecondActivity.class));
			}
		});
    }

	private void initData() {
		for(int i=0;i<80;i++){
			Map<String,String> map=new HashMap<String, String>();
			map.put("key", "value---"+i);
			ll.add(map);
		}
	}

	public boolean onTouch(View v, MotionEvent event) {
		int action=event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			down_y = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int cur_y=(int) event.getY();
			if(gridview.getChildAt(0).getTop()==0&&cur_y-down_y>=0){
				scrollview.requestDisallowInterceptTouchEvent(false);
			}else if(gridview.getChildAt(gridview.getChildCount()-1).getBottom()==gridview.getHeight()&&cur_y-down_y<=0){
				scrollview.requestDisallowInterceptTouchEvent(false);
			}else{
				scrollview.requestDisallowInterceptTouchEvent(true);
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