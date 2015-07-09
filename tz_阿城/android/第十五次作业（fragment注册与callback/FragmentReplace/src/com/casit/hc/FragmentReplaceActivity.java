package com.casit.hc;

import com.casit.hc.fragment.RegisterFirstFragment;
import com.casit.hc.fragment.RegisterSecondFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FragmentReplaceActivity extends FragmentActivity implements OnClickListener {
    /** Called when the activity is first created. */
	private Button button;
	private RegisterFirstFragment registerFirstFragment;
	private FragmentManager fm;
	private RegisterSecondFragment registerSecondFragment;
	private int step;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.bt);
        button.setOnClickListener(this);
        fm = this.getSupportFragmentManager();
		registerSecondFragment = new RegisterSecondFragment();		
        registerFirstFragment = new RegisterFirstFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        //直接将FIRSTFRAGMENT弄进来
        fm.addOnBackStackChangedListener(new MybackStackListener());
        transaction.replace(R.id.framelayout, registerFirstFragment);
        transaction.commit();
        step=0;
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(step==0){
			repalaceFragment();		
		}else if(step==1){
		      showToast();
		}

	}
	private void showToast() {
		StringBuffer sb = new StringBuffer();
		sb.append(registerFirstFragment.getUserAccount().getText().toString().trim()+"\n");
		sb.append(registerFirstFragment.getUserAccount().getText().toString().trim()+"\n");
		sb.append(registerSecondFragment.getName().getText().toString().trim()+"\n");
		sb.append(registerSecondFragment.getAdrr().getText().toString().trim()+"\n");
		Toast.makeText(this, sb.toString(), 3000).show();
		// TODO Auto-generated method stub
		
	}
	private void repalaceFragment() {

		FragmentTransaction transactionsecond = fm.beginTransaction();
		transactionsecond.replace(R.id.framelayout,registerSecondFragment );
		transactionsecond.addToBackStack("second");
		transactionsecond.commit();
		step = 1;
		// TODO Auto-generated method stub
		
	}


	private class MybackStackListener implements OnBackStackChangedListener{

		public void onBackStackChanged() {
			// TODO Auto-generated method stub
			int backstackcount = fm.getBackStackEntryCount();
	/*        for(int i = 0; i< backstackcount; i++){
	        	BackStackEntry backStackEntry = fm.getBackStackEntryAt(i);
	        }*/
			if(backstackcount==0){
	        	step = 0;
	        	button.setText("下一步");
	        }else{
	        	step = 1;
	        	button.setText("注册");
	        }
		}
		
	}
}