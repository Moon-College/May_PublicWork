package com.zjm.replace.fragment;

import com.zjm.replace.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class RegisterSecondFragment extends Fragment {
	private View root;
	private EditText et_regist_name,et_regist_addr;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root =  inflater.inflate(R.layout.regist_second_layout, null);
		et_regist_name = (EditText) root.findViewById(R.id.et_regist_name);
		et_regist_addr = (EditText) root.findViewById(R.id.et_regist_addr);
		return root;
	}

	public EditText getEt_regist_name() {
		return et_regist_name;
	}

	public void setEt_regist_name(EditText et_regist_name) {
		this.et_regist_name = et_regist_name;
	}

	public EditText getEt_regist_addr() {
		return et_regist_addr;
	}

	public void setEt_regist_addr(EditText et_regist_addr) {
		this.et_regist_addr = et_regist_addr;
	}
	
	

}
