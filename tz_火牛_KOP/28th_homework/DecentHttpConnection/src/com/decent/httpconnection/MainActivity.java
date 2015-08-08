package com.decent.httpconnection;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.decent.decentconnection.R;
import com.decent.httpconnection.bean.HttpResult;
import com.decent.httpconnection.util.HttpConnectionCallback;
import com.decent.httpconnection.util.HttpConnectionService;

public class MainActivity extends Activity implements OnClickListener,
		OnItemSelectedListener, HttpConnectionCallback {

	private static final String TAG = "MainActivity";
	private static final String REQUEST_URL = "http://10.0.2.2:8080/DecentWeb/indexPage";
	private String mSelectedMethod = HttpConnectionService.REQUEST_METHODS[0];
	private Map<String, String> mParameters;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mParameters = new HashMap<String, String>();
		mParameters.put("user_name", "firebull");
		mParameters.put("password", "123");
		Spinner requestMethodSpinner = (Spinner) findViewById(R.id.requestMethodSpinner);
		Button requestBtn = (Button) findViewById(R.id.requestBtn);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				HttpConnectionService.REQUEST_METHODS);
		requestMethodSpinner.setAdapter(arrayAdapter);
		requestMethodSpinner.setOnItemSelectedListener(this);
		requestBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.requestBtn:
			HttpConnectionService hcService = new HttpConnectionService(
					this, REQUEST_URL, mSelectedMethod, mParameters);
			hcService.doRequestHttpConnection();
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		mSelectedMethod = HttpConnectionService.REQUEST_METHODS[position];
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRequest(HttpResult result) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "resultcode:"+result.getResultCode(), Toast.LENGTH_SHORT).show();
		Toast.makeText(this, "content:\r\n"+result.getContent(), Toast.LENGTH_SHORT).show();
	}

}