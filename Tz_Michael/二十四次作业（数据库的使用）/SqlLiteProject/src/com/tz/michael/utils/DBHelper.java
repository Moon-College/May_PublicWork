package com.tz.michael.utils;

import java.util.ArrayList;
import java.util.List;

import com.tz.michael.bean.ClassBean;
import com.tz.michael.bean.GradeBean;
import com.tz.michael.bean.Student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库的一些工具类
 * @author michael
 *
 */
public class DBHelper {

	/**
	 * 插入数据
	 * @param db 数据库
	 * @param table 表名（这里只有students,ClassBean,GradeBean）
	 * @param t 类型
	 */
	public static <T>void insertData(SQLiteDatabase db,String table,T t){
		ContentValues values=new ContentValues();
		if(table.equals("students")){
			Student s=(Student) t;
			values.put(s.getName(), s.getName());
			values.put(s.getAge()+"", s.getAge());
			values.put(s.getClassId()+"", s.getClassId());
			db.insert("students", null, values);
		}else if(table.equals("ClassBean")){
			ClassBean classBean=(ClassBean) t;
			values.put(classBean.getName(), classBean.getName());
			values.put(classBean.getStudentTotalNum()+"", classBean.getStudentTotalNum());
			db.insert("students", null, values);
		}else if(table.equals("GradeBean")){
			GradeBean gradeBean=(GradeBean) t;
			values.put(gradeBean.getName(), gradeBean.getName());
			values.put(gradeBean.getClassName(), gradeBean.getClassName());
			db.insert("students", null, values);
		}
	}
	
	/**
	 * 根据name删除
	 * @param db 
	 * @param table 
	 * @param name 
	 */
	public static  void deleteData(SQLiteDatabase db,String table,String name){
		if(table.equals("students")){
			db.delete("students", "name=?", new String[]{name});
		}else if(table.equals("ClassBean")){
			db.delete("class", "name=?", new String[]{name});
		}else if(table.equals("GradeBean")){
			db.delete("grade", "name=?", new String[]{name});
		}
	}
	
	/**
	 * 根据id删除
	 * @param db
	 * @param table
	 * @param id
	 */
	public static  void deleteData(SQLiteDatabase db,String table,int id){
		if(table.equals("students")){
			db.delete("students", "_id=?", new String[]{id+""});
		}else if(table.equals("ClassBean")){
			db.delete("class", "_id=?", new String[]{id+""});
		}else if(table.equals("GradeBean")){
			db.delete("grade", "_id=?", new String[]{id+""});
		}
	}
	
	/**
	 * 更新指定name的数据
	 * @param db
	 * @param table 要更新的数据所在的表名
	 * @param t 数据的类型
	 * @param name 过滤条件
	 */
	public static <T> void updateData(SQLiteDatabase db,String table,T t,String name){
		ContentValues values=new ContentValues();
		if(table.equals("students")){
			Student s=(Student) t;
			values.put(s.getName(), s.getName());
			values.put(s.getAge()+"", s.getAge());
			values.put(s.getClassId()+"", s.getClassId());
			db.update("students", values, "name=?", new String[]{name});
		}else if(table.equals("ClassBean")){
			ClassBean classBean=(ClassBean) t;
			values.put(classBean.getName(), classBean.getName());
			values.put(classBean.getStudentTotalNum()+"", classBean.getStudentTotalNum());
			db.update("ClassBean", values, "name=?", new String[]{name});
		}else if(table.equals("GradeBean")){
			GradeBean gradeBean=(GradeBean) t;
			values.put(gradeBean.getName(), gradeBean.getName());
			values.put(gradeBean.getClassName(), gradeBean.getClassName());
			db.update("GradeBean", values, "name=?", new String[]{name});
		}
	}
	
	/**
	 * 查询
	 * @param db
	 * @param table
	 * @param name
	 * @return
	 */
	public static <T> List<T> queryData(SQLiteDatabase db,String table,String name){
		List<T> ll=new ArrayList<T>();
		if(table.equals("students")){
			Cursor cursor=db.query("students", null, "name=?", new String[]{name}, null, null, null);
			while(cursor.moveToNext()){
				Student s=new Student();
				String v_name=cursor.getString(cursor.getColumnIndex("name"));
				String v_classId=cursor.getString(cursor.getColumnIndex("classId"));
				String v_age=cursor.getString(cursor.getColumnIndex("age"));
				s.setName(v_name);
				s.setAge(Integer.valueOf(v_age));
				s.setClassId(Integer.valueOf(v_classId));
				ll.add((T) s);
			}
			return ll;
		}else if(table.equals("ClassBean")){
			Cursor cursor=db.query("ClassBean", null, "name=?", new String[]{name}, null, null, null);
			while(cursor.moveToNext()){
				ClassBean classBean=new ClassBean();
				String v_name=cursor.getString(cursor.getColumnIndex("name"));
				String v_studentNum=cursor.getString(cursor.getColumnIndex("studentTotalNum"));
				classBean.setName(v_name);
				classBean.setStudentTotalNum(Integer.valueOf(v_studentNum));
				ll.add((T) classBean);
			}
			return ll;
		}else if(table.equals("GradeBean")){
			Cursor cursor=db.query("GradeBean", null, "name=?", new String[]{name}, null, null, null);
			while(cursor.moveToNext()){
				GradeBean gradeBean=new GradeBean();
				String v_name=cursor.getString(cursor.getColumnIndex("name"));
				String v_className=cursor.getString(cursor.getColumnIndex("className"));
				gradeBean.setName(v_name);
				gradeBean.setClassName(v_className);
				ll.add((T) gradeBean);
			}
			return ll;
		}
		return null;
	}
	
	/**
	 * 查询给定班级的学生
	 * @param db
	 * @param className
	 * @return
	 */
	public static List<Student> queryStudentsByClassName(SQLiteDatabase db,String className){
		List<Student> ll=new ArrayList<Student>();
		String sql="select s.* from students as s left join class as c on s.classId=c._id where c.name=?";
		Cursor c=db.rawQuery(sql, new String[]{className});
		while(c.moveToNext()){
			String name=c.getString(c.getColumnIndex("name"));
			String age=c.getString(c.getColumnIndex("age"));
			String classId=c.getString(c.getColumnIndex("classId"));
			Student s=new Student(name, Integer.valueOf(age), Integer.valueOf(classId));
			ll.add(s);
		}
		return ll;
	}
	
}
