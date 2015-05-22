package com.example.lesson4;

import com.example.lesson4.R;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
		final EditText et = new EditText(this);
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
		
		ScrollView sv = new ScrollView(this);
		sv.setBackgroundColor(Color.GRAY);
		RelativeLayout.LayoutParams lp_sv = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		lp_sv.topMargin = dipToPx(4);
		lp_sv.addRule(RelativeLayout.BELOW, et.getId());
		sv.setLayoutParams(lp_sv);
		final LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ScrollView.LayoutParams lp_ll = new ScrollView.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp_ll);
		sv.addView(ll);
		
		
		contentView.addView(et);
		contentView.addView(btn);
		contentView.addView(sv);
		
		setContentView(contentView);
		
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				ll.removeAllViews();
				try {
					Integer count = Integer.valueOf(et.getText().toString());
					int height = dipToPx(30);
					int margin = dipToPx(4);
					for(int i=0;i<count;i++){
						TextView tv = new TextView(MainActivity.this);
						tv.setBackgroundColor(i%2==0?Color.GREEN:Color.BLUE);
						tv.setTextColor(Color.WHITE);
						tv.setGravity(Gravity.CENTER);
						tv.setText(String.valueOf(i));
						LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
						lp.topMargin = margin;
						lp.leftMargin = margin;
						lp.rightMargin = margin;
						ll.addView(tv, lp);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
	}
	
	private int dipToPx(int dp){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}
}
