package com.example.tz_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SecondRegisterFragment extends Fragment{
	private EditText name,address;
	private MainActivity activity;

	public EditText getName() {
		return name;
	}

	public void setName(EditText name) {
		this.name = name;
	}

	public EditText getAddress() {
		return address;
	}

	public void setAddress(EditText address) {
		this.address = address;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		activity = (MainActivity) this.getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.regist_second_layout, null);
		name = (EditText) view.findViewById(R.id.et_regist_name);
		address = (EditText) view.findViewById(R.id.et_regist_addr);
		return view;
	}
	
}
