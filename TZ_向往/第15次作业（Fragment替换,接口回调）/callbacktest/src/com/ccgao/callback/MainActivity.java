package com.ccgao.callback;

import com.ccgao.callback.Interface.MyListencener;
import com.ccgao.callback.view.MyView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements MyListencener{
	public MyView cb;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cb =(MyView) findViewById(R.id.cb);
        cb.setOnMyListencener(this);
    }

	public void onKnok(View v) {
		Toast.makeText(this, "很帅", 1000).show();
	}
}