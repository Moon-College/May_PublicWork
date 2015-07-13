package com.itskylin.android.sqlite.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteDatabaseHelper extends SQLiteOpenHelper {

	public MySqliteDatabaseHelper(Context context, CursorFactory factory,
			int version) {
		super(context, "school.db", factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createStudent = "CREATE TABLE student(_id Integer primary key autoincrement,name varchar(20), age int(3),sex int(2),classId int(11))";
		String createClasss = "CREATE TABLE classs(_id Integer primary key autoincrement,name varchar(20),teacherId int(20))";
		String createTeacher = "CREATE TABLE teacher(_id Integer primary key autoincrement,name varchar(20) ,tel varchar(20) ,sex int(2) ,addr varchar(255) ,marry INT(2))";

		db.beginTransaction();

		try {
			db.execSQL(createStudent);
			Log.i("INFO", "create student....");
			db.execSQL(createClasss);
			Log.i("INFO", "create createClasss....");
			db.execSQL(createTeacher);
			Log.i("INFO", "create createTeacher....");
			/**
			 * createProvince
			 */
			db.execSQL("INSERT INTO student(name,age,sex,classId) VALUES ('memory',18,1,1)");
			db.execSQL("INSERT INTO student(name,age,sex,classId) VALUES ('追忆11111',19,1,2)");
			db.execSQL("INSERT INTO student(name,age,sex,classId) VALUES ('追忆112',12,2,2)");
			db.execSQL("INSERT INTO student(name,age,sex,classId) VALUES ('追忆1222',12,1,1)");
			db.execSQL("INSERT INTO student(name,age,sex,classId) VALUES ('memory3',17,1,3)");
			db.execSQL("INSERT INTO student(name,age,sex,classId) VALUES ('memory4',15,1,1)");
			db.execSQL("INSERT INTO classs(name,teacherId) VALUES ('20150517',1)");
			db.execSQL("INSERT INTO classs(name,teacherId) VALUES ('20150624',1)");
			db.execSQL("INSERT INTO classs(name,teacherId) VALUES ('20141010',2)");
			db.execSQL("INSERT INTO classs(name,teacherId) VALUES ('20151212',1)");
			db.execSQL("INSERT INTO classs(name,teacherId) VALUES ('20150510',2)");
			db.execSQL("INSERT INTO teacher(name,tel,sex,addr,marry) VALUES ('坑总','1388888888',1,'湖南长沙',2)");
			db.execSQL("INSERT INTO teacher(name,tel,sex,addr,marry) VALUES ('坑总2','1388818888',2,'湖南长沙2',1)");
			db.execSQL("INSERT INTO teacher(name,tel,sex,addr,marry) VALUES ('Grace','1288818888',2,'湖南长沙6',2)");
			db.execSQL("INSERT INTO teacher(name,tel,sex,addr,marry) VALUES ('坑总3','1388828888',1,'湖南长沙2',1)");

			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
