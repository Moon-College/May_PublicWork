package com.tz.michael.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.tz.michael.adapter.ListViewAdapter;
import com.tz.michael.custom.AutoListView;
import com.tz.michael.custom.AutoListView.OnLoadListener;
import com.tz.michael.custom.AutoListView.OnRefreshListener;

public class SecondActivity extends Activity implements OnRefreshListener, OnLoadListener {

	private AutoListView lv;
	private ListViewAdapter adapter;
	private List<String> list = new ArrayList<String>();
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			List<String> result = (List<String>) msg.obj;
			switch (msg.what) {
			case AutoListView.REFRESH:
				lv.onRefreshComplete();
				list.clear();
				list.addAll(result);
				break;
			case AutoListView.LOAD:
				lv.onLoadComplete();
				list.addAll(result);
				break;
			}
			lv.setResultSize(result.size());
			adapter.notifyDataSetChanged();
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_ac);
		lv=(AutoListView) findViewById(R.id.lv);
		adapter = new ListViewAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnRefreshListener(this);
		lv.setOnLoadListener(this);
		initData();
	}

	private void initData() {
		loadData(AutoListView.REFRESH);
	}
	
	private void loadData(final int what) {
		// 这里模拟从服务器获取数据
		new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = handler.obtainMessage();
				msg.what = what;
				msg.obj = getData();
				handler.sendMessage(msg);
			}
		}).start();
	}
	
	// 测试数据
		public List<String> getData() {
			List<String> result = new ArrayList<String>();
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				long l = random.nextInt(10000);
				result.add("当前条目的ID：" + l);
			}
			return result;
		}
	
	public void onRefresh() {
		loadData(AutoListView.REFRESH);
	}

	public void onLoad() {
		loadData(AutoListView.LOAD);
	}
	
}
