package com.dd.fragmentandcallback;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

@SuppressLint("NewApi")
public class RegisteSecondFragment extends Fragment {
	private EditText name,addr;
	private MainActivity activity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.regist_second_layout, null);
		name = (EditText) view.findViewById(R.id.et_regist_name);
		addr = (EditText) view.findViewById(R.id.et_regist_addr);
		return view;
	}
}
