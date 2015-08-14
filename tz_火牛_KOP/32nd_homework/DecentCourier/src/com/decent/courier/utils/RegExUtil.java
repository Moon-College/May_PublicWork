package com.decent.courier.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {
	/**
	 * 判断字符串是否为空或则""
	 * 
	 * @param str
	 *            字符串
	 * @return 是否为空或者""
	 */
	public static boolean isStringEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * check手机号是否正确（1开头的11位数字）
	 * 
	 * @param mobile
	 *            手机号
	 * @return 是否正确
	 */
	public static boolean checkMobile(String mobile) {
		if (isStringEmpty(mobile)) {
			return false;
		}
		Pattern p = Pattern.compile("^1[0-9]{10}");
		Matcher m = p.matcher(mobile);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * check用户注册的登录名2-10个英文字符或者下划线
	 * 
	 * @param userName
	 *            用户登录名字符串
	 * @return 是否正确
	 */
	public static boolean chekUserName(String userName) {
		if (isStringEmpty(userName)) {
			return false;
		}
		Pattern p = Pattern.compile("[a-z0-9A-Z_]{2,10}");
		Matcher m = p.matcher(userName);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * check用户注册的密码是否是>6 <20个英文字符或者下划线
	 * @param password 密码
	 * @return 是否正确
	 */
	public static boolean checkPassword(String password) {
		if(isStringEmpty(password)){
			return false;
		}
		Pattern p = Pattern.compile("[a-z0-9A-Z_]{6,20}");
		Matcher m = p.matcher(password);
		if (!m.matches()) {
			return false;
		}		
		return true;
	}

	/**
	 * check 注册时候age是否正确(0~100)
	 * @param age
	 * @return 是否正确
	 */
	public static boolean checkAge(String age) {
		if(isStringEmpty(age)){
			return false;
		}
		Pattern p = Pattern.compile("^([0~9]|[0-9]{2}|100])$");
		Matcher m = p.matcher(age);
		if (!m.matches()) {
			return false;
		}		
		return true;
	}

	/**
	 * check 注册时候用户姓名是否正确(2-10,可以有中文)
	 * @param name 姓名
	 * @return 是否正确
	 */
	public static boolean checkName(String name) {
		if(isStringEmpty(name)){
			return false;
		}
		Pattern p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,10}$");
		Matcher m = p.matcher(name);
		if (!m.matches()) {
			return false;
		}		
		return true;
	}
	
}
