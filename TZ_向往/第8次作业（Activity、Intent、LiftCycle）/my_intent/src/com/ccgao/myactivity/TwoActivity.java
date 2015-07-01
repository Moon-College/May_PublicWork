package com.ccgao.myactivity;

import java.io.Serializable;

import com.ccgao.myactivity.bean.User;
import com.ccgao.myactivity.bean.MyClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TwoActivity extends Activity implements OnClickListener {
	private MyClass classes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout relativeLayout = new RelativeLayout(this);
		LayoutParams layoutParams=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		relativeLayout.setLayoutParams(layoutParams);
		//添加一个居中的imageView
		ImageView imageView = new ImageView(this);
		RelativeLayout.LayoutParams rlp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rlp.addRule(RelativeLayout.CENTER_IN_PARENT);  //将图片控件居中
		imageView.setImageResource(R.drawable.ic_launcher);
		imageView.setLayoutParams(rlp);
		imageView.setOnClickListener(this);
		//将ImageView放入控件 
		relativeLayout.addView(imageView);
		
		//获取传递过来的值
		Intent intent = this.getIntent();
		int intExtra = intent.getIntExtra("data1", 0);
		boolean booleanExtra = intent.getBooleanExtra("data2", true);
		User user = (User) intent.getSerializableExtra("data3");
		Log.i("INFO", user.getUserName());
		
		classes = intent.getParcelableExtra("data4");
		Log.i("INFO", classes.getClassName());
		
		//最后将布局放入
		setContentView(relativeLayout);
	}

	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra("backdata", classes);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}
}
