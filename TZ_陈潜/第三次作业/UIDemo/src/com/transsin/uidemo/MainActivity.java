package com.transsin.uidemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int i=0;
    private Button bt;
    private EditText et;
    private LinearLayout  ll2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//父容器线性布局垂直排列
		LinearLayout ll=new LinearLayout(this);
		ll.setOrientation(1);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		//放一个线性布局水平排列
		LinearLayout linearlayout=new LinearLayout(this);
		linearlayout.setOrientation(0);
		LinearLayout.LayoutParams lps=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		linearlayout.setLayoutParams(lps);
		
		//水平布局里面放一个EditText
	    et=new EditText(this);
		LinearLayout.LayoutParams etlp=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1);
		et.setLayoutParams(etlp);
		linearlayout.addView(et);
		
		//水平布局里面放一个Button
	    bt=new Button(this);
		LinearLayout.LayoutParams btlp=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
		bt.setText("搜索");
		bt.setLayoutParams(btlp);
		linearlayout.addView(bt);
		
		
		ScrollView sv=new ScrollView(this);
		LayoutParams lpp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		sv.setLayoutParams(lpp);
		
		
	    ll2=new LinearLayout(this);
		ll2.setOrientation(1);
		LinearLayout.LayoutParams lps2=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		ll2.setLayoutParams(lps2);
		sv.addView(ll2);
		
		ll.addView(linearlayout);
		ll.addView(sv);
		setContentView(ll);
		
		setListener();
		
	}

	private void setListener() {
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str1=et.getText().toString();
					TextView tv=new TextView(MainActivity.this);
				    LinearLayout.LayoutParams lptv=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					tv.setLayoutParams(lptv);
					ll2.addView(tv);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
