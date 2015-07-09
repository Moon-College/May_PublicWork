package com.lx.refragment;

import com.lx.listener.MyListener;
import com.lx.main.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegistSecondFragment  extends Fragment{
	private MyListener  listener;
	private EditText edt1, edt2;

    //在这里的从分理解Fragment的生命周期 才有利于我们对知识的运用
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 View  view =inflater.inflate(R.layout.regist_second_layout, null);
	        change(view);
		return view;
	}
	
	private void change(View view) {
		 edt1 = (EditText) view.findViewById(R.id.et_names);
         edt2 = (EditText) view.findViewById(R.id.te_user_adress);
         String numner = edt1.getText().toString().trim();
         if(numner.startsWith("9")){
        	 listener.show(view);
         }
        	 
       
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	 
	public EditText getEdt1() {
		return edt1;
	}

	public void setEdt1(EditText edt1) {
		this.edt1 = edt1;
	}

	public EditText getEdt2() {
		return edt2;
	}

	public void setEdt2(EditText edt2) {
		this.edt2 = edt2;
	}
	

}
