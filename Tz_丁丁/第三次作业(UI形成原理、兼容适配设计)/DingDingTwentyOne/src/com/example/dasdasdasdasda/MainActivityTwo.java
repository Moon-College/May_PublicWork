package com.example.dasdasdasdasda;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivityTwo extends Activity {
	private int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout rl = new RelativeLayout(this);
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		rl.setLayoutParams(rlp);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setId(1);
		LinearLayout.LayoutParams llp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setLayoutParams(llp);

		final EditText et = new EditText(this);
		LinearLayout.LayoutParams llp2 = new LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		et.setLayoutParams(llp2);
		ll.addView(et, 0);

		Button bt = new Button(this);
		bt.setPadding(30, 30, 30, 30);
		bt.setText("ËÑË÷");
		ll.addView(bt, 1);
		
		rl.addView(ll,0);
		
		final LinearLayout ll1 = new LinearLayout(this);
		ll1.setOrientation(LinearLayout.VERTICAL);
		ll1.setBackgroundColor(0x44ff0000);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW,1);
		ImageView iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.ic_launcher);
		LinearLayout.LayoutParams gr = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		gr.gravity=Gravity.CENTER;
		ll1.addView(iv, gr);
		ll1.setLayoutParams(params);
		
		rl.addView(ll1,1);
		setContentView(rl);
		
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String trim = et.getText().toString().trim();
				final int parseInt = Integer.parseInt(trim);
				for (int i = 0; i < parseInt; i++) {
					TextView tv = new TextView(MainActivityTwo.this);
					tv.setText(i+1+"");
					LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);
					tv.setLayoutParams(lp);
					ll1.addView(tv, i);
				}
			}
		});
	}
		

}
