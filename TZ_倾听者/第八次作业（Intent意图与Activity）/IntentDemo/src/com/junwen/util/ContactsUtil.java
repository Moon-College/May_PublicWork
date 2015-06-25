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
	 * 读取所有的联系人
	 * 
	 * @return
	 */
	public static List<Contacts> readAllContacts(Context context) {
		//联系人集合
		List<Contacts> data =new ArrayList<Contacts>();
		//联系人
		Contacts contacts ;
		////获得一个ContentResolver数据共享的对象
		ContentResolver resolver = context.getContentResolver();
		//查询联系人
		Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
		//如果游标不为空
		if (cursor != null) {
			//如果还有下一行
			while (cursor.moveToNext()) {
				// 联系人ID
				String id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
				// 联系人姓名
				String name = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME));
				//根据ID查询id的联系人信息
				Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
								+ "=" + id, null, null);
				//如果游标不为空
				if (phone != null) {
					//如果还有下一行
					while (phone.moveToNext()) {
						//创建联系人对象
						contacts = new Contacts();
						//获取电话号码的列名
						int phoneFieldColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
						//获取电话号码
						String phoneNumber = phone.getString(phoneFieldColumnIndex);
						//设置名字
						contacts.setName(name);
						//设置电话
						contacts.setPhoneNum(phoneNumber);
						//添加到集合当中
						data.add(contacts);
					}
				}
			}
		}
		//关闭流
		cursor.close();
		return data;
	}
}
