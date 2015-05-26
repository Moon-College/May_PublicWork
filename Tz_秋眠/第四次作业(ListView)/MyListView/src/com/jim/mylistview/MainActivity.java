package com.jim.mylistview;

import java.util.ArrayList;
import java.util.List;

import com.jim.mylistview.adapter.MyAdapter;
import com.jim.mylistview.beans.Persons;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {
	private ListView listView;
	private List<Persons> data = new ArrayList<Persons>();
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		addData();
		initView();
	}

	/**
	 * 初始化ListView
	 */
	private void initView() {
		listView = (ListView) findViewById(R.id.listview);

		adapter = new MyAdapter(data, this);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				final int position = arg2;
				final String mName = data.get(arg2).getName();
				AlertDialog.Builder ad = new AlertDialog.Builder(
						MainActivity.this);
				ad.setMessage("是否删除该好友");
				ad.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
								adapter.removeItem(position);
								adapter.notifyDataSetChanged();
								Toast.makeText(MainActivity.this,
										"已删除" + mName, Toast.LENGTH_SHORT)
										.show();
							}
						});
				ad.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
							}
						});
				ad.create().show();

			}
		});
	}

	/**
	 * 添加数据
	 */
	private void addData() {
		data.add(new Persons("潭州-David老师", "男", "敲代码", 75,
				R.drawable.icon_david));
		data.add(new Persons("潭州-Grace老师", "女", "吃、睡、卖萌", 85,
				R.drawable.icon_grace));
		data.add(new Persons("潭州-Jason老师", "男", "吹拉弹唱、敲代码", 78,
				R.drawable.icon_jason));
		data.add(new Persons("潭州-鸿宇老师", "男", "阅读、敲代码", 81,
				R.drawable.icon_hongyu));
		data.add(new Persons("潭州-子墨老师", "男", "运动、敲代码", 78, R.drawable.icon_zimo));
		data.add(new Persons("潭州-影子老师", "女", "吃、睡、看帅哥", 82,
				R.drawable.icon_yingzi));
		data.add(new Persons("潭州-Eyre老师", "男", "敲代码", 76, R.drawable.icon_eyre));
		data.add(new Persons("潭州-瑶瑶老师", "女", "吃、睡、逛街", 86,
				R.drawable.icon_yaoyao));
		data.add(new Persons("潭州-GEMTLEMAN-深圳", "男", "撸啊撸、敲代码", 77,
				R.drawable.icon_gemtleman));
		data.add(new Persons("潭州-轩儿", "女", "吃、睡、敲代码", 84,
				R.drawable.icon_xuaner));
		data.add(new Persons("潭州-清风啸-深圳", "男", "敲代码", 76,
				R.drawable.icon_qingfengxiao));
		data.add(new Persons("潭州-Rocy-长沙", "男", "敲代码", 73, R.drawable.icon_rock));
		data.add(new Persons("潭州-Danny", "男", "敲代码、挖坑", 83,
				R.drawable.icon_danny));
	}

}
