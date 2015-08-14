package com.decent.courier.bean.request;

import java.io.File;

public class HttpRequestRegister implements HttpParams {
	private String userName;
	private String password;
	private String name;
	private String cellphone;
	private Integer age;
	private File identificationFaceImg;
	private File identificationBackImg;
	private File licenseImg;

	public HttpRequestRegister(String userName, String password, String name,
			String cellphone, Integer age, File identificationFaceImg,
			File identificationBackImg, File licenseImg) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.cellphone = cellphone;
		this.age = age;
		this.identificationFaceImg = identificationFaceImg;
		this.identificationBackImg = identificationBackImg;
		this.licenseImg = licenseImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public File getIdentificationFaceImg() {
		return identificationFaceImg;
	}

	public void setIdentificationFaceImg(File identificationFaceImg) {
		this.identificationFaceImg = identificationFaceImg;
	}

	public File getIdentificationBackImg() {
		return identificationBackImg;
	}

	public void setIdentificationBackImg(File identificationBackImg) {
		this.identificationBackImg = identificationBackImg;
	}

	public File getLicenseImg() {
		return licenseImg;
	}

	public void setLicenseImg(File licenseImg) {
		this.licenseImg = licenseImg;
	}

}
