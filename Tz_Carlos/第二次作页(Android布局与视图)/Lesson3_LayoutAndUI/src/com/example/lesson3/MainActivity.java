package com.example.lesson3;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
* @author Carlos
* @version 1.0
* @updateTime 2015年5月20日 下午11:55:08
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout contentView = new RelativeLayout(this);
		int padding = dipToPx(4);
		contentView.setPadding(padding, padding, padding, padding);
		EditText et = new EditText(this);
		et.setId(0xaaffee3c);
		RelativeLayout.LayoutParams lp_et = new RelativeLayout.LayoutParams(dipToPx(250), dipToPx(48));
		lp_et.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		et.setLayoutParams(lp_et);
		
		Button btn = new Button(this);
		btn.setGravity(Gravity.CENTER);
		btn.setText("click");
		RelativeLayout.LayoutParams lp_btn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, dipToPx(48));
		lp_btn.leftMargin = dipToPx(4);
		lp_btn.addRule(RelativeLayout.RIGHT_OF, et.getId());
		lp_btn.addRule(RelativeLayout.ALIGN_TOP, et.getId());
		btn.setLayoutParams(lp_btn);
		
		LinearLayout ll = new LinearLayout(this);
		RelativeLayout.LayoutParams lp_ll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		lp_ll.topMargin = dipToPx(4);
		lp_ll.addRule(RelativeLayout.BELOW, et.getId());
		ll.setLayoutParams(lp_ll);
		ll.setGravity(Gravity.CENTER);
		ll.setBackgroundColor(Color.GRAY);
		ImageView img = new ImageView(this);
		img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		img.setImageResource(R.drawable.ic_launcher);
		ll.addView(img);
		
		contentView.addView(et);
		contentView.addView(btn);
		contentView.addView(ll);
		
		setContentView(contentView);
	}
	
	private int dipToPx(int dp){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}
}
