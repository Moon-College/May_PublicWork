package com.myandroid.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.myandroid.demo.adapter.ListViewAdapter;
import com.myandroid.demo.bean.Contacts;

public class MainrActivity extends Activity implements OnItemClickListener{
	/** Called when the activity is first created. */

	private ListView lv;
	private Contacts contacts = null;
	private String[] name = { "剑侠客", "剑侠客-渡劫", "骨精灵", "飞燕女", "玄彩娥", "巫蛮儿",
			"杀破狼", "羽灵神", "巨魔王", "梦幻西游", "一对的" };
	private int[] image = { R.drawable.jxk, R.drawable.jxkf, R.drawable.gjl,
			R.drawable.fyn, R.drawable.xce, R.drawable.wme, R.drawable.spl,
			R.drawable.yls, R.drawable.jmw, R.drawable.mhxy, R.drawable.zh };
	private String[] sex = { "男", "男", "女", "女", "女", "女", "男", "男", "男", "男",
			"男" };
	private String[] handSome = { "颜值：9999", "颜值：????", "颜值：9999", "颜值：999?",
			"颜值：未知", "颜值：好萌", "颜值：已爆表", "颜值：9999", "颜值：9999", "颜值：????",
			"颜值：????" };
	private String[] interest = { "我的剑就是你的剑........", "我已渡劫，看破红尘........",
			"爱好：睡觉........", "爱好：我不知道........", "爱好：带你飞........",
			"爱好：萌萌哒........", "爱好：未知........", "爱好：不知........",
			"爱好：未知........", "梦幻西游........", "萌萌哒........" };
	private List<Contacts> list = null;
	private ListViewAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		init();
	}

	/**
	 * 初始化ListView
	 */
	private void init() {
		lv = (ListView) findViewById(R.id.listview);
		addList();
		adapter = new ListViewAdapter(this, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	/**
	 * 添加ListView数据
	 */
	private void addList(){
		list = new ArrayList<Contacts>();
		for (int i = 0; i < name.length; i++) {
			contacts = new Contacts();
			contacts.setPicture(image[i]);
			contacts.setName(name[i]);
			contacts.setSex(sex[i]);
			contacts.setHandsome(handSome[i]);
			contacts.setInterest(interest[i]);
			list.add(contacts);
			
		}
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		list.remove(position);
		Log.i("info", "++++++++++++++++++++++++++++++++");
		adapter.notifyDataSetChanged();
	}

}