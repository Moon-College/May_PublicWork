package com.zht.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * ClassName:MainActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-4 ÏÂÎç6:55:23 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class MainActivity extends Activity {
	
	public static final int ID_BTN = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//×ÜµÄ¸¸ÈÝÆ÷
		RelativeLayout rl = new RelativeLayout(this);
		rl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		//°´Å¥¿Ø¼þ
		Button btn = new Button(this);
		btn.setText("ËÑË÷");
		btn.setId(ID_BTN);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
				startActivity(intent);
			}
		});
		
		RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btnParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rl.addView(btn, btnParams);
		
		//ÊäÈë¿ò¿Ø¼þ
		EditText et = new EditText(this);
		RelativeLayout.LayoutParams etParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		etParams.addRule(RelativeLayout.LEFT_OF, ID_BTN);
		rl.addView(et, etParams);
		
		//Í¼Æ¬
		ImageView iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.ic_launcher);
		RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ivParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		rl.addView(iv, ivParams);
		
		setContentView(rl);
	}
}
