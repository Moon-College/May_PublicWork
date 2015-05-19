package com.first.work.logjar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liyawei 获取APP的日志信息
 */
public class GetAppLogInformation {

	public static List<String> GetLogInformation(int log_level, String tag)
			throws IOException {
		List<String> list = new ArrayList<String>();
		List<String> log_str = new ArrayList<String>();

		list.add("logcat");
		list.add("-d");
		list.add("-s");
		/**
		 * 出了获取指定的tag外，获取系统的LOG都需要在条件之前加 *： 才能正常获取
		 */
		if (tag == null) {
			switch (log_level) {
			case 2:
				list.add("*:V");
				break;
			case 3:
				list.add("*:D");
				break;
			case 4:
				list.add("*:I");
				break;
			case 5:
				list.add("*:W");
				break;
			case 6:
				list.add("*:E");
				break;
			default:
				list.add("*:W,E");
				break;
			}

		} else {
			list.add(tag);
		}
		Process exec = Runtime.getRuntime().exec(
				list.toArray(new String[list.size()]));
		InputStream inputStream = exec.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		while ((str = br.readLine()) != null) {
			log_str.add(str);
		}
		return log_str;
	}

}
