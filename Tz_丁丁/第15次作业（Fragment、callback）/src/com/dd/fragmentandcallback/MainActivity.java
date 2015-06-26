package com.dd.fragmentandcallback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button button;
	private MyEditText editText;
	private RegisteFirstFragment firstFragment;
	private FragmentManager fm;
	private RegisteSecondFragment secondFrgament;
	private int step;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		editText = (MyEditText) findViewById(R.id.button1);
		editText.setOnInputListener(new onInputListener() {
			
			@Override
			public void onInput(View v) {
				if (editText.getText().toString().equals("9")) {
					button.setText("让你输入你就输9你是猪啊");
					Toast.makeText(MainActivity.this, "让你输入你就输9你是猪啊", 1000).show();
				}else {
					button.setText("再来啊，有种就输一个9");
				}
			}
		});
		
		fm = getFragmentManager();
		fm.addOnBackStackChangedListener( new OnBackStackChangedListener() {
			
			@Override
			public void onBackStackChanged() {
				int backStackEntryCount = fm.getBackStackEntryCount();
				for (int i = 0; i < backStackEntryCount; i++) {
					
				}
				if (backStackEntryCount==1) {
					button.setText("注册");
					step = 1;
				}else {
					button.setText("下一步");
					step = 0;
				}
			}
		});
		firstFragment = new RegisteFirstFragment();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.framelayout, firstFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.button) {
			replaceFragment();
		}
	}
	@SuppressLint("NewApi")
	private void replaceFragment() {
		Log.v("home", "sss");
		if (secondFrgament == null) {
			secondFrgament = new RegisteSecondFragment();
		}
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.framelayout, secondFrgament);
		fragmentTransaction.addToBackStack("second");//添加该fragment到fragment栈
		fragmentTransaction.commit();
		button.setText("注册");
	}

}
