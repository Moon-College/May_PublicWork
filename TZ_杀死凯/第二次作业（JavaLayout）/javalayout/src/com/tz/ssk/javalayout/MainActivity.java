package com.tz.ssk.javalayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends Activity {

	private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mContext=this;
        
        //LinearLayout 初始化
        LinearLayout ll=new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams ll_lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(ll_lp);
        
        //Button 初始化
        Button bt=new Button(mContext);
        LayoutParams bt_lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        bt.setLayoutParams(bt_lp);
        bt.setText("搜索");
        
        //EditText 初始化
        EditText et=new EditText(mContext);
        LayoutParams et_lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(et_lp);
        
        
        ll.addView(et, 0);
        ll.addView(bt, 1);
        setContentView(ll);
    }

}
