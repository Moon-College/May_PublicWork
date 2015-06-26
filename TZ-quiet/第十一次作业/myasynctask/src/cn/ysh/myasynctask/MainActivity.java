package cn.ysh.myasynctask;

import cn.ysh.refutil.MyReflection;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button button1;
	private Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);
		MyReflection.initView(this, "findViewById");
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent =new Intent();
		switch (v.getId()) {
		case R.id.button1:
			intent.setClass(this, DownloadPicActivity.class);
			break;
		case R.id.button2:
			intent.setClass(this, DownloadApkActivity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}
}
