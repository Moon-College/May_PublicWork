package com.tz.listview;

public class Person {
	
	private int photoId;
	
	private String onlineName;
	
	private String sex;
	
	private String beauty;
	
	private String hobby;
	
	public Person(int photoId, String onlineName, String sex, String beauty, String hobby) {
		this.photoId = photoId;
		this.onlineName = onlineName;
		this.sex = sex;
		this.beauty = beauty;
		this.hobby = hobby;
	}

	/**
	 * @return the photoId
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the onlineName
	 */
	public String getOnlineName() {
		return onlineName;
	}

	/**
	 * @param onlineName the onlineName to set
	 */
	public void setOnlineName(String onlineName) {
		this.onlineName = onlineName;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the beauty
	 */
	public String getBeauty() {
		return beauty;
	}

	/**
	 * @param beauty the beauty to set
	 */
	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}

	/**
	 * @return the hobby
	 */
	public String getHobby() {
		return hobby;
	}

	/**
	 * @param hobby the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	@Override
	public String toString() {
		return "网名：" + onlineName + ",性别：" + sex + ",颜值：" + beauty + ",爱好：" + hobby;
	}
}
