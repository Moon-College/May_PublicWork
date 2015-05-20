package com.tz.ssk.javalayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundResource(R.color.white);
        LayoutParams ll_lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(ll_lp);
        
        //LinearLayout 初始化
        LinearLayout ll_top=new LinearLayout(mContext);
        ll_top.setOrientation(LinearLayout.HORIZONTAL);
        ll_top.setBackgroundResource(R.color.blue);
        LayoutParams ll_toplp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll_top.setLayoutParams(ll_toplp);
        
        //Button 初始化
        Button bt=new Button(mContext);
        LayoutParams bt_lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        bt.setLayoutParams(bt_lp);
        bt.setText("搜索");
        
        //EditText 初始化
        EditText et=new EditText(mContext);
        LayoutParams et_lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(et_lp);
        
        
        //LinearLayout 初始化
        LinearLayout ll_middle=new LinearLayout(mContext);
        ll_middle.setGravity(Gravity.CENTER);
        LayoutParams ll_middlelp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll_middle.setLayoutParams(ll_middlelp);
        ll_middle.setBackgroundResource(R.color.yellow);
        
        
        //ImageView 初始化
        ImageView iv=new ImageView(mContext);
        LayoutParams iv_lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(iv_lp);
        iv.setImageResource(R.drawable.user);
        
        
        ll_top.addView(et, 0);
        ll_top.addView(bt, 1);
        ll_middle.addView(iv, 0);
        ll.addView(ll_top,0);
        ll.addView(ll_middle,1);
        setContentView(ll);
    }

}
