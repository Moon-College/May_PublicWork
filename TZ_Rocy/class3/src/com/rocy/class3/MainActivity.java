package com.rocy.class3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText mEditText;
	private RelativeLayout rl;
	private Button mButton;
	private ImageView mImageView;
	private int anthorId =1000;//首次添加id

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_main);
		//全局的容器
		rl =new RelativeLayout(this);
		android.widget.RelativeLayout.LayoutParams alllp=new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT, android.widget.RelativeLayout.LayoutParams.MATCH_PARENT); 
		rl.setLayoutParams(alllp);
		//顶层容器
		LinearLayout ll =new LinearLayout(this);
		LayoutParams lp =new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(lp);
		//编辑框
		mEditText =new EditText(this);
		LayoutParams editTextLP =new LayoutParams(0,LayoutParams.WRAP_CONTENT,1);
		mEditText.setLayoutParams(editTextLP);
		ll.addView(mEditText);
		//按钮
		mButton =new Button(this);
		LayoutParams buttonLP =new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mButton.setLayoutParams(buttonLP);
		//设置点击监听
		mButton.setOnClickListener(this);
		mButton.setTag("mButton");
		mButton.setText("点击");
		ll.addView(mButton);
		//添加子容器
		rl.addView(ll,0);
		//图片
		mImageView =new ImageView(this);
		android.widget.RelativeLayout.LayoutParams mImageViewLP =new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT); 
		mImageView.setLayoutParams(mImageViewLP);
		mImageView.setBackgroundResource(R.drawable.ic_launcher);
		mImageView.setId(anthorId);
		mImageViewLP.addRule(RelativeLayout.CENTER_IN_PARENT);
		rl.addView(mImageView,1);
		//添加到视图中
		setContentView(rl);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String content =mEditText.getText().toString();
		if("mButton".equals(arg0.getTag())){
			//.当edittetext为“增加”的时候，在图片下面增加一条记录
			//当edittetext为“减少”的时候，在图片下面增加一条记录
			if("增加".equals(content)){
				//添加
				TextView mTextView =new TextView(this);
				android.widget.RelativeLayout.LayoutParams textViewLP =new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT); 
				mTextView.setLayoutParams(textViewLP);
				textViewLP.addRule(RelativeLayout.BELOW, anthorId);
				mTextView.setId(anthorId+1);
				mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
				anthorId=anthorId+1;
				mTextView.setText("我来了啊啊啊");
				//添加到容器的位置
				rl.addView(mTextView,rl.getChildCount());
			}
			else if("减少".equals(content)){
				int childCount = rl.getChildCount();
				//只有2个控件时候，说明神马都没有添加
				if(childCount<=2){
					Toast.makeText(this, "╮(╯_╰)/神马都没有", 1000).show();
				}else{
					rl.removeViewAt(childCount-1);
					anthorId=anthorId-1;
				}
			}
			else {
				Toast.makeText(this, "╮(╯_╰)/我只认识增加或减少", 1000).show();
			}
		}
	}
}
