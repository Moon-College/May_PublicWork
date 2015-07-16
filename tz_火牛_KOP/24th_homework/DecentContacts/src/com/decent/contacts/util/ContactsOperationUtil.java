package com.decent.contacts.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;

import com.decent.decentutil.DecentLogUtil;

public class ContactsOperationUtil {

	/**
	 * 获得联系人列表
	 * @param a T的类型
	 * @param context 上下文
	 * @param selection 选择语句
	 * @param selectionArgs 选择语句的参数
	 * @return 联系人列表 
	 */
	public static <T> List<T> getAllContacts(Class<T> a, Context context,
			String selection, String[] selectionArgs) {
		// Uri.parse("content://"+ContactsContract.AUTHORITY+"/"+ContactsContract.RawContacts);
		// 使用withAppendedPath获得raw_contacts的uri
		Uri raw_contacts_uri = Uri.withAppendedPath(
				ContactsContract.AUTHORITY_URI,
				Constants.RAW_CONTACTS_TABLE_NAME);
		// 获得ContentResolver
		ContentResolver cr = context.getContentResolver();
		// 查询，带有条件的查询
		Cursor raw_contacts_cursor = cr.query(raw_contacts_uri,
				new String[] { "_id" }, selection, selectionArgs, null);
		//初始化列表
		List<T> contactList = new ArrayList<T>();
		try {
			// 遍历raw_data游标
			while (raw_contacts_cursor.moveToNext()) {
				//初始化一个data
				T data = a.newInstance();
				// 获得raw_contacts_id
				int raw_contacts_id = raw_contacts_cursor
						.getInt(raw_contacts_cursor.getColumnIndex("_id"));
				/*
				 * 反射出_id字段，设置_id字段
				 */
				Field _id_field = data.getClass().getDeclaredField("_id");
				_id_field.setAccessible(true);
				_id_field.set(data, raw_contacts_id);
				_id_field.setAccessible(false);
				
				// 组装出raw_contacts_id对应的data路径，通过id查询出data表里面的对应数据
				Uri data_uri = Uri.withAppendedPath(
						ContactsContract.AUTHORITY_URI,
						Constants.RAW_CONTACTS_TABLE_NAME + "/"
								+ raw_contacts_id + "/"
								+ Constants.DATA_TABLE_NAME);
				Cursor data_cursor = cr.query(data_uri, null, null, null, null);
				//初始化一个电话号码的列表，也许后面有用
				List<String> phoneList = new ArrayList<String>();
				while (data_cursor.moveToNext()) {
					String value = data_cursor.getString(data_cursor
							.getColumnIndex("data1"));
					String mimeType = data_cursor.getString(data_cursor
							.getColumnIndex("mimetype"));
					/*
					 * 根据 mimeType，通过反射填充字段，目前支持的还比较少3种,
					 * mail只支持1个，
					 * phone支持列表
					 */
					if (Constants.MIMETYPE_NAME.equals(mimeType)) {
						Field name_field = data.getClass().getDeclaredField(
								"name");
						/*
						 * 设置名字字段
						 */
						name_field.setAccessible(true);
						name_field.set(data, value);
						name_field.setAccessible(false);
					} else if (Constants.MIMETYPE_EMAILV2.equals(mimeType)) {
						Field email_field = data.getClass().getDeclaredField(
								"email");
						/*
						 * 设置名字字段
						 */
						email_field.setAccessible(true);
						email_field.set(data, value);
						email_field.setAccessible(false);
					} else if (Constants.MIMETYPE_PHONEV2.equals(mimeType)) {
						Field phoneList_field = data.getClass()
								.getDeclaredField("phoneList");
						phoneList_field.setAccessible(true);
						List<String> dataList = (List<String>) phoneList_field
								.get(data);
						if(dataList==null){
							phoneList_field.set(data, phoneList);
							dataList = (List<String>) phoneList_field
									.get(data);
						}
						dataList.add(value);						
						phoneList_field.setAccessible(false);
					}
				}
				contactList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactList;
	}

	/**
	 * 
	 * @param context
	 * @param data
	 */
	public static <T> void insertSingleContact(Context context, T data,
			String[] fieldList) {

		Uri raw_contacts_uri = Uri.withAppendedPath(
				ContactsContract.AUTHORITY_URI,
				Constants.RAW_CONTACTS_TABLE_NAME);
		// 获得ContentResolver
		ContentResolver cr = context.getContentResolver();
		// 需要使用远程事，1、创建操作列表
		ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();

		/*
		 * 1、创建插入raw_contacts的operation 这个只需要插入一个空的内容即可(new了之后不插入数据),需要获取它返回的id
		 */
		ContentValues raw_contacts_data = new ContentValues();
		ContentProviderOperation insertRawContactsOperation = ContentProviderOperation
				.newInsert(raw_contacts_uri).withValues(raw_contacts_data)
				.build();
		operationList.add(insertRawContactsOperation);

		// 组装出对应的data路径
		Uri data_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI,
				Constants.DATA_TABLE_NAME);
		try {
			if (fieldList != null) {
				/*
				 * 如果 fieldList不是空，则遍历fieldList
				 */
				for (String strField : fieldList) {
					Field field = data.getClass().getDeclaredField(strField);
					Class type = field.getType();
					/*
					 * 如果遍历到phoneList，emailList这样的字段，则需要遍历list插入
					 */
					if (type.isAssignableFrom(List.class)) {
						field.setAccessible(true);
						/*
						 * 强转成list
						 */
						List<String> dataList = (List<String>) field.get(data);
						/*
						 * 统一获得mimeType
						 */
						String srtMimetype = Constants
								.getMimeTypeNameByFieldName(strField);
						/*
						 * 遍历list,目前暂时只支持的list只支持String的，如果有其他的也需要这样
						 */
						for (String singleData : dataList) {
							/*
							 * 解析数据，插入对应的数据
							 */
							ContentValues values = new ContentValues();
							values.put("data1", singleData);
							values.put("mimetype", srtMimetype);
							ContentProviderOperation phoneDataOperation = ContentProviderOperation
									.newInsert(data_uri)
									.withValueBackReference("raw_contact_id", 0)
									.withValues(values).build();
							operationList.add(phoneDataOperation);
						}
						field.setAccessible(false);
					} else {
						/*
						 * 这个分支则通过反射获取字段的，然后插入
						 */
						ContentValues values = new ContentValues();
						addToValuesByType(data, values, "data1", field);

						ContentProviderOperation nameDataOperation = ContentProviderOperation
								.newInsert(data_uri)
								.withValueBackReference("raw_contact_id", 0)
								.withValues(values).build();
						operationList.add(nameDataOperation);
					}
				}
			} else {
				/*
				 * 没有fieldList的分支，则通过反射所有的字段来插入数据 1、遍历所有的field
				 */
				Field[] fileds = data.getClass().getDeclaredFields();
				for (Field field : fileds) {
					field.setAccessible(true);
					String fieldName = field.getName();
					/*
					 * 除了id字段其他的都需要插入
					 */
					if (!"_id".equals(fieldName)) {
						Class fieldType = field.getType();
						/*
						 * 填充values
						 */
						ContentValues values = new ContentValues();
						addToValuesByType(data, values, "data1", field);

						/*
						 * 加入列表中
						 */
						ContentProviderOperation nameDataOperation = ContentProviderOperation
								.newInsert(data_uri)
								.withValueBackReference("raw_contact_id", 0)
								.withValues(values).build();
						operationList.add(nameDataOperation);
					}
					field.setAccessible(false);
				}
			}
			cr.applyBatch(ContactsContract.AUTHORITY, operationList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除通讯录的一条记录
	 * 
	 * @param context
	 *            上下文
	 * @param data
	 *            需要删除的内容（一定要包含_id字段），删除的时候是按照_id字段来作为删除的依据
	 */
	public static <T> void deleteSingleContactById(Context context, T data) {
		// TODO Auto-generated method stub
		Uri raw_contacts_uri = Uri.withAppendedPath(
				ContactsContract.AUTHORITY_URI,
				Constants.RAW_CONTACTS_TABLE_NAME);

		Uri data_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI,
				Constants.DATA_TABLE_NAME);
		// 获得ContentResolver
		ContentResolver cr = context.getContentResolver();

		int _id = -1;
		try {
			Field _id_filed;
			_id_filed = data.getClass().getDeclaredField("_id");
			_id_filed.setAccessible(true);
			_id = (Integer) _id_filed.get(data);
			_id_filed.setAccessible(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (_id == -1) {
			return;
		}
		// 删除也是需要远程事务的
		ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();

		// 先删除data里面的，外键是raw_contact_id并且和contac里面的_id相等的那些数据
		// Uri data_uri_with_id = Uri.withAppendedPath(data_uri,
		// String.valueOf(_id));
		ContentProviderOperation deleteDataOperation = ContentProviderOperation
				.newDelete(data_uri)
				.withSelection("raw_contact_id = ?",
						new String[] { String.valueOf(_id) }).build();
		operationList.add(deleteDataOperation);

		// 再删除raw_contacts里面的,_id == 这里的_id的那些数据
		ContentProviderOperation deleteRawContactsOperation = ContentProviderOperation
				.newDelete(raw_contacts_uri)
				.withSelection("_id = ?", new String[] { String.valueOf(_id) })
				.build();
		operationList.add(deleteRawContactsOperation);

		try {
			cr.applyBatch(ContactsContract.AUTHORITY, operationList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// cr.delete(url, where, selectionArgs);
	}

	/**
	 * 根据data里面的_id作为键值，更新contac记录
	 * 
	 * @param context
	 *            上下文
	 * @param data
	 *            数据
	 * @param stringsFields
	 *            需要更新的字段，如果为空则使用反射把除了_id字段的都更新
	 */
	public static <T> void updateContact(Context context, T data,
			String[] stringsFields) {
		// TODO Auto-generated method stub
		// 获得ContentResolver
		ContentResolver cr = context.getContentResolver();
		int _id = -1;
		ContentValues values = new ContentValues();
		try {
			// 先获取到id
			Field idField = data.getClass().getDeclaredField("_id");
			idField.setAccessible(true);
			_id = (Integer) idField.get(data);
			idField.setAccessible(false);
			Uri data_uri = Uri.withAppendedPath(
					ContactsContract.AUTHORITY_URI,
					Constants.RAW_CONTACTS_TABLE_NAME + "/"
							+ String.valueOf(_id) + "/"
							+ Constants.DATA_TABLE_NAME);
			if (stringsFields != null) {
				for (String fieldName : stringsFields) {
					Field field = data.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					Class fieldType = field.getType();
					addToValuesByType(data, values, "data1", field);
					DecentLogUtil.d("INFO", "_id=" + _id + ",mimetype:"
							+ values.getAsString("mimetype"));
					DecentLogUtil.d("INFO", "uri:" + data_uri.toString());
					cr.update(data_uri, values, "mimetype = ?",
							new String[] { values.getAsString("mimetype") });

					field.setAccessible(false);
				}
			} else {
				/*
				 * 1、遍历所有的field
				 */
				Field[] fileds = data.getClass().getDeclaredFields();
				for (Field field : fileds) {
					field.setAccessible(true);
					String fieldName = field.getName();
					if (!"_id".equals(fieldName)) {
						Class fieldType = field.getType();
						addToValuesByType(data, values, "data1", field);
					}
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据field的名字和类型填充到values
	 * 
	 * @param data
	 *            data
	 * @param field
	 *            要填充的字段的field的信息
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private static <T> void addToValuesByType(T data, ContentValues values,
			String key, Field field) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		field.setAccessible(true);
		Class fieldType = field.getType();
		String fieldName = field.getName();
		/*
		 * 如果keyName为空，则默认 values.put的key就是字段本身的名字
		 */
		String keyName = key;
		if (keyName == null) {
			keyName = fieldName;
		}
		if (fieldType.isAssignableFrom(byte[].class)) {
			values.put(fieldName, (byte[]) field.get(data));
		} else if (fieldType.isAssignableFrom(String.class)
				|| fieldType.isAssignableFrom(int.class)
				|| fieldType.isAssignableFrom(Long.class)
				|| fieldType.isAssignableFrom(Short.class)
				|| fieldType.isAssignableFrom(Double.class)
				|| fieldType.isAssignableFrom(Float.class)
				|| fieldType.isAssignableFrom(Integer.class)) {
			values.put(keyName, String.valueOf(field.get(data)));
			values.put("mimetype",
					Constants.getMimeTypeNameByFieldName(fieldName));
			// DecentLogUtil.d("INFO",
			// "data1 at String"+values.getAsString("data1"));
		} else if (fieldType.isAssignableFrom(Boolean.class)) {
			values.put(keyName, (Boolean) field.get(data));
			values.put("mimetype",
					Constants.getMimeTypeNameByFieldName(fieldName));
		} else if (fieldType.isAssignableFrom(Byte.class)) {
			values.put(keyName, (Byte) field.get(data));
			values.put("mimetype",
					Constants.getMimeTypeNameByFieldName(fieldName));
		} else if (fieldType.isAssignableFrom(List.class)) {
			/*
			 * 目前只支持List<String>
			 */
			List<String> dataList = (List<String>) field.get(data);
			values.put(keyName, dataList.get(0));
			values.put("mimetype",
					Constants.getMimeTypeNameByFieldName(fieldName));
			// DecentLogUtil.d("INFO",
			// "data1 at list"+values.getAsString("data1"));
			/*
			 * List<String> dataList = (List<String>) field.get(a); for (String
			 * data : dataList) { values.put(fieldName, data);
			 * values.put("mimetype",
			 * Constants.getMimeTypeNameByFieldName(fieldName)); }
			 */
		}
		field.setAccessible(false);
		// DecentLogUtil.d("INFO", "data1"+values.getAsString("data1"));
	}
}
