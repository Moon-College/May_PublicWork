package com.tz.michael.fragment;

import com.tz.michael.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterStepTwoFragment extends Fragment {

	private EditText et_position,et_name;
	
	public EditText getEt_position() {
		return et_position;
	}

	public void setEt_position(EditText et_position) {
		this.et_position = et_position;
	}

	public EditText getEt_name() {
		return et_name;
	}

	public void setEt_name(EditText et_name) {
		this.et_name = et_name;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fg_two, container, false);
		et_position=(EditText) v.findViewById(R.id.et_position);
		et_name=(EditText) v.findViewById(R.id.et_name);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
}
