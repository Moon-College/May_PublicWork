package com.yl.Register;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.yl.Register.R;
import com.yl.fragment.MyFirstFragment;
import com.yl.fragment.MySecondFragment;
import com.yl.myinterface.OnMyKeyDownListener;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnMyKeyDownListener {
	private FragmentManager fm;
	private MyFirstFragment firstFragment;
	// first_fragement.xml TextView 需要写id,否则back返回不要显示上次填写的内容
	private MySecondFragment secondFragment;
	private Button btn;
	private int flag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		firstFragment = new MyFirstFragment();
		firstFragment.setOnMyKeyDownListener(this);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		// 获得FragmentManager
		fm = getSupportFragmentManager();
		fm.addOnBackStackChangedListener(new MyBackStackListener());
		// 获取事务
		FragmentTransaction transaction = fm.beginTransaction();
		// 替换fragment
		transaction.replace(R.id.fl, firstFragment);
		// 提交事务
		transaction.commit();
	}

	public void onClick(View v) {
		if (flag == 0) {
			if (secondFragment == null) {
				secondFragment = new MySecondFragment();
			}
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.fl, secondFragment);
			transaction.addToBackStack("second");
			transaction.commit();
		} else if (flag == 1) {
			showToast();
		}
	}

	private void showToast() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("user:" + firstFragment.getUser().getText().toString().trim()
				+ "\n");
		sb.append("password:"
				+ firstFragment.getPass().getText().toString().trim() + "\n");
		sb.append("name:"
				+ secondFragment.getName().getText().toString().trim() + "\n");
		sb.append("age:" + secondFragment.getAge().getText().toString().trim());
		Toast.makeText(this, sb.toString(), 1000).show();
	}

	private class MyBackStackListener implements OnBackStackChangedListener {

		public void onBackStackChanged() {
			// TODO Auto-generated method stub
			// 获取里面栈的数量
			int backStackEntryCount = fm.getBackStackEntryCount();// 栈的数量
			for (int i = 0; i < backStackEntryCount; i++) {
				BackStackEntry backStackEntryAt = fm.getBackStackEntryAt(i);
				Toast.makeText(MainActivity.this, backStackEntryAt.getName(),
						1000).show();
			}
			if (backStackEntryCount == 0) {
				flag = 0;
				btn.setText("下一步");
			} else {
				flag = 1;
				btn.setText("注册");
			}
		}

	}

	public void onMyKeyDown(View v) {
		Toast.makeText(this, "不能包含9", Toast.LENGTH_SHORT).show();
	}
}