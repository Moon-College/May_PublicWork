package com.tz.replace.fragment;

import com.tz.fragmentreplace.R;
import com.tz.replace.listener.OnTextChangListener;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterTwoFragment extends Fragment implements TextWatcher {

	/**
	 * 真实姓名
	 */
	private EditText realName;
	/**
	 * 手机号
	 */
	private EditText phoneNo;

	private OnTextChangListener onTextChangListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.two_fragment, null);
		realName = (EditText) view.findViewById(R.id.et_realName);
		phoneNo = (EditText) view.findViewById(R.id.et_phone);

		phoneNo.addTextChangedListener(this);
		return view;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String phone = phoneNo.getText().toString();
		if (phone.contains("9")) {
			if (onTextChangListener != null) {
				onTextChangListener.onTextChang(phoneNo);
			}
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
	}
	
	public EditText getRealName() {
		return realName;
	}

	public void setRealName(EditText realName) {
		this.realName = realName;
	}

	public EditText getphoneNo() {
		return phoneNo;
	}

	public void setPhone(EditText phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public EditText getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(EditText phoneNo) {
		this.phoneNo = phoneNo;
	}
	

	public OnTextChangListener getOnTextChangListener() {
		return onTextChangListener;
	}

	public void setOnTextChangListener(OnTextChangListener onTextChangListener) {
		this.onTextChangListener = onTextChangListener;
	}
	
}
