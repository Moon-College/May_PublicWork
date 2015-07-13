package com.itskylin.android.sqlite.bean;

import java.io.Serializable;

public class Classs implements Serializable {
	private static final long serialVersionUID = -5643811358230702188L;
	Integer id;
	String name;
	Integer teacherId;

	public Classs() {
		super();
	}

	public Classs(Integer id, String name, Integer teacherId) {
		super();
		this.id = id;
		this.name = name;
		this.teacherId = teacherId;
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

	public Integer getTeacher() {
		return teacherId;
	}

	public void setTeacher(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Classs other = (Classs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Classs [id=" + id + ", name=" + name + ", teacherId="
				+ teacherId + "]";
	}
}
