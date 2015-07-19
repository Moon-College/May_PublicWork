/**
 * Project Name:lsn25_sqlite
 * File Name:DataService.java
 * Package Name:com.zht.sqlite.utils
 * Date:2015-7-19下午4:46:48
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.sqlite.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zht.sqlite.bean.Word;

/**
 * ClassName:DataService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-19 下午4:46:48 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class DataService {
	private MySqliteHelper helper;
	private Context context;

	public DataService(Context context, String dbName) {
		this.context = context;
		helper = new MySqliteHelper(context, dbName, null, 1);
	}

	// 增
	public Long addData(Word w) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("word", w.getContent());
		long id = db.insert(Constants.DB_NAME, null, values);
		db.close();
		return id;
	}

	// 删
	public int deleteData(Word w) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int delete = db.delete(Constants.DB_NAME, "word = ?",
				new String[] { w.getContent() });
		return delete;
	}

	// 改(修改名字)
	public void updateData(Word oldW, Word newW) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("word", newW.getContent());
		db.update(Constants.DB_NAME, values, "word = ?",
				new String[] { oldW.getContent() });
	}

	// 查
//	public List<Word> queryAll() {
//		List<Word> stuList = new ArrayList<Word>();
//		SQLiteDatabase db = helper.getReadableDatabase();
//		Cursor cursor = db.rawQuery("select * from words", null);
//		while (cursor.moveToNext()) {
//			Word w = new Word();
//			w.setContent(cursor.getString(cursor.getColumnIndex("word")));
//			stuList.add(w);
//		}
//		cursor.close();
//		db.close();
//		return stuList;
//	}
	//查
	public Cursor queryData(String text){
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select * from words where word like ?";
		Cursor query = db.rawQuery(sql, new String[] { text + "%" });
		return query;
	}
}
