package com.chris.fragmentreplacedemo.fragment;

import com.chris.fragmentreplacedemo.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SecondFragment extends Fragment
{
	private EditText name, address;

	public EditText getName()
	{
		return name;
	}

	public void setName(EditText name)
	{
		this.name = name;
	}

	public EditText getAddress()
	{
		return address;
	}

	public void setAddress(EditText address)
	{
		this.address = address;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.regist_second_layout, null);
		name = (EditText) view.findViewById(R.id.et_regist_name);
		address = (EditText) view.findViewById(R.id.et_regist_addr);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}
