package com.tz.intent.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qinhan on 15/6/4.
 */
public class Person implements Parcelable{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel source)
        {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size)
        {
            // TODO Auto-generated method stub
            return new Person[size];
        }

    };

    public Person(Parcel source)
    {
        name = source.readString();
        age = source.readInt();
        // TODO Auto-generated constructor stub
    }
}
