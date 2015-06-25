package com.tz.replace;

import com.tz.fragmentreplace.R;
import com.tz.replace.fragment.RegisterOneFragment;
import com.tz.replace.fragment.RegisterTwoFragment;
import com.tz.replace.listener.OnTextChangListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Android sdk为8【2.2】，MainActivity要继承FragmentActivity
public class MainActivity extends Activity implements OnClickListener, OnTextChangListener, OnBackStackChangedListener {

	private Button btn;
	private RegisterOneFragment oneFragment;
	private RegisterTwoFragment twoFragment;
	private FragmentManager fm;
	private int flag;  // flag：0【下一步】 flag：1【注册】

	 public static final int REGONE = 0;
	 public static final int REGTWO = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		oneFragment = new RegisterOneFragment();
		
		// 监听fragment的进栈和出栈
		// fm.addOnBackStackChangedListener(new MyBackStackListener()); 
		
		// 开启事务，事务只能提交一次,不可以声明成为全局变量，每次开启的都是一个新的事务
		FragmentTransaction ft = fm.beginTransaction();
		// ft.add(R.id.frameLayout, regOneFragment); //添加
		ft.replace(R.id.frameLayout, oneFragment); // 替换
		// oneFragment不需要添加到fragment栈中，否则按back返回键就会销毁【oneFragment调用系统，有fragment就finish】直接退出到activity中，则不会显示oneFragment
		// ft.addToBackStack("one"); //one是标识oneFragment,通过one可以找到oneFragment
		ft.commit(); // 提交事务
		fm.addOnBackStackChangedListener(this); // 监听fragment的进栈和出栈
	}

	private void initView() {
		btn = (Button) findViewById(R.id.btn);
		twoFragment = new RegisterTwoFragment(); // 将regOneFragment添加进来

		fm = this.getFragmentManager(); // 2.2获取fm为getSupportFragmentManager

		btn.setOnClickListener(this);
		twoFragment.setOnTextChangListener(this);
	}

	@Override
	public void onClick(View v) {
		// 点击按钮,跳转到twoFragment,替换oneFragment
		if (flag == REGONE) {
			//替换fragment
			replaceFragment();
		} else if (flag == REGTWO) {
			//显示注册信息
			showRegisterInfo();
		}
	}

	/**
	 * 显示注册信息
	 */
	private void showRegisterInfo() {
		// 拼接字符串
		StringBuffer sb = new StringBuffer();
		sb.append("account:" + oneFragment.getAccount().getText().toString().trim() + "\n");
		sb.append("password:" + oneFragment.getPassword().getText().toString().trim() + "\n");
		sb.append("realName:" + twoFragment.getRealName().getText().toString().trim() + "\n");
		sb.append("phoneNo:" + twoFragment.getphoneNo().getText().toString().trim());
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBackStackChanged() {
		// 获取里面栈的数量
		int count = fm.getBackStackEntryCount();
		// 点击按钮,跳转到twoFragment,替换oneFragment
		for (int i = 0; i < count; i++) {
			BackStackEntry backStackEntryAt = fm.getBackStackEntryAt(i);
			backStackEntryAt.getName();
			backStackEntryAt.getClass();
			backStackEntryAt.getId();
			Log.i("INFO", "name:" + backStackEntryAt.getName());
			Log.i("INFO", "class:" + backStackEntryAt.getClass());
			Log.i("INFO", "class:" + backStackEntryAt.getId());
		}
		if (count == 0) {
			btn.setText("下一步");
			flag = REGONE;
		} else if (count == 1) {
			btn.setText("注册");
			flag = REGTWO;
		}

	}

	/**
	 * 跳转到twoFragment
	 */
	private void replaceFragment() {
		if (twoFragment == null) {
			twoFragment = new RegisterTwoFragment();
		}
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.frameLayout, twoFragment);
		// 系统判断是否有fragment，有则finish
		// 将twoFragment添加到fragment栈中
		ft.addToBackStack("two"); // two标识twoFragment，通过two可以找到twoFragment，名字可以随便命名
		ft.commit(); //提交事务
	}

	/**
	private class MyBackStackListener implements OnBackStackChangedListener {

		@Override
		public void onBackStackChanged() {
			// 获取里面栈的数量
			int backStackEntryCount = fm.getBackStackEntryCount();
			for (int i = 0; i < backStackEntryCount; i++) {
				BackStackEntry backStackEntryAt = fm.getBackStackEntryAt(i);
				backStackEntryAt.getName();
				backStackEntryAt.getClass();
				backStackEntryAt.getId();
				Log.i("INFO", "name:" + backStackEntryAt.getName());
				Log.i("INFO", "class:" + backStackEntryAt.getClass());
				Log.i("INFO", "class:" + backStackEntryAt.getId());
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
	*/

	@Override
	public void onTextChang(TextView tv) {
		Toast.makeText(MainActivity.this, "不可以输入数字9！", Toast.LENGTH_SHORT).show();
	}

}
