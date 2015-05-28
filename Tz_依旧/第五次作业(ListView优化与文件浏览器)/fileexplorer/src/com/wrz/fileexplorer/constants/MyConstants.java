package com.wrz.fileexplorer.constants;

import android.os.Environment;

public class MyConstants {
	
	// 获取手机SD卡根路径
	public static final String ROOT = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
}
