package com.tz.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		Log.i("INFO","onCreate被调用,数据库第一次创建！");
		
		db.beginTransaction();
		try {
			//【1】创建数据表
			//创建students数据表（学生表）
			db.execSQL("create table students(_sid integer primary key autoincrement,studentname varchar(30),classname varchar(30))");
			//创建classes数据表（班级表）
			db.execSQL("create table classes(_cid integer primary key autoincrement,classname varchar(30),count integer)");
			//创建scores数据表（成绩表）
			db.execSQL("create table scores(_scoreid integer primary key autoincrement,studentname varchar(30),android integer,java integer)");
			
			//【2】向数据表插入初始化的数据
			//向学生表插入数据
			db.execSQL("insert into students values(null,?,?)",new String [] {"王延飞","1班"});
			//向班级表插入数据
			db.execSQL("insert into classes values(null,?,?)",new String [] {"1班","30"});
			//向成绩表插入数据
			db.execSQL("insert into scores values(null,?,?,?)",new String [] {"王延飞","96","85"});
			
			db.setTransactionSuccessful();
			Log.i("INFO", "onCreate被调用,创建数据表并插入数据成功！");
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("INFO", "创建数据表时异常！");
		}finally{
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("INFO", "onUpgrade被调用，数据库有新版本！");
		//向students数据表中添加age字段
		//db.execSQL("alter table students add age integer");
		
		db.execSQL("create table provinces(_id integer primary key autoincrement,provincename varchar(30))");
		db.execSQL("insert into provinces values(null,?)",new String []{"a*"});
		db.execSQL("insert into provinces values(null,?)",new String []{"a**"});
		db.execSQL("insert into provinces values(null,?)",new String []{"a***"});
		db.execSQL("insert into provinces values(null,?)",new String []{"ab"});
		db.execSQL("insert into provinces values(null,?)",new String []{"ab*"});
		db.execSQL("insert into provinces values(null,?)",new String []{"ab**"});

		Log.i("INFO", "创建provinces表成功，并插入了6条数据！");
	}

}
