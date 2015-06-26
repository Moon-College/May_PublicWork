package com.tz.michael.activity;

import com.tz.michael.fragment.RegisterStepOneFragment;
import com.tz.michael.fragment.RegisterStepTwoFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FragmentAndCallBackActivity extends FragmentActivity implements OnClickListener, OnBackStackChangedListener {
	
	private Button btn;
	private LinearLayout lay_toast;
	public LinearLayout getLay_toast() {
		return lay_toast;
	}

	private FragmentManager fm;
	private RegisterStepOneFragment fg1;
	private RegisterStepTwoFragment fg2;
	private int flag;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        lay_toast=(LinearLayout) findViewById(R.id.lay_toast);
        fm=getSupportFragmentManager();
        fm.addOnBackStackChangedListener(this);
        FragmentTransaction ft=fm.beginTransaction();
        fg1=new RegisterStepOneFragment();
        ft.replace(R.id.fg, fg1);
        ft.commit();
    }

	public void onClick(View v) {
		if(flag==0){
			replaceFragment();
		}else{
			Toast.makeText(FragmentAndCallBackActivity.this, getInfoContents(), 0).show();
		}
	}
	
	public String getInfoContents(){
		StringBuffer sb=new StringBuffer();
		sb.append(fg1.getEt_phone().getText().toString().trim()+"\n")
		  .append(fg1.getEt_password().getText().toString().trim()+"\n")
		  .append(fg2.getEt_name().getText().toString().trim()+"\n")
		  .append(fg2.getEt_position().getText().toString().trim());
		return sb.toString();
	}
	
	public void replaceFragment(){
		fg2=new RegisterStepTwoFragment();
		FragmentTransaction ft=fm.beginTransaction();
		ft.replace(R.id.fg,fg2);
		ft.addToBackStack("fg2");
		ft.commit();
	}

	public void onBackStackChanged() {
		if(fm.getBackStackEntryCount()==0){
			btn.setText("下一步");
			flag=0;
		}else{
			btn.setText("完成注册");
			flag=1;
		}
	}
	
}