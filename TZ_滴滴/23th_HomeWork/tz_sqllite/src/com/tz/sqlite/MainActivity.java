package com.tz.sqlite;

import java.util.List;

import com.tz.sqlite.model.Student;
import com.tz.sqlite.utils.StudentsService;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements TextWatcher {
    
	SQLiteDatabase db;
	StudentsService objStudentsService;
	
	private AutoCompleteTextView actv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        actv = (AutoCompleteTextView) findViewById(R.id.actv);
        actv.setThreshold(1);
        actv.addTextChangedListener(this);
        
        //获取SQLiteOpenHelper()
        MySQLiteHelper helper = new MySQLiteHelper(this, "MyDB.db", null, 4);
        
        db = helper.getWritableDatabase();
        
        objStudentsService = new StudentsService();
        
//        Student objStudent = new Student();
//        objStudent.setStudentname("wangyanfei");
//        objStudent.setClassname("3班");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("wangbin");
//        objStudent.setClassname("3班");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("wangjie");
//        objStudent.setClassname("3班");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("王光");
//        objStudent.setClassname("3班");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("王延霞");
//        objStudent.setClassname("3班");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("王延超");
//        objStudent.setClassname("3班");
//        objStudentsService.AddStudent(db, objStudent);
        
//        objStudentsService.UpdateClassNameByStudentName(db, "李帅", "9班");
        
        
//        objStudentsService.DeleteStudent(db, "李帅");
        
        
        
//        ContentValues values = new ContentValues();
        
        //面向对象（增删改查）
        
//        //插入数据
//        ContentValues values = new ContentValues();
//        values.put("studentname", "查楠楠");
//        values.put("classname", "2班");
//        db.insert("students", null, values);
//        
//        values.clear();
//        values.put("classname", "2班");
//        values.put("count", "40");
//        db.insert("classes", null, values);
//        
//        values.clear();
//        values.put("studentname", "查楠楠");
//        values.put("android", "88");
//        values.put("java", "96");
//        db.insert("scores", null, values);
        
//        //更新数据
//        values.clear();
//        values.put("android", "92");
//        db.update("scores", values, "studentname=?", new String [] {"查楠楠"});
        
          //删除数据
//          db.delete("scores", "studentname=?", new String [] {"王延飞"});
        
//          //查询数据
//          Cursor cursor = db.query("students", null,null, null, null, null, null);
//          while(cursor.moveToNext()){
//        	  String studentname = cursor.getString(cursor.getColumnIndex("studentname"));
//        	  String classname = cursor.getString(cursor.getColumnIndex("classname"));
//        	  Log.i("INFO", "姓名："+studentname+" || 班级："+classname);
//          }
          
          List<Student> list = objStudentsService.QueryStudents(db);
          for (int i = 0; i < list.size(); i++) {
			Log.i("INFO", 
					"姓名："+list.get(i).getStudentname()+" || 班级："+list.get(i).getClassname());
		}
    }


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db!=null) {
			db.close();
		}
	}
	
	private class MyAdapter extends CursorAdapter{

		public MyAdapter(Context context, Cursor c, boolean autoRequery) {
			super(context, c, autoRequery);
			// TODO Auto-generated constructor stub
		}

		
		
		@Override
		public CharSequence convertToString(Cursor cursor) {
	
			//return super.convertToString(cursor);
			return cursor == null?"":cursor.getString(cursor.getColumnIndex("provincename"));
		}



		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View view = View.inflate(MainActivity.this, R.layout.list_item, null);
			return view;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {

			TextView tv = (TextView) view.findViewById(R.id.tv);
			String text = cursor.getString(cursor.getColumnIndex("provincename"));
			tv.setText(text);
			
		}
		
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}


	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}


	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String text = actv.getText().toString().trim();
		if (!text.equals("")) {
			String sql = "select * from provinces where provincename like ?";
			Cursor cursor = db.rawQuery(sql, new String[]{text+"%"});
			MyAdapter adapter = new MyAdapter(MainActivity.this, cursor, true);
			actv.setAdapter(adapter);
					
			}
		}
	
}