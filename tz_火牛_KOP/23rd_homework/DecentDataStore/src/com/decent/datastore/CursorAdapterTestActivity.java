package com.decent.datastore;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.decent.datastore.util.DencetDataStoreService;
import com.decent.datastore.util.DencetDataStoreServiceManager;
import com.decent.datastore.util.RecordCursorAdapter;
import com.decent.decentutil.DecentLogUtil;
import com.decent.decentutil.ReflictionUtil;

public class CursorAdapterTestActivity extends Activity implements TextWatcher {
	private static final String QUERY_SPORT_TYPE_BY_NAME = "select _id,name from sport_types where name like ?";
	private AutoCompleteTextView actv;
	private DencetDataStoreService mDdss;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cursoradapter_test);
		// ��ʼ���ؼ�
		ReflictionUtil.InjectionView(CursorAdapterTestActivity.class.getName(),
				R.class.getName(), this);
		// �����ַ��仯��listener
		actv.addTextChangedListener(this);
		// ���ü����ַ�֮�����ʾ�Ǹ�dropdown list
		actv.setThreshold(0);
		// ���ddss
		mDdss = DencetDataStoreServiceManager.getDencetDataStoreService(this,
				"sport_records.db");
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s) {
		DecentLogUtil.d("INFO", "into afterTextChanged!!!");
		String textTyped = actv.getText().toString().trim();
		if (!"".equals(textTyped)) {
			//ͨ��mDdss���db
			SQLiteDatabase db = mDdss.getmDb();
			//��ѯ���cursor
			Cursor cursor = db.rawQuery(QUERY_SPORT_TYPE_BY_NAME,
					new String[] { textTyped+"%" });
			//�½�cursor
			RecordCursorAdapter recordCursorAdapter = new RecordCursorAdapter(
					this, cursor);
			actv.setAdapter(recordCursorAdapter);
		}
	}

}
