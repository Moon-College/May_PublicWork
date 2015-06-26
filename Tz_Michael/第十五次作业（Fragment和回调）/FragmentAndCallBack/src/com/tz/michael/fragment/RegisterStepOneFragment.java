package com.tz.michael.fragment;

import com.tz.michael.activity.FragmentAndCallBackActivity;
import com.tz.michael.activity.R;
import com.tz.michael.custominterface.TextChangeListenner;
import com.tz.michael.customview.MyEditText;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterStepOneFragment extends Fragment implements TextChangeListenner{

	private MyEditText et_phone;
	private EditText et_password;
	private FragmentAndCallBackActivity facb;
	

	public MyEditText getEt_phone() {
		return et_phone;
	}

	public void setEt_phone(MyEditText et_phone) {
		this.et_phone = et_phone;
	}

	public EditText getEt_password() {
		return et_password;
	}

	public void setEt_password(EditText et_password) {
		this.et_password = et_password;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fg_one, container, false);
		et_phone=(MyEditText) v.findViewById(R.id.et_phone);
		et_password=(EditText) v.findViewById(R.id.et_password);
		et_phone.setTcl(this);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		facb=(FragmentAndCallBackActivity) getActivity();
	}

	public void onText(String str) {
		if(str.length()!=0&&Integer.valueOf(str)==9){
			Toast.makeText(getActivity(), "手机号不合法", 0).show();
			TextView tv=new TextView(getActivity());
			tv.setText("手机号不合法");
			facb.getLay_toast().addView(tv);
		}
	}
	
}
