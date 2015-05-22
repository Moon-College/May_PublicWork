package com.cn.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UiActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initViewByCode();
        
    }

	private void initViewByCode() {
		//最外层容器
		LinearLayout ll_all=new LinearLayout(this);
		ll_all.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		ll_all.setOrientation(LinearLayout.HORIZONTAL);
		
		//文本框
		EditText et=new EditText(this);
		et.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1));
		ll_all.addView(et,0);
		
		//按钮
		Button bt=new Button(this);
		bt.setText("搜索");
		bt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		ll_all.addView(bt,1);
		
		//设置布局
		setContentView(ll_all);
		
		
	}
}