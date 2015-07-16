package com.decent.datastore.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DencetDataStoreServiceManager {
	//����dbName��DencetDataStoreService�Ķ�Ӧ��ϵ
	private static Map<String, DencetDataStoreService> ddssMap = new HashMap<String, DencetDataStoreService>();
	
	/**
	 * ���DencetDataStoreServiceʵ��
	 * @param context ������
	 * @param dbName db������
	 * @return DencetDataStoreServiceʵ��
	 */
	public static DencetDataStoreService getDencetDataStoreService(
			Context context, String dbName) {
		//�ȴ�ddssMap�����ȡ�����ֱ�ӷ���
		DencetDataStoreService ddss = ddssMap.get(dbName);
		if (ddss == null) {
			//���û�У����ʼ��һ��
			DecentSQLiteHelper sdh = new DecentSQLiteHelper(context, dbName,
					null, 2);
			SQLiteDatabase db = sdh.getWritableDatabase();
			ddss = new DencetDataStoreService(db);
			//Ȼ�����ddssMap
			ddssMap.put(dbName, ddss);
		}

		return ddss;
	}
}
