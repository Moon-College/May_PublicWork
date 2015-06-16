package com.xigua.myreflection;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private ImageView image;
	private TextView text;
	private EditText edit;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ReflectionUtil.initViews(this);
		
	}

	//ÔÚXMLÖÐ
	public void showtoast(View view){
		Toast.makeText(this, edit.getText(), Toast.LENGTH_LONG).show();
	}

}
