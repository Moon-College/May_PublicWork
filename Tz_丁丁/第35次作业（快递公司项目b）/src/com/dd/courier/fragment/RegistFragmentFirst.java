package com.dd.courier.fragment;


import com.dd.ays_dd_courier.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegistFragmentFirst extends Fragment{
	private EditText phoneNum;
	private EditText userName;
	private EditText password;
	
	public EditText getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(EditText phoneNum) {
		this.phoneNum = phoneNum;
	}

	public EditText getUserName() {
		return userName;
	}

	public void setUserName(EditText userName) {
		this.userName = userName;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.registe_fragment_one, null);
		phoneNum = (EditText) view.findViewById(R.id.et_regist_mobile);
		userName = (EditText) view.findViewById(R.id.et_regist_name);
		password = (EditText) view.findViewById(R.id.et_pwd);
		return view;
	}
}
