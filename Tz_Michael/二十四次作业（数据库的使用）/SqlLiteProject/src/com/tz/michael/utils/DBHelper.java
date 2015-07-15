package com.tz.michael.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.tz.michael.bean.ClassBean;
import com.tz.michael.bean.GradeBean;
import com.tz.michael.bean.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 数据库的一些工具类
 * 
 * @author michael
 * 
 */
public class DBHelper {

	/**
	 * 根据name删除
	 * 
	 * @param db
	 * @param table
	 * @param name
	 */
	public static void deleteData(SQLiteDatabase db, String table, String name) {
		db.beginTransaction();
		db.delete(table, "name=?", new String[] { name });
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	/**
	 * 通用的根据id删除
	 * 
	 * @param db
	 * @param table
	 * @param id
	 */
	public static void deleteData(SQLiteDatabase db, String table, int id) {
		db.beginTransaction();
		db.delete(table, "_id=?", new String[] { id + "" });
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	/**
	 * 查询给定班级的学生
	 * 
	 * @param db
	 * @param className
	 * @return
	 */
	public static List<Student> queryStudentsByClassName(SQLiteDatabase db,
			String className) {
		List<Student> ll = new ArrayList<Student>();
		String sql = "select s.* from students as s left join class as c on s.classId=c._id where c.name=?";
		Cursor c = db.rawQuery(sql, new String[] { className });
		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("name"));
			String age = c.getString(c.getColumnIndex("age"));
			String classId = c.getString(c.getColumnIndex("classId"));
			Student s = new Student(name, Integer.valueOf(age),
					Integer.valueOf(classId));
			ll.add(s);
		}
		return ll;
	}

	/**
	 * 通用的插入方法
	 * 
	 * @param db
	 * @param object
	 *            要插入的对象
	 * @param table
	 *            所要操作的表名
	 */
	public static void insertData(SQLiteDatabase db, Object object, String table) {
		ContentValues values = new ContentValues();
		// 拿到磨具类
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			db.beginTransaction();
			for (Field field : fields) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				if (type.getName().equals("int")) {
					values.put(field.getName(), (Integer) field.get(object));
				} else if (type.getName().equals(String.class.getName())) {
					values.put(field.getName(), (String) field.get(object));
				} else if (type.getName().equals("boolean")) {
					values.put(field.getName(), (Boolean) field.get(object));
				} else if (type.getName().equals("long")) {
					values.put(field.getName(), (Long) field.get(object));
				} else if (type.getName().equals("double")) {
					values.put(field.getName(), (Double) field.get(object));
				} else if (type.getName().equals("float")) {
					values.put(field.getName(), (Float) field.get(object));
				} else if (type.getName().equals(Short.class.getName())) {
					values.put(field.getName(), (Short) field.get(object));
				} else if (type.getName().equals(Byte.class.getName())) {
					values.put(field.getName(), (Byte) field.get(object));
				}/*
				 * else{ values.put(field.getName(), ""); }
				 */
			}
			db.insert(table, null, values);
			db.setTransactionSuccessful();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 通用的更新指定id的数据的方法
	 * 
	 * @param db
	 * @param table
	 * @param object
	 * @param id
	 */
	public static void updateData(SQLiteDatabase db, String table,
			Object object, int id) {
		ContentValues values = new ContentValues();
		// 拿到磨具类
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			db.beginTransaction();
			for (Field field : fields) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				if (type.getName().equals("int")) {
					values.put(field.getName(), (Integer) field.get(object));
				} else if (type.getName().equals(String.class.getName())) {
					values.put(field.getName(), (String) field.get(object));
				} else if (type.getName().equals("boolean")) {
					values.put(field.getName(), (Boolean) field.get(object));
				} else if (type.getName().equals("long")) {
					values.put(field.getName(), (Long) field.get(object));
				} else if (type.getName().equals("double")) {
					values.put(field.getName(), (Double) field.get(object));
				} else if (type.getName().equals("float")) {
					values.put(field.getName(), (Float) field.get(object));
				} else if (type.getName().equals(Short.class.getName())) {
					values.put(field.getName(), (Short) field.get(object));
				} else if (type.getName().equals(Byte.class.getName())) {
					values.put(field.getName(), (Byte) field.get(object));
				}
			}
			db.update(table, values, "_id=?", new String[] { id + "" });
			db.setTransactionSuccessful();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	public static String ChangeFirstLetterToUpper(String strChange) {
		String tempFirst = strChange.substring(0, 1);
		String tempElse = strChange.substring(1);
		return (tempFirst.toUpperCase() + tempElse);
	}

