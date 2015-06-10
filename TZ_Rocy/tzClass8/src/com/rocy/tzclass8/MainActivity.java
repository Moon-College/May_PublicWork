package com.rocy.tzclass8;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private TextView tvCarema;
    private ImageView ivPicture;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FindViewUtril.getView(this);
		tvCarema.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent =new Intent();
		intent.setAction("van.rocy.camera");
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		startActivityForResult(intent, 100);
	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		if(arg0==100){
			if(arg1==200){
				//成功获取图片
			    Bitmap bitmap = arg2.getParcelableExtra("bitmap");
			    ivPicture.setImageBitmap(bitmap);
			}else if(arg1==400){
				Toast.makeText(this, "取消了", 1).show();
			}else if(arg1==500){
				Toast.makeText(this, "没有获取tupian", 1).show();
			}
		}
	}
}
