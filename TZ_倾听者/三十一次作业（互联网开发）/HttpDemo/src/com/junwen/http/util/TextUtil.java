package com.junwen.http.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextUtil {

	public static String getString(InputStream inputStream){
		String result = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while((line = reader.readLine())!=null){
				result += line;
			}
			System.out.println(result+"½á¹û");
			return result;
		} catch (IOException e) {
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
