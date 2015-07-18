package com.decent.contacts.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String RAW_CONTACTS_TABLE_NAME = "raw_contacts";
	public static final String DATA_TABLE_NAME = "data";
	public static final String MIMETYPE_EMAILV2 = "vnd.android.cursor.item/email_v2";
	public static final String MIMETYPE_IM = "vnd.android.cursor.item/im";
	public static final String MIMETYPE_ADDRESSV2 = "vnd.android.cursor.item/postal-address_v2";
	public static final String MIMETYPE_PHOTO = "vnd.android.cursor.item/photo";
	public static final String MIMETYPE_PHONEV2 = "vnd.android.cursor.item/phone_v2";
	public static final String MIMETYPE_NAME = "vnd.android.cursor.item/name";
	public static final String MIMETYPE_ORGANIZATION = "vnd.android.cursor.item/organization";
	public static final String MIMETYPE_NICKNAME = "vnd.android.cursor.item/nickname";
	public static final String MIMETYPE_GROUP_MEMBERSHIP = "vnd.android.cursor.item/group_membership";
	/**
	 * mimetype列表
	 */
	public static final String MIMETypeNameArray[] = { MIMETYPE_EMAILV2,
			MIMETYPE_IM, MIMETYPE_ADDRESSV2, MIMETYPE_PHOTO, MIMETYPE_PHONEV2,
			MIMETYPE_NAME, MIMETYPE_ORGANIZATION, MIMETYPE_NICKNAME,
			MIMETYPE_GROUP_MEMBERSHIP };
	/**
	 * 已知字段的列表，顺序要和MIMETypeNameArray一致，就可以
	 */
	public static final String[][] KnownFieldList = { { "email", "emialList" },
			{ "im", "imList" }, { "address", "addressList" },
			{ "photo", "photoList" }, { "phone", "phoneList" }, { "name" },
			{ "organization", "organizationList" },
			{ "nickname", "nicknameList" }, { "membership", "membershipList" } };
	/**
	 * 已知字段和mimetype之间的对应关系
	 */
	public static final Map<String, String> FieldAndMimeTypeMap = new HashMap<String, String>();
	/**
	 * 初始化
	 */
	static {
		/*
		 * 初始化FieldAndMimeTypeMap 1、遍历FieldAndTypeNameMap列表
		 */
		for (int i = 0; i < KnownFieldList.length; i++) {
			for (int j = 0; j < KnownFieldList[i].length; j++) {
				FieldAndMimeTypeMap.put(KnownFieldList[i][j],
						MIMETypeNameArray[i]);
			}
		}
	}

	/**
	 * 通过字段的名字来获取到对应MimeType在contacts表里面对应的名字
	 * (需要维护KnownFieldList这个二维数组和MIMETypeNameArray这个数组)
	 * 
	 * @param name
	 *            字段名字
	 * @return 对应的mimeType名字
	 */
	public static String getMimeTypeNameByFieldName(String name) {
		// TODO Auto-generated method stub
		return FieldAndMimeTypeMap.get(name);
	}
}
