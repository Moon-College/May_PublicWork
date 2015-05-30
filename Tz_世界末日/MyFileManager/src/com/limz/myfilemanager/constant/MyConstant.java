package com.limz.myfilemanager.constant;

import android.os.Environment;

public class MyConstant {
	public static final String path = 
			Environment.getExternalStorageDirectory().getAbsolutePath().toString();
}
