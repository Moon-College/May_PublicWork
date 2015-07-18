package com.dd.dd_content_provider;

import java.util.ArrayList;
import java.util.List;

import com.dd.dd_content_provider.bean.ContactInfo;

import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		queryData();
	}

	/**
	 * 查询联系人
	 */
	private void queryData() {
		List<ContactInfo> data = new ArrayList<ContactInfo>();
		ContentResolver cr = this.getContentResolver();//获取内容接收者
		Uri uri_raw_contacts = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
		Cursor cursor = cr.query(uri_raw_contacts, new String[]{"_id","display_name"}, null, null, null);
		while (cursor.moveToNext()) {
			ContactInfo info = new ContactInfo();
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			Uri uri_data = ContactsContract.AUTHORITY_URI;
			uri_data = Uri.withAppendedPath(uri_data, "data");//第二种方式"raw_contact_id"+_id+"/data"
			Cursor cursor2 = cr.query(uri_data, null, "raw_contact_id = ?", new String[]{String.valueOf(_id)}, null);
			while (cursor2.moveToNext()) {
				String data1 = cursor2.getString(cursor2.getColumnIndex("data1"));
				String mimeType = cursor2.getString(cursor2.getColumnIndex("mimeType"));
				if (mimeType.equals("vnd.android.cursor.item/email_v2")) {
					info.setEmail(data1);
				}else if (mimeType.equals("vnd.android.cursor.item/phone_v2")) {
					info.setPhone(data1);
				}else if (mimeType.equals("vnd.android.cursor.item/name")) {
					info.setName(data1);
				}
			}
			data.add(info);
		}
		for (ContactInfo contactInfo : data) {
			Log.v("home", "name:"+contactInfo.getName());
			Log.v("home", "phone:"+contactInfo.getPhone());
		}
	}
	/**
	 * 远程事务
	 */
	public void transactionCommit(ContactInfo info){
		ContentResolver cr = this.getContentResolver();
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		Uri uri_raw_contacts = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
		//向raw_contacts表里插入空数据获取自增_id
		//第一次操作，插入raw_contacts表
		ContentValues values_raw = new ContentValues();
		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri_raw_contacts).withValues(values_raw).build();
		operations.add(op1);
		//向data表里卖弄插入属性
		//第二次操作，插入名字
		Uri uri_data = ContactsContract.AUTHORITY_URI;
		uri_data = Uri.withAppendedPath(uri_data, "data");
		ContentValues values = new ContentValues();
		values.put("data1", "aaaa");
		values.put("mimetype","vnd.android.cursor.item/name");
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values).build();
		operations.add(op2);
		
		//第三次操作，插入电话
		values.clear();
		values.put("data1", "aa111");
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		ContentProviderOperation op3 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op3);

		// 第四次操作，插入邮箱
		values.clear();
		values.put("data1", "aa11@qq.com");
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		ContentProviderOperation op4 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op4);

		//远程事务提交
		try {
			cr.applyBatch(ContactsContract.AUTHORITY, operations);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 远程更新事务
	 */
	public void transactionUpdateCommit(ContactInfo info){
		ContentResolver cr = this.getContentResolver();
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		Uri uri_raw_contacts = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
		//向raw_contacts表里插入空数据获取自增_id
		//第一次操作，插入raw_contacts表
		ContentValues values_raw = new ContentValues();
		values_raw.put("_id", 1);
		ContentProviderOperation op1 = ContentProviderOperation.newUpdate(uri_raw_contacts).withValues(values_raw).build();
//		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri_raw_contacts).withValues(values_raw).build();
		operations.add(op1);
		//向data表里卖弄插入属性
		//第二次操作，插入名字
		Uri uri_data = ContactsContract.AUTHORITY_URI;
		uri_data = Uri.withAppendedPath(uri_data, "data");
		ContentValues values = new ContentValues();
		values.put("data1", "bbbbbb");
		values.put("mimetype","vnd.android.cursor.item/name");
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values).build();
		operations.add(op2);
		
		//第三次操作，插入电话
		values.clear();
		values.put("data1", "bbbbbb000");
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		ContentProviderOperation op3 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op3);
		
		// 第四次操作，插入邮箱
		values.clear();
		values.put("data1", "bbb0@qq.com");
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		ContentProviderOperation op4 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op4);
		
		//远程事务提交
		try {
			cr.applyBatch(ContactsContract.AUTHORITY, operations);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
