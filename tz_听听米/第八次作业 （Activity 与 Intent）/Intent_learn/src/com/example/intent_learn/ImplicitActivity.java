package com.example.intent_learn;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.intent_learn.bean.Student;

/**
 * 隐式调用的Activity
 * @author 邹继明
 * @date 2015-6-3上午2:17:36
 */
public class ImplicitActivity extends Activity {
	
	private Student student;
	private ArrayList<Student> students;
	private ListView lv;
	private Button backBtn;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stu_item);
		initData();
		initView();
	}

	/**
	 * 初始化控件
	 *@author 邹继明
	 *@date 2015-6-3上午2:17:51
	 *@param 
	 *@return void
	 */
	private void initView() {
		backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent.putExtra("student", student);
				setResult(Activity.RESULT_OK,intent);
				finish();
			}
		});
		lv = (ListView) findViewById(R.id.lv);
		ArrayAdapter <Student>adapter = new ArrayAdapter<Student>(this,android.R.layout.simple_list_item_1, students);
		lv.setAdapter(adapter);
	}

	/**
	 * 初始化数据
	 *@author 邹继明
	 *@date 2015-6-3上午2:18:10
	 *@param 
	 *@return void
	 */
	private void initData() {
		intent = getIntent();
		student = intent.getParcelableExtra("student");
		students = intent.getParcelableArrayListExtra("students");
	}

}
