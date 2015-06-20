package com.deccent.decentfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.decent.decentfragment.view.MobileEditText;
import com.decent.decentfragment.view.OnNumberChangeListner;

public class MobileFagment extends Fragment implements OnNumberChangeListner {

	private static final String warningText = "手机号里面有9，不能注册";
	private MobileEditText mobile;
	private EditText address;
	private DecentFragmentActivity mDFActivity;

	public MobileEditText getMobile() {
		return mobile;
	}

	public void setMobile(MobileEditText mobile) {
		this.mobile = mobile;
	}

	public EditText getAddress() {
		return address;
	}

	public void setAddress(EditText address) {
		this.address = address;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.mobile, container, false);
		mobile = (MobileEditText) view.findViewById(R.id.mobile);
		address = (EditText) view.findViewById(R.id.address);
		mobile.setOnNumberChangeListner(this);
		return view;
	}

	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mDFActivity = (DecentFragmentActivity)activity;
	}

	@Override
	public void onNumberChange(View v) {
		// TODO Auto-generated method stub
		MobileEditText met = (MobileEditText)v;
		if(met.getText().toString().trim().indexOf("9")!=-1)
		{
			//Toast.makeText(getActivity(), "手机号里面有9，不能注册", 10).show();
			mDFActivity.setWarningState(true, warningText);
		}
		else
		{
			mDFActivity.setWarningState(false, "");
		}
	}

}
