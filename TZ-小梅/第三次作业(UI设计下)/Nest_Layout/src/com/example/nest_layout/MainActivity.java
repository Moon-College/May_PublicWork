package com.example.nest_layout;

import android.app.Activity;
import android.os.Bundle;
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
		//setContentView(R.layout.activity_main);
		RelativeLayout rl = new RelativeLayout(this);
		LinearLayout ll = new LinearLayout(this);
		//设置LinearLayout的排列方式为横向
		ll.setOrientation(LinearLayout.HORIZONTAL);
		//布局属性
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//把布局属性设置到LinearLayout容器中去
		ll.setLayoutParams(lp);
		//EditText控件
		EditText et_content = new EditText(this);
		//
		et_content.setHint("请输入要搜索的内容");
		//EditText在LinearLayout中的属性
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		et_content.setLayoutParams(llp);
		//添加到LinearLayout容器中去
		ll.addView(et_content,0);
		//Button控件
		Button bt_search = new Button(this);
		bt_search.setText("搜索");
		//添加
		ll.addView(bt_search,1);
		//把LinearLayout添加到RelativeLayout中去
		rl.addView(ll,0);
		//图片
		ImageView iv_image = new ImageView(this);
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//设置相对于父控件完全居中
		rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
		iv_image.setLayoutParams(rlp);
		iv_image.setImageResource(R.drawable.movie);
		rl.addView(iv_image,1);
		//把布局绑定到setContentView
		setContentView(rl);
		
		
	}
}
