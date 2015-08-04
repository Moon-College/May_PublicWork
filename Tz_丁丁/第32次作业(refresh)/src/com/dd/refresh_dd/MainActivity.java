package com.dd.refresh_dd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dd.refresh_dd.refresh.PullToRefreshLayout;
import com.dd.refresh_dd.refresh.PullToRefreshLayout.OnRefreshListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity  implements OnRefreshListener{
	PullToRefreshLayout refreshLayout;
	private ListView contentView;
	private List<Map<String,String>> data;
	private SimpleAdapter adapter;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			refreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			adapter.notifyDataSetChanged();
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contentView = (ListView) findViewById(R.id.content_view);
		refreshLayout = (PullToRefreshLayout) findViewById(R.id.refresh);
		refreshLayout.setOnRefreshListener(this);
		data = new ArrayList<Map<String,String>>();
		for(int i = 0;i<10;i++){
			Map<String,String> map = new HashMap<String, String>();
			map.put("item", "item:"+String.valueOf(i));
			data.add(map);
		}
		adapter = new SimpleAdapter(
				this, 
				data, 
				android.R.layout.simple_list_item_1, 
				new String[]{"item"}, 
				new int[]{android.R.id.text1});
		contentView.setAdapter(adapter);
	}

	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
		for(int i = 0;i<4;i++){
			Map<String,String> map = new HashMap<String, String>();
			map.put("item", "新加的item:"+String.valueOf(i));
			data.add(map);
		}
		handler.sendEmptyMessage(0);
	}

}
