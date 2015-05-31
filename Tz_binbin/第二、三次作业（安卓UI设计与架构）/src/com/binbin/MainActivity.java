package com.binbin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

/**
 * 
 * @author wangbin
 *
 */
public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//主容器
		RelativeLayout reLayout=new RelativeLayout(this);
		RelativeLayout.LayoutParams imageLayoutParam=new RelativeLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		imageLayoutParam.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		//顶部容器
		LinearLayout layout=new LinearLayout(this);
		
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		EditText et=new EditText(this);
		Button btn=new Button(this);
		LinearLayout.LayoutParams llp=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
		et.setLayoutParams(llp);
		et.setHint("请输入网址");

		LinearLayout.LayoutParams llp2=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,0);
		btn.setLayoutParams(llp2);
		btn.setText("搜索");
		
		
		ImageView iv=new ImageView(this);
		iv.setImageResource(R.drawable.mm);
		iv.setLayoutParams(imageLayoutParam);
		
		reLayout.addView(layout);
		reLayout.addView(iv);
		
		layout.addView(et,0);
		layout.addView(btn,1);
		setContentView(reLayout);
	}

}
