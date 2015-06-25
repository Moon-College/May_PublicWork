package com.dd.activity;

import java.util.ArrayList;
import java.util.List;

import com.chris.userdefineadapter.R;
import com.dd.adapter.MainAdapter;
import com.dd.bean.People;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView mList;
	private MainAdapter mCharAdapter;
	private List<People> mPerson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_define_adapter);

		mList = (ListView) findViewById(R.id.list);

		setPeopleData();

		setListView();
	}

	private void setPeopleData() {
		mPerson = new ArrayList<People>(10);
		Resources arryRes = getResources();
		String[] names = arryRes.getStringArray(R.array.names);
		String[] phoneNums = arryRes.getStringArray(R.array.phoneNum);
		String[] sexs = arryRes.getStringArray(R.array.sex);
		for (int i = 0; i < names.length; i++) {
			People people = new People();
			people.setName(names[i]);
			people.setPhoneNum(phoneNums[i]);
			people.setSex(sexs[i]);
			mPerson.add(people);
		}
	}

	private void setListView() {
		mCharAdapter = new MainAdapter(mPerson, this);
		mList.setAdapter(mCharAdapter);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				new AlertDialog.Builder(MainActivity.this).setTitle("删除?")
			    .setNegativeButton("取消", null)
			    .setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mPerson.remove(position);
						mCharAdapter.notifyDataSetChanged();
					}
				}).show();
			
			}
		});
		mList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				new AlertDialog.Builder(MainActivity.this).setTitle("删除?")
			    .setNegativeButton("取消", null)
			    .setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mPerson.remove(position);
						mCharAdapter.notifyDataSetChanged();
					}
				}).show();
				
				return false;
			}
		});
	}
}
