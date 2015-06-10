package com.qfx.intentstartway;

import com.qfx.intentstartway.bean.Student;
import com.qfx.intentstartway.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TwoActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		Intent intent = getIntent();
//		String name = intent.getStringExtra("name");
//		int age = intent.getIntExtra("age", 0);
//		boolean isMarried = intent.getBooleanExtra("isMarried", true);
//		User user = (User) intent.getSerializableExtra("user");
//		Toast.makeText(this, "名字："+name+"年龄:"+age+"婚否："+isMarried+"   "+user.toString(), Toast.LENGTH_LONG).show();
		
		Student stu = intent.getParcelableExtra("student");
		Toast.makeText(this, stu.toString(), 1000).show();
	}
	
	
	
}
