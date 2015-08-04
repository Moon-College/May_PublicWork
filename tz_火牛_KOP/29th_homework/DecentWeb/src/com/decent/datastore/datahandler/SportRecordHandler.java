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
	 * ��ȡ����sportRecord���б�
	 * 
	 * @return �����б�
	 */
	public static List<SportRecord> getAllSportRecordList() {
		return mSportRecordList;
	}

	/**
	 * ��ȡid��Ӧ��sportrecord
	 * 
	 * @param id
	 *            Ҫ��ѯ��id��
	 * @return ����id��Ӧ��sportRecord,û�оͷ���null
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
