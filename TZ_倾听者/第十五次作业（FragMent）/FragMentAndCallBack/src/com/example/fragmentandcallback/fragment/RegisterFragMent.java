package com.example.fragmentandcallback.fragment;

import com.example.fragmentandcallback.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterFragMent extends Fragment{

	private EditText user;
	private EditText pass;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register, null);
		user = (EditText) view.findViewById(R.id.user);
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
	
}
