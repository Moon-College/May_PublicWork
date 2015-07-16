package junwen.junwen.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admi on 2015/7/15.
 */
public class SqliteHelp extends SQLiteOpenHelper{
    public SqliteHelp(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //创建表
    db.execSQL("create table words(_id INTEGER primary key autoincrement,word varchar)");
        ContentValues values = new ContentValues();

        values.put("word","junwen");
        db.insert("words", null, values);

        values.clear();;
        values.put("word","jun");
        db.insert("words",null,values);

        values.clear();;
        values.put("word","junjun");
        db.insert("words",null,values);

        values.clear();;
        values.put("word","wenwen");
        db.insert("words",null,values);

        values.clear();;
        values.put("word","dandan");
        db.insert("words",null,values);

        values.clear();;
        values.put("word","junyao");
        db.insert("words",null,values);

        values.clear();;
        values.put("word","junxian");
        db.insert("words",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //更新数据库时调用
    }
}
