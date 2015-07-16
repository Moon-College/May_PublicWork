package com.decent.datastore.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.decent.datastore.R;
import com.decent.datastore.SportRecordsActivity;
import com.decent.datastore.bean.SportRecord;
import com.decent.datastore.util.DencetDataStoreService;

public class RecordItemAdapter extends BaseAdapter{

	private List<SportRecord> mRecordList;
	private SportRecordsActivity mActivity;
	private DencetDataStoreService mDdss;

	public RecordItemAdapter(DencetDataStoreService ddss,List<SportRecord> list, SportRecordsActivity context) {
		mRecordList = list;
		mActivity = context;
		mDdss = ddss;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mRecordList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mRecordList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		View view = inflater.inflate(R.layout.record_item, null);
		SportRecord record = mRecordList.get(position);
		TextView record_id = (TextView) view.findViewById(R.id.record_id);
		TextView record_type = (TextView) view.findViewById(R.id.record_type);
		TextView start_time = (TextView) view.findViewById(R.id.start_time);
		TextView duration_time = (TextView) view
				.findViewById(R.id.duration_time);
		final String strId = String.valueOf(record.get_id());
		record_id.setText(strId);
		record_type.setText(record.getName());
		start_time.setText(record.getStart_time());
		duration_time.setText(String.valueOf(record.getDuration()));

		Button delete_record = (Button) view.findViewById(R.id.delete_record);
		Button edit_record = (Button) view.findViewById(R.id.edit_record);
		//删除按钮的处理
		delete_record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//删除记录，条件是id
				mDdss.delete("sport_records", "_id = ?", new String[]{strId});
				mActivity.freshRecordList();
			}
		});
		//编辑按钮的处理
		edit_record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActivity.startEditRecord(strId);
			}
		});
		return view;
	}

	public List<SportRecord> getmRecordList() {
		return mRecordList;
	}

	/**
	 * 更新数据列表
	 * @param mRecordList 数据列表
	 */
	public void setmRecordList(List<SportRecord> mRecordList) {
		this.mRecordList = mRecordList;
	}

}
