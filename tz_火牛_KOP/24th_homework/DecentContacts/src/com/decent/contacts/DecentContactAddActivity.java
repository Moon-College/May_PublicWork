package com.decent.contacts;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.decent.contacts.bean.Contact;
import com.decent.contacts.util.ContactsOperationUtil;
import com.decent.decentutil.DecentLogUtil;
import com.decent.decentutil.ReflictionUtil;

public class DecentContactAddActivity extends Activity implements
		OnClickListener {
	private static final String TAG = "DecentContactAddActivity";
	private EditText nameEt;
	private EditText phoneEt;
	private EditText emailEt;
	private Button confirmAddBtn;
	private Button cancelAddBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_add);
		ReflictionUtil.InjectionView(DecentContactAddActivity.class.getName(),
				R.class.getName(), this);
		confirmAddBtn.setOnClickListener(this);
		cancelAddBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.confirmAddBtn:
			String name = nameEt.getText().toString().trim();
			String email = emailEt.getText().toString().trim();
			String phone = phoneEt.getText().toString().trim();
			List<String> phoneList = new ArrayList<String>();
			phoneList.add(phone);
			Contact c = new Contact();
			c.setName(name);
			c.setEmail(email);
			c.setPhoneList(phoneList);
			ContactsOperationUtil.insertSingleContact(this, c, new String[] {
					"name", "email", "phoneList" });
			finish();
			break;
		case R.id.cancelAddBtn:
			DecentLogUtil.d(TAG, "into cancelAddBtn");
			finish();
			break;
		default:
			break;
		}
	}

}
