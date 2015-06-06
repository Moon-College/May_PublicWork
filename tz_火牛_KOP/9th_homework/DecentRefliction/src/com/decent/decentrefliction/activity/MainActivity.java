package com.decent.decentrefliction.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.decent.decentrefliction.R;
import com.decent.decentrefliction.util.ReflictionUtil;

public class MainActivity extends Activity implements OnClickListener
{
	private static final String TAG = "MainActivity";
	private EditText et_name;
	private EditText et_gender;
	private EditText et_phone;
	private EditText et_address;
	private Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		ReflictionUtil.InjectionView(this);
		btn_submit.setOnClickListener(this);
	}

	private String getSubmitInfo()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(et_name.getEditableText().toString().trim());
		sb.append(",");
		sb.append(et_gender.getEditableText().toString().trim());
		sb.append(",");
		sb.append(et_phone.getEditableText().toString().trim());
		sb.append(",");
		sb.append(et_address.getEditableText().toString().trim());
		return sb.toString();
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		Toast.makeText(this, getSubmitInfo(), Toast.LENGTH_LONG).show();
	}

}
