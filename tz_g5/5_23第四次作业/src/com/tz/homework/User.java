package com.tz.homework;

/**
 * Created by qinhan on 15/5/25.
 */
public class User {
    public String name;
    public Gender gender;
    public int resId;
    public Look look;
    public String favourite;

    public enum Gender {
        MALE("基佬"), FEMALE("妹纸"), UNKNOWN("未知");
        public String info;

        Gender(String info) {
            this.info = info;
        }
    }

    public enum Look {
        CUTE("可爱"),HANDSOME("帅气"), UGLY("挫");

        public String level;

        Look(String level) {
            this.level=level;
        }
    }
}
