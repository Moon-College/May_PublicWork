package com.tz.michael.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tz.michael.aactivity.R;
import com.tz.michael.bean.Car;
import com.tz.michael.utils.SharedPreferenceUtil;
import com.tz.michael.utils.XmlUtils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataSaveCollectActivity extends Activity implements OnClickListener {
	
	private EditText et_name,et_pwd;
	private Button btn,btn_parser;
	private String sp_name;
	private String sp_pwd;
	private GestureDetector  gestureDetector;
	
	private int touchSlop;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sp_name = (String) SharedPreferenceUtil.getValueFromSP(this, "test", "name", String.class);
        sp_pwd = (String) SharedPreferenceUtil.getValueFromSP(this, "test", "pwd", String.class);
        et_name=(EditText) findViewById(R.id.et_name);
        et_pwd=(EditText) findViewById(R.id.et_pwd);
        if(sp_name!=null&&!sp_name.equals("")){
        	et_name.setText(sp_name);
        }
        if(sp_pwd!=null&&!sp_pwd.equals("")){
        	et_pwd.setText(sp_pwd);
        }
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btn_parser=(Button) findViewById(R.id.btn_parser);
        btn_parser.setOnClickListener(this);
        //Distance a touch can wander before we think the user is scrolling in pixels
        final ViewConfiguration configuration = ViewConfiguration.get(this);
        touchSlop = configuration.getScaledTouchSlop();
        gestureDetector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
        	@Override
        	public boolean onFling(MotionEvent e1, MotionEvent e2,
        			float velocityX, float velocityY) {
        		//下滑
        		if(e2.getRawY()-e1.getRawY()>touchSlop){
        			Toast.makeText(DataSaveCollectActivity.this, "下滑", 0).show();
        			return true;
        		}
        		//上滑
        		if(e1.getRawY()-e2.getRawY()>touchSlop){
        			Toast.makeText(DataSaveCollectActivity.this, "上滑", 0).show();
        			return true;
        		}
        		// 控制只右滑 
                if (e2.getX() - e1.getX() > 0
                        && (e1.getX() >= 0 && e1.getX() <= 100)) { 
                    if (Math.abs(e2.getX() - e1.getX()) > Math.abs(e2.getY() - e1.getY()) 
                            && Math.abs(velocityX) > 1000) { 
                        finish(); 
                        onBackPressed(); 
                    } 
                } 
        		return super.onFling(e1, e2, velocityX, velocityY);
        	}
        });
        //数据库的简单使用
        //创建数据库
        SQLiteDatabase db=this.openOrCreateDatabase("datatest", Context.MODE_PRIVATE, null);
        //创建表
//        db.execSQL("create table szm(_id integer primary key autoincrement ,name varchar(20))");
        //插入数据
        db.execSQL("insert into szm (name) values(?)",new String[]{"szm1"});
        db.execSQL("insert into szm (name) values(?)",new String[]{"szm2"});
        //查询
//        db.execSQL("select * from szm");
        Cursor cursor=db.rawQuery("select * from szm", null);
        while(cursor.moveToNext()){
        	String id=cursor.getString(cursor.getColumnIndex("_id"));
        	String name=cursor.getString(cursor.getColumnIndex("name"));
        	Log.i("db--", "_id="+id+"  name="+name);
        }
        //删除
        db.execSQL("delete from szm where name=?",new String[]{"szm1"});
        //更新
        db.execSQL("update szm set name=? where _id=?",new String[]{"asd","2"});
    }
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			String name=et_name.getText().toString().trim();
			String pwd=et_pwd.getText().toString().trim();
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("name", name);
			map.put("pwd", pwd);
			SharedPreferenceUtil.writeToSP(this, "test", map);
			break;
		case R.id.btn_parser:
			List<Car> ll=XmlUtils.getCarList(this);
			Log.i("car--", ll.toString());
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
}