package com.junwen.activity;

import com.junwen.util.IocForAnnotation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;

public class BaseActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IocForAnnotation.FindViewByID(this);
	}

}
