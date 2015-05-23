package com.javaview;

import com.second.tz.javaview.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity{
	private LinearLayout ll,iv_ll,ll_parent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ll_parent=GetView.getParent(this);
		
		ll=GetView.getTop(this,"搜索",new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
		ll_parent.addView(ll);
		
		init_image();
		ll_parent.addView(iv_ll);
		
		setContentView(ll_parent);
	}

	private void init_image() {
		iv_ll=new LinearLayout(this);
		iv_ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		iv_ll.setBackgroundColor(Color.YELLOW);
		
		ImageView iv=new ImageView(this);
		LinearLayout.LayoutParams iv_lp=(LinearLayout.LayoutParams) new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,500 );
		iv_lp.gravity=Gravity.CENTER_VERTICAL;
		iv.setLayoutParams(iv_lp);
		iv.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_launcher));
		iv.setScaleType(ScaleType.CENTER_CROP);
		iv_ll.addView(iv);		
	}

}
