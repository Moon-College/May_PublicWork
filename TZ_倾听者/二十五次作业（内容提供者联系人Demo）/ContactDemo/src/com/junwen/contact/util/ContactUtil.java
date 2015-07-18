package com.junwen.contact.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;

import com.junwen.contact.constant.Constant;
import com.junwen.contact.modle.Contacts;

public class ContactUtil {

	
	/**
	 * 获取所有的联系人
	 * @return
	 */
	public static List<Contacts> getAllContacts(Context context){
		List<Contacts> data = new ArrayList<Contacts>();
		ContentResolver resolver = context.getContentResolver();
		//Raw_content表的uri
		Uri uri = ContactsContract.RawContacts.CONTENT_URI;
		//查询
		Cursor result = resolver.query(uri , null, null, null, null);
		if(result!=null){
			while(result.moveToNext()){
				//ID
				Contacts con = new Contacts();
				int id = result.getInt(result.getColumnIndex(ContactsContract.RawContacts._ID));
				Uri uri_data = ContactsContract.Data.CONTENT_URI;
//				Uri uri_data = Uri.parse(ContactsContract.RawContacts.CONTENT_URI+"/"+id+"/data");
				//根据ID查询id对应的属性
				Cursor cursor = resolver.query(uri_data, null, ContactsContract.Data.RAW_CONTACT_ID+"=?", new String[]{String.valueOf(id)}, null);
				if(cursor!=null){
					while(cursor.moveToNext()){
						//数据
						String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA1));
						//类型
						String type = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
						if(type.equals(Constant.TYPE_NAME)){
							//名字
							con.setName(name);
						}else if(type.equals(Constant.TYPE_EMAIL)){
							//Eamil
							con.setEmail(name);
						}
						else if(type.equals(Constant.TYPE_PHONE)){
							//电话号码
							con.setPhoneNumber(name);
						}
					}
					if(con!=null ){
						data.add(con);
					}
					cursor.close();
				}
			}
			result.close();
		}
		for (Contacts contacts : data) {
			System.out.println(contacts.getName());
		}
		return data;
	}
	
	/**
	 * 添加联系人
	 * @return
	 */
	public static boolean insertContacts(Context context,Contacts contact){
		
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		
		ContentResolver resolver = context.getContentResolver();
		//Raw_content的URI
		Uri uri_raw = ContactsContract.RawContacts.CONTENT_URI;
		//Data的uri
		Uri uri_data = ContactsContract.Data.CONTENT_URI;
		//id的添加
		ContentValues values = new ContentValues();
		ContentProviderOperation op1  = ContentProviderOperation.newInsert(uri_raw).withValues(values).build();
		operations.add(op1);
		
		//添加名字
		values.clear();
		values.put(ContactsContract.Data.DATA1, contact.getName()==null?"无":contact.getName());
		values.put(ContactsContract.Data.MIMETYPE, Constant.TYPE_NAME);
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri_data).withValueBackReference(Constant.RAW_CONTACT_ID, 0).withValues(values).build();
		operations.add(op2);
		//添加号码
		values.clear();
		values.put(ContactsContract.Data.DATA1, contact.getPhoneNumber()==null?"无":contact.getPhoneNumber());
		values.put(ContactsContract.Data.MIMETYPE, Constant.TYPE_PHONE);
		ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri_data).withValueBackReference(Constant.RAW_CONTACT_ID, 0).withValues(values).build();
		operations.add(op3);
				
		//添加号码
		values.clear();
		values.put(ContactsContract.Data.DATA1,  contact.getEmail()==null?"无":contact.getEmail());
		values.put(ContactsContract.Data.MIMETYPE, Constant.TYPE_EMAIL);
		ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri_data).withValueBackReference(Constant.RAW_CONTACT_ID, 0).withValues(values).build();
		operations.add(op4);
		
		try {
			ContentProviderResult[] applyBatch = resolver.applyBatch(ContactsContract.AUTHORITY, operations);
			if(applyBatch.length == operations.size()){
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 根据ID来更新数据
	 * @param context
	 * @return
	 */
	public static boolean updateContacts(Context context,int id,Contacts contact){
		
		ArrayList<ContentProviderOperation> list = new ArrayList<ContentProviderOperation>();
		ContentResolver resolver = context.getContentResolver();
		//更新名字
		ContentValues values = new ContentValues();
		values.put(ContactsContract.Data.DATA1, contact.getName());
		ContentProviderOperation op1 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).
				withSelection(ContactsContract.Data.RAW_CONTACT_ID+"=? and "+ContactsContract.Data.MIMETYPE+"=?", new String[]{String.valueOf(id),Constant.TYPE_NAME}).withValues(values).build();
		list.add(op1);
		//更新号码
				values.clear();
				values.put(ContactsContract.Data.DATA1, contact.getPhoneNumber());
				ContentProviderOperation op2 = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).
						withSelection(ContactsContract.Data.RAW_CONTACT_ID+"=? and "+ContactsContract.Data.MIMETYPE+"=?", new String[]{String.valueOf(id),Constant.TYPE_PHONE}).withValues(values).build();
				list.add(op2);
		//更新Email
		values.clear();
		values.put(ContactsContract.Data.DATA1, contact.getEmail());
		ContentProviderOperation op3= ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).
		      withSelection(ContactsContract.Data.RAW_CONTACT_ID+"=? and "+ContactsContract.Data.MIMETYPE+"=?", new String[]{String.valueOf(id),Constant.TYPE_EMAIL}).withValues(values).build();
		list.add(op3);
		//开始提交事务
		try {
			ContentProviderResult[] applyBatch = resolver.applyBatch(ContactsContract.AUTHORITY, list);
			if(applyBatch.length == list.size()){
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除联系人
	 * @param context
	 * @return
	 */
	public static boolean deleteContacts(Context context,String name){
		ContentResolver contentResolver = context.getContentResolver();
		Cursor query = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.DATA1+"=?", new String[]{name}, null);
		while(query.moveToNext()){
			int id = query.getInt(query.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
			int delete = contentResolver.delete(ContactsContract.Data.CONTENT_URI, ContactsContract.Data.RAW_CONTACT_ID+"=?", new String[]{String.valueOf(id)});
			int delete2 = contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts._ID+"=?", new String[]{String.valueOf(id)});
			if(delete >0 || delete2> 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除所有的联系人
	 * @param context
	 * @return
	 */
	public static boolean deleteAll(Context context){
		ContentResolver contentResolver = context.getContentResolver();
		contentResolver.delete(ContactsContract.Data.CONTENT_URI, null, null);
		contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, null, null);
		return false;
	}
}
