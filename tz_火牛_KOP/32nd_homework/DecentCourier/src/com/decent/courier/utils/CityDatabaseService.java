package com.decent.courier.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CityDatabaseService {

	private static final String STR_QUERY_CITY_NAME_BY_ID = "select * from city where id = ?";
	private static final String CITY_NAME = "name";

	/**
	 * ����cityid��ȡ���ж�Ӧ������
	 * @param db ���ݿ�
	 * @param cityId ���е�id
	 * @return ��Ӧ���е�����
	 */
	public static String getCityById(SQLiteDatabase db, Integer cityId) {
		// TODO Auto-generated method stub
		String result = null;
		Cursor cursor = db.rawQuery(STR_QUERY_CITY_NAME_BY_ID, new String[]{String.valueOf(cityId)});
		if(cursor.moveToNext()){
			result = cursor.getString(cursor.getColumnIndex(CITY_NAME));
			//һ��Ҫ�ǵùر�cusor
			cursor.close();
		}
		return result;
	}

}
