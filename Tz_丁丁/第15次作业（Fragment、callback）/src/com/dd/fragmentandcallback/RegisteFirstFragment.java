package com.dd.fragmentandcallback;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

@SuppressLint("NewApi")
public class RegisteFirstFragment extends Fragment{
	private EditText userName,password;	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.regist_first_layout, null);
		userName = (EditText) view.findViewById(R.id.et_regist_username);
		password = (EditText) view.findViewById(R.id.et_regist_password);
		return view;
	}
}
