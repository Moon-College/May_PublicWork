package com.yl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yl.Register.R;
import com.yl.myinterface.OnMyKeyDownListener;

public class MyFirstFragment extends Fragment {
	private EditText user;
	private EditText pass;
	private OnMyKeyDownListener onMyKeyDownListener;

	public OnMyKeyDownListener getOnMyKeyDownListener() {
		return onMyKeyDownListener;
	}

	public void setOnMyKeyDownListener(OnMyKeyDownListener onMyKeyDownListener) {
		this.onMyKeyDownListener = onMyKeyDownListener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.first_fragement, null);
		user = (EditText) view.findViewById(R.id.user);
		user.addTextChangedListener(new MyKeyDown());
		pass = (EditText) view.findViewById(R.id.pass);
		return view;
	}

	public EditText getUser() {
		return user;
	}

	public void setUser(EditText user) {
		this.user = user;
	}

	public EditText getPass() {
		return pass;
	}

	public void setPass(EditText pass) {
		this.pass = pass;
	}

	private class MyKeyDown implements TextWatcher {

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String phone = user.getText().toString();
			if (phone.contains("9")) {
				if (onMyKeyDownListener != null) {
					onMyKeyDownListener.onMyKeyDown(user);
				}
			}
			
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

	}
}
