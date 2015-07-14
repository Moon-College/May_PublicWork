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
		// 初始化控件
		ReflictionUtil.InjectionView(CursorAdapterTestActivity.class.getName(),
				R.class.getName(), this);
		// 设置字符变化的listener
		actv.addTextChangedListener(this);
		// 设置几个字符之后才显示那个dropdown list
		actv.setThreshold(0);
		// 获得ddss
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
			//通过mDdss获得db
			SQLiteDatabase db = mDdss.getmDb();
			//查询获得cursor
			Cursor cursor = db.rawQuery(QUERY_SPORT_TYPE_BY_NAME,
					new String[] { textTyped+"%" });
			//新建cursor
			RecordCursorAdapter recordCursorAdapter = new RecordCursorAdapter(
					this, cursor);
			actv.setAdapter(recordCursorAdapter);
		}
	}

}
