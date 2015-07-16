package com.example.dd_cursor_adapter;

import com.example.dd_cursor_adapter.sqlitehelper.MySqliteHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements TextWatcher {
	private AutoCompleteTextView actv;
    private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actv = (AutoCompleteTextView) findViewById(R.id.actv);
        actv.setThreshold(1);
        MySqliteHelper helper = new MySqliteHelper(this, "dictionary.db", null, 1);
        db = helper.getWritableDatabase();
        actv.addTextChangedListener(this);
	}

	private class MyAdapter extends CursorAdapter{

		public MyAdapter(Context context, Cursor c, boolean autoRequery) {
			super(context, c, autoRequery);
		}

		@Override
		public CharSequence convertToString(Cursor cursor) {
			return cursor == null?"":cursor.getString(cursor.getColumnIndex("word"));
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
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		String text = actv.getText().toString().trim();
		if(!text.equals("")){	
			String sql = "select * from words where word like ?";
			Cursor query = db.rawQuery(sql, new String[]{text+"%"});
			MyAdapter adapter = new MyAdapter(MainActivity.this, query, true);
			actv.setAdapter(adapter);
		}
	}

}
