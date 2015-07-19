package com.rocy.tz_class_contentprivoder.bean;
/**
 * 所有属性名字需要跟表名字对应
 * @author Rocy
 *
 */
public class PhoneContact extends BaseBean{
     private String account_name;
     private String account_type;
     private String phone ;
     private String email;
	/**
	 * @return the account_name
	 */
	public String getAccount_name() {
		return account_name;
	}
	/**
	 * @param account_name the account_name to set
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	/**
	 * @return the account_type
	 */
	public String getAccount_type() {
		return account_type;
	}
	/**
	 * @param account_type the account_type to set
	 */
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
     
}
