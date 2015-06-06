package com.limz.intentdemo.activity;

import com.limz.intentdemo.constant.MyConstant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class IntentDemoActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private Button button;
	private Context mContext;
	private ImageView imageView;
	private Button btn1;
	private Button btn2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.mybutton);
        imageView = (ImageView) findViewById(R.id.photo);
        button.setOnClickListener(this);
        
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mybutton:
				Intent intent = new Intent();
				intent.setAction(MyConstant.TAKEPHOTOACTION);
				startActivityForResult(intent, MyConstant.MYREQUESTCODE);
				
				break;

			case R.id.button1:
				Intent intent2 = new Intent();
				intent2.setClass(this, OneAcitivity.class);
				startActivity(intent2);
				break;
				
			case R.id.button2:
				Intent intent3 = new Intent();
				intent3.setAction(MyConstant.ACTION_STRING);
				startActivity(intent3);
				break;
				
			default:
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != RESULT_OK) {
			Toast.makeText(mContext, "数据处理失败", Toast.LENGTH_LONG).show();
			return;
		}
		switch (requestCode) {
			case MyConstant.MYREQUESTCODE:
				Bundle bundle = data.getExtras();
				Bitmap map = (Bitmap) bundle.get("data");
				imageView.setImageBitmap(map);
				break;
	
			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}