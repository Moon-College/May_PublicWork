package com.itskylin.android.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.itskylin.android.sqlite.adapter.MyClasssAdapter;
import com.itskylin.android.sqlite.adapter.MyStudentAdapter;
import com.itskylin.android.sqlite.adapter.MyTeacherAdapter;
import com.itskylin.android.sqlite.db.MySqliteDatabaseHelper;

public class MainActivity extends Activity {

	private AutoCompleteTextView student;
	private AutoCompleteTextView classs;
	private AutoCompleteTextView teacher;
	private MySqliteDatabaseHelper mdb;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		student = (AutoCompleteTextView) this.findViewById(R.id.student);
		classs = (AutoCompleteTextView) this.findViewById(R.id.classs);
		teacher = (AutoCompleteTextView) this.findViewById(R.id.teacher);
		mdb = new MySqliteDatabaseHelper(this, null, 1);
		db = mdb.getReadableDatabase();
		student.setThreshold(1);
		classs.setThreshold(2);
		teacher.setThreshold(3);
		classs.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 获取用户输入的文字
				String text = classs.getText().toString().trim();
				if (!text.equals("")) {
					String sql = "select * from classs where name like ?";
					Cursor query = db
							.rawQuery(sql, new String[] { text + "%" });
					MyClasssAdapter adapter = new MyClasssAdapter(
							MainActivity.this, query, true);
					classs.setAdapter(adapter);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		student.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 获取用户输入的文字
				String text = student.getText().toString().trim();
				if (!text.equals("")) {
					String sql = "select * from student where name like ?";
					Cursor query = db
							.rawQuery(sql, new String[] { text + "%" });
					MyStudentAdapter adapter = new MyStudentAdapter(
							MainActivity.this, query, true);
					student.setAdapter(adapter);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		teacher.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 获取用户输入的文字
				String text = teacher.getText().toString().trim();
				if (!text.equals("")) {
					String sql = "select * from teacher where name like ?";
					Cursor query = db
							.rawQuery(sql, new String[] { text + "%" });
					MyTeacherAdapter adapter = new MyTeacherAdapter(
							MainActivity.this, query, true);
					teacher.setAdapter(adapter);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mdb.close();
	}
}
