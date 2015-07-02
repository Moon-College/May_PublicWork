package com.lx.refragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lx.main.MainActivity;
import com.lx.main.R;

public class RegistFirstFragment extends Fragment{
	private EditText edt1, edt2;
    private MainActivity  main;
	
	private void oncreate() {
		// TODO Auto-generated method stub

	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//加载xml当中的布局
	    View view = inflater.inflate(R.layout.regist_first_layout, null);
	    edt1 = (EditText) view.findViewById(R.id.et_username);
	    edt2 = (EditText) view.findViewById(R.id.te_user_password);
	    
	     
		return view;
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
