package com.tz.michael.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.tz.michael.bean.Person;

public class SecondActivity extends Activity {
	
	Bitmap bitmap=null;
	private List<Person> parr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		parr= getIntent().getParcelableArrayListExtra("pp");
		Toast.makeText(this, parr.toString(), 0).show();
	}
	
	public void fff(View v){
		Intent intent=new Intent();
		intent.putParcelableArrayListExtra("pp1", (ArrayList<? extends Parcelable>) parr);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
	
	/**
	 * 界面显示出来时调用
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	/**
	 * 窗口获得焦点时调用
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	/**
	 * 窗体部分被遮住时调用
	 * 此方法中可以用来保存数据
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	/**
	 * 窗口完全被遮住时调用
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	/**
	 * 窗体被销毁时调用
	 * 此方法销毁不必要的对象，释放空间
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
