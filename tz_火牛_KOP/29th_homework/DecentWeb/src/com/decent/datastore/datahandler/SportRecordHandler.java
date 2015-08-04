package com.decent.datastore.datahandler;

import java.util.ArrayList;
import java.util.List;

import com.decent.datastore.bean.SportRecord;

public class SportRecordHandler {

	public static List<SportRecord> mSportRecordList;
	public static int INIT_RECORD_NUM = 5;

	static {
		mSportRecordList = new ArrayList<SportRecord>();
		for (int i = 0; i < INIT_RECORD_NUM; i++) {
			SportRecord sr = new SportRecord(i, 1, 1, "Meditation",
					"2015-8-4 09:52:33", 100 * (i + 1));
			mSportRecordList.add(sr);
		}
	}

	/**
	 * 获取所有sportRecord的列表
	 * 
	 * @return 返回列表
	 */
	public static List<SportRecord> getAllSportRecordList() {
		return mSportRecordList;
	}

	/**
	 * 获取id对应的sportrecord
	 * 
	 * @param id
	 *            要查询的id号
	 * @return 返回id对应的sportRecord,没有就返回null
	 */
	public static List<SportRecord> getSportRecordById(int id) {
		if (id >= mSportRecordList.size() || mSportRecordList.get(id) == null) {
			return null;
		}
		List<SportRecord> list = new ArrayList<SportRecord>();
		list.add(mSportRecordList.get(id));
		return list;
	}
}
