package com.itskylin.android.sqlite.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itskylin.android.sqlite.bean.Student;
import com.itskylin.android.sqlite.db.MySqliteDatabaseHelper;

public class StudentService {

	public static boolean insert(SQLiteDatabase db, Student... students) {
		db.beginTransaction();
		try {
			for (Student student : students) {
				ContentValues values = new ContentValues();
				values.put("_id", student.getId());
				values.put("name", student.getName());
				values.put("age", student.getAge());
				values.put("sex", student.getSex());
				values.put("classId", student.getClassId());
				db.insert("student", null, values);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static boolean update(SQLiteDatabase db, Student... students) {
		db.beginTransaction();
		try {
			for (Student student : students) {
				ContentValues values = new ContentValues();
				values.put("_id", student.getId());
				values.put("name", student.getName());
				values.put("age", student.getAge());
				values.put("sex", student.getSex());
				values.put("classId", student.getClassId());
				db.update("student", values, "_id=?",
						new String[] { student.getId() + "" });
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
				db.delete("student", "_id=?", new String[] { id + "" });
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return false;
		} finally {
			db.close();
		}
		return true;
	}

	public static Student findById(SQLiteDatabase db, int id) {
		Cursor query = db.query("student", null, "_id=?",
				new String[] { String.valueOf(id) }, null, null, null);
		Student student = null;
		while (query.moveToNext()) {
			student = new Student();
			student.setId(query.getInt(query.getColumnIndex("_id")));
			student.setName(query.getString(query.getColumnIndex("name")));
			student.setAge(query.getInt(query.getColumnIndex("age")));
			student.setSex(query.getInt(query.getColumnIndex("sex")));
			student.setClassId(query.getInt(query.getColumnIndex("classId")));
		}
		return student;
	}

	public static List<Student> findAll(MySqliteDatabaseHelper mdb) {
		SQLiteDatabase db = mdb.getReadableDatabase();
		Cursor query = db.query("student", null, null, null, null, null, null);
		List<Student> students = new ArrayList<Student>();
		while (query.moveToNext()) {
			Student student = new Student();
			student.setId(query.getInt(query.getColumnIndex("_id")));
			student.setName(query.getString(query.getColumnIndex("name")));
			student.setAge(query.getInt(query.getColumnIndex("age")));
			student.setSex(query.getInt(query.getColumnIndex("sex")));
			student.setClassId(query.getInt(query.getColumnIndex("classId")));
			students.add(student);
		}
		return students;
	}
}
