package com.itskylin.android.sqlite.bean;

import java.io.Serializable;

public class Teacher implements Serializable {

	private static final long serialVersionUID = -576359369343308747L;
	Integer id;
	String name;
	String tel;
	Boolean sex;
	String addr;
	Boolean marry;

	public Teacher() {
		super();
	}

	public Teacher(Integer id, String name, String tel, Boolean sex,
			String addr, Boolean marry) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.sex = sex;
		this.addr = addr;
		this.marry = marry;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Boolean getMarry() {
		return marry;
	}

	public void setMarry(Boolean marry) {
		this.marry = marry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marry == null) ? 0 : marry.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marry == null) {
			if (other.marry != null)
				return false;
		} else if (!marry.equals(other.marry))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", tel=" + tel
				+ ", sex=" + sex + ", addr=" + addr + ", marry=" + marry + "]";
	}

}
