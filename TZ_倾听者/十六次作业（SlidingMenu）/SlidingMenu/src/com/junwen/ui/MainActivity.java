package com.junwen.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slidingmenu.R;
import com.junwen.adapter.MenuAdapter;
import com.junwen.bean.MenuItem;

public class MainActivity extends Activity implements  OnItemClickListener {

	private ListView lv;
	private MenuAdapter adapter;
	private List<MenuItem> data;
	private String[] title = { "开通会员", "QQ钱包", "网上营业厅", "个性装扮", "我的收藏", "我的相册",
			"我的文件" };
	private int[] img = { R.drawable.vip, R.drawable.money, R.drawable.yinyet,
			R.drawable.yifu, R.drawable.shoucan, R.drawable.photo,
			R.drawable.file };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 添加数据
		for (int i = 0; i < title.length; i++) {
			MenuItem item = new MenuItem();
			item.setTitle(title[i]);
			item.setBitmap(BitmapFactory.decodeResource(getResources(), img[i]));
			data.add(item);
		}
		adapter = new MenuAdapter(this, data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	/**
	 * 初始化
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.listview);
		data = new ArrayList<MenuItem>();
	}
	
	/**
	 * 按条目的时候
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MenuItem item = (MenuItem) adapter.getItem(position);
		Toast.makeText(MainActivity.this, item.getTitle(), 0).show();
	}
}
