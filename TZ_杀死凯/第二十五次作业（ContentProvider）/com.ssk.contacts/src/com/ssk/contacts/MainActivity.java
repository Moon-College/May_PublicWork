package com.ssk.contacts;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Contacts_Raw raw=new Contacts_Raw();
		List<Contacts_Data> listdate=new ArrayList<Contacts_Data>();
		Contacts_Data date=new Contacts_Data();
		date.setData1("我叫凯哥2");
		date.setMimetype("vnd.android.cursor.item/name");
		listdate.add(date);
		date=new Contacts_Data();
		date.setData1("10086");
		date.setMimetype("vnd.android.cursor.item/phone_v2");
		listdate.add(date);
		date=new Contacts_Data();
		date.setData1("3838438@qq.com");
		date.setMimetype("vnd.android.cursor.item/email_v2");
		listdate.add(date);
		raw.setDate(listdate);
		addContacts(raw);
	}

	/**
	 * 添加联系人
	 * 
	 */
	public void addContacts(Contacts_Raw rawinfo){
		if(rawinfo.getDate().size()>0){
			ContentResolver cr=getContentResolver();
			Uri rawuri=Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
			ContentValues cv=new ContentValues();
			Uri riduri=cr.insert(rawuri, cv);
			Log.i("MainActivity", riduri.toString());
			rawinfo.setRaw_contact_id(ContentUris.parseId(riduri));
			Uri datauri=Uri.parse("content://"+ContactsContract.AUTHORITY+"/data");
			Log.i("MainActivity", rawinfo.getRaw_contact_id()+"");
			for(Contacts_Data dateinfo:rawinfo.getDate()){
				cv.clear();
				cv.put("data1", dateinfo.getData1());
				cv.put("mimetype",dateinfo.getMimetype());
				cv.put("raw_contact_id", rawinfo.getRaw_contact_id());
				cr.insert(datauri, cv);
				Log.i("MainActivity", "添加成功");
			}
		}else{
			Toast.makeText(MainActivity.this, "哥们，别逗我了", Toast.LENGTH_SHORT).show();
		}
	}
}
