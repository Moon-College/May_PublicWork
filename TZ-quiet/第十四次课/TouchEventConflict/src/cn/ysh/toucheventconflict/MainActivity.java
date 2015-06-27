package cn.ysh.toucheventconflict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.app.Activity;

public class MainActivity extends Activity implements OnTouchListener {
	private ScrollView scrollView;
	private ListView listView;
	List<Map<String,String>> data = new ArrayList<Map<String,String>>();
	private int firstPosition;
	private int lastPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scrollView = (ScrollView) findViewById(R.id.scrollview);
		listView = (ListView) findViewById(R.id.listview);
		listView.setOnTouchListener(this);
		for(int i = 0; i < 20; i++ ){
			Map<String , String> map = new HashMap<String, String>();
			map.put("item", "item:"+ i);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, 
				data,
				R.layout.demo, 
				new String[]{"item"}, 
				new int[]{R.id.textview});
		listView.setAdapter(adapter);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			break;
			
		case MotionEvent.ACTION_MOVE:
			firstPosition = listView.getFirstVisiblePosition();
			lastPosition = listView.getLastVisiblePosition();
			float moveY = event.getY();
			float y = listView.getTop()+moveY;
				if(y < listView.getTop() && lastPosition == (data.size()-1)){
					scrollView.requestDisallowInterceptTouchEvent(false);
				}else if(y > listView.getBottom() && firstPosition == 0){
					scrollView.requestDisallowInterceptTouchEvent(false);
				}else{
					scrollView.requestDisallowInterceptTouchEvent(true);
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
