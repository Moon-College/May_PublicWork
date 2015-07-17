package com.junwen.contact.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactdemo.R;
import com.junwen.contact.adapter.ContactsAdapter;
import com.junwen.contact.modle.Contacts;
import com.junwen.contact.util.ContactUtil;

public class MainActivity extends Activity {
	
	private ListView lv; //ListView
	private List<Contacts> data; //联系人集合
	private ContactsAdapter adapter; //适配器
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
		
		//添加联系人
//		addContact();
		
		//删除联系人
//		ContactUtil.deleteContacts(this, "陈值为");
		
		//删除所有联系人
//		ContactUtil.deleteAll(this);
		
		//更新联系人
//		Contacts contact = new Contacts();
//		contact.setName("啊哈哈");
//		contact.setPhoneNumber("110");
//		contact.setEmail("344176791@qq");
//		ContactUtil.updateContacts(this,31, contact);
		//查询手机里所有的联系人
		data = ContactUtil.getAllContacts(this);
		adapter = new ContactsAdapter(this, data);
		lv.setAdapter(adapter);
	}
	
	
	/**
	 *	添加联系人
	 */
	private void addContact() {
		Contacts contact = new Contacts();
		contact.setName("俊文");
		contact.setPhoneNumber("8000");
		contact.setEmail("344176791d@qq.com");
		boolean insertContacts = ContactUtil.insertContacts(this, contact);
		if(insertContacts){
			Toast.makeText(this, "添加成功!", 0).show();
		}else{
			Toast.makeText(this, "添加失败!!", 0).show();
		}		
	}

	/**
	 * 初始化
	 */
	private void initView() {
		lv = (ListView) findViewById(R.id.listview);
		data = new ArrayList<Contacts>();
	}
	
	
}
