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
	 * ��ѯ��ϵ��
	 */
	private void queryData() {
		List<ContactInfo> data = new ArrayList<ContactInfo>();
		ContentResolver cr = this.getContentResolver();//��ȡ���ݽ�����
		Uri uri_raw_contacts = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
		Cursor cursor = cr.query(uri_raw_contacts, new String[]{"_id","display_name"}, null, null, null);
		while (cursor.moveToNext()) {
			ContactInfo info = new ContactInfo();
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			Uri uri_data = ContactsContract.AUTHORITY_URI;
			uri_data = Uri.withAppendedPath(uri_data, "data");//�ڶ��ַ�ʽ"raw_contact_id"+_id+"/data"
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
	 * Զ������
	 */
	public void transactionCommit(ContactInfo info){
		ContentResolver cr = this.getContentResolver();
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		Uri uri_raw_contacts = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
		//��raw_contacts�����������ݻ�ȡ����_id
		//��һ�β���������raw_contacts��
		ContentValues values_raw = new ContentValues();
		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri_raw_contacts).withValues(values_raw).build();
		operations.add(op1);
		//��data������Ū��������
		//�ڶ��β�������������
		Uri uri_data = ContactsContract.AUTHORITY_URI;
		uri_data = Uri.withAppendedPath(uri_data, "data");
		ContentValues values = new ContentValues();
		values.put("data1", "aaaa");
		values.put("mimetype","vnd.android.cursor.item/name");
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values).build();
		operations.add(op2);
		
		//�����β���������绰
		values.clear();
		values.put("data1", "aa111");
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		ContentProviderOperation op3 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op3);

		// ���Ĵβ�������������
		values.clear();
		values.put("data1", "aa11@qq.com");
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		ContentProviderOperation op4 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op4);

		//Զ�������ύ
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
	 * Զ�̸�������
	 */
	public void transactionUpdateCommit(ContactInfo info){
		ContentResolver cr = this.getContentResolver();
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		Uri uri_raw_contacts = Uri.parse("content://"+ContactsContract.AUTHORITY+"/raw_contacts");
		//��raw_contacts�����������ݻ�ȡ����_id
		//��һ�β���������raw_contacts��
		ContentValues values_raw = new ContentValues();
		values_raw.put("_id", 1);
		ContentProviderOperation op1 = ContentProviderOperation.newUpdate(uri_raw_contacts).withValues(values_raw).build();
//		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri_raw_contacts).withValues(values_raw).build();
		operations.add(op1);
		//��data������Ū��������
		//�ڶ��β�������������
		Uri uri_data = ContactsContract.AUTHORITY_URI;
		uri_data = Uri.withAppendedPath(uri_data, "data");
		ContentValues values = new ContentValues();
		values.put("data1", "bbbbbb");
		values.put("mimetype","vnd.android.cursor.item/name");
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values).build();
		operations.add(op2);
		
		//�����β���������绰
		values.clear();
		values.put("data1", "bbbbbb000");
		values.put("mimetype", "vnd.android.cursor.item/phone_v2");
		ContentProviderOperation op3 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op3);
		
		// ���Ĵβ�������������
		values.clear();
		values.put("data1", "bbb0@qq.com");
		values.put("mimetype", "vnd.android.cursor.item/email_v2");
		ContentProviderOperation op4 = ContentProviderOperation
				.newInsert(uri_data)
				.withValueBackReference("raw_contact_id", 0).withValues(values)
				.build();
		operations.add(op4);
		
		//Զ�������ύ
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
