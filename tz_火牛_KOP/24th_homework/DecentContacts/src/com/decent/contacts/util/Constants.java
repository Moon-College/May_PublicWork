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
	 * mimetype�б�
	 */
	public static final String MIMETypeNameArray[] = { MIMETYPE_EMAILV2,
			MIMETYPE_IM, MIMETYPE_ADDRESSV2, MIMETYPE_PHOTO, MIMETYPE_PHONEV2,
			MIMETYPE_NAME, MIMETYPE_ORGANIZATION, MIMETYPE_NICKNAME,
			MIMETYPE_GROUP_MEMBERSHIP };
	/**
	 * ��֪�ֶε��б�˳��Ҫ��MIMETypeNameArrayһ�£��Ϳ���
	 */
	public static final String[][] KnownFieldList = { { "email", "emialList" },
			{ "im", "imList" }, { "address", "addressList" },
			{ "photo", "photoList" }, { "phone", "phoneList" }, { "name" },
			{ "organization", "organizationList" },
			{ "nickname", "nicknameList" }, { "membership", "membershipList" } };
	/**
	 * ��֪�ֶκ�mimetype֮��Ķ�Ӧ��ϵ
	 */
	public static final Map<String, String> FieldAndMimeTypeMap = new HashMap<String, String>();
	/**
	 * ��ʼ��
	 */
	static {
		/*
		 * ��ʼ��FieldAndMimeTypeMap 1������FieldAndTypeNameMap�б�
		 */
		for (int i = 0; i < KnownFieldList.length; i++) {
			for (int j = 0; j < KnownFieldList[i].length; j++) {
				FieldAndMimeTypeMap.put(KnownFieldList[i][j],
						MIMETypeNameArray[i]);
			}
		}
	}

	/**
	 * ͨ���ֶε���������ȡ����ӦMimeType��contacts�������Ӧ������
	 * (��Ҫά��KnownFieldList�����ά�����MIMETypeNameArray�������)
	 * 
	 * @param name
	 *            �ֶ�����
	 * @return ��Ӧ��mimeType����
	 */
	public static String getMimeTypeNameByFieldName(String name) {
		// TODO Auto-generated method stub
		return FieldAndMimeTypeMap.get(name);
	}
}
