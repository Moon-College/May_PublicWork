package com.tz.ays.michael.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 验证类，给出相应的提示
 * @author szm
 *
 */
public class Check {

	private Context mContext;
	
	public Check(Context context) {
		this.mContext=context;
	}

	/**
	 * 字符串非空验证
	 * @param str
	 * @return boolean 空为true
	 */
	public boolean strNotNullCheck(String str){
		return TextUtils.isEmpty(str);
	}
	
	/**
	 * 账号验证
	 * @param str
	 * @return
	 */
	public boolean pHoneCheck(String str){
		boolean b;
		if(!strNotNullCheck(str)){
			//加入其他验证--合法性验证
			b=true;
		}else{
			b=false;
			Toast.makeText(mContext, "账号不能为空", 0).show();
		}
		return b;
	}
	
	/**
	 * 对密码的验证
	 * @param str
	 * @return
	 */
	public boolean passWordCheck(String str){
		boolean b;
		if(!strNotNullCheck(str)){
			//加入其他验证--合法性验证
			if(str.length()<6){
				b=false;
				Toast.makeText(mContext, "密码不能少于6位", 0).show();
			}else{
				b=true;
			}
		}else{
			b=false;
			Toast.makeText(mContext, "密码不能为空", 0).show();
		}
		return b;
	}
	
	/**
	 * 对年龄的验证
	 * @param str
	 * @return
	 */
	public boolean ageCheck(String str){
		boolean b;
		if(!strNotNullCheck(str)){
			//加入其他验证--合法性验证
			int age=Integer.valueOf(str);
			if(age<0||age>100){
				b=false;
				Toast.makeText(mContext, "年龄不合法", 0).show();
			}else{
				b=true;
			}
			b=true;
		}else{
			b=false;
			Toast.makeText(mContext, "请填写年龄", 0).show();
		}
		return b;
	}
	
	/**
	 * 对姓名的验证
	 * @param str
	 * @return
	 */
	public boolean nameCheck(String str){
		boolean b;
		if(!strNotNullCheck(str)){
			//加入其他验证--合法性验证
			int len=str.length();
			if(len<2||len>10){
				b=false;
				Toast.makeText(mContext, "姓名为2到10位的字母或汉字", 0).show();
			}else{
				b=true;
			}
			b=true;
		}else{
			b=false;
			Toast.makeText(mContext, "请填写姓名", 0).show();
		}
		return b;
	}
	
}
