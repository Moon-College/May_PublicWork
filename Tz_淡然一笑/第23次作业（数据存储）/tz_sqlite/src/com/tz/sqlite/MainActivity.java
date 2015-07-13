package com.tz.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		db = this.getDatabasePath(name);  //路径
		//两个应用放在同一个进程
		db = this.openOrCreateDatabase("school.db", Context.MODE_PRIVATE, null);
		//创建表,只能创建一次
//		db.execSQL("create table students(id integer primary key autoincrement,name varchar(20))");
		
		//插入数据,只能插入一次数据
//		db.execSQL("insert into students(name)values(?)",new String[]{"Amy"});
//		db.execSQL("insert into students(name)values(?)",new String[]{"John"});
		
		//删除数据
//		db.execSQL("delete from students where name = ?",new String[]{"Amy"});
		
		//更新数据
//		db.execSQL("update students set name = 'Jo' where id = ?", new String[]{"2"});  //id = 2,当做字符串处理
		
		//查询
//		db.execSQL("select * from students");
		Cursor cursor = db.rawQuery("select * from students", null);  //selectionArgs查询条件
		if(cursor.moveToNext()){  //每找到一条数据,游标会往下移动一次,移动完之后,显示当前的数据
//			String name = cursor.getString(1); //下标,不推荐
			String name = cursor.getString(cursor.getColumnIndex("name"));  //字段名
			Log.i("INFO", "name:"+name);
		}
		
	}
}
