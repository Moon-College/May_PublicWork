package com.tz.michael.activity;

import java.util.List;

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
        List<Student> s=DBHelper.queryData(db, "students", "学生3");
        Log.i("ss--", s.toString());
        List<Student> sl=DBHelper.queryStudentsByClassName(db, "班级1");
        Log.i("sl--", sl.toString());
    }
    
    @Override
    protected void onDestroy() {
    	if(db!=null){
    		db.close();
    	}
    	super.onDestroy();
    }
}