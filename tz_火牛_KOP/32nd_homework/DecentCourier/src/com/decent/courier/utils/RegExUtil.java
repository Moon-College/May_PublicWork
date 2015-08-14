package com.decent.courier.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {
	/**
	 * �ж��ַ����Ƿ�Ϊ�ջ���""
	 * 
	 * @param str
	 *            �ַ���
	 * @return �Ƿ�Ϊ�ջ���""
	 */
	public static boolean isStringEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * check�ֻ����Ƿ���ȷ��1��ͷ��11λ���֣�
	 * 
	 * @param mobile
	 *            �ֻ���
	 * @return �Ƿ���ȷ
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
	 * check�û�ע��ĵ�¼��2-10��Ӣ���ַ������»���
	 * 
	 * @param userName
	 *            �û���¼���ַ���
	 * @return �Ƿ���ȷ
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
	 * check�û�ע��������Ƿ���>6 <20��Ӣ���ַ������»���
	 * @param password ����
	 * @return �Ƿ���ȷ
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
	 * check ע��ʱ��age�Ƿ���ȷ(0~100)
	 * @param age
	 * @return �Ƿ���ȷ
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
	 * check ע��ʱ���û������Ƿ���ȷ(2-10,����������)
	 * @param name ����
	 * @return �Ƿ���ȷ
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
