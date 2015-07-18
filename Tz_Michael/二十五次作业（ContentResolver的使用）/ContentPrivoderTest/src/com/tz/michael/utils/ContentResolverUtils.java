package com.tz.michael.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.tz.michael.bean.ContactInfo;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;

public class ContentResolverUtils {

	/**
	 * 通用的查询所有手机联系人的方法(尝试了很多方法，无法完美的用反射实现，不是覆盖，就是顺序不对)
	 * @param context
	 * @param dataTye
	 * @return
	 */
	public static List<Object> queryData(Context context,Class dataTye){
		List<Object> ll=new ArrayList<Object>();
		Object object=null;
		try {
			Constructor constructor = dataTye.getDeclaredConstructor((Class[])null);
			Field[] fields = dataTye.getDeclaredFields();
			ContentResolver contentResolver=context.getContentResolver();
	        Uri uri=ContactsContract.AUTHORITY_URI;
	        uri=uri.withAppendedPath(uri, "raw_contacts");
	        Cursor custor = contentResolver.query(uri, new String[]{"contact_id"}, null, null, null);
	        while(custor.moveToNext()){
	        	object=constructor.newInstance((Object[])null);
	        	String contact_id=custor.getString(custor.getColumnIndex("contact_id"));
	        	Uri uri_data=ContactsContract.AUTHORITY_URI;
	        	uri_data=uri_data.withAppendedPath(uri_data, "data");
	        	Cursor custor_data = contentResolver.query(uri_data, null, "raw_contact_id=?", new String[]{contact_id}, null);
	        	while(custor_data.moveToNext()){
	        		String data1=custor_data.getString(custor_data.getColumnIndex("data1"));
	        		for(Field field:fields){
	        			field.setAccessible(true);
	        			Class fieldType=field.getType();
	        			String fieldName=(String) field.getName();
	        			String method_name="set"+ ChangeFirstLetterToUpper(fieldName);
	        			Method method = dataTye.getDeclaredMethod(method_name, new Class[]{fieldType});
	        			String mimitype=custor_data.getString(custor_data.getColumnIndex("mimetype"));
//	        			method.invoke(object, data1);
	        			if(mimitype.equals("vnd.android.cursor.item/name")){
	        				method.invoke(object, data1);
	            		}else if(mimitype.equals("vnd.android.cursor.item/phone_v2")){
	            			method.invoke(object, data1);
	            		}else if(mimitype.equals("vnd.android.cursor.item/email_v2")){
	            			method.invoke(object, data1);
	            		}
	        		}
	        	}
	        	ll.add(object);
	        }
	        return ll;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String ChangeFirstLetterToUpper(String strChange) {
		String tempFirst = strChange.substring(0, 1);
		String tempElse = strChange.substring(1);
		return (tempFirst.toUpperCase() + tempElse);
	}
	
	/**
	 * 查询手机所有联系人
	 * @param context
	 * @return
	 */
	public static List<ContactInfo> queryData(Context context){
		List<ContactInfo> ll=new ArrayList<ContactInfo>();
		ContactInfo contactInfo=null;
		ContentResolver contentResolver=context.getContentResolver();
        Uri uri=ContactsContract.AUTHORITY_URI;
        uri=uri.withAppendedPath(uri, "raw_contacts");
        Cursor custor = contentResolver.query(uri, new String[]{"contact_id"}, null, null, null);
        while(custor.moveToNext()){
        	contactInfo=new ContactInfo();
        	String contact_id=custor.getString(custor.getColumnIndex("contact_id"));
        	Uri uri_data=ContactsContract.AUTHORITY_URI;
        	uri_data=uri_data.withAppendedPath(uri_data, "data");
        	Cursor custor_data = contentResolver.query(uri_data, null, "raw_contact_id=?", new String[]{contact_id}, null);
        	while(custor_data.moveToNext()){
        		String data1=custor_data.getString(custor_data.getColumnIndex("data1"));
        		String mimitype=custor_data.getString(custor_data.getColumnIndex("mimetype"));
        		if(mimitype.equals("vnd.android.cursor.item/name")){
        			contactInfo.setName(data1);
        		}else if(mimitype.equals("vnd.android.cursor.item/phone_v2")){
        			contactInfo.setPhoneNum(data1);
        		}else if(mimitype.equals("vnd.android.cursor.item/email_v2")){
        			contactInfo.setEmail(data1);
        		}
        	}
        }
        ll.add(contactInfo);
		return ll;
	}
	
	/**
	 * 实现远程事物插入数据（跨应用）
	 * @param context
	 * @param contactInfo
	 */
	public static void remoteInsertData(Context context,ContactInfo contactInfo){
		ContentResolver contentResolver=context.getContentResolver();
		ArrayList<ContentProviderOperation> operations=new ArrayList<ContentProviderOperation>();
		Uri uri_raw_contact=Uri.parse("content://" + ContactsContract.AUTHORITY+"/raw_contacts");
		ContentValues contentValues=new ContentValues();
		//向raw_contacts表里插入空数据获取自增_id
		operations.add(makeAContentProviderOperation(contentValues, uri_raw_contact));
		Uri uri_data=Uri.parse("content://" + ContactsContract.AUTHORITY+"/data");
		contentValues.put("data1", contactInfo.getName());
		contentValues.put("mimetype", "vnd.android.cursor.item/name");
		operations.add(makeAContentProviderOperationWithBackReference(contentValues, uri_data,"raw_contact_id", 0));
		contentValues.clear();
		contentValues.put("data1", contactInfo.getPhoneNum());
		contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		operations.add(makeAContentProviderOperationWithBackReference(contentValues, uri_data,"raw_contact_id", 0));
		contentValues.clear();
		contentValues.put("data1", contactInfo.getEmail());
		contentValues.put("mimetype", "vnd.android.cursor.item/email_v2");
		operations.add(makeAContentProviderOperationWithBackReference(contentValues, uri_data,"raw_contact_id", 0));
		try {
			//提交操作队列
			contentResolver.applyBatch(ContactsContract.AUTHORITY, operations);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回一个操作
	 * @param contentValues
	 * @param uri
	 * @return
	 */
	public static ContentProviderOperation makeAContentProviderOperation(ContentValues contentValues,Uri uri){
		ContentProviderOperation operation=ContentProviderOperation.newInsert(uri).withValues(contentValues).build();
		return operation;
	}
	
	/**
	 * 返回一个带回调引用的操作
	 * @param contentValues
	 * @param uri
	 * @param clumName
	 * @param index
	 * @return
	 */
	public static ContentProviderOperation makeAContentProviderOperationWithBackReference(ContentValues contentValues,Uri uri,String clumName,int index){
		ContentProviderOperation operation=ContentProviderOperation.newInsert(uri).withValues(contentValues).withValueBackReference(clumName, index).build();
		return operation;
	}
	
}
