package com.itskylin.android.sqlite.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itskylin.android.sqlite.bean.Teacher;
import com.itskylin.android.sqlite.db.MySqliteDatabaseHelper;

public class TeacherService {

	public static boolean insert(SQLiteDatabase db, Teacher... teachers) {
		db.beginTransaction();
		try {
			for (Teacher teacher : teachers) {
				ContentValues values = new ContentValues();
				values.put("_id", teacher.getId());
				values.put("name", teacher.getName());
				values.put("tel", teacher.getTel());
				values.put("sex", teacher.getSex());
				values.put("addr", teacher.getAddr());
				values.put("marry", teacher.getMarry());
				db.insert("teacher", null, values);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static boolean update(SQLiteDatabase db, Teacher... teachers) {
		db.beginTransaction();
		try {
			for (Teacher teacher : teachers) {
				ContentValues values = new ContentValues();
				values.put("_id", teacher.getId());
				values.put("name", teacher.getName());
				values.put("tel", teacher.getTel());
				values.put("sex", teacher.getSex());
				values.put("addr", teacher.getAddr());
				values.put("marry", teacher.getMarry());
				db.update("teacher", values, "_id=?",
						new String[] { teacher.getId() + "" });
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static boolean delete(SQLiteDatabase db, int... ids) {
		db.beginTransaction();
		try {
			for (int id : ids) {
				db.delete("teacher", "_id=?", new String[] { id + "" });
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static Teacher findById(SQLiteDatabase db, int id) {
		Cursor query = db.query("teacher", null, "_id=?",
				new String[] { String.valueOf(id) }, null, null, null);
		Teacher teacher = null;
		while (query.moveToNext()) {
			teacher = new Teacher();
			teacher.setId(query.getInt(query.getColumnIndex("_id")));
			teacher.setName(query.getString(query.getColumnIndex("name")));
			teacher.setSex(query.getInt(query.getColumnIndex("sex")) == 1 ? true
					: false);
			teacher.setSex(query.getInt(query.getColumnIndex("sex")) == 1 ? true
					: false);
			teacher.setAddr(query.getString(query.getColumnIndex("addr")));
			teacher.setMarry(query.getInt(query.getColumnIndex("marry")) == 1 ? true
					: false);
		}
		return teacher;
	}

	public static List<Teacher> findAll(MySqliteDatabaseHelper mdb) {
		SQLiteDatabase db = mdb.getReadableDatabase();
		Cursor query = db.query("teacher", null, null, null, null, null, null);
		List<Teacher> teachers = new ArrayList<Teacher>();
		while (query.moveToNext()) {
			Teacher teacher = new Teacher();
			teacher.setId(query.getInt(query.getColumnIndex("_id")));
			teacher.setName(query.getString(query.getColumnIndex("name")));
			teacher.setSex(query.getInt(query.getColumnIndex("sex")) == 1 ? true
					: false);
			teacher.setSex(query.getInt(query.getColumnIndex("sex")) == 1 ? true
					: false);
			teacher.setAddr(query.getString(query.getColumnIndex("addr")));
			teacher.setMarry(query.getInt(query.getColumnIndex("marry")) == 1 ? true
					: false);
		}
		return teachers;
	}
}
