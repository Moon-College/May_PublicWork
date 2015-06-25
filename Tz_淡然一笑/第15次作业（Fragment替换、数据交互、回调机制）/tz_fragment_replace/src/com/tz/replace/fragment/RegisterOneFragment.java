package com.tz.replace.fragment;

import com.tz.fragmentreplace.R;
import com.tz.replace.listener.OnTextChangListener;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterOneFragment extends Fragment {
	
	/**
	 * 账号
	 */
	private EditText account;
	/**
	 * 密码
	 */
	private EditText password;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	//注意one_fragment视图是activity的局部，在activity视图创建完成了以后，才会执行onCreateView
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.one_fragment, null);
		account = (EditText) view.findViewById(R.id.et_account);
		password = (EditText) view.findViewById(R.id.et_password);
		return view;
	}
	
	public EditText getAccount() {
		return account;
	}

	public void setAccount(EditText account) {
		this.account = account;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}

}
