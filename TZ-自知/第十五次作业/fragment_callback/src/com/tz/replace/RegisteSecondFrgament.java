package com.tz.replace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tz.replace.R;

public class RegisteSecondFrgament extends Fragment{
	private EditText name,addr;
	private MainActivity activity;
	public EditText getName() {
		return name;
	}

	public void setName(EditText name) {
		this.name = name;
	}

	public EditText getAddr() {
		return addr;
	}

	public void setAddr(EditText addr) {
		this.addr = addr;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		activity  = (MainActivity) this.getActivity();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.regist_second_layout, null);
		name = (EditText) view.findViewById(R.id.et_name);
		addr = (EditText) view.findViewById(R.id.et_address);
		return view;
	}
}
