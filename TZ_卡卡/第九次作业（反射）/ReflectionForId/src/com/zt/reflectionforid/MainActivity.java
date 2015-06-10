package com.zt.reflectionforid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tv1,tv2,tv3,tv4;
	private EditText et1;
	private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReflectionUtils.initViews(this);
        Toast.makeText(this, et1.getText(), 2000).show();
    }


    
}
