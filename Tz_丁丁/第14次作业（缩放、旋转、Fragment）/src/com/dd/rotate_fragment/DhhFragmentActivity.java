package com.dd.rotate_fragment;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v4.app.FragmentActivity;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DhhFragmentActivity extends FragmentActivity{
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity_main);
		textView = (TextView) findViewById(R.id.tv);
	}
	public TextView getTextView() {
		return textView;
	}
	public void setTextView(TextView textView) {
		this.textView = textView;
	}
}
