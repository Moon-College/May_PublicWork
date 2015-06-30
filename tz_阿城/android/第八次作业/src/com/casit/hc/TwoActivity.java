package com.casit.hc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TwoActivity extends Activity implements OnClickListener {
	 MyClass myclass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RelativeLayout relativeLayout = new RelativeLayout(this);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT );
		relativeLayout.setLayoutParams(layoutParams);
		//Ìí¼ÓÒ»¸öIAMGEVIEW
		ImageView imageView = new ImageView(this);
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	    rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
	    imageView.setImageResource(R.drawable.ic_launcher);
	    imageView.setLayoutParams(rlp);	    
	    imageView.setOnClickListener(this);
	    relativeLayout.addView(imageView);
	    Intent intent = this.getIntent();
	    myclass = (MyClass) intent.getParcelableExtra("data1");
        setContentView(relativeLayout);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("INFO", "onclick");
		Log.i("INFO", myclass.getClassName());
		Intent data = new Intent();
		data.putExtra("backdata", myclass);
		setResult(RESULT_OK, data);
	    Toast.makeText(this, myclass.getClassName(), 2000).show();
	    finish();
	}
}
