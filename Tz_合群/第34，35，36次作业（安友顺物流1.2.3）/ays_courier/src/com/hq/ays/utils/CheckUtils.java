package com.hq.ays.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;

public class CheckUtils {
	
	private Context context;
	
	public CheckUtils(Context context){
		this.context = context;
	}
	
	
	/**
	 * 是否为空字符串
	 */
	public static boolean isEmpty(String str){
		if(str == null||str.equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isPathCheck(String path,Context context){
		if(!isEmpty(path)){
			return true;
		}else{
			Toast.makeText(context, "证件图片不能为空", 1000).show();
		}
		return false;
	}
	/**
	 * 电话号码正则
	 */
	
	public static boolean isMobileCheck(String mobileNumber,Context context){
		if(!isEmpty(mobileNumber)){
			Pattern p = Pattern.compile("^1[0-9]{10}$");
			Matcher m = p.matcher(mobileNumber);
			if(!m.matches()){
				Toast.makeText(context, "请输入正确的手机号码", 1000).show();
			}
			return m.matches();
		}else{
			Toast.makeText(context, "电话号码不能为空", 1000).show();
		}
		return false;
	}
	
	
	public static boolean isPasswordCheck(String password,Context context){
		if(!isEmpty(password)){
			if(password.length()>=6){
				return true;
			}else{
				Toast.makeText(context, "密码不能少于6位", 1000).show();
			}
		}
		return false;
	}
	
	public static boolean isUserNameCheck(String userName,Context context){
		if(!isEmpty(userName)){
			if(userName.length()>16){
				Toast.makeText(context, "用户名过长", 1000).show();
			}else if(userName.length()<=2){
				Toast.makeText(context, "用户名太短", 1000).show();
			}else{
				return true;
			}
		}else{
			Toast.makeText(context, "用户名不能为空", 1000).show();
		}
		return false;
	}
	
	public static boolean isAgeCheck(String age,Context context){
		if(!isEmpty(age)){
			Pattern p = Pattern.compile("^([0-9]|[0-9]{2}|100)$");
			Matcher m = p.matcher(age);
			if(!m.matches()){
				Toast.makeText(context, "年龄不合法", 1000).show();
			}else{
				return true;
			}
		}else{
			Toast.makeText(context, "年龄不能为空", 1000).show();
		}
		return false;
	}
	
	public static boolean isNameCheck(String name,Context context){
		if(!isEmpty(name)){
			return true;
		}else{
			Toast.makeText(context, "真实姓名不能为空", 1000).show();
		}
		return false;
	}
}
