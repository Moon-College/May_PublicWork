package com.zjm.replace;

import com.zjm.replace.fragment.RegisterSecondFragment;
import com.zjm.replace.fragment.RegisterFirstFragment;
import com.zjm.view.listener.OnInputTextListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener, OnBackStackChangedListener,OnInputTextListener {
	
	int step;
	Button btn;
	RegisterFirstFragment registerFirstFragment;
	RegisterSecondFragment registerSecondFragment;
	private FragmentManager fm;
	private LinearLayout mLinearLayout;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		registerFirstFragment = new RegisterFirstFragment();
		registerFirstFragment.setOnInputTextListener(this);
		fm = this.getSupportFragmentManager();
		fm.addOnBackStackChangedListener(this);
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.replace(R.id.framelayout, registerFirstFragment);
		beginTransaction.commit();
		
		tv = new TextView(MainActivity.this);
		tv.setVisibility(View.GONE);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tv.setLayoutParams(params);
		tv.setText("手机号格式不正确!");
		mLinearLayout.addView(tv);
		setContentView(mLinearLayout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			if(step == 0){
				if(null == registerSecondFragment){
					registerSecondFragment = new RegisterSecondFragment();
				}
				replace(registerSecondFragment);
			}else if(step == 1){
				StringBuffer sb = new StringBuffer();
				sb.append("username:"+registerFirstFragment.getEt_regist_username().getText().toString()+"\n")
				.append("password:"+registerFirstFragment.getEt_regist_password().getText().toString()+"\n")
				.append("name:"+registerSecondFragment.getEt_regist_name().getText().toString()+"\n")
				.append("addr:"+registerSecondFragment.getEt_regist_addr().getText().toString());
				Toast.makeText(MainActivity.this, sb.toString(), 1000).show();
			}
			
			break;

		default:
			break;
		}
	}

	/**
	 * Fragment的替换
	 *@author 邹继明
	 *@date 2015-6-22下午10:48:20
	 *@param fragment
	 *@return void
	 */
	private void replace(Fragment fragment) {
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.addToBackStack("second");
		//添加该fragment到fragment栈
		beginTransaction.replace(R.id.framelayout, fragment);
		beginTransaction.commit();
	}

	@Override
	public void onBackStackChanged() {
		int count = fm.getBackStackEntryCount();//fragment栈中fragment的数量
		if(count == 0){
			step =0;
			btn.setText("下一步");
		}else{
			step = 1;
			btn.setText("注册");
		}
	}

	@Override
	public void onCompleted(boolean isMobile) {
		if(!isMobile){
			tv.setVisibility(View.VISIBLE);
		}else{
			tv.setVisibility(View.GONE);
		}
	}
	
}
