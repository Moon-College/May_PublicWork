package com.tz.riders;

import java.util.ArrayList;
import java.util.List;
import com.tz.riders.adapter.ContsAdapter;
import com.tz.riders.entity.Persion;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView ListView;
	private List<Persion> listPersions;
	private ContsAdapter adapter;
	private Persion persion;
	private int[] picIds = new int[] { R.drawable.fanye, R.drawable.liudehua,
			R.drawable.xingye, R.drawable.lizi, R.drawable.tingfeng };
	private int[] genders = new int[] { 1, 0, 0, 1, 0 };
	private int[] numbers = new int[] { 110, 120, 119, 114, 112 };
	private String[] names = new String[] { "范冰冰", "刘德华", "周星驰", "黎姿", "谢霆锋" };
	private String[] hobbys = new String[] { "舞蹈", "保龄球", "拍电影", "演员", "做甜点" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initData();
		initUI();
	}
	/**
	 * @author Riders
	 * 初始化数据
	 */
	private void initData() {
		listPersions = new ArrayList<Persion>();
		for (int i = 0; i < names.length; i++) {
			persion = new Persion();
			persion.setPicture_id(picIds[i]);
			persion.setpName(names[i]);
			persion.setpGender(genders[i]);
			persion.setNumber(numbers[i]);
			persion.setHobby(hobbys[i]);
			listPersions.add(persion);
		}

	}

	/**
	 * @author Riders
	 * 初始化控件
	 */
	private void initUI() {
		if (adapter == null) {
			ListView = (ListView) findViewById(R.id.listView);
			adapter = new ContsAdapter(MainActivity.this, listPersions);
			ListView.setAdapter(adapter);
			ListView.setOnItemClickListener(this);
		}else {
			adapter.notifyDataSetChanged();
		}

	}

	/**
	 * @author Riders
	 * item点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		listPersions.remove(position);
		adapter.notifyDataSetChanged();
	}

}
