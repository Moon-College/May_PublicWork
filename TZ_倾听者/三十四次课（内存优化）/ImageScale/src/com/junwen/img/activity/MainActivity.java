package com.junwen.img.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.example.imagescale.R;
import com.junwen.img.util.ImageUtil;

public class MainActivity extends Activity {
	
	private ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		img = (ImageView) findViewById(R.id.img);
	}

	/**
	 * º”‘ÿÕº∆¨
	 * @param v
	 */
	public void onclick(View v){
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zhouzhuang);
//		img.setImageBitmap(ImageUtil.getBitamp(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/"+"img.jpg", 200,200));
		img.setImageBitmap(ImageUtil.getBitmap(bitmap, 500, 800));
	}

}
