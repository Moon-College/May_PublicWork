package com.tz.michael.activity;

import java.util.List;

import com.tz.michael.bean.ClassBean;
import com.tz.michael.bean.Student;
import com.tz.michael.utils.DBHelper;
import com.tz.michael.utils.MySqlLiteHelper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class SqlLiteProjectActivity extends Activity {
	SQLiteDatabase db;
	MySqlLiteHelper helper;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        helper=new MySqlLiteHelper(this, "school", null, 1);
        db=helper.getWritableDatabase();
//        List<Student> s=DBHelper.queryData(db, "students", "学生3");
//        Log.i("ss--", s.toString());
//        List<Student> sl=DBHelper.queryStudentsByClassName(db, "班级1");
//        Log.i("sl--", sl.toString());
        Student student=new Student("szm", 24, 1);
//        DBHelper.queryDataTest(student);
//        DBHelper.insertData(db, student, "students");
        ClassBean classBean=new ClassBean("vip7月", 20);
        DBHelper.insertData(db, classBean, "class");
//        List<Student> s_in=DBHelper.queryData(db, "students", "szm");
//        Log.i("ins--", s_in.toString());
        DBHelper.updateData(db, "students", student, 12);
        DBHelper.deleteData(db, "students", 11);
        Student student2=(Student) DBHelper.queryData(db, "students", Student.class, 14);
        Log.i("quer--", student2.toString());
        List<Object> ls = DBHelper.queryDataByName(db, "students", Student.class, "szm");
        for(Object object:ls){
        	Student student3=(Student) object;
        	Log.i("llqq--", student3.toString());
        }
    }
    
    @Override
    protected void onDestroy() {
    	if(db!=null){
    		db.close();
    	}
    	super.onDestroy();
    }
}