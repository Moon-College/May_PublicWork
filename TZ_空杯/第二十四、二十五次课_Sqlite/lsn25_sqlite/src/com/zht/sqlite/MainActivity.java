/**
 * Project Name:lsn25_sqlite
 * File Name:MainActivity.java
 * Package Name:com.zht.sqlite
 * Date:2015-7-19下午1:51:28
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zht.sqlite.bean.Word;
import com.zht.sqlite.utils.DataService;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-19 下午1:51:28 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity implements TextWatcher {
	private AutoCompleteTextView actv;
	private DataService ds;
	private EditText et_old;
	private EditText et_new;
	private RadioButton rb_add;
	private RadioButton rb_delete;
	private RadioButton rb_update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ds = new DataService(this, "words.db");
		actv = (AutoCompleteTextView) findViewById(R.id.actv);
		et_old = (EditText) findViewById(R.id.et_old);
		et_new = (EditText) findViewById(R.id.et_new);
		rb_add = (RadioButton) findViewById(R.id.rb_add);
		rb_delete = (RadioButton) findViewById(R.id.rb_delete);
		rb_update = (RadioButton) findViewById(R.id.rb_update);
		actv.setThreshold(1);
		actv.addTextChangedListener(this);
	}

	public void click(View v) {
		String oldContent = et_old.getText().toString().trim();
		String newContent = et_new.getText().toString().trim();

		// 增加
		if (rb_add.isChecked() && !TextUtils.isEmpty(oldContent)) {
			ds.addData(new Word(oldContent));
		} 
		// 删除
		else if (rb_delete.isChecked() && !TextUtils.isEmpty(oldContent)) {
			ds.deleteData(new Word(oldContent));
		} 
		// 修改
		else if (rb_update.isChecked() && !TextUtils.isEmpty(oldContent)
				&& !TextUtils.isEmpty(oldContent)) {
			ds.updateData(new Word(oldContent), new Word(newContent));
		}else {
			Toast.makeText(this, "数据不能为空！", Toast.LENGTH_SHORT).show();
		}
	}

	private class MyAdapter extends CursorAdapter {

		public MyAdapter(Context context, Cursor c, boolean autoRequery) {
			super(context, c, autoRequery);
		}

		@Override
		public CharSequence convertToString(Cursor cursor) {
			return cursor == null ? "" : cursor.getString(cursor
					.getColumnIndex("word"));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.list_item, null);
			return view;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv = (TextView) view.findViewById(R.id.tv);
			String text = cursor.getString(cursor.getColumnIndex("word"));
			tv.setText(text);
		}

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
		// 获取用户输入的文字
		String text = actv.getText().toString().trim();
		if (!text.equals("")) {
			MyAdapter adapter = new MyAdapter(MainActivity.this,
					ds.queryData(text), true);
			actv.setAdapter(adapter);
		}

	}
}