	/**
	 * 通用的查询方法
	 * 
	 * @param db
	 * @param table
	 *            要操作的表名
	 * @param clazz
	 *            要查询的对象的类型
	 * @param id
	 *            查询条件id
	 * @return
	 */
	public static Object queryData(SQLiteDatabase db, String table,
			Class clazz, int id) {
		Cursor cursor = db.query(table, null, "_id=?",
				new String[] { id + "" }, null, null, null);
		Constructor constructor;
		try {
			// 拿到构造方法
			constructor = clazz.getDeclaredConstructor((Class[]) null);
			// 执行构造方法拿到对象
			Object object = constructor.newInstance((Object[]) null);
			Field[] fields = clazz.getDeclaredFields();
			// 便利各个field
			for (Field field : fields) {
				// 获取对应的field的权限
				field.setAccessible(true);
				// 拿到field对应的类型
				Class<?> fieldType = field.getType();
				// 拿取field的name
				String fieldName = (String) field.getName();
				// 拼接对应field的set方法
				String methodName = "set" + ChangeFirstLetterToUpper(fieldName);
				Log.i("cc--", "--" + methodName + "==" + fieldType.getName());
				Method method = clazz.getDeclaredMethod(methodName,
						new Class[] { fieldType });
				cursor.moveToFirst();// 这里因为是根据id来差，id唯一，所以结果只有一行数据
				String v_Str = cursor.getString(cursor
						.getColumnIndex(fieldName));
				if (fieldType.getName().equals(String.class.getName())) {
					method.invoke(object, v_Str);
				} else if (fieldType.getName().equals("int")) {
					method.invoke(object, Integer.valueOf(v_Str));
				} else if (fieldType.getName().equals("float")) {
					method.invoke(object, Float.valueOf(v_Str));
				} else if (fieldType.getName().equals("double")) {
					method.invoke(object, Double.valueOf(v_Str));
				} else if (fieldType.getName().equals("boolean")) {
					method.invoke(object, Boolean.valueOf(v_Str));
				}
			}
			return object;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据名字来查询（名字可以相同，所以这里返回一个集合）
	 * 
	 * @param db
	 * @param table
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static List<Object> queryDataByName(SQLiteDatabase db, String table,
			Class clazz, String name) {
		List<Object> ll = new ArrayList<Object>();
		Cursor cursor = db.query(table, null, "name=?", new String[] { name },
				null, null, null);
		Constructor constructor;
		try {
			// 拿到构造方法
			constructor = clazz.getDeclaredConstructor((Class[]) null);
			// 执行构造方法拿到对象
			Object object;
			Field[] fields = clazz.getDeclaredFields();
			while (cursor.moveToNext()) {
				object = constructor.newInstance((Object[]) null);
				// 便利各个field
				for (Field field : fields) {
					// 获取对应的field的权限
					field.setAccessible(true);
					// 拿到field对应的类型
					Class<?> fieldType = field.getType();
					// 拿取field的name
					String fieldName = (String) field.getName();
					// 拼接对应field的set方法
					String methodName = "set"
							+ ChangeFirstLetterToUpper(fieldName);
					Log.i("cc--",
							"--" + methodName + "==" + fieldType.getName());
					Method method = clazz.getDeclaredMethod(methodName,
							new Class[] { fieldType });
					String v_Str = cursor.getString(cursor
							.getColumnIndex(fieldName));
					if (fieldType.getName().equals(String.class.getName())) {
						method.invoke(object, v_Str);
					} else if (fieldType.getName().equals("int")) {
						method.invoke(object, Integer.valueOf(v_Str));
					} else if (fieldType.getName().equals("float")) {
						method.invoke(object, Float.valueOf(v_Str));
					} else if (fieldType.getName().equals("double")) {
						method.invoke(object, Double.valueOf(v_Str));
					} else if (fieldType.getName().equals("boolean")) {
						method.invoke(object, Boolean.valueOf(v_Str));
					}
				}
				ll.add(object);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return ll;
	}

	public static void queryDataTest(Object object) {
		// 拿到磨具类
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Log.i("field--", field.getName() + field.get(object) + "---"
						+ field.getType().getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
