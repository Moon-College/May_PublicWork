package com.vincen.fragment;

import com.vincen.fragment_replace.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SecondFragment extends Fragment{
	
	private EditText address,email;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.second, null);
		
		address = (EditText) view.findViewById(R.id.address);
		email = (EditText) view.findViewById(R.id.email);
		return view ;
	}

	public EditText getAddress() {
		return address;
	}

	public void setAddress(EditText address) {
		this.address = address;
	}

	public EditText getEmail() {
		return email;
	}

	public void setEmail(EditText email) {
		this.email = email;
	}
	
	
}
