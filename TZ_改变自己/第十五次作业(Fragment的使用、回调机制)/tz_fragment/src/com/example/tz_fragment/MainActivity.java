package com.example.tz_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private Button button;
	private FirstRegisterFragment firstFragment;
	private SecondRegisterFragment secondFragment;
	private FragmentManager fm;
	private int step;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("wdj","MainActivity onCreate");
		setContentView(R.layout.main);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		
		fm = this.getSupportFragmentManager();
		fm.addOnBackStackChangedListener(new MyBackStackListener());
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(step == 0) {
			replaceFragment();
		} else if(step == 1) {
			showToast();
		}
	}
	
	/**
	 * 
	 */
	private void showToast() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("userName:"+firstFragment.getUserName().getText().toString().trim()+"\n");
		sb.append("password:"+firstFragment.getPassword().getText().toString().trim()+"\n");
		sb.append("name:"+ secondFragment.getName().getText().toString().trim()+"\n");
		sb.append("addr:"+ secondFragment.getAddress().getText().toString().trim());
		Toast.makeText(this, sb.toString(), 1000).show();
	}
	
	private void replaceFragment() {
		if(secondFragment == null) {
			secondFragment = new SecondRegisterFragment();
		}
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.framelayout, secondFragment);
		transaction.commit();
	}
	
	private class MyBackStackListener implements OnBackStackChangedListener {

		@Override
		public void onBackStackChanged() {
			//获取里面栈的数量
			int backStackEntryCount = fm.getBackStackEntryCount();
			for(int i = 0; i < backStackEntryCount; i++) {
				BackStackEntry backStackEntry = fm.getBackStackEntryAt(i);
			}
			
			if(backStackEntryCount == 0) {
				step = 0;
				button.setText("下一步");
			}else{
				step = 1;
				button.setText("注册");
			}
		}
	
	
	};
	
	


}
