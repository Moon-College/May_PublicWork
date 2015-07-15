package com.junwen.ui;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitedemo.R;
import com.junwen.bean.Student;
import com.junwen.util.DataService;

public class MainActivity extends Activity {
	
	private EditText sName; //姓名
	private EditText sAge; //年龄
	private EditText sClass; //班级
	private EditText sProfessional;//专业
	private EditText deleteName; //要删除的学生姓名
	private EditText updateName; //要更新的学生姓名
	private DataService db; //数据库
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	/**
	 * 初始化
	 */
	private void initView() {
		sName = (EditText) findViewById(R.id.sName);
		sAge = (EditText) findViewById(R.id.sAge);
		sClass = (EditText) findViewById(R.id.sClass);
		sProfessional = (EditText) findViewById(R.id.Professional);
		deleteName = (EditText) findViewById(R.id.deleteName);
		updateName = (EditText) findViewById(R.id.updateName);
		tv = (TextView) findViewById(R.id.showStudent);
		db = new DataService(this, "Student.db", 1);
	}

	/**
	 * 点击按钮
	 * 
	 * @param view
	 */
	public void onclick(View view) {
		switch (view.getId()) {
		
		case R.id.addStudent:
			//添加学生
			String name = sName.getText().toString().trim();
			String age = sAge.getText().toString().trim();
			String clazz = sClass.getText().toString().trim();
			String professional = sProfessional.getText().toString().trim();
			if(name.equals("") || age.equals("")||clazz.equals("")||professional.equals("")){
				Toast.makeText(MainActivity.this, "信息有缺漏，请输入完整信息", 0).show();
				return;
			}
			addStudent(name, Integer.valueOf(age), clazz, professional);
			break;
			
		case R.id.deleteStudent:
			//删除学生
			String deleName = deleteName.getText().toString().trim();
			if(deleName.equals("")){
				Toast.makeText(MainActivity.this, "请输入要删除的名字", 0).show();
				deleteName.setFocusable(true);
				return;
			}
			deleteStudent(deleName);
			break;
			
		case R.id.updateStudent:
			//更新学生
			String updaeName = updateName.getText().toString().trim();
			if(updaeName.equals("")){
				Toast.makeText(MainActivity.this, "请输入要更新的名字", 0).show();
				deleteName.setFocusable(true);
				return;
			}
			updateStudent(updaeName);
			break;
		case R.id.selectStudent:
			//查询学生
			selectAllStudent();
			break;
		default:
			break;
		}
	}
	/**
	 * 根据指定姓名，更改其人的年龄年龄
	 */
	private void updateStudent(String name) {
		int state = db.updateStudent("sname",name, 20);
		if(state == DataService.SUCCESS){
			Toast.makeText(MainActivity.this, "更新成功!", 0).show();
		}else{
			Toast.makeText(MainActivity.this, "不存在此学生", 0).show();
		}
	}
	/**
	 * 根据指定的名字，删除这条记录
	 * @param name
	 */
	private void deleteStudent(String name) {
		int type = db.deleteStudent("sname",name);
		if(type == DataService.SUCCESS){
			Toast.makeText(MainActivity.this, "删除成功!", 0).show();
		}else{
			Toast.makeText(MainActivity.this, "不存在此学生", 0).show();
		}
	}
	/**
	 * 查询出所有学生并且显示出来
	 */
	private void selectAllStudent() {
		List<Student> students = db.getAllStudent();
		if(students != null && students.size()>0){
			StringBuffer sb = new StringBuffer();
			for (Student student : students) {
				sb.append("姓名："+student.getName()+"\n"+
									"年龄："+student.getAge()+"\n"+
									"班级："+student.getClasses()+"\n"+
									"专业："+student.getProfessional()+"\n"
									+"\n"
						);
			}
			tv.setText(sb);
		}else{
			tv.setText("还没有学生信息");
		}
	}
	/**
	 * 添加学生
	 * @param name
	 * @param age
	 * @param classes
	 * @param professional
	 */
	private void addStudent(String name,int age,String classes,String professional) {
		int state = db.addStudent("sname",name, Integer.valueOf(age), classes, professional);
		if(state == DataService.SUCCESS){
			Toast.makeText(MainActivity.this, "添加成功!", 0).show();
			sName.setText("");
			sAge.setText("");
			sClass.setText("");
			sProfessional.setText("");
		}else if(state == DataService.FAIL){
			Toast.makeText(MainActivity.this, "已存在学生，不要重复添加!", 0).show();
		}else if(state == DataService.EXCEPTION){
			Toast.makeText(MainActivity.this, "添加出现异常!", 0).show();
		}
	}
}
