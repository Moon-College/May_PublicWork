package com.decent.datastore.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.decent.decentutil.DecentLogUtil;

public class DencetDataStoreService {
	private static final String TAG = "DencetDataStoreService";
	private DecentSQLiteHelper mDbHelper;
	private SQLiteDatabase mDb;

	public DencetDataStoreService(SQLiteDatabase db) {
		mDb = db;
	}

	/**
	 * �򵥲�ѯ��ֻ�ܲ�ѯ����
	 * 
	 * @param table
	 *            Ҫ��ѯ��table�������null�����ѯ<b>c</b>�����ֶ�Ӧ�ı�
	 * @param c
	 *            T��Ӧ��class
	 * @param selection
	 *            ��ѯ����
	 * @param selectionArgs
	 *            ��ѯ������Ӧ�Ĳ����ַ�����
	 * @return ���list
	 */
	public <T> List<T> query(String table, Class<T> c, String selection,
			String[] selectionArgs) {
		List<T> resultList = new ArrayList<T>();
		Field[] fileds = c.getDeclaredFields();
		String tableName = table;
		if (tableName == null) {
			tableName = getTableNameFromClass(c);
		}
		Cursor cursor = mDb.query(tableName, null, selection, selectionArgs,
				null, null, null);
		while (cursor.moveToNext()) {
			try {
				T t = c.newInstance();
				for (Field field : fileds) {
					field.setAccessible(true);
					Class type = field.getType();
					String cursorGetMethodName = getCursorGetMethodNameFromClass(type);
					Method cursorGetMethod = Cursor.class.getMethod(
							cursorGetMethodName, int.class);
					DecentLogUtil.d(TAG, "field.getName().toLowerCase():"
							+ field.getName().toLowerCase());
					int columnIndex = cursor.getColumnIndex(field.getName()
							.toLowerCase());
					/*
					 *  -1˵����cursorȡ�ص��ֶ�����û�к�field��Ӧ���Ǹ�
					 */
					if (columnIndex != -1) {
						field.set(t,
								cursorGetMethod.invoke(cursor, columnIndex));
						field.setAccessible(false);
					}
				}
				resultList.add(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cursor.close();
		return resultList;
	}

	/**
	 * ԭʼ��ѯ
	 * 
	 * @param c
	 *            T������
	 * @param sql
	 *            sql���
	 * @param selectionArgs
	 *            ��ѯ������Ӧ�Ĳ����ַ�����
	 * @return ��ѯ���list
	 */
	public <T> List<T> rawQuery(Class<T> c, String sql, String[] selectionArgs) {
		List<T> resultList = new ArrayList<T>();
		Field[] fileds = c.getDeclaredFields();
		Cursor cursor = mDb.rawQuery(sql, selectionArgs);
		while (cursor.moveToNext()) {
			try {
				T t = c.newInstance();
				for (Field field : fileds) {
					field.setAccessible(true);
					String fieldName = field.getName().toLowerCase();
					Class type = field.getType();
					String cursorGetMethodName = getCursorGetMethodNameFromClass(type);
					Method cursorGetMethod = Cursor.class.getMethod(
							cursorGetMethodName, int.class);
					DecentLogUtil.d(TAG, "field.getName().toLowerCase():"
							+ fieldName);
					int columnIndex = cursor.getColumnIndex(fieldName);
					if (columnIndex != -1) {
						field.set(t,
								cursorGetMethod.invoke(cursor, columnIndex));
					}
					field.setAccessible(false);
				}
				resultList.add(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cursor.close();
		return resultList;
	}

	/**
	 * ����һ������
	 * 
	 * @param table
	 *            �����ݲ��뵽�ĸ����ݱ����棬���Ϊnull�����ѯ<b>T</b>�����ֶ�Ӧ�ı�
	 * @param data
	 *            һ����¼
	 * @param insertFields
	 *            ��Ҫ����ĸñ��ֶε����飬���Ϊ�գ�����<b>a</b>����������ֶ�ͨ������������������ݿ�
	 */
	public <T> long insert(String table, T data, String[] insertFields) {
		String tableName = table;
		long result = -1;
		if (tableName == null) {
			tableName = getTableNameFromClass(data.getClass());
		}
		ContentValues values = new ContentValues();
		/*
		 * ���intertFileds�ֶβ�Ϊ�գ�������û��������ֶ�
		 */
		if (insertFields != null) {
			/*
			 * 1������intertFileds�� 2��ͨ�������ȡ����Ӧ�ֶε�ֵ 3������values 4������
			 */
			for (String fieldName : insertFields) {
				Field field;
				try {
					field = data.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					/*
					 * ����Ӧ�ð���������
					 */
					Class fieldType = field.getType();
					addToValuesByType(data, values, field);
					field.setAccessible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			/*
			 * ���intertFileds�ֶ�Ϊ�գ����a����������ֶ�ͨ������������������ݿ�
			 */
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					addToValuesByType(data, values, field);
					field.setAccessible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		result = mDb.insert(tableName, null, values);

		return result;
	}

	/**
	 * ����field�����ֺ�������䵽values
	 * 
	 * @param values
	 *            values
	 * @param field
	 *            Ҫ�����ֶε�field����Ϣ
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private <T> void addToValuesByType(T a, ContentValues values, Field field)
			throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		Class fieldType = field.getType();
		String fieldName = field.getName();
		if (fieldType.isAssignableFrom(byte[].class)) {
			values.put(fieldName, (byte[]) field.get(a));
		} else if (fieldType.isAssignableFrom(String.class)
				|| fieldType.isAssignableFrom(int.class)
				|| fieldType.isAssignableFrom(Long.class)
				|| fieldType.isAssignableFrom(Short.class)
				|| fieldType.isAssignableFrom(Double.class)
				|| fieldType.isAssignableFrom(Float.class)
				|| fieldType.isAssignableFrom(Integer.class)) {
			values.put(fieldName, String.valueOf(field.get(a)));
		} else if (fieldType.isAssignableFrom(Boolean.class)) {
			values.put(fieldName, (Boolean) field.get(a));
		} else if (fieldType.isAssignableFrom(Byte.class)) {
			values.put(fieldName, (Byte) field.get(a));
		}
	}

	/**
	 * ��������ɾ��
	 * 
	 * @param table
	 *            �����֣����Ϊnull����ɾ��<b>T</b>�����ֶ�Ӧ�ı�
	 * @param whereClause
	 *            where���
	 * @param whereArgs
	 *            where���Ĳ���
	 * @return ɾ���Ƿ�ɹ�
	 */
	public long delete(String table, String whereClause, String[] whereArgs) {
		long result = -1;
		result = mDb.delete(table, whereClause, whereArgs);
		return result;
	}

	/**
	 * 
	 * @param table
	 *            �����֣����Ϊnull����ɾ��<b>T</b>�����ֶ�Ӧ�ı�
	 * @param data
	 *            ����
	 * @param updateFields
	 *            ��Ҫ���µ��ֶ�����
	 * @param whereClause
	 *            where���
	 * @param whereArgs
	 *            where���Ĳ���
	 */
	public <T> int update(String table, T data, String[] updateFields,
			String whereClause, String[] whereArgs) {
		int result = 0;
		ContentValues values = new ContentValues();
		if (updateFields != null) {
			/*
			 * 1������updateFields���� 2����data�л�ȡ��Ӧfield��ֵ 3������values 4������
			 */
			for (String fieldName : updateFields) {
				try {
					Field field = data.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					/*
					 * ����Ӧ�ð���������
					 */
					addToValuesByType(data, values, field);
					field.setAccessible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			/*
			 * ���intertFileds�ֶ�Ϊ�գ����data����������ֶ�ͨ������������������ݿ�
			 */
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					addToValuesByType(data, values, field);
					field.setAccessible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		result = mDb.update(table, values, whereClause, whereArgs);
		return result;
	}

	/**
	 * ͨ�������ַ���table������
	 * 
	 * @param c
	 *            ��
	 * @return ͨ�������ַ���table�����֣�Լ�������־��������ֵ�Сд��
	 */
	private String getTableNameFromClass(Class c) {
		String tableName = c.getSimpleName().toLowerCase();
		return tableName;
	}

	/**
	 * ͨ�������������ͻ��Cursor������getInt,getString�����ĺ���������
	 * 
	 * @param type
	 *            calssType
	 * @return Cursor������getInt,getString�����ĺ���������
	 */
	private String getCursorGetMethodNameFromClass(Class type) {
		String typeName = type.getSimpleName();
		if (type.isAssignableFrom(byte[].class)) {
			return "getBlob";
		} else if (type.isAssignableFrom(Integer.class)) {
			return "getInt";
		}
		return "get" + typeName.substring(0, 1).toUpperCase()
				+ typeName.substring(1);
	}

	public SQLiteDatabase getmDb() {
		return mDb;
	}

	public void setmDb(SQLiteDatabase mDb) {
		this.mDb = mDb;
	}

}
