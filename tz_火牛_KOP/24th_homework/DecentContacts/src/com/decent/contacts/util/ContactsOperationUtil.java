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
	 * �����ϵ���б�
	 * @param a T������
	 * @param context ������
	 * @param selection ѡ�����
	 * @param selectionArgs ѡ�����Ĳ���
	 * @return ��ϵ���б� 
	 */
	public static <T> List<T> getAllContacts(Class<T> a, Context context,
			String selection, String[] selectionArgs) {
		// Uri.parse("content://"+ContactsContract.AUTHORITY+"/"+ContactsContract.RawContacts);
		// ʹ��withAppendedPath���raw_contacts��uri
		Uri raw_contacts_uri = Uri.withAppendedPath(
				ContactsContract.AUTHORITY_URI,
				Constants.RAW_CONTACTS_TABLE_NAME);
		// ���ContentResolver
		ContentResolver cr = context.getContentResolver();
		// ��ѯ�����������Ĳ�ѯ
		Cursor raw_contacts_cursor = cr.query(raw_contacts_uri,
				new String[] { "_id" }, selection, selectionArgs, null);
		//��ʼ���б�
		List<T> contactList = new ArrayList<T>();
		try {
			// ����raw_data�α�
			while (raw_contacts_cursor.moveToNext()) {
				//��ʼ��һ��data
				T data = a.newInstance();
				// ���raw_contacts_id
				int raw_contacts_id = raw_contacts_cursor
						.getInt(raw_contacts_cursor.getColumnIndex("_id"));
				/*
				 * �����_id�ֶΣ�����_id�ֶ�
				 */
				Field _id_field = data.getClass().getDeclaredField("_id");
				_id_field.setAccessible(true);
				_id_field.set(data, raw_contacts_id);
				_id_field.setAccessible(false);
				
				// ��װ��raw_contacts_id��Ӧ��data·����ͨ��id��ѯ��data������Ķ�Ӧ����
				Uri data_uri = Uri.withAppendedPath(
						ContactsContract.AUTHORITY_URI,
						Constants.RAW_CONTACTS_TABLE_NAME + "/"
								+ raw_contacts_id + "/"
								+ Constants.DATA_TABLE_NAME);
				Cursor data_cursor = cr.query(data_uri, null, null, null, null);
				//��ʼ��һ���绰������б�Ҳ���������
				List<String> phoneList = new ArrayList<String>();
				while (data_cursor.moveToNext()) {
					String value = data_cursor.getString(data_cursor
							.getColumnIndex("data1"));
					String mimeType = data_cursor.getString(data_cursor
							.getColumnIndex("mimetype"));
					/*
					 * ���� mimeType��ͨ����������ֶΣ�Ŀǰ֧�ֵĻ��Ƚ���3��,
					 * mailֻ֧��1����
					 * phone֧���б�
					 */
					if (Constants.MIMETYPE_NAME.equals(mimeType)) {
						Field name_field = data.getClass().getDeclaredField(
								"name");
						/*
						 * ���������ֶ�
						 */
						name_field.setAccessible(true);
						name_field.set(data, value);
						name_field.setAccessible(false);
					} else if (Constants.MIMETYPE_EMAILV2.equals(mimeType)) {
						Field email_field = data.getClass().getDeclaredField(
								"email");
						/*
						 * ���������ֶ�
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
		// ���ContentResolver
		ContentResolver cr = context.getContentResolver();
		// ��Ҫʹ��Զ���£�1�����������б�
		ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();

		/*
		 * 1����������raw_contacts��operation ���ֻ��Ҫ����һ���յ����ݼ���(new��֮�󲻲�������),��Ҫ��ȡ�����ص�id
		 */
		ContentValues raw_contacts_data = new ContentValues();
		ContentProviderOperation insertRawContactsOperation = ContentProviderOperation
				.newInsert(raw_contacts_uri).withValues(raw_contacts_data)
				.build();
		operationList.add(insertRawContactsOperation);

		// ��װ����Ӧ��data·��
		Uri data_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI,
				Constants.DATA_TABLE_NAME);
		try {
			if (fieldList != null) {
				/*
				 * ��� fieldList���ǿգ������fieldList
				 */
				for (String strField : fieldList) {
					Field field = data.getClass().getDeclaredField(strField);
					Class type = field.getType();
					/*
					 * ���������phoneList��emailList�������ֶΣ�����Ҫ����list����
					 */
					if (type.isAssignableFrom(List.class)) {
						field.setAccessible(true);
						/*
						 * ǿת��list
						 */
						List<String> dataList = (List<String>) field.get(data);
						/*
						 * ͳһ���mimeType
						 */
						String srtMimetype = Constants
								.getMimeTypeNameByFieldName(strField);
						/*
						 * ����list,Ŀǰ��ʱֻ֧�ֵ�listֻ֧��String�ģ������������Ҳ��Ҫ����
						 */
						for (String singleData : dataList) {
							/*
							 * �������ݣ������Ӧ������
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
						 * �����֧��ͨ�������ȡ�ֶεģ�Ȼ�����
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
				 * û��fieldList�ķ�֧����ͨ���������е��ֶ����������� 1���������е�field
				 */
				Field[] fileds = data.getClass().getDeclaredFields();
				for (Field field : fileds) {
					field.setAccessible(true);
					String fieldName = field.getName();
					/*
					 * ����id�ֶ������Ķ���Ҫ����
					 */
					if (!"_id".equals(fieldName)) {
						Class fieldType = field.getType();
						/*
						 * ���values
						 */
						ContentValues values = new ContentValues();
						addToValuesByType(data, values, "data1", field);

						/*
						 * �����б���
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
	 * ɾ��ͨѶ¼��һ����¼
	 * 
	 * @param context
	 *            ������
	 * @param data
	 *            ��Ҫɾ�������ݣ�һ��Ҫ����_id�ֶΣ���ɾ����ʱ���ǰ���_id�ֶ�����Ϊɾ��������
	 */
	public static <T> void deleteSingleContactById(Context context, T data) {
		// TODO Auto-generated method stub
		Uri raw_contacts_uri = Uri.withAppendedPath(
				ContactsContract.AUTHORITY_URI,
				Constants.RAW_CONTACTS_TABLE_NAME);

		Uri data_uri = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI,
				Constants.DATA_TABLE_NAME);
		// ���ContentResolver
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
		// ɾ��Ҳ����ҪԶ�������
		ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();

		// ��ɾ��data����ģ������raw_contact_id���Һ�contac�����_id��ȵ���Щ����
		// Uri data_uri_with_id = Uri.withAppendedPath(data_uri,
		// String.valueOf(_id));
		ContentProviderOperation deleteDataOperation = ContentProviderOperation
				.newDelete(data_uri)
				.withSelection("raw_contact_id = ?",
						new String[] { String.valueOf(_id) }).build();
		operationList.add(deleteDataOperation);

		// ��ɾ��raw_contacts�����,_id == �����_id����Щ����
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
	 * ����data�����_id��Ϊ��ֵ������contac��¼
	 * 
	 * @param context
	 *            ������
	 * @param data
	 *            ����
	 * @param stringsFields
	 *            ��Ҫ���µ��ֶΣ����Ϊ����ʹ�÷���ѳ���_id�ֶεĶ�����
	 */
	public static <T> void updateContact(Context context, T data,
			String[] stringsFields) {
		// TODO Auto-generated method stub
		// ���ContentResolver
		ContentResolver cr = context.getContentResolver();
		int _id = -1;
		ContentValues values = new ContentValues();
		try {
			// �Ȼ�ȡ��id
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
				 * 1���������е�field
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
	 * ����field�����ֺ�������䵽values
	 * 
	 * @param data
	 *            data
	 * @param field
	 *            Ҫ�����ֶε�field����Ϣ
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
		 * ���keyNameΪ�գ���Ĭ�� values.put��key�����ֶα��������
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
			 * Ŀǰֻ֧��List<String>
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
