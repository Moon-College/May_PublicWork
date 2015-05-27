package com.xiaowei.tasktwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	/**最外层容器**/
	private LinearLayout layout_out;
	/**上面的水平容器**/
	private LinearLayout layout_top;
	private EditText et;
	/**下面的容器**/
	private LinearLayout layout_bottom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout_out = new LinearLayout(this);
		//设置排列属性(设置外层的线性布局为垂直排列)
		layout_out.setOrientation(LinearLayout.VERTICAL);
		layout_top = new LinearLayout(this);
		//设置上层的线性布局为水平排列
		layout_top.setOrientation(LinearLayout.HORIZONTAL);
		
		et = new EditText(this);
		LayoutParams params  = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);//权重为1
		et.setLayoutParams(params);
		
		Button submitBtn = new Button(this);
		LayoutParams params_btn = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		submitBtn.setLayoutParams(params_btn);
		submitBtn.setText("提交");
		//将 et和btn都添加到layout_tip中
		layout_top.addView(et,0);
		layout_top.addView(submitBtn,1);
		
		
		//设置底部布局
		layout_bottom  = new LinearLayout(this);
		LinearLayout.LayoutParams params_bottom = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		layout_bottom.setLayoutParams(params_bottom);
		layout_bottom.setGravity(Gravity.CENTER);
		layout_bottom.setBackgroundColor(0xff00ff);
		TextView tView = new TextView(this);
		tView.setText("动态添加布局代码");
		layout_bottom.addView(tView);
		
		//给最外层布局添加布局
		layout_out.addView(layout_top);
		layout_out.addView(layout_bottom);
		
		//设置试图
		setContentView(layout_out);
		
	}


}
