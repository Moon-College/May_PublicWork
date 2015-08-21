package com.decent.courier.activity;

import android.widget.TextView;

import com.decent.courier.common.BaseActivity;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.MyDataUtils;

public class BusinessActivity extends BaseActivity {

	private TextView addrTv;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.bussiness);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		addrTv = (TextView) findViewById(R.id.addrTv);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		addrTv.setText((String) MyDataUtils.getData(this, DecentConstants.LOC,
				DecentConstants.ADDR, String.class));
	}

}
