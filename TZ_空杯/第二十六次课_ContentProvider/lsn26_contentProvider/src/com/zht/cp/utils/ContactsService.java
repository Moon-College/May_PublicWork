package com.zht.cp.utils;

import java.util.ArrayList;
import java.util.List;

import com.zht.cp.bean.ContactsInfo;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

public class ContactsService {
	private Context context;

	public ContactsService(Context context) {
		this.context = context;
	}
	
	public List<ContactsInfo> readContacts(){
		List<ContactsInfo> contacts = new ArrayList<ContactsInfo>();
		Cursor c = null;
		try {
			c = context.getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					null, null, null);
			while(c.moveToNext()){
				ContactsInfo info = new ContactsInfo();
              String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			  String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			  info.setName(name);
			  info.setPhone(number);
			  contacts.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != c){
				c.close();
			}
		}
		return contacts;
		
	}

	public List<ContactsInfo> getAllContacts() {
		List<ContactsInfo> contacts = new ArrayList<ContactsInfo>();
		try {
			Contacts c;
			// 已经拿到了内容分解器
			ContentResolver cr = context.getContentResolver();
			Cursor cursor = cr.query(
					//ContactsContract.Contacts.CONTENT_URI;
					Uri.parse("content://com.android.contacts/contacts"),
					new String[] { "_id" }, null, null, null);
			while (cursor.moveToNext()) {
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				ContactsInfo contact = new ContactsInfo();
				contact.setId(id);
				Uri uri = Uri
						.parse("content://com.android.contacts/raw_contacts/"
								+ id + "/data");
				Cursor cursor2 = cr.query(uri, new String[] { "data1",
						"mimetype" }, null, null, null);
				while (cursor2.moveToNext()) {
					String mimetype = cursor2.getString(cursor2
							.getColumnIndex("mimetype"));
					String data1 = cursor2.getString(cursor2
							.getColumnIndex("data1"));
					if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
						contact.setPhone(data1);
					} else if (mimetype.equals("vnd.android.cursor.item/name")) {
						contact.setName(data1);
					} else if (mimetype
							.equals("vnd.android.cursor.item/email_v2")) {
						contact.setEmail(data1);
					}
				}
				cursor2.close();
				contacts.add(contact);
			}
			cursor.close();
		} catch (Exception ex) {
			Log.i("i", ex.getMessage());
		} finally {
			return contacts;
		}
	}

	public long insertContact(ContactsInfo contact) {
		long id = 0L;
		try {

			ContentResolver cr = context.getContentResolver();
			ContentValues values = new ContentValues();
			// 先对raw_contacts表进行插入
			Uri uri = cr.insert(RawContacts.CONTENT_URI, values);
			// 解析从服务器返回的URI 获得服务器最新插入数据库的id(自动表示)
			id = ContentUris.parseId(uri);
			Uri data_uri = Uri.parse("content://com.android.contacts/data");
			// 往data表插入姓名
			values.clear();
			values.put("raw_contact_id", id);
			values.put("data1", contact.getName());
			values.put("mimetype", "vnd.android.cursor.item/name");
			cr.insert(data_uri, values);
			// 往data表插入电话
			values.clear();
			values.put("raw_contact_id", id);
			values.put("data1", contact.getPhone());
			values.put("mimetype", "vnd.android.cursor.item/phone_v2");
			cr.insert(data_uri, values);
			// 往data表插入email
			values.clear();
			values.put("raw_contact_id", id);
			values.put("data1", contact.getEmail());
			values.put("mimetype", "vnd.android.cursor.item/email_v2");
			cr.insert(data_uri, values);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return id;
		}

	}

	public void insertContact2(ContactsInfo contact) {
		//定义一个装内容介绍者操作的集合
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();

		ContentResolver cr = context.getContentResolver();
		
		//往raw_contacts表
		ContentValues values1 = new ContentValues();
		ContentProviderOperation op1 = ContentProviderOperation
				.newInsert(RawContacts.CONTENT_URI)
				.withValues(values1)
				.build();
		operations.add(op1);
		
		ContentValues values2 = new ContentValues();
		values2.put("data1", contact.getName());
		values2.put("mimetype", "vnd.android.cursor.item/name");
		ContentProviderOperation op2 = ContentProviderOperation
				.newInsert(Uri.parse("content://com.android.contacts/data"))
				.withValueBackReference("raw_contact_id", 0)
				.withValues(values2)
				.build();
		operations.add(op2);
		
		ContentValues values3 = new ContentValues();
		values3.put("data1", contact.getPhone());
		values3.put("mimetype", "vnd.android.cursor.item/phone_v2");
		ContentProviderOperation op3 = ContentProviderOperation
				.newInsert(Uri.parse("content://com.android.contacts/data"))
				.withValueBackReference("raw_contact_id", 0)
				.withValues(values3)
				.build();
		operations.add(op3);
		
		ContentValues values4 = new ContentValues();
		values4.put("data1", contact.getEmail());
		values4.put("mimetype", "vnd.android.cursor.item/email_v2");
		ContentProviderOperation op4 = ContentProviderOperation
				.newInsert(Uri.parse("content://com.android.contacts/data"))
				.withValueBackReference("raw_contact_id", 0)
				.withValues(values4)
				.build();
		operations.add(op4);
		try {
			cr.applyBatch("com.android.contacts", operations);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
