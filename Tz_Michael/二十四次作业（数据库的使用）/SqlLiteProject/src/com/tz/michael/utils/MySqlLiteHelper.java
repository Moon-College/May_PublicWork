package com.tz.michael.utils;

import com.tz.michael.bean.ClassBean;
import com.tz.michael.bean.GradeBean;
import com.tz.michael.bean.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 数据库工具类
 * @author michael
 *
 */
public class MySqlLiteHelper extends SQLiteOpenHelper {

	public MySqlLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.beginTransaction();
			//创建表
			db.execSQL("create table students(_id integer primary key autoincrement ,name varchar(20),age integer,classId integer)");
			db.execSQL("create table class (_id integer primary key autoincrement ,name varchar(20),studentTotalNum integer)");
			db.execSQL("create table grade (_id integer primary key autoincrement ,name varchar(20),className varchar(20))");
			//初始化一些数据
			//初始化年级
			for(int i=1;i<4;i++){
				insertData(new GradeBean(i+"年级", i+"班"), db);
			}
			//初始化班级
			for(int i=1;i<4;i++){
				insertData(new ClassBean("班级"+i, 10), db);
			}
			//初始化学生
			for(int i=1;i<11;i++){
				insertData(new Student("学生"+i, i+18, i%3+1), db);
			}
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.endTransaction();
		}
	}

	private void insertData(Student s,SQLiteDatabase db){
		ContentValues values=new ContentValues();
		values.put("name", s.getName());
		values.put("age", s.getAge());
		values.put("classId", s.getClassId());
		db.insert("students", null, values);
	}
	
	private void insertData(ClassBean classBean,SQLiteDatabase db){
		ContentValues values=new ContentValues();
		values.put("name", classBean.getName());
		values.put("studentTotalNum", classBean.getStudentTotalNum());
		db.insert("students", null, values);
	}
	
	private void insertData(GradeBean gradeBean,SQLiteDatabase db){
		ContentValues values=new ContentValues();
		values.put("name", gradeBean.getName());
		values.put("className", gradeBean.getClassName());
		db.insert("students", null, values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//这里做一些升级或降级的操作
	}

}
