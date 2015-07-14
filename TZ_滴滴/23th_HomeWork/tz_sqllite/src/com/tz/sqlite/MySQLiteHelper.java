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

		Log.i("INFO","onCreate������,���ݿ��һ�δ�����");
		
		db.beginTransaction();
		try {
			//��1���������ݱ�
			//����students���ݱ�ѧ����
			db.execSQL("create table students(_sid integer primary key autoincrement,studentname varchar(30),classname varchar(30))");
			//����classes���ݱ��༶��
			db.execSQL("create table classes(_cid integer primary key autoincrement,classname varchar(30),count integer)");
			//����scores���ݱ��ɼ���
			db.execSQL("create table scores(_scoreid integer primary key autoincrement,studentname varchar(30),android integer,java integer)");
			
			//��2�������ݱ�����ʼ��������
			//��ѧ�����������
			db.execSQL("insert into students values(null,?,?)",new String [] {"���ӷ�","1��"});
			//��༶���������
			db.execSQL("insert into classes values(null,?,?)",new String [] {"1��","30"});
			//��ɼ����������
			db.execSQL("insert into scores values(null,?,?,?)",new String [] {"���ӷ�","96","85"});
			
			db.setTransactionSuccessful();
			Log.i("INFO", "onCreate������,�������ݱ��������ݳɹ���");
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("INFO", "�������ݱ�ʱ�쳣��");
		}finally{
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("INFO", "onUpgrade�����ã����ݿ����°汾��");
		//��students���ݱ������age�ֶ�
		//db.execSQL("alter table students add age integer");
		
		db.execSQL("create table provinces(_id integer primary key autoincrement,provincename varchar(30))");
		db.execSQL("insert into provinces values(null,?)",new String []{"a*"});
		db.execSQL("insert into provinces values(null,?)",new String []{"a**"});
		db.execSQL("insert into provinces values(null,?)",new String []{"a***"});
		db.execSQL("insert into provinces values(null,?)",new String []{"ab"});
		db.execSQL("insert into provinces values(null,?)",new String []{"ab*"});
		db.execSQL("insert into provinces values(null,?)",new String []{"ab**"});

		Log.i("INFO", "����provinces��ɹ�����������6�����ݣ�");
	}

}
