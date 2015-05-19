package com.binbin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.util.Log;

public class IoUtils {
	private static final String TAG = IoUtils.class.getSimpleName();
	
	public static String read(InputStream inputStream){
		if(null==inputStream){
			return null;
		}
		StringBuffer sb=new StringBuffer();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        try {
			while((str = bufferedReader.readLine())!=null){
			    sb.append(str);
			    sb.append("\n");
			}
		} catch (IOException e) {
			LogUtils.printLog(Log.ERROR, TAG, e.toString());
		}
        return sb.toString();
	}
	
}
