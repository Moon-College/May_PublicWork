package com.junwen.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelp extends SQLiteOpenHelper {

	public SqliteHelp(Context context, String name ,
			int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建数据表
		db.execSQL("create table student(_sid INTEGER PRIMARY KEY AUTOINCREMENT,sname varchar,age Integer,sclass varchar,professional varchar)");
		db.execSQL("create table classes(_cid INTEGER PRIMARY KEY AUTOINCREMENT , classname varchar,classcount Integer)");
		db.execSQL("create table professional(_pid INTEGER PRIMARY KEY AUTOINCREMENT,pname varchar,pcount Integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
