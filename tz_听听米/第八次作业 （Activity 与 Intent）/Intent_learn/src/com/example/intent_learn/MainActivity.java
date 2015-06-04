package com.example.intent_learn;

import java.util.ArrayList;
import java.util.List;

import com.example.intent_learn.bean.Student;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author 邹继明
 * @date 2015-6-3上午1:18:12
 */
public class MainActivity extends Activity implements OnClickListener {
	
	private static final int REQUEST_IMAGE_CAPTURE = 0;
	private static final int REQUEST_IMPLICIT_INTENT = 1;
	private Button camear,startIntent;
	private ImageView img;
	private TextView tv;
	private ArrayList<Student> data;
	private Student stu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initData();
		initView();
	}

	/**
	 * 初始化控件
	 *@author 邹继明
	 *@date 2015-6-3上午1:23:43
	 *@param 
	 *@return void
	 */
	private void initView() {
		camear = (Button) findViewById(R.id.camear);
		startIntent = (Button) findViewById(R.id.startIntent);
		camear.setOnClickListener(this);
		startIntent.setOnClickListener(this);
		img = (ImageView) findViewById(R.id.img);
		tv = (TextView) findViewById(R.id.tv);
	}
	
	/**
	 * 初始化数据
	 *@author 邹继明
	 *@date 2015-6-3上午2:00:27
	 *@param 
	 *@return void
	 */
	public void initData(){
		stu = new Student();
		stu.setId(0);
		stu.setName("TZ00");
		stu.setGender(true);
		data = new ArrayList<Student>();
		for (int i = 1; i < 5; i++) {
			Student s = new Student();
			s.setId(i);
			s.setName("TZ0"+i);
			s.setGender(i%2==0?true:false);
			data.add(s);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.camear:
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//查询是否有符合条件的Activity
		    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
		        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		    }
			break;
		case R.id.startIntent:
			Intent intent = new Intent("com.zjm.implicit");
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra("student",stu);
			intent.putParcelableArrayListExtra("students",data);
			startActivityForResult(intent, REQUEST_IMPLICIT_INTENT);
			break;

		default:
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_IMAGE_CAPTURE:
			 if (resultCode == RESULT_OK) {
			        Bundle extras = data.getExtras();
			        Bitmap imageBitmap = (Bitmap) extras.get("data");
			        img.setImageBitmap(imageBitmap);
			    }
			break;
		case REQUEST_IMPLICIT_INTENT:
			if(resultCode == RESULT_OK){
				Student student = data.getParcelableExtra("student");
				tv.setText(student.toString());
//				Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
		if(resultCode == Activity.RESULT_OK)
		super.onActivityResult(requestCode, resultCode, data);
	}
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	
}
