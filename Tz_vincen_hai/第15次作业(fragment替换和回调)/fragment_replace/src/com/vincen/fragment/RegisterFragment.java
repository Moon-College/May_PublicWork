package com.vincen.fragment;

import com.vincen.callback.myinterface.MyInterface;
import com.vincen.fragment_replace.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterFragment extends Fragment{
	
	private MyInterface myInterface;
	private EditText username;
	private EditText password;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.register, null);
		username = (EditText) view.findViewById(R.id.username);
		password = (EditText) view.findViewById(R.id.password);
		username.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				Log.i("INFO", "aaaaaaaaaa");
				if("9".equals(arg0.toString())){
					Log.i("INFO", "99999");
					MyInterface myInterface = (MyInterface) getActivity();
					myInterface.onHitListener(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
		return view;
	}
	
	
	
	
	

	public EditText getUsername() {
		return username;
	}

	public void setUsername(EditText username) {
		this.username = username;
	}

	public EditText getPassword() {
		return password;
	}

	public void setPassword(EditText password) {
		this.password = password;
	}

	public MyInterface getMyInterface() {
		return myInterface;
	}

	public void setMyInterface(MyInterface myInterface) {
		this.myInterface = myInterface;
	}




	
	
	
}
