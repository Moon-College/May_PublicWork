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
	//һ�ѿؼ�
	private ListView recordListView;
	private TextView usernameText;
	private EditText newRecordType;
	private EditText newRecordStartTime;
	private EditText newRecordDuration;
	/**
	 * �����ڱ༭��ʱ���¼id�ģ������ֶ�
	 */
	private TextView hiddenIdText;
	/**
	 * ��Ӱ�ť
	 */
	private Button addNewRecordBtn;
	/**
	 * �༭��ť
	 */
	private Button editRecordBtn;
	/**
	 * ȥ����CursorAdapter
	 */
	private Button goToTextCursorAdapterBtn;
	/**
	 * �˶���¼�б�
	 */
	private List<SportRecord> sportRecordList;
	/**
	 * �û���¼id
	 */
	private int loginuserId;
	/**
	 * �û���¼��Ϣ
	 */
	private UserSession userSession;
	/**
	 * �б��adapter
	 */
	private RecordItemAdapter mRecordItemAdapter;

	/**
	 * �Ƿ��ڱ༭״̬
	 */
	private boolean mClickBackToNewMode = false;
	/**
	 * ��ѯ�б��sql
	 */
	private static final String GET_ALL_RECORD_SQL = "select a._id,b.name,datetime(a.start_time,'localtime') as start_time,a.duration from sport_records as a left join sport_types as b where a.sport_type_id = b._id and a.user_id = ?";
	/**
	 * ��ѯ������sql
	 */
	private static final String QUERY_ONE_RECORD_SQL = "select _id,sport_type_id,start_time,duration from sport_records where _id = ?";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_list);
		// ��ʼ���ؼ�
		ReflictionUtil.InjectionView(SportRecordsActivity.class.getName(),
				R.class.getName(), this);
		addNewRecordBtn.setOnClickListener(this);
		editRecordBtn.setOnClickListener(this);
		goToTextCursorAdapterBtn.setOnClickListener(this);
		// ��ȡDencetDataStoreService
		mDdss = DencetDataStoreServiceManager.getDencetDataStoreService(this,
				"sport_records.db");
		// ����̧ͷ���û���¼��
		userSession = (UserSession) getApplication();
		loginuserId = userSession.getLoginUser().get_id();
		usernameText
				.setText(userSession.getLoginUser().getName() + ",welcome!");
		// ��ʼ��sportRecordList;
		updateSportRecordList();
		// ��ʼ��recordList������Adapter
		mRecordItemAdapter = new RecordItemAdapter(mDdss, sportRecordList, this);
		recordListView.setAdapter(mRecordItemAdapter);
	}

	/**
	 * �����ݿ��ѯ������sportRecordList������
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
				 * ��Ӱ�ť�ڱ༭״̬�����У��Ǿ��ǻص������״̬
				 */
				editRecordBtn.setVisibility(View.INVISIBLE);
				addNewRecordBtn.setText(R.string.add_mode);
				mClickBackToNewMode = false;
			} else {
				/*
				 * ��ӵ�ʱ�����ִ�о���������
				 */
				//��ʼ������������SportRecord���ֶε�ֵ
				SportRecord record = new SportRecord();
				String recordType = newRecordType.getText().toString();
				String startTime = newRecordStartTime.getText().toString();
				String duration = newRecordDuration.getText().toString();
				record.setSport_type_id(Integer.valueOf(recordType));
				record.setStart_time(startTime);
				record.setDuration(Integer.valueOf(duration));
				record.setUser_id(userSession.getLoginUser().get_id());
				//ִ�в������,�����string[]����ʱ��Ҫʹ�õ��ֶ�
				Long result = mDdss.insert("sport_records", record,
						new String[] { "sport_type_id", "start_time",
								"duration", "user_id" });
				if (result > 0) {
					Toast.makeText(this, "insert success,update list",
							Toast.LENGTH_SHORT).show();
					//�ɹ���ˢ���б�
					freshRecordList();
				} else {
					Toast.makeText(this, "insert fail", Toast.LENGTH_SHORT)
							.show();
				}
			}
			break;
		case R.id.editRecordBtn:
			//��ʼ������������SportRecord���ֶε�ֵ
			SportRecord record = new SportRecord();
			String recordType = newRecordType.getText().toString();
			String startTime = newRecordStartTime.getText().toString();
			String duration = newRecordDuration.getText().toString();
			//��ȡ���ص��Ǹ�id�ֶε�ֵ������ʱ����ж�����
			String hiddenId = hiddenIdText.getText().toString();
			record.setSport_type_id(Integer.valueOf(recordType));
			record.setStart_time(startTime);
			record.setDuration(Integer.valueOf(duration));
			record.setUser_id(userSession.getLoginUser().get_id());
			//ִ��update���޸ĵĶ���д�����ݿ�
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
	 * ����sportRecordList��ˢ���б����������
	 */
	public void freshRecordList() {
		// TODO Auto-generated method stub
		updateSportRecordList();
		mRecordItemAdapter.setmRecordList(sportRecordList);
		mRecordItemAdapter.notifyDataSetChanged();
	}

	/**
	 * �����༭��ӦstrId��record
	 * 
	 * @param strId
	 *            record id��ֵ
	 */
	public void startEditRecord(String strId) {
		//�����ݿ��ѯ��strId��Ӧ������
		List<SportRecord> recordList = mDdss.rawQuery(SportRecord.class,
				QUERY_ONE_RECORD_SQL, new String[] { strId });
		SportRecord record;
		if (recordList.size() > 0) {
			//��������ݻ�ȡ����
			record = recordList.get(0);
			//���ñ༭���������ЩֵΪrecord��
			newRecordType.setText(String.valueOf(record.getSport_type_id()));
			newRecordStartTime.setText(record.getStart_time());
			newRecordDuration.setText(String.valueOf(record.getDuration()));
			//�����ֶμ�¼�༭��id
			hiddenIdText.setText(strId);
			//�༭��ť��ʾ
			editRecordBtn.setVisibility(View.VISIBLE);
			//addNewRecordBtn����༭״̬
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