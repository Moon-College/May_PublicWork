package com.tzmichael.activity;

import java.util.ArrayList;
import java.util.List;

import com.tzmichael.adapter.MyVPAdapter;
import com.tzmichael.custom.MyCombinViewTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TestCombinViewActivity extends FragmentActivity {

	private MyCombinViewTest mcvt;
	private List<String> ll_tt;
	
	private MyVPAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter=new MyVPAdapter(getSupportFragmentManager());
		initData();
		mcvt=new MyCombinViewTest(this,ll_tt, adapter);
		setContentView(mcvt);
	}

	private void initData() {
		ll_tt=new ArrayList<String>();
		for(int i=0;i<7;i++){
			ll_tt.add("栏目"+(i+1));
		}
	}
	
}
