package com.decent.datastore;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.decent.datastore.adapter.RecordItemAdapter;
import com.decent.datastore.bean.SportRecord;
import com.decent.datastore.bean.UserSession;
import com.decent.datastore.util.DencetDataStoreService;
import com.decent.datastore.util.DencetDataStoreServiceManager;
import com.decent.decentutil.ReflictionUtil;

public class SportRecordsActivity extends Activity implements OnClickListener {
	private static final String TAG = "DecentDataStoreActivity";
	/**
	 * ddms
	 */
	private DencetDataStoreService mDdss;
	//一堆控件
	private ListView recordListView;
	private TextView usernameText;
	private EditText newRecordType;
	private EditText newRecordStartTime;
	private EditText newRecordDuration;
	/**
	 * 用来在编辑的时候记录id的，隐藏字段
	 */
	private TextView hiddenIdText;
	/**
	 * 添加按钮
	 */
	private Button addNewRecordBtn;
	/**
	 * 编辑按钮
	 */
	private Button editRecordBtn;
	/**
	 * 去测试CursorAdapter
	 */
	private Button goToTextCursorAdapterBtn;
	/**
	 * 运动记录列表
	 */
	private List<SportRecord> sportRecordList;
	/**
	 * 用户登录id
	 */
	private int loginuserId;
	/**
	 * 用户登录信息
	 */
	private UserSession userSession;
	/**
	 * 列表的adapter
	 */
	private RecordItemAdapter mRecordItemAdapter;

	/**
	 * 是否在编辑状态
	 */
	private boolean mClickBackToNewMode = false;
	/**
	 * 查询列表的sql
	 */
	private static final String GET_ALL_RECORD_SQL = "select a._id,b.name,datetime(a.start_time,'localtime') as start_time,a.duration from sport_records as a left join sport_types as b where a.sport_type_id = b._id and a.user_id = ?";
	/**
	 * 查询单条的sql
	 */
	private static final String QUERY_ONE_RECORD_SQL = "select _id,sport_type_id,start_time,duration from sport_records where _id = ?";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_list);
		// 初始化控件
		ReflictionUtil.InjectionView(SportRecordsActivity.class.getName(),
				R.class.getName(), this);
		addNewRecordBtn.setOnClickListener(this);
		editRecordBtn.setOnClickListener(this);
		goToTextCursorAdapterBtn.setOnClickListener(this);
		// 获取DencetDataStoreService
		mDdss = DencetDataStoreServiceManager.getDencetDataStoreService(this,
				"sport_records.db");
		// 设置抬头的用户登录名
		userSession = (UserSession) getApplication();
		loginuserId = userSession.getLoginUser().get_id();
		usernameText
				.setText(userSession.getLoginUser().getName() + ",welcome!");
		// 初始化sportRecordList;
		updateSportRecordList();
		// 初始化recordList，设置Adapter
		mRecordItemAdapter = new RecordItemAdapter(mDdss, sportRecordList, this);
		recordListView.setAdapter(mRecordItemAdapter);
	}

	/**
	 * 从数据库查询，更新sportRecordList的内容
	 */
	private void updateSportRecordList() {
		// TODO Auto-generated method stub
		sportRecordList = mDdss.rawQuery(SportRecord.class, GET_ALL_RECORD_SQL,
				new String[] { String.valueOf(loginuserId) });
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addNewRecordBtn:
			if (mClickBackToNewMode) {
				/*
				 * 添加按钮在编辑状态被点中，那就是回到纯添加状态
				 */
				editRecordBtn.setVisibility(View.INVISIBLE);
				addNewRecordBtn.setText(R.string.add_mode);
				mClickBackToNewMode = false;
			} else {
				/*
				 * 添加的时候就是执行据数库的添加
				 */
				//初始化，并且设置SportRecord的字段的值
				SportRecord record = new SportRecord();
				String recordType = newRecordType.getText().toString();
				String startTime = newRecordStartTime.getText().toString();
				String duration = newRecordDuration.getText().toString();
				record.setSport_type_id(Integer.valueOf(recordType));
				record.setStart_time(startTime);
				record.setDuration(Integer.valueOf(duration));
				record.setUser_id(userSession.getLoginUser().get_id());
				//执行插入操作,后面的string[]插入时候要使用的字段
				Long result = mDdss.insert("sport_records", record,
						new String[] { "sport_type_id", "start_time",
								"duration", "user_id" });
				if (result > 0) {
					Toast.makeText(this, "insert success,update list",
							Toast.LENGTH_SHORT).show();
					//成功则刷新列表
					freshRecordList();
				} else {
					Toast.makeText(this, "insert fail", Toast.LENGTH_SHORT)
							.show();
				}
			}
			break;
		case R.id.editRecordBtn:
			//初始化，并且设置SportRecord的字段的值
			SportRecord record = new SportRecord();
			String recordType = newRecordType.getText().toString();
			String startTime = newRecordStartTime.getText().toString();
			String duration = newRecordDuration.getText().toString();
			//获取隐藏的那个id字段的值，更新时候的判断条件
			String hiddenId = hiddenIdText.getText().toString();
			record.setSport_type_id(Integer.valueOf(recordType));
			record.setStart_time(startTime);
			record.setDuration(Integer.valueOf(duration));
			record.setUser_id(userSession.getLoginUser().get_id());
			//执行update，修改的东西写入数据库
			int result = mDdss.update("sport_records", record, new String[] {
					"sport_type_id", "start_time", "duration", "user_id" },
					"_id = ?", new String[] { hiddenId });
			if (result > 0) {
				Toast.makeText(this, "edit record" + hiddenId + " success",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "edit record" + hiddenId + " failed",
						Toast.LENGTH_SHORT).show();
			}
			freshRecordList();
			break;
		case R.id.goToTextCursorAdapterBtn:
			Intent intent = new Intent();
			intent.setClass(this, CursorAdapterTestActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/**
	 * 更新sportRecordList，刷新列表里面的数据
	 */
	public void freshRecordList() {
		// TODO Auto-generated method stub
		updateSportRecordList();
		mRecordItemAdapter.setmRecordList(sportRecordList);
		mRecordItemAdapter.notifyDataSetChanged();
	}

	/**
	 * 启动编辑对应strId的record
	 * 
	 * @param strId
	 *            record id的值
	 */
	public void startEditRecord(String strId) {
		//从数据库查询出strId对应的数据
		List<SportRecord> recordList = mDdss.rawQuery(SportRecord.class,
				QUERY_ONE_RECORD_SQL, new String[] { strId });
		SportRecord record;
		if (recordList.size() > 0) {
			//如果有数据获取数据
			record = recordList.get(0);
			//设置编辑框里面的那些值为record的
			newRecordType.setText(String.valueOf(record.getSport_type_id()));
			newRecordStartTime.setText(record.getStart_time());
			newRecordDuration.setText(String.valueOf(record.getDuration()));
			//隐藏字段记录编辑的id
			hiddenIdText.setText(strId);
			//编辑按钮显示
			editRecordBtn.setVisibility(View.VISIBLE);
			//addNewRecordBtn进入编辑状态
			addNewRecordBtn.setText(R.string.back_to_add_mode);
			mClickBackToNewMode = true;
			Toast.makeText(this, "begin to edit record " + strId,
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "start edit record" + strId + " failed",
					Toast.LENGTH_SHORT).show();
		}
	}

}