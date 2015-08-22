package com.decent.courier.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CityDatabaseService {

	private static final String STR_QUERY_CITY_NAME_BY_ID = "select * from city where id = ?";
	private static final String CITY_NAME = "name";

	/**
	 * 根据cityid获取城市对应的名称
	 * @param db 数据库
	 * @param cityId 城市的id
	 * @return 对应城市的名称
	 */
	public static String getCityById(SQLiteDatabase db, Integer cityId) {
		// TODO Auto-generated method stub
		String result = null;
		Cursor cursor = db.rawQuery(STR_QUERY_CITY_NAME_BY_ID, new String[]{String.valueOf(cityId)});
		if(cursor.moveToNext()){
			result = cursor.getString(cursor.getColumnIndex(CITY_NAME));
			//一定要记得关闭cusor
			cursor.close();
		}
		return result;
	}

}
