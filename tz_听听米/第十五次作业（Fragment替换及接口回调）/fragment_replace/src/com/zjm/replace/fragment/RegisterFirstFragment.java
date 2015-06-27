package com.zjm.replace.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zjm.replace.MainActivity;
import com.zjm.replace.R;
import com.zjm.view.listener.OnInputTextListener;
import com.zjm.view.utils.StringUtil;


public class RegisterFirstFragment extends Fragment implements TextWatcher{
	private OnInputTextListener onInputTextListener;

	private View root;
	private EditText et_regist_username;
	private EditText et_regist_password;
	private MainActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity = (MainActivity) getActivity();
		root = inflater.inflate(R.layout.regist_first_layout, null);
		et_regist_username = (EditText) root.findViewById(R.id.et_regist_username);
		et_regist_username.addTextChangedListener(this);
		et_regist_password = (EditText) root.findViewById(R.id.et_regist_password);
		return root;
	}
	

	public OnInputTextListener getOnInputTextListener() {
		return onInputTextListener;
	}

	public void setOnInputTextListener(OnInputTextListener onInputTextListener) {
		this.onInputTextListener = onInputTextListener;
	}

	public EditText getEt_regist_username() {
		return et_regist_username;
	}


	public void setEt_regist_username(EditText et_regist_username) {
		this.et_regist_username = et_regist_username;
	}


	public EditText getEt_regist_password() {
		return et_regist_password;
	}

	public void setEt_regist_password(EditText et_regist_password) {
		this.et_regist_password = et_regist_password;
	}


	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}


	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String account = et_regist_username.getText().toString();
		onInputTextListener.onCompleted(StringUtil.isPhoneNumber(account));
	}


	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}
	
	
}
