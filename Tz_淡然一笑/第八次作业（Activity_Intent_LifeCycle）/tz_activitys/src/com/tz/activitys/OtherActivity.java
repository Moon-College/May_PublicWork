package com.tz.activitys;

import com.tz.activitys.bean.Person;
import com.tz.activitys.bean.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;

public class OtherActivity extends Activity implements OnClickListener{
	
	private Person person;
	private Student student;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_other);
		
		RelativeLayout rl = new RelativeLayout(this);
		rl.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); //布局容器属性
		
		ImageView img = new ImageView(this);
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rlp.addRule(RelativeLayout.CENTER_IN_PARENT);  //添加ImageView的位置属性
		img.setImageResource(R.drawable.stu6); //设置图片
		img.setLayoutParams(rlp); //将rlp属性设置给ImageView
		//设置图片点击事件
		img.setOnClickListener(this);
		
		rl.addView(img);
		
		//获取传过来的Intent
		Intent intent = this.getIntent();
		
		int num = intent.getIntExtra("num", 0);
		String name = intent.getStringExtra("name");
		boolean flag = intent.getBooleanExtra("flag", true);
		Log.i("INFO", "num:" + num+"\n"+"name:" + name+"\n"+"flag:" + flag);
		
		student = (Student) intent.getSerializableExtra("stu_data");
		Log.i("INFO", "name:"+student.getName()+"\n"+"age:"+student.getAge());
		
		person = intent.getParcelableExtra("person_data");
		Log.i("INFO", "name:"+person.getName()+"\n"+"age:"+person.getAge());
		
		//加载视图
		setContentView(rl);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra("data_back", person);
		setResult(Activity.RESULT_OK, intent); //将要回传的意图绑定给result
		finish(); //销毁当前界面
	}

}
