package com.zjm.activity;

import com.zjm.activity.view.SlidingButton;
import com.zjm.activity.view.SlidingButton.PriceListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements PriceListener {
	
	SlidingButton sdBtn;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sdBtn = (SlidingButton) findViewById(R.id.sdBtn);
		sdBtn.setOnChangerPriceListener(this);
		tv = (TextView) findViewById(R.id.tv);
	}

	@Override
	public void onChanger(int priceUp, int priceDown) {
		String str = "所选价格为："+priceDown+"元~"+priceUp+"元";
		tv.setText(str);
	}

	
}
