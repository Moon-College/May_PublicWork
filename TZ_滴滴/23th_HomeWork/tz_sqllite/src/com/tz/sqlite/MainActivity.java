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
        
        //��ȡSQLiteOpenHelper()
        MySQLiteHelper helper = new MySQLiteHelper(this, "MyDB.db", null, 4);
        
        db = helper.getWritableDatabase();
        
        objStudentsService = new StudentsService();
        
//        Student objStudent = new Student();
//        objStudent.setStudentname("wangyanfei");
//        objStudent.setClassname("3��");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("wangbin");
//        objStudent.setClassname("3��");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("wangjie");
//        objStudent.setClassname("3��");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("����");
//        objStudent.setClassname("3��");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("����ϼ");
//        objStudent.setClassname("3��");
//        objStudentsService.AddStudent(db, objStudent);
//         objStudent = new Student();
//        objStudent.setStudentname("���ӳ�");
//        objStudent.setClassname("3��");
//        objStudentsService.AddStudent(db, objStudent);
        
//        objStudentsService.UpdateClassNameByStudentName(db, "��˧", "9��");
        
        
//        objStudentsService.DeleteStudent(db, "��˧");
        
        
        
//        ContentValues values = new ContentValues();
        
        //���������ɾ�Ĳ飩
        
//        //��������
//        ContentValues values = new ContentValues();
//        values.put("studentname", "����");
//        values.put("classname", "2��");
//        db.insert("students", null, values);
//        
//        values.clear();
//        values.put("classname", "2��");
//        values.put("count", "40");
//        db.insert("classes", null, values);
//        
//        values.clear();
//        values.put("studentname", "����");
//        values.put("android", "88");
//        values.put("java", "96");
//        db.insert("scores", null, values);
        
//        //��������
//        values.clear();
//        values.put("android", "92");
//        db.update("scores", values, "studentname=?", new String [] {"����"});
        
          //ɾ������
//          db.delete("scores", "studentname=?", new String [] {"���ӷ�"});
        
//          //��ѯ����
//          Cursor cursor = db.query("students", null,null, null, null, null, null);
//          while(cursor.moveToNext()){
//        	  String studentname = cursor.getString(cursor.getColumnIndex("studentname"));
//        	  String classname = cursor.getString(cursor.getColumnIndex("classname"));
//        	  Log.i("INFO", "������"+studentname+" || �༶��"+classname);
//          }
          
          List<Student> list = objStudentsService.QueryStudents(db);
          for (int i = 0; i < list.size(); i++) {
			Log.i("INFO", 
					"������"+list.get(i).getStudentname()+" || �༶��"+list.get(i).getClassname());
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