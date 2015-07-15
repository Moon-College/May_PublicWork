package com.junwen.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.junwen.bean.Student;
import com.junwen.sqlite.SqliteHelp;

public class DataService {
		
	public static final int SUCCESS= 0; //成功
	public static final int FAIL= 1; //失败
	public static final int EXCEPTION = 2; //异常
	private  String dabaName = "server.db"; //数据库名字
	private  int version = 1; //版本
	private SqliteHelp sql; //SQLite帮助类
	private Context context; //上下文
	/**
	 * 构造
	 * @param context
	 */
	public DataService(Context context) {
		this.context = context;
		sql = new SqliteHelp(this.context, dabaName, version);
	}
	
	/**
	 * 构造
	 * @param context 上下文
	 * @param dabaName //数据库名字
	 * @param version //版本
	 */
	public DataService(Context context,String dabaName, int version) {
		this.context = context;
		this.dabaName = dabaName;
		this.version = version;
		sql = new SqliteHelp(this.context, dabaName, version);
	}
	
	
	/**
	 * 添加学生
	 * @param column 字段名
	 * @param name 姓名
	 * @param age 年龄
	 * @param classes //班级
	 * @param professional //专业
	 * @return
	 */
	public  int addStudent(String column,String name,int age,String classes,String professional){
		 SQLiteDatabase db =null;
		try {
			db = sql.getWritableDatabase();
			int type = checkStudent(column,name);
			if(type==SUCCESS){
				ContentValues values = new ContentValues();
				values.put("sname", name);
				values.put("age", age);
				values.put("sclass", classes);
				values.put("professional", professional);
				long insert = db.insert("student", null, values);
				if(insert != -1){
					return SUCCESS;
				}else{
					return EXCEPTION;
				}
			}
			else if(type == FAIL){
				//名字已存在
				return FAIL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return EXCEPTION;
		}finally{
			if(db!=null){
				db.close();
			}
		}
	return FAIL;
	} 
	/**
	 * 根据column参数，输入列名，和后面的name值对应，返回是否存在这条记录
	 * @param column
	 * @param name
	 * @return Success:没有这条记录，  fail：有这条记录
	 */
	public int checkStudent(String column,String name) {
		SQLiteDatabase db = null;
		Cursor rawQuery = null;
		try {
			db = sql.getWritableDatabase();
			System.out.println(column+"列明");
			rawQuery =  db.rawQuery("select * from student where  "+column+"= ?", new String[]{name});
			if(rawQuery != null){
				if(rawQuery.moveToNext()){
					return FAIL;
				}else{
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常");
			return EXCEPTION;
		}finally{
			if(rawQuery!=null){
				rawQuery.close();
			}
		}
		return EXCEPTION;
	}
	/**
	 * 获取出所有的学生
	 * @return
	 */
	public List<Student> getAllStudent(){
		SQLiteDatabase db = null;
		List<Student> students = new ArrayList<Student>();
		db = sql.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from student", null);
		if(cursor!=null){
			while(cursor.moveToNext()){
				String name = cursor.getString(cursor.getColumnIndex("sname"));
				int age = cursor.getInt(cursor.getColumnIndex("age"));
				String sclass = cursor.getString(cursor.getColumnIndex("sclass"));
				String professional = cursor.getString(cursor.getColumnIndex("professional"));
				Student stu = new Student(name, age, sclass, professional);
				students.add(stu);
			}
		}
		return students;
	}
	/**
	 * 删除指定名字的学生
	 * @param name
	 * @return
	 */
	public int deleteStudent(String column,String name){
		SQLiteDatabase db = null;
		try {
			int state = checkStudent(column,name);
			if(state == SUCCESS){
				//不存在
				return FAIL;
			}
			else if(state == FAIL){
				//存在
				db = sql.getWritableDatabase();
				int delete = db.delete("student", "sname=?", new String[]{name});
				if(delete>0){
					return SUCCESS;
				}else{
					return FAIL;
				}
			}
		} catch (Exception e) {
			return EXCEPTION;
		}finally{
			if(db!=null){
				db.close();
			}
		}
		return FAIL;
	}
	
	/**
	 * 更新学生信息
	 * @param name
	 * @param age
	 * @return
	 */
	public int updateStudent(String column,String name,int age){
		SQLiteDatabase db = null;
		try {
			int state = checkStudent(column,name);
			if(state == SUCCESS){
				return FAIL;
			}else if(state == FAIL){
				//更新
				db = sql.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("age", age);
				int update = db.update("student", values, "sname=?", new String[]{name});
				if(update>0){
					return SUCCESS;
				}else{
					return FAIL;
				}
			}
		} catch (Exception e) {
			return EXCEPTION;
		}finally{
			if(db!=null){
				db.close();
			}
		}
		return FAIL;
	}
	
}
