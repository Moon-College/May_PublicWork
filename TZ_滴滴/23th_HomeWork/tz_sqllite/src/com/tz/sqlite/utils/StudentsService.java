package com.tz.sqlite.utils;

import java.util.ArrayList;
import java.util.List;

import com.tz.sqlite.MySQLiteHelper;
import com.tz.sqlite.model.Student;

import android.R.string;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StudentsService {
	
	/**
	 * ��ѧ�����в������ݣ�����
	 * @param db
	 * @param objStudent
	 */
	public void AddStudent(SQLiteDatabase db,Student objStudent){

		ContentValues values = new ContentValues();

        values.put("studentname", objStudent.getStudentname());
        values.put("classname", objStudent.getClassname());
        db.insert("students", null, values);
	}
	

	/**
	 * ��ѧ������ɾ�����ݣ�ɾ��
	 * @param db
	 * @param studentname
	 */
	public void DeleteStudent(SQLiteDatabase db,String studentname){
		db.delete("students", "studentname=?",new String [] {studentname});
	}
	
	/**
	 * ��ѧ�����и���ѧ�������޸ĸ�ѧ�������ڰ༶���ƣ��ģ�
	 * @param db
	 * @param studentname
	 * @param classname
	 */
	public void UpdateClassNameByStudentName(SQLiteDatabase db,String studentname,String classname){
		ContentValues values = new ContentValues();
		values.put("classname", classname);
		db.update("students", values, "studentname=?", new String[]{studentname});
	}
	
	/**
	 * ��ѧ�����в�ѯ���м�¼���飩
	 * @param db
	 * @return
	 */
	public List<Student> QueryStudents(SQLiteDatabase db){
		Cursor cursor = db.query("students", null, null, null, null, null, null);
		List<Student> list = new ArrayList<Student>();
		
		while(cursor.moveToNext()){
			Student objStudent = new Student();
			objStudent.setStudentname(cursor.getString(cursor.getColumnIndex("studentname")));
			objStudent.setClassname(cursor.getString(cursor.getColumnIndex("classname")));
			list.add(objStudent);
		}
		
		return list;
	}

}
