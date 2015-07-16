package com.lin.mytzwork;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.lin.mytzwork.util.ReflectUtil;


public class MainActivity extends Activity implements TextWatcher {
    private Activity activity;
    private AutoCompleteTextView act;
    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ReflectUtil.initView(activity);
        act.addTextChangedListener(this);
        act.setThreshold(1);
        mySQLiteHelper = new MySQLiteHelper(activity, "my.db", null, 1);
        db = mySQLiteHelper.getWritableDatabase();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String trim = act.getText().toString().trim();

        if (!TextUtils.isEmpty(trim)) {
            Cursor cursor = db.rawQuery("select * from words where word like ?", new String[]{trim + "%"});
            MyAdapter adapter = new MyAdapter(activity, cursor, true);
            act.setAdapter(adapter);
        }


    }

    class MyAdapter extends CursorAdapter {


        public MyAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public CharSequence convertToString(Cursor cursor) {
            return cursor == null ? "" : cursor.getString(cursor.getColumnIndex("word"));
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.item, null);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView viewById = (TextView) view.findViewById(R.id.tv);
            String text = cursor.getString(cursor.getColumnIndex("word"));
            viewById.setText(text);
        }
    }
}
