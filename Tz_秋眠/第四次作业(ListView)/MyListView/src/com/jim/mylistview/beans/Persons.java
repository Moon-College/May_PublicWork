package com.jim.mylistview.beans;

/**
 * �����б��ʵ����
 * 
 * @author fb
 * @time 2015.05.25
 */
public class Persons {

	private String name;// ����
	private String gender;// �Ա�
	private String hobby;// ����
	private int appearance;// ��ֵ
	private int img;// ͷ��

	/**
	 * 
	 * @param name
	 *            ����
	 * @param gender
	 *            �Ա�
	 * @param hobby
	 *            ����
	 * @param appearance
	 *            ��ֵ
	 * @param img
	 *            ͷ��
	 */
	public Persons(String name, String gender, String hobby, int appearance,
			int img) {
		super();
		this.name = name;
		this.gender = gender;
		this.hobby = hobby;
		this.appearance = appearance;
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getHobby() {
		return hobby;
	}

	public int getAppearance() {
		return appearance;
	}

	public int getImg() {
		return img;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	public void setImg(int img) {
		this.img = img;
	}
}
