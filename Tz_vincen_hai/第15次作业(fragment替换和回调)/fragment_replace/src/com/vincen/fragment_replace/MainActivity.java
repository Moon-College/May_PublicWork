package com.vincen.fragment_replace;

import com.vincen.callback.myinterface.MyInterface;
import com.vincen.fragment.RegisterFragment;
import com.vincen.fragment.SecondFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener,MyInterface{
	private Button btn;
	private FragmentManager fm;
	private RegisterFragment register;
	private SecondFragment second = null;
	private int step;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		
		//得到fragmentManager的管理类
		fm = this.getSupportFragmentManager();
		fm.addOnBackStackChangedListener(new MyOnBackStackChangedListener());
		register = new RegisterFragment();
		FragmentTransaction tranaction = fm.beginTransaction();
		tranaction.replace(R.id.frame,register);
		tranaction.commit();
		
	}

	@Override
	public void onClick(View view) {
		if(step == 0){
			replaceFragment();
		}else{
			showToast();
		}
	}
	
	private void showToast() {
		StringBuilder sb = new StringBuilder();
		sb.append("用户名："+register.getUsername().getText().toString().trim()+"\n");
		sb.append("密码："+register.getPassword().getText().toString().trim()+"\n");
		sb.append("地址："+second.getAddress().getText().toString().trim()+"\n");
		sb.append("邮箱："+second.getEmail().getText().toString().trim()+"\n");
		
		Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
	}

	private void replaceFragment() {
		if(second == null){
			second = new SecondFragment();
		}
		
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.replace(R.id.frame, second);
		beginTransaction.addToBackStack("second");
		beginTransaction.commit();
	}

	class MyOnBackStackChangedListener implements OnBackStackChangedListener{
		
		@Override
		public void onBackStackChanged() {
			//获取栈的数量
			int count = fm.getBackStackEntryCount();
			if(count == 0){
				step = 0;
				btn.setText("注册");
			}else{
				step = 1 ;
				btn.setText("下一步");
			}
		}
		
	}

	@Override
	public void onHitListener(boolean flag) {
		Log.i("aa", flag+"");
		if(flag == true){
			LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
			TextView tv = new TextView(this);
			tv.setText("用户名中有不合法字符9");
			layout.addView(tv);
		}
		
	}

}
