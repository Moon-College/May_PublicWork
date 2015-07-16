package com.junwen.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.example.admi.sqlitedemo.R;
import com.junwen.adapter.adapter.CursorAdapter;

import junwen.junwen.sqlite.SqliteHelp;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private AutoCompleteTextView textView;
    private com.junwen.adapter.adapter.CursorAdapter adapter;
    private  SQLiteDatabase db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        textView = (AutoCompleteTextView) findViewById(R.id.text);
        textView.addTextChangedListener(this);
        SqliteHelp sql = new SqliteHelp(this,"word.db",1);
        db = sql.getWritableDatabase();
    }

    /**
     * 文本改变之前
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /**
     * 文本改变的时候
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * 文本改变之后
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        String word = textView.getText().toString().trim();
        Cursor result = db.rawQuery("select * from words where word like ?", new String[]{word+"%"});
        adapter = new CursorAdapter(MainActivity.this,result,false);
        textView.setAdapter(adapter);
    }
}
