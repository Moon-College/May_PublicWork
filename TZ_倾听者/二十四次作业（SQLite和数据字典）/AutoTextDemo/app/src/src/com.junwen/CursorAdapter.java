package com.junwen.adapter.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admi.sqlitedemo.R;

/**
 * Created by admi on 2015/7/15.
 */
public class CursorAdapter extends android.widget.CursorAdapter {


    public CursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public CharSequence convertToString(Cursor cursor) {
        return cursor==null?"":cursor.getString(cursor.getColumnIndex("word"));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView) view.findViewById(R.id.item);
        tv.setText(cursor.getString(cursor.getColumnIndex("word")));
    }

}
