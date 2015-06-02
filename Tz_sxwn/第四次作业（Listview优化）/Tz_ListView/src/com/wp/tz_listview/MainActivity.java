package com.wp.tz_listview;

import java.util.ArrayList;
import java.util.List;

import com.binbinsh.tz_listview.adapter.UserAdapter;
import com.binbinsh.tz_listview.entity.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView lv;
	private List<User> list = new ArrayList<User>(0);
	private UserAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		adapter = new UserAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
	}

	private void initData() {
		list.add(new User("田佳佳", "girl1", 98, "女", "上网、酒吧"));
		list.add(new User("张海生", "boy11", 89, "男", "上网、游戏"));
		list.add(new User("冯美美", "girl2", 92, "女", "看书、酒吧"));
		list.add(new User("李军", "boy12", 82, "男", "上网、泡妞"));
		list.add(new User("句佳丽", "girl3", 91, "女", "上网、网吧"));
		list.add(new User("王宙", "boy15", 84, "男", "上网、美食"));
		list.add(new User("王林", "boy16", 86, "男", "上网、游戏"));
		list.add(new User("沈晓", "boy17", 88, "男", "上网"));
		list.add(new User("真小甜", "girl4", 95, "女", "看书"));
		list.add(new User("龙思宇", "girl5", 97, "女", "运动"));
		list.add(new User("冯昌喜", "boy19", 85, "男", "上网"));
		list.add(new User("房甄", "girl6", 93, "女", "看书、星座"));
		list.add(new User("龙杰君少", "boy20", 87, "男", "上网、游戏"));
		list.add(new User("李蕾媛", "girl7", 92, "女", "整人、旅游"));
		list.add(new User("甄恺", "girl8", 97, "女", "小说、游戏、KTV"));
		list.add(new User("操英梅", "girl9", 91, "女", "旅游"));
		list.add(new User("顾封", "boy13", 86, "男", "上网、文学"));
		list.add(new User("张龙", "boy14", 84, "男", "上网、游戏"));
		list.add(new User("灵白冰", "girl10", 92, "女", "美食、金融"));
		list.add(new User("金燕", "girl21", 99, "女", "跳舞"));
		list.add(new User("薛涛", "boy18", 82, "男", "上网、游戏"));

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		list.remove(position);
		adapter.notifyDataSetChanged();
	}


}
