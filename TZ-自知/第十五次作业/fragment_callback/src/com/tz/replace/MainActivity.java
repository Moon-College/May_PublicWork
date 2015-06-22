package com.tz.replace;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements YanZhengPhone {

	private Button button;
	public LinearLayout root;

	private FragmentManager fm;

	private RegisteFirstFragment firstFragment;
	private RegisteSecondFrgament secondFrgament;

	private int step;
	private Context context;

	private Handler handler =new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			TextView tv = (TextView) msg.obj;
			root.removeView(tv);
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;
		button = (Button) findViewById(R.id.button);
		root = (LinearLayout) findViewById(R.id.root);
		fm = this.getSupportFragmentManager();
		fm.addOnBackStackChangedListener(new MyBackStackListener());

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (step == 0) {
					replaceFragment();
				} else if (step == 1) {
					showToast();
				}
			}
		});

		firstFragment = new RegisteFirstFragment(this);
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.framelayout, firstFragment);
		transaction.commit();
	}

	private void showToast() {
		StringBuffer sb = new StringBuffer();
		sb.append("用户名:"+ firstFragment.getUserName().getText().toString().trim()+ "\n");
		sb.append("密码:"	+ firstFragment.getPassword().getText().toString().trim()+ "\n");
		sb.append("名称:" + secondFrgament.getName().getText().toString().trim()+ "\n");
		sb.append("邮箱:" + secondFrgament.getAddr().getText().toString().trim());
		Toast.makeText(this, sb.toString(), 1000).show();
	}

	private void replaceFragment() {
		if (secondFrgament == null) {
			secondFrgament = new RegisteSecondFrgament();
		}
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.framelayout, secondFrgament);
		transaction.addToBackStack("second");
		transaction.commit();
	}

	private class MyBackStackListener implements OnBackStackChangedListener {

		public void onBackStackChanged() {
			int backStackEntryCount = fm.getBackStackEntryCount();
			for (int i = 0; i < backStackEntryCount; i++) {
				BackStackEntry backStackEntryAt = fm.getBackStackEntryAt(i);
			}
			if (backStackEntryCount == 0) {
				step = 0;
				button.setText("下一步");
			} else {
				step = 1;
				button.setText("完成");
			}
		}
	}

	public void tixing(String str) {
		TextView tv = new TextView(context);
		tv.setText(str + "不是正确的格式");
		tv.setTextColor(Color.RED);
		tv.setTextSize(30);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		llp.gravity = Gravity.CENTER;
		tv.setLayoutParams(llp);
		root.addView(tv);
		
		Message msg = new Message();
		msg.obj = tv;
		handler.sendMessageDelayed(msg, 1000);
	}

}