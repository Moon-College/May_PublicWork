package com.decent.decentjson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.decent.decentutil.DecentLogUtil;
import com.decent.decentutil.ReflictionUtil;
import com.decent.httpconnection.HttpAccessCallback;
import com.decent.httpconnection.HttpAccessClientService;
import com.decent.httpconnection.HttpAccessService;
import com.decent.httpconnection.bean.HttpResult;
import com.decent.web.bean.SportRecord;

public class DecentJsonActivity extends Activity implements OnClickListener,
		HttpAccessCallback, OnItemSelectedListener {
	// genymotion使用 10.0.3.2:8080，Android默认虚拟机是10.0.2.2:8080，请注意
	private static final String JSON_HTTP_URL = "http://10.0.3.2:8080/DecentWeb/jsonPage";
	private EditText recordIdEt;
	private Button searchBtn;
	private TextView resultTv;
	private Spinner requestMethodSpinner;
	private HttpAccessService mHttpAccessService;
	private String mSelectedMethod;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentJsonActivity.class.getName(),
				R.class.getName(), this);
		searchBtn.setOnClickListener(this);

		mSelectedMethod = HttpAccessService.REQUEST_METHODS[0];
		requestMethodSpinner = (Spinner) findViewById(R.id.requestMethodSpinner);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				HttpAccessService.REQUEST_METHODS);
		requestMethodSpinner.setAdapter(arrayAdapter);
		requestMethodSpinner.setOnItemSelectedListener(this);

		mHttpAccessService = new HttpAccessClientService(this, JSON_HTTP_URL,
				mSelectedMethod, null);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String record_id = recordIdEt.getText().toString().trim();
		Map<String, String> map = new HashMap<String, String>();
		if (record_id != null && !"".equals(record_id)) {
			map.put("record_id", record_id);
			mHttpAccessService.setParameters(map);
		} else {
			mHttpAccessService.setParameters(null);
		}
		mHttpAccessService.setMethod(mSelectedMethod);
		mHttpAccessService.doRequestHttpAccessAsync();
		DecentLogUtil.d("INFO", "begin doRequestHttpAccessAsync!!!");
	}

	@Override
	public void afterRequest(HttpResult result) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		int resultCode = result.getResultCode();
		String content = result.getContent();
		sb.append("result code   :" + result.getResultCode() + "\r\n");
		sb.append("content       :" + content + "\r\n");
		sb.append("\r\n\r\n\r\n\r\n");

		if (resultCode == HttpResult.HTTP_OK && content != null
				&& !"".equals(content)) {
			List<JSONObject> srList = JSON.parseObject(content, List.class);
			if (srList != null) {
				sb.append("after fastJson parse," + srList.size()
						+ " SportRecord!!! \r\n");

				for (int i = 0; i < srList.size(); i++) {
					// sb.append(""+);
					JSONObject jSONObject = srList.get(i);
					SportRecord sr = JSON.parseObject(jSONObject.toString(),
							SportRecord.class);

					sb.append("SportRecord[" + i + "]     :" + sr.get_id()
							+ "," + sr.getDuration() + "," + sr.getName() + ","
							+ sr.getSport_type_id() + "," + sr.getStart_time()
							+ "," + sr.getUser_id() + "\r\n");
				}
			}
		}
		resultTv.setText(sb.toString());

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		mSelectedMethod = HttpAccessService.REQUEST_METHODS[position];
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}