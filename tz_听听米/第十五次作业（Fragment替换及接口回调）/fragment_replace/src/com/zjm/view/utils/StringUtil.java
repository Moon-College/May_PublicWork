package com.zjm.view.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 判断电话
	 *@author 邹继明
	 *@date 2015-6-23上午12:41:54
	 *@param phonenumber
	 *@return
	 *@return boolean
	 */
    public static boolean isTelephone(String phonenumber) {
        String phone = "0\\d{2,3}-\\d{7,8}";
        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(phonenumber);
        return m.matches();
    }
	
	/**
	 * 是否是手机号
	 *@author 邹继明
	 *@date 2015-6-23上午12:06:15
	 *@param input
	 *@return
	 *@return boolean
	 */
	public static boolean isPhoneNumber(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
	}  
	
	/**
	 * 判断邮箱
	 *@author 邹继明
	 *@date 2015-6-23上午12:42:47
	 *@param email
	 *@return
	 *@return boolean
	 */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
