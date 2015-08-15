package com.hq.ays.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hq.ays.activity.R;

public class RegistFirstFragment extends Fragment {
	private EditText etRegistMobile,etUserName,etPassword;
	
	public EditText getEtRegistMobile() {
		return etRegistMobile;
	}

	public void setEtRegistMobile(EditText etRegistMobile) {
		this.etRegistMobile = etRegistMobile;
	}

	public EditText getEtUserName() {
		return etUserName;
	}

	public void setEtUserName(EditText etUserName) {
		this.etUserName = etUserName;
	}

	public EditText getEtPassword() {
		return etPassword;
	}

	public void setEtPassword(EditText etPassword) {
		this.etPassword = etPassword;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.registe_fragment_one, null);
		etRegistMobile = (EditText) v.findViewById(R.id.et_regist_mobile);
		etUserName = (EditText) v.findViewById(R.id.et_regist_name);
		etPassword = (EditText) v.findViewById(R.id.et_pwd);
		return v;
	}
}
