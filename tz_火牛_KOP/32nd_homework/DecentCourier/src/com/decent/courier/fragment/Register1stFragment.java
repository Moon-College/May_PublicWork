package com.decent.courier.fragment;

import com.decent.courier.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Register1stFragment extends Fragment {
	private EditText et_regist_mobile;
	private EditText et_regist_name;
	private EditText et_pwd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.register_fragment_one, null);
		et_regist_mobile = (EditText)view.findViewById(R.id.et_regist_mobile);
		et_regist_name = (EditText)view.findViewById(R.id.et_regist_name);
		et_pwd = (EditText)view.findViewById(R.id.et_pwd);
		// TODO Auto-generated method stub
		return view;
	}

	public EditText getEt_regist_mobile() {
		return et_regist_mobile;
	}

	public void setEt_regist_mobile(EditText et_regist_mobile) {
		this.et_regist_mobile = et_regist_mobile;
	}

	public EditText getEt_regist_name() {
		return et_regist_name;
	}

	public void setEt_regist_name(EditText et_regist_name) {
		this.et_regist_name = et_regist_name;
	}

	public EditText getEt_pwd() {
		return et_pwd;
	}

	public void setEt_pwd(EditText et_pwd) {
		this.et_pwd = et_pwd;
	}
	
	
}
