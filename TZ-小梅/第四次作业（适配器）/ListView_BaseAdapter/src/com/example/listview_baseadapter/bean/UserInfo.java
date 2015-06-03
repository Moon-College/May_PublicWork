package com.example.listview_baseadapter.bean;

public class UserInfo {
	/**网名*/
	private String name;
	/**性别*/
	private String sex;
	/**颜值*/
	private String appearance;
	/**爱好*/
	private String hobbys;
	/**头像*/
	private int head;
	
	
	
	public UserInfo() {
		super();
	}
	public UserInfo(String name, String sex, String appearance, String hobbys,
			int head) {
		super();
		this.name = name;
		this.sex = sex;
		this.appearance = appearance;
		this.hobbys = hobbys;
		this.head = head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getHobbys() {
		return hobbys;
	}
	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", sex=" + sex + ", appearance="
				+ appearance + ", hobbys=" + hobbys + ", head=" + head + "]";
	}
	
	
}
