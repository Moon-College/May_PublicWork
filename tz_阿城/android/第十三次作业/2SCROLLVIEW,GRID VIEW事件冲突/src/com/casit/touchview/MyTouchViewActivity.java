package com.casit.touchview;

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

public class MyTouchViewActivity extends Activity implements OnTouchListener {
    /** Called when the activity is first created. */
    private ScrollView scrollview;//重写SCROLLVIEW非常困难
    private GridView gridview; 
    List<Map<String,String>> data;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        scrollview = (ScrollView) findViewById(R.id.lv);
        scrollview.requestDisallowInterceptTouchEvent(true);//为TRUE放行
        gridview = (GridView) findViewById(R.id.gv);
        data = new ArrayList<Map<String,String>>();
        gridview.getChildAt(0).getTop();
        gridview.getChildAt(49).getBottom();
        for(int i = 0; i<50; i++){
        	Map<String,String> map = new HashMap<String,String>();
        	map.put("Item", "Item:"+i);
        	data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
        		this, 
        		data, 
        		R.layout.gridviewlayout, 
        		new String[]{"Item"}, 
        		new int[]{R.id.tv1});
        gridview.setAdapter(adapter);
        gridview.setOnTouchListener(this);
	}
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		switch(action){
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "down");
			break;
		case MotionEvent.ACTION_MOVE:
		    scrollview.requestDisallowInterceptTouchEvent(true);//为TRUE放行		       
			Log.i("INFO", "move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "up");
			break;
		default:
			break;			
		}
		return super.onTouchEvent(event);
	}
    
}