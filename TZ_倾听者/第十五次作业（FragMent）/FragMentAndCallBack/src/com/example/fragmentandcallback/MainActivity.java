package com.example.fragmentandcallback;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentandcallback.fragment.LoginFragMent;
import com.example.fragmentandcallback.fragment.OnTextChangListener;
import com.example.fragmentandcallback.fragment.RegisterFragMent;

public class MainActivity extends FragmentActivity implements OnClickListener, OnBackStackChangedListener, OnTextChangListener{

	private Button btn;
	private FragmentManager fm;
	private LoginFragMent login;
	private int fragState = 0;
	private int REGIST= 0;
	private int LOGIN = 1;
	private RegisterFragMent regist2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		regist2 = new RegisterFragMent();
		
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.replace(R.id.fraglayout, regist2);
		beginTransaction.commit();
		fm.addOnBackStackChangedListener(this);
	}

	/**
	 * 初始化
	 */
	private void initView() {
		btn = (Button) findViewById(R.id.button);
		login = new LoginFragMent();
		fm = getSupportFragmentManager();
		btn.setOnClickListener(this);
		login.setOnTextListener(this);
	}

	/**
	 * 当点击按钮时
	 */
	@Override
	public void onClick(View v) {
		if(fragState == LOGIN){
			StringBuffer sb = new StringBuffer();
			sb.append("账号："+regist2.getUser().getText().toString()+"\n");
			sb.append("密码："+regist2.getPass().getText().toString()+"\n");
			sb.append("姓名："+login.getName().getText().toString()+"\n");
			sb.append("手机号："+login.getPhonenumber().getText().toString()+"\n");
			Toast.makeText(MainActivity.this, sb, 5000).show();
		}else if( fragState == REGIST){
			replaceFragMent();
		}
	}
	private void replaceFragMent() {
			// 当前是用户输入页面
			FragmentTransaction beginTransaction1 = fm.beginTransaction();
			beginTransaction1.replace(R.id.fraglayout, login);
			beginTransaction1.addToBackStack("regist");
			beginTransaction1.commit();
			fragState = LOGIN;
	}
	@Override
	public void onBackStackChanged() {
		int count = fm.getBackStackEntryCount();
		if(count == 0 ){
			//当前是第一个界面
			btn.setText("下一步");
			fragState =REGIST;
		}else{
			btn.setText("注册");
			fragState = LOGIN;
		}
	}
	@Override
	public void onTextChanged(TextView tv) {
		Toast.makeText(MainActivity.this, "不可输入9", 1).show();
	}
}
