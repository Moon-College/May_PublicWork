package com.example.tz_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FirstRegisterFragment extends Fragment{
	private EditText userName, password;

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
		View view = inflater.inflate(R.layout.regist_first_layout, null);
		userName = (EditText) view.findViewById(R.id.et_regist_username);
		password = (EditText) view.findViewById(R.id.et_regist_password);
		return view;
	}
	
}
