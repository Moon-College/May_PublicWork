package com.xigua.observer;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{

    Button mButton;
    EditText mEditText;
    RealAObserver realAObserver;
    RealObserver observer1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.mButton);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mButton.setOnClickListener(this);
        realAObserver = new RealAObserver();//创建了一个被观察者
        observer1 = new RealObserver();
        realAObserver.registeObserver(observer1);
        
    }
	public void onClick(View v) {
		if(!mEditText.getText().toString().equals("")){
		int number =Integer.valueOf(mEditText.getText().toString().trim());
		if(number == 1){
			//通知观察者数据发生了变化
			realAObserver.notifyAllObserver();
		}
		}
	}

}
