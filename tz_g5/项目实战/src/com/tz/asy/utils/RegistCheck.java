package com.tz.asy.utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Archer on 2015/5/29.
 */
public class RegistCheck {
	private String checkText;

    public String getCheckText() {
		return checkText;
	}


	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}


	public RegistCheck() {
    }

    
    /**
     * 正则空字符串
     */
    public boolean checkEmpty(String str,String desc){
    	if(Check.isEmpty(str)){
    		checkText = desc+"不能为空"; 
    		return false;
    	}
    	return true;
    }
    /**
     * 用户名
     * @param userName
     * @return
     */
    public  boolean checkUserName(String userName) {
        if(!Check.isEmpty(userName)){
            String all  = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,24}$";
            Pattern p = Pattern.compile(all);
            Matcher m = p.matcher(userName);
            if(!m.matches()){
            	checkText = "请输入正确的用户名,用户名长度最少为2位，不可包含特殊符号";
            }
            return m.matches();
        }else{
            checkText = "用户名不能为空";
        }
        return false;
    }

    /**
     * 姓名
     * @param name
     * @return
     */
    public boolean checkName(String name){
        if(!Check.isEmpty(name)){
//            String regex = "[\u4E00-\u9FA5]";
//            Pattern p = Pattern.compile(regex);
//            Matcher m = p.matcher(name);
//            if(!m.matches()){
//                toastor.showSingletonToast("请输入正确的真实姓名,长度为是2-12位，只可输入中文");
//            }
            return true;
        }else{
        	checkText = "真实姓名不能为空";
        }
        return false;
    }
    
    /**
     * 密码
     * @param password
     * @return
     */
    public boolean isPasswordNo(String password) {
        if(!Check.isEmpty(password)){
            //不能为中文
            String regex = "[0-9a-zA-Z_]{6,24}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);
            if(!m.matches()){
                checkText = "请输入正确的密码,长度最少为6位、不可包含特殊符号";
            }
            return m.matches();
        }else{
           checkText = "密码不能为空";
        }
        return false;
    }
    /**
     * 手机号码
     * @param mobiles
     * @return
     */
    public boolean isMobileNO(String mobiles) {
        if(!Check.isEmpty(mobiles)){
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            if(!m.matches()){
                checkText = "请输入正确的手机号码";
            }
            return m.matches();
        }else{
            checkText = "手机号码不能为空";
        }
        return false;
    }

    /**
     * 年龄验证
     * @param str
     * @return
     */
    public boolean checkAge(String str){
        if(!Check.isEmpty(str)){
            Pattern p = Pattern.compile("^([0-9]|[0-9]{2}|100)$");
            Matcher m = p.matcher(str);
            if(!m.matches()){
            	checkText = "请输入正确的年龄0~100";
            }
            return m.matches();
        }else{
        	checkText = "年龄信息不能为空";
        }
        return false;
    }

//    public boolean checkPath(String str,int code){
//        if(!Check.isEmpty(str)){
//            return true;
//        }else{
//            switch (code){
//                case Constants.PHOTO_CDRD_FRONT_DATA:
//                    toastor.showSingletonToast("身份证正面照片尚未选择");
//                break;
//
//                case Constants.PHOTO_CDRD_REAR_DATA:
//                    toastor.showSingletonToast("身份证背面照片尚未选择");
//                break;
//
//                case Constants.PHOTO_CDRD_WITH_DATA:
//                    toastor.showSingletonToast("证件照片文件尚未选择");
//                break;
//            }
//        }
//        return false;
//    }

}
