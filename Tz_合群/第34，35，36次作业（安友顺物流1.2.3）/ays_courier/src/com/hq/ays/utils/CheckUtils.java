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
	 * �Ƿ�Ϊ���ַ���
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
			Toast.makeText(context, "֤��ͼƬ����Ϊ��", 1000).show();
		}
		return false;
	}
	/**
	 * �绰��������
	 */
	
	public static boolean isMobileCheck(String mobileNumber,Context context){
		if(!isEmpty(mobileNumber)){
			Pattern p = Pattern.compile("^1[0-9]{10}$");
			Matcher m = p.matcher(mobileNumber);
			if(!m.matches()){
				Toast.makeText(context, "��������ȷ���ֻ�����", 1000).show();
			}
			return m.matches();
		}else{
			Toast.makeText(context, "�绰���벻��Ϊ��", 1000).show();
		}
		return false;
	}
	
	
	public static boolean isPasswordCheck(String password,Context context){
		if(!isEmpty(password)){
			if(password.length()>=6){
				return true;
			}else{
				Toast.makeText(context, "���벻������6λ", 1000).show();
			}
		}
		return false;
	}
	
	public static boolean isUserNameCheck(String userName,Context context){
		if(!isEmpty(userName)){
			if(userName.length()>16){
				Toast.makeText(context, "�û�������", 1000).show();
			}else if(userName.length()<=2){
				Toast.makeText(context, "�û���̫��", 1000).show();
			}else{
				return true;
			}
		}else{
			Toast.makeText(context, "�û�������Ϊ��", 1000).show();
		}
		return false;
	}
	
	public static boolean isAgeCheck(String age,Context context){
		if(!isEmpty(age)){
			Pattern p = Pattern.compile("^([0-9]|[0-9]{2}|100)$");
			Matcher m = p.matcher(age);
			if(!m.matches()){
				Toast.makeText(context, "���䲻�Ϸ�", 1000).show();
			}else{
				return true;
			}
		}else{
			Toast.makeText(context, "���䲻��Ϊ��", 1000).show();
		}
		return false;
	}
	
	public static boolean isNameCheck(String name,Context context){
		if(!isEmpty(name)){
			return true;
		}else{
			Toast.makeText(context, "��ʵ��������Ϊ��", 1000).show();
		}
		return false;
	}
}
