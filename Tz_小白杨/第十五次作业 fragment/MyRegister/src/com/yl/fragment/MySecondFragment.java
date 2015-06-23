package com.yl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yl.Register.R;

public class MySecondFragment extends Fragment {
	private EditText name;
	private EditText age;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.second_fragement, null);
		name = (EditText) view.findViewById(R.id.name);
		age = (EditText) view.findViewById(R.id.age);
		return view;
	}

	public EditText getName() {
		return name;
	}

	public void setName(EditText name) {
		this.name = name;
	}

	public EditText getAge() {
		return age;
	}

	public void setAge(EditText age) {
		this.age = age;
	}
}
