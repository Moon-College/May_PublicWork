package com.junwen.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputUtil {
	/**
	 * 根据输入流，返回字符串
	 * @param is
	 * @return
	 */
	public static String getString(InputStream is)
	{
		InputStreamReader  reader = new InputStreamReader(is) ;
		BufferedReader  br = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer();
		String str = null;
		try {
			while((str = br.readLine()) !=null)
			{
				sb.append(str);
				sb.append("\n");
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
