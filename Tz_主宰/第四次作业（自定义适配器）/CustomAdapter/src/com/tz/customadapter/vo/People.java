package com.tz.customadapter.vo;

import com.tz.customadapter.consts.Sex;

public class People {
	public int avatar;
	public String name;
	public Sex sex;
	public String[] hobbies;
	public int faceScore;
	
	public People(int avatar, String name, Sex sex, String[] hobbies,
			int faceScore) {
		super();
		this.avatar = avatar;
		this.name = name;
		this.sex = sex;
		this.hobbies = hobbies;
		this.faceScore = faceScore;
	}	
}
