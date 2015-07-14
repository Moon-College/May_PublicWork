package com.decent.datastore.util;

import com.decent.decentutil.DecentLogUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DecentSQLiteHelper extends SQLiteOpenHelper {
	private static final String TAG = "DecentSQLiteHelper";

	public DecentSQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.beginTransaction();
		try {
			DecentLogUtil.d(TAG, "into onCreate");
			/*
			 * 创建表
			 */
			// 用户表
			db.execSQL("create table users (_id integer primary key autoincrement,name varchar(20),passwd varchar(20),gender varchar(10),age integer,pic BLOB);");
			// 运动类型
			db.execSQL("create table sport_types (_id integer primary key autoincrement,name varchar(20),des varchar(400));");
			// 运动记录
			db.execSQL("create table sport_records (_id integer primary key autoincrement,sport_type_id integer,start_time datetime,duration integer);");

			/*
			 * 插入基本数据
			 */
			// 用户
			db.execSQL(
					"insert into users(name,passwd,gender,age) values(?,?,?,?)",
					new String[] { "firebull", "123", "male", "27" });
			// 运动类型
			db.execSQL("insert into sport_types(name,des) values(?,?)",
					new String[] { "mediation", "Meditation for calm" });
			db.execSQL("insert into sport_types(name,des) values(?,?)",
					new String[] { "run", "Run free" });
			db.execSQL("insert into sport_types(name,des) values(?,?)",
					new String[] { "bicycle", "ride a bicycle" });

			/*
			 * 设置整个事务执行成功，后面的endTransaction里面会根据这个值做判断， 如果没有成功会回滚 如果成功会提交
			 */
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		DecentLogUtil.d(TAG, "into onUpgrade");
		if(oldVersion==1&&newVersion==2){
			db.beginTransaction();
			try{
				db.execSQL("alter table sport_records add column user_id integer;");
				db.setTransactionSuccessful();
				DecentLogUtil.d(TAG, "Upgrade Successful");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				db.endTransaction();
			}
		}
	}

}
