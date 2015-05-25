package com.junwen.bean;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class Student {
	
	//网名
	private String name;
	//性别
	private String sex;
	//爱好
	private String hobby;
	//颜值
	private int faceValue;
	//头像
	private SoftReference<Bitmap> headImage;
	//是否是男生
	private boolean isBoy=true;
	
	/**
	 * 获取名字
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置名字
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取性格
	 * @return
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置性格
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取爱好
	 * @return
	 */
	public String getHobby() {
		return hobby;
	}
	/**
	 * 设置爱好
	 * @param hobby
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	/**
	 * 获取颜值
	 * @return
	 */
	public int getFaceValue() {
		return faceValue;
	}
	/**
	 * 设置颜值
	 * @param faceValue
	 */
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	/**
	 * 获取头像
	 * @return
	 */
	public SoftReference<Bitmap> getHeadImage() {
		return headImage;
	}
	/**
	 * 设置头像
	 * @param headImage
	 */
	public void setHeadImage(SoftReference<Bitmap> headImage) {
		this.headImage = headImage;
	}
	/**
	 * 是男生
	 * @return
	 */
	public boolean isBoy() {
		return isBoy;
	}
	/**
	 * 设置是否是男生
	 * @param isBoy
	 */
	public void setBoy(boolean isBoy) {
		this.isBoy = isBoy;
	}

}
