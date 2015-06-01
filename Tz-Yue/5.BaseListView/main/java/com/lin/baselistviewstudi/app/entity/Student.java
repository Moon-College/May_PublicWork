package com.lin.baselistviewstudi.app.entity;

/**
 * Created by Administrator on 2015/5/24.
 * Ҫ��1��ÿ����Ŀ��ͬѧ��ͷ��+����+�Ա�+��ֵ+����
 * Ҫ��2����Ŀ��������10  ��������������ɫ�����ӱ����ú�ɫ
 * Ҫ��3�������Ӧ����Ŀ���Ƴ�����Ŀ
 */
public class Student {
    private String name;

    private char sex;
    private int img;
    private int prettyNum;
    private String hobbies;

    public Student(String name, char sex, int img, int prettyNum, String hobbies) {
        this.name = name;
        this.sex = sex;
        this.img = img;
        this.prettyNum = prettyNum;
        this.hobbies = hobbies;
    }

    public int getPrettyNum() {
        return prettyNum;
    }

    public void setPrettyNum(int prettyNum) {
        this.prettyNum = prettyNum;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", img=" + img +
                ", prettyNum=" + prettyNum +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }


}
