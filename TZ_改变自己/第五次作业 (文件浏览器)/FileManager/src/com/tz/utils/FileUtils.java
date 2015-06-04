package com.tz.utils;

import android.os.Environment;
import android.widget.Toast;

public class FileUtils {
	public FileUtils() {
		
	}
	
	public static boolean hasSDCard() {
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static String getSDcardRoot() {
		if(hasSDCard()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			return "no sdcard";
		}
		
	}
}
