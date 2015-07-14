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
	 * 简单查询，只能查询单表
	 * 
	 * @param table
	 *            要查询的table，如果是null，则查询<b>c</b>类名字对应的表
	 * @param c
	 *            T对应的class
	 * @param selection
	 *            查询条件
	 * @param selectionArgs
	 *            查询条件对应的参数字符数组
	 * @return 结果list
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
					field.set(t, cursorGetMethod.invoke(cursor, columnIndex));
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
	 * 原始查询
	 * 
	 * @param c
	 *            T的类型
	 * @param sql
	 *            sql语句
	 * @param selectionArgs
	 *            查询条件对应的参数字符数组
	 * @return 查询结果list
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
	 * 插入一条数据
	 * 
	 * @param table
	 *            把数据插入到哪个数据表里面，如果为null，则查询<b>T</b>类名字对应的表
	 * @param data
	 *            一条记录
	 * @param insertFields
	 *            需要插入的该表字段的数组，如果为空，则会把<b>a</b>里面的所有字段通过反射遍历都插入数据库
	 */
	public <T> long insert(String table, T data, String[] insertFields) {
		String tableName = table;
		long result = -1;
		if (tableName == null) {
			tableName = getTableNameFromClass(data.getClass());
		}
		ContentValues values = new ContentValues();
		/*
		 * 如果intertFileds字段不为空，则插入用户想插入的字段
		 */
		if (insertFields != null) {
			/*
			 * 1、遍历intertFileds， 2、通过反射获取到对应字段的值 3、放入values 4、插入
			 */
			for (String fieldName : insertFields) {
				Field field;
				try {
					field = data.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					/*
					 * 这里应该按照类型来
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
			 * 如果intertFileds字段为空，则把a里面的所有字段通过反射遍历都插入数据库
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
	 * 根据field的名字和类型填充到values
	 * 
	 * @param values
	 *            values
	 * @param field
	 *            要填充的字段的field的信息
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
	 * 按照条件删除
	 * 
	 * @param table
	 *            表名字，如果为null，则删除<b>T</b>类名字对应的表
	 * @param whereClause
	 *            where语句
	 * @param whereArgs
	 *            where语句的参数
	 * @return 删除是否成功
	 */
	public long delete(String table, String whereClause, String[] whereArgs) {
		long result = -1;
		result = mDb.delete(table, whereClause, whereArgs);
		return result;
	}

	/**
	 * 
	 * @param table
	 *            表名字，如果为null，则删除<b>T</b>类名字对应的表
	 * @param data
	 *            数据
	 * @param updateFields
	 *            需要更新的字段名字
	 * @param whereClause
	 *            where语句
	 * @param whereArgs
	 *            where语句的参数
	 */
	public <T> int update(String table, T data, String[] updateFields,
			String whereClause, String[] whereArgs) {
		int result = 0;
		ContentValues values = new ContentValues();
		if (updateFields != null) {
			/*
			 * 1、遍历updateFields根据 2、从data中获取对应field的值 3、放入values 4、更新
			 */
			for (String fieldName : updateFields) {
				try {
					Field field = data.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					/*
					 * 这里应该按照类型来
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
			 * 如果intertFileds字段为空，则把data里面的所有字段通过反射遍历都插入数据库
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
	 * 通过类名字返回table的名字
	 * 
	 * @param c
	 *            类
	 * @return 通过类名字返回table的名字（约定表名字就是类名字的小写）
	 */
	private String getTableNameFromClass(Class c) {
		String tableName = c.getSimpleName().toLowerCase();
		return tableName;
	}

	/**
	 * 通过传入数据类型获得Cursor类里面getInt,getString这样的函数的名字
	 * 
	 * @param type
	 *            calssType
	 * @return Cursor类里面getInt,getString这样的函数的名字
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
