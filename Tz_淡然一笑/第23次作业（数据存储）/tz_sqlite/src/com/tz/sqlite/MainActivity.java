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
		
//		db = this.getDatabasePath(name);  //·��
		//����Ӧ�÷���ͬһ������
		db = this.openOrCreateDatabase("school.db", Context.MODE_PRIVATE, null);
		//������,ֻ�ܴ���һ��
//		db.execSQL("create table students(id integer primary key autoincrement,name varchar(20))");
		
		//��������,ֻ�ܲ���һ������
//		db.execSQL("insert into students(name)values(?)",new String[]{"Amy"});
//		db.execSQL("insert into students(name)values(?)",new String[]{"John"});
		
		//ɾ������
//		db.execSQL("delete from students where name = ?",new String[]{"Amy"});
		
		//��������
//		db.execSQL("update students set name = 'Jo' where id = ?", new String[]{"2"});  //id = 2,�����ַ�������
		
		//��ѯ
//		db.execSQL("select * from students");
		Cursor cursor = db.rawQuery("select * from students", null);  //selectionArgs��ѯ����
		if(cursor.moveToNext()){  //ÿ�ҵ�һ������,�α�������ƶ�һ��,�ƶ���֮��,��ʾ��ǰ������
//			String name = cursor.getString(1); //�±�,���Ƽ�
			String name = cursor.getString(cursor.getColumnIndex("name"));  //�ֶ���
			Log.i("INFO", "name:"+name);
		}
		
	}
}
