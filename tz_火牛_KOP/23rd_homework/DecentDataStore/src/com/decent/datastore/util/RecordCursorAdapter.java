package com.decent.datastore.util;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.decent.datastore.R;

public class RecordCursorAdapter extends CursorAdapter {

	public RecordCursorAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.cursor_adapter_item, null);
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView tv = (TextView) view.findViewById(R.id.tv);
		tv.setText(cursor.getString(cursor.getColumnIndex("name")));
	}

}
