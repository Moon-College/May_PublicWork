package com.itskylin.android.sqlite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyTeacherAdapter extends CursorAdapter {

	public MyTeacherAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	@Override
	public CharSequence convertToString(Cursor cursor) {
		return cursor == null ? "" : cursor.getString(cursor
				.getColumnIndex("name"));
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		view.setBackgroundColor(Color.TRANSPARENT);
		Log.i("INFO", "view:" + view.getId());
		TextView tv = new TextView(context);
		tv.setTextColor(Color.RED);
		tv.setText(cursor.getString(cursor.getColumnIndex("name")));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup group) {
		TextView view = (TextView) LayoutInflater.from(context).inflate(
				android.R.layout.simple_list_item_1, null);
		view.setTextColor(Color.RED);
		view.setText(cursor.getString(cursor.getColumnIndex("name")));
		return view;
	}

}
