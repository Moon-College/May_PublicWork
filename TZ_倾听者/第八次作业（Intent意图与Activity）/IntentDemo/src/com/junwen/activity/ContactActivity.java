package com.junwen.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.intentdemo.R;
import com.junwen.adapter.ContactsAdapter;
import com.junwen.bean.Contacts;
import com.junwen.util.ContactsUtil;

public class ContactActivity extends Activity implements OnItemClickListener{
	private ListView lv;
	private ContactsAdapter adapter;
	private List<Contacts> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_layout);
		initView();
		lv.setAdapter(adapter);
	}
	/**
	 * 初始化
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.listview);
		data = ContactsUtil.readAllContacts(this);
		adapter = new ContactsAdapter(this, data);
		lv.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//获取当前点击的联系人
		Contacts contacts = (Contacts) adapter.getItem(position);
		//获取联系人电话号码
		String phoneNum = contacts.getPhoneNum();
		Intent intent = new Intent();
		intent.putExtra("number", phoneNum);
		setResult(RESULT_OK, intent);
		finish();
	}
}
