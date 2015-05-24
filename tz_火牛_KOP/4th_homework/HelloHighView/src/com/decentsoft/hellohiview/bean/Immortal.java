package com.decentsoft.hellohiview.bean;

public class Immortal
{ 
	public static final String MALE = "男性";
	public static final String FEMALE = "女性";
	public static final String NAME = "姓名:";
	public static final String SEX = "性别:";
	public static final String INTEREST = "爱好:"; 
	
    private String name;
    private String sex;
    private String interest;
    private int headPic;
    public Immortal(String name,String sex,String interest,int headpic)
    {
    	this.name = name;
    	this.sex = sex;
    	this.interest = interest;
    	this.headPic = headpic;
    }
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getInterest()
	{
		return interest;
	}
	public void setInterest(String interest)
	{
		this.interest = interest;
	}
	public int getHeadPic()
	{
		return headPic;
	}
	public void setHeadPic(int headPic)
	{
		this.headPic = headPic;
	}
}
