package com.casit.hc.fragment;

import com.casit.hc.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterSecondFragment extends Fragment {

	public EditText getName() {
		return name;
	}
	public void setName(EditText name) {
		this.name = name;
	}
	public EditText getAdrr() {
		return adrr;
	}
	public void setAdrr(EditText adrr) {
		this.adrr = adrr;
	}
	private EditText name,adrr;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.regist_second_layout, null);
		name = ((EditText) view.findViewById(R.id.et_regist_name));
		adrr = (EditText) view.findViewById(R.id.et_regist_addr);
		return view;
	}
	
}
