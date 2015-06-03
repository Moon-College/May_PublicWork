package com.snowj.volume.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.snowj.volume.BaseActivity;
import com.snowj.volume.R;

/**
 * 第三次作业
 * @author lixuejiao
 *
 */
@SuppressLint("NewApi")
public class SearchActivity extends BaseActivity{

	
	private EditText et;
	private Button btn;
	private LinearLayout linearLayout;
	private LinearLayout.LayoutParams tvParams;
	private LinearLayout linearLayout2;



	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		LayoutParams params2 = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,0.2f);
		LinearLayout.LayoutParams lParams2 = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,0.8f);
		tvParams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		//父类布局
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(params);
		ll.setOrientation(LinearLayout.VERTICAL);
		//子类横向布局
		linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(params2);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		//子类纵向布局
		linearLayout2 = new LinearLayout(this);
		linearLayout2.setLayoutParams(params);
		linearLayout2.setOrientation(LinearLayout.VERTICAL);
		
		
		/**
		 * 横向布局et+btn
		 */
		et = new EditText(this);
		et.setLayoutParams(lParams);
		et.setId(1);
		et.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		
		btn = new Button(this);
		btn.setLayoutParams(lParams2);
		btn.setText(getResources().getString(R.string.btn1));
		btn.setOnClickListener(this);
		btn.setId(2);
		
		linearLayout.addView(et);
		linearLayout.addView(btn);
		
		
		
		ll.addView(linearLayout);
		ll.addView(linearLayout2);
		setContentView(ll);
	}
	
	
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case 2:
//			goBack();
			addView();
			break;

		default:
			break;
		}
	}



	private void addView() {
		int n = Integer.valueOf(et.getText().toString());
		if(n>0){
			for (int i = 0; i < n; i++) {
				TextView tv = new TextView(this);
				tv.setLayoutParams(tvParams);
				tv.setText(String.valueOf(i+1));
				tv.setGravity(Gravity.CENTER);
				linearLayout2.addView(tv);
			}
		}
	}



	private void goBack() {
		String str = et.getText().toString();
		if(str!=null&&!str.equals("")){
			
			Intent data = new Intent();
			data.putExtra("str", str);
			setResult(100, data);
			finish();
		}else{
			Toast.makeText(this, getResources().getString(R.string.search_key), Toast.LENGTH_SHORT).show();
		}
		
	}



}
