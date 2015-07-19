/**
 * Project Name:lsn25_sqlite
 * File Name:MySqliteHelper.java
 * Package Name:com.zht.sqlite.utils
 * Date:2015-7-19下午2:04:48
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.sqlite.utils;

import com.zht.sqlite.bean.Word;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ClassName:MySqliteHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-19 下午2:04:48 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MySqliteHelper extends SQLiteOpenHelper {

	public MySqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	// 创建表格 也可初始化一部分数据 该方法只执行一次
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.beginTransaction();
			db.execSQL("create table words (_id integer primary key autoincrement,word varchar(20))");
			// 初始化3个人的数据
			initData(db, new Word("jack"));
			initData(db, new Word("jason"));
			initData(db, new Word("jessica"));
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	private void initData(SQLiteDatabase db, Object obj) {
		Word w = (Word) obj;
		ContentValues values = new ContentValues();
		values.put("word", w.getContent());
		db.insert(Constants.DB_NAME, null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
