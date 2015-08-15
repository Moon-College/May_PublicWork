package com.tz.ays.michael.bean.request;

import java.io.File;

/**
 * 注册请求模板类
 * @author szm
 *
 */
public class RegisterRequestBean implements IHttpParams{

	private String userName;//用户名    
    private String password;//密码
    private String name;//姓名
    private String cellphone;//手机号码
    private Integer age;//年龄
    private File identificationFaceImg;//身份证正面    
    private File identificationBackImg;//身份证反面  
    private File licenseImg;//证件照（如驾证）
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
	@Override
	public String toString() {
		return "RegisterRequestBean [userName=" + userName + ", password="
				+ password + ", name=" + name + ", cellphone=" + cellphone
				+ ", age=" + age + ", identificationFaceImg="
				+ identificationFaceImg + ", identificationBackImg="
				+ identificationBackImg + ", licenseImg=" + licenseImg + "]";
	}
	public RegisterRequestBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterRequestBean(String userName, String password, String name,
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
    
}
