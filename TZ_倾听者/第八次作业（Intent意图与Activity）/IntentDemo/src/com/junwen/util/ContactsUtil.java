package com.junwen.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.junwen.bean.Contacts;

public class ContactsUtil {
	private static Uri uri = Uri
			.parse("content://com.android.contacts/contacts");

	/**
	 * ��ȡ���е���ϵ��
	 * 
	 * @return
	 */
	public static List<Contacts> readAllContacts(Context context) {
		//��ϵ�˼���
		List<Contacts> data =new ArrayList<Contacts>();
		//��ϵ��
		Contacts contacts ;
		////���һ��ContentResolver���ݹ���Ķ���
		ContentResolver resolver = context.getContentResolver();
		//��ѯ��ϵ��
		Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
		//����α겻Ϊ��
		if (cursor != null) {
			//���������һ��
			while (cursor.moveToNext()) {
				// ��ϵ��ID
				String id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
				// ��ϵ������
				String name = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME));
				//����ID��ѯid����ϵ����Ϣ
				Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
								+ "=" + id, null, null);
				//����α겻Ϊ��
				if (phone != null) {
					//���������һ��
					while (phone.moveToNext()) {
						//������ϵ�˶���
						contacts = new Contacts();
						//��ȡ�绰���������
						int phoneFieldColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
						//��ȡ�绰����
						String phoneNumber = phone.getString(phoneFieldColumnIndex);
						//��������
						contacts.setName(name);
						//���õ绰
						contacts.setPhoneNum(phoneNumber);
						//��ӵ����ϵ���
						data.add(contacts);
					}
				}
			}
		}
		//�ر���
		cursor.close();
		return data;
	}
}
