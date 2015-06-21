package com.example.fragmentandcallback.fragment;

import com.example.fragmentandcallback.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class LoginFragMent extends Fragment implements TextWatcher  {

	private EditText name;
	private EditText phonenumber;
	private OnTextChangListener onTextListener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register, null);
		name = (EditText) view.findViewById(R.id.user);
		phonenumber = (EditText) view.findViewById(R.id.pass);
		name.setHint("请输入姓名");
		phonenumber.setHint("请输入手机号");
		phonenumber.addTextChangedListener(this);
		return view ;
	}

	public EditText getName() {
		return name;
	}

	public void setName(EditText name) {
		this.name = name;
	}

	public EditText getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(EditText phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String text = phonenumber.getText().toString();
		if(text.contains("9")){
			if(onTextListener != null){
				onTextListener.onTextChanged(phonenumber);
			}
		}
	}
	@Override
	public void afterTextChanged(Editable s) {
	}
	public void setOnTextListener(OnTextChangListener onTextListener) {
		this.onTextListener = onTextListener;
	}
}
