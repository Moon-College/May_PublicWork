package com.deccent.decentfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DecentFragmentActivity extends FragmentActivity implements
		OnClickListener, OnBackStackChangedListener {
	private static final String TAG = "DecentFragmentActivity";
	private Button registerBtn;
	private TextView warning;

	FragmentManager mFragmentManager;
	/*
	 * 记录注册的步骤
	 */
	private int mStep = 0;
	private PasswordFragment pf = null;
	private MobileFagment mf = null;
	private boolean mIsWarning;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*
		 * 注册按钮的处理
		 */
		registerBtn = (Button) findViewById(R.id.registerBtn);
		mStep = 0;
		registerBtn.setOnClickListener(this);

		// 非support版本的话调用getFragmentManager
		mFragmentManager = getSupportFragmentManager();
		// 设置Fragment的stackchange
		mFragmentManager.addOnBackStackChangedListener(this);

		warning = (TextView) findViewById(R.id.warning);
	}

	public void setWarningState(boolean isWarning, String warningText) {
		mIsWarning = isWarning;
		warning.setText(warningText);
	}

	@Override
	public void onClick(View arg0) {
		Log.d(TAG, "into onClick");
		// TODO Auto-generated method stub
		replaceFragment();
	}

	/**
	 * 处理替换fragment
	 */
	private void replaceFragment() {
		Log.d(TAG, "into replaceFragment mStep=" + mStep);
		/*
		 * 根据mStep做相应的替换 mStep
		 */
		switch (mStep) {
		case 0:
			/*
			 * 把输入用户名密码的fragment用于替换R.id.frameLayout 并且加入fragment栈
			 */
			if (pf == null) {
				pf = new PasswordFragment();
			}
			FragmentTransaction transaction = mFragmentManager
					.beginTransaction();
			transaction.replace(R.id.frameLayout, pf);
			transaction.addToBackStack(null);
			transaction.commit();
			break;
		case 1:
			/*
			 * 把输入名字和地址的fragment用于替换R.id.frameLayout 并且加入fragment栈
			 */
			if (mf == null) {
				mf = new MobileFagment();
				/*
				 * 设置mobile的自定义listner为this，检查文字里面不能有9
				 */
			}

			FragmentTransaction secondTransaction = mFragmentManager
					.beginTransaction();
			secondTransaction.replace(R.id.frameLayout, mf);
			secondTransaction.addToBackStack(null);
			secondTransaction.commit();
			break;
		case 2:
			StringBuffer sb = new StringBuffer();
			/*
			 * 打印注册的结果
			 */
			if (!mIsWarning) {
				sb.append("用户名:" + pf.getUsername().getText().toString().trim());
				sb.append("\n");
				sb.append("密    码:"
						+ pf.getPassword().getText().toString().trim());
				sb.append("\n");
				sb.append("手机号:" + mf.getMobile().getText().toString().trim());
				sb.append("\n");
				sb.append("地    址:"
						+ mf.getAddress().getText().toString().trim());
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			} else {
				sb.append("请先处理告警问题再提交!!!");
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}

	/*
	 * 根据getBackStackEntryCount的变化来更新registerBtn显示的文字和mStep
	 * 
	 * @see android.support.v4.app.FragmentManager.OnBackStackChangedListener#
	 * onBackStackChanged()
	 */
	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		int count = mFragmentManager.getBackStackEntryCount();
		if (count == 0) {
			registerBtn.setText("注册");
			mStep = 0;
		} else if (count == 1) {
			registerBtn.setText("下一步");
			mStep = 1;
		} else if (count == 2) {
			registerBtn.setText("提交");
			mStep = 2;
		}
	}
}