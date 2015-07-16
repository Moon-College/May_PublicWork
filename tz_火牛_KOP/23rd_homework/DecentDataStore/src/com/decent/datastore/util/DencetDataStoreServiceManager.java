package com.decent.datastore.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DencetDataStoreServiceManager {
	//保存dbName和DencetDataStoreService的对应关系
	private static Map<String, DencetDataStoreService> ddssMap = new HashMap<String, DencetDataStoreService>();
	
	/**
	 * 获得DencetDataStoreService实例
	 * @param context 上下文
	 * @param dbName db的名字
	 * @return DencetDataStoreService实例
	 */
	public static DencetDataStoreService getDencetDataStoreService(
			Context context, String dbName) {
		//先从ddssMap里面获取如果有直接返回
		DencetDataStoreService ddss = ddssMap.get(dbName);
		if (ddss == null) {
			//如果没有，则初始化一个
			DecentSQLiteHelper sdh = new DecentSQLiteHelper(context, dbName,
					null, 2);
			SQLiteDatabase db = sdh.getWritableDatabase();
			ddss = new DencetDataStoreService(db);
			//然后加入ddssMap
			ddssMap.put(dbName, ddss);
		}

		return ddss;
	}
}
