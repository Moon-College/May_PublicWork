package com.junwen.activity;

import com.example.customrelative.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout relative = initView();
		setContentView(relative);
	}
	/**
	 * 自定义视图
	 * @return
	 */
	private RelativeLayout initView() {
		//外层RelativeLayout
		RelativeLayout reLayout = new RelativeLayout(this);
		reLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		//头部LinearLayout
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		LinearLayout linear = new LinearLayout(this);
		linear.setOrientation(LinearLayout.HORIZONTAL);
		linear.setLayoutParams(params);
		
		//EditText文本框
		EditText et_txt = new EditText(this);
		et_txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et_txt.setHint("请输入数据");
		
		//Button按钮
		Button btn = new Button(this);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn.setText("按钮");
		
		//ImageView图片
		RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		paramss.addRule(RelativeLayout.CENTER_IN_PARENT);
		ImageView img = new ImageView(this);
		img.setImageResource(R.drawable.movie);
		img.setLayoutParams(paramss);
		
		//把文本框和按钮添加进入头部LinearLayout里
		linear.addView(et_txt);
		linear.addView(btn);
		
		//把头部布局和图片一起加入外部RelativeLayout
		reLayout.addView(linear);
		reLayout.addView(img);
		
		//返回布局
		return reLayout;
	}

}
