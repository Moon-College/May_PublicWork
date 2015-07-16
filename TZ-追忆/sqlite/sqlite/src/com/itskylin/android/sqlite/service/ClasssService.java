package com.itskylin.android.sqlite.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itskylin.android.sqlite.bean.Classs;
import com.itskylin.android.sqlite.db.MySqliteDatabaseHelper;

public class ClasssService {

	public static boolean insert(SQLiteDatabase db, Classs... classss) {
		db.beginTransaction();
		try {
			for (Classs classs : classss) {
				ContentValues values = new ContentValues();
				values.put("_id", classs.getId());
				values.put("name", classs.getName());
				values.put("teacherId", classs.getTeacher());
				db.insert("classs", null, values);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static boolean update(SQLiteDatabase db, Classs... classss) {
		db.beginTransaction();
		try {
			for (Classs classs : classss) {
				ContentValues values = new ContentValues();
				values.put("_id", classs.getId());
				values.put("name", classs.getName());
				values.put("teacherId", classs.getTeacher());
				db.update("classs", values, "_id=?",
						new String[] { classs.getId() + "" });
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
				db.delete("classs", "_id=?", new String[] { id + "" });
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static Classs findById(SQLiteDatabase db, int id) {
		Cursor query = db.query("classs", null, "_id=?",
				new String[] { String.valueOf(id) }, null, null, null);
		Classs classs = null;
		while (query.moveToNext()) {
			classs = new Classs();
			classs.setId(query.getInt(query.getColumnIndex("_id")));
			classs.setName(query.getString(query.getColumnIndex("name")));
			classs.setTeacher(query.getInt(query.getColumnIndex("teacherId")));
		}
		return classs;
	}

	public static List<Classs> findAll(MySqliteDatabaseHelper mdb) {
		SQLiteDatabase db = mdb.getReadableDatabase();
		Cursor query = db.query("classs", null, null, null, null, null, null);
		List<Classs> classss = new ArrayList<Classs>();
		while (query.moveToNext()) {
			Classs classs = new Classs();
			classs.setId(query.getInt(query.getColumnIndex("_id")));
			classs.setName(query.getString(query.getColumnIndex("name")));
			classs.setTeacher(query.getInt(query.getColumnIndex("teacherId")));
			classss.add(classs);
		}
		return classss;
	}
}
