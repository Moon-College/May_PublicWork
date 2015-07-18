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
import com.decent.decentutil.ReflictionUtil;

public class DecentContactsEditActivity extends Activity implements
		OnClickListener {
	private EditText editNameEt;
	private EditText editPhoneEt;
	private EditText editEmailEt;
	private Button confirmEditBtn;
	private Button cancelEditBtn;
	private int edit_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_edit);
		ReflictionUtil.InjectionView(
				DecentContactsEditActivity.class.getName(), R.class.getName(),
				this);
		edit_id = getIntent().getIntExtra("_id", -1);
		List<Contact> contactList = ContactsOperationUtil.getAllContacts(Contact.class,this,
				"deleted = ? and _id = ?", new String[] { String.valueOf(0),
						String.valueOf(edit_id) });

		if (contactList.size() == 0) {
			finish();
			return;
		}
		Contact contact = contactList.get(0);
		editNameEt.setText(contact.getName());
		editPhoneEt.setText(contact.getPhoneList().get(0));
		editEmailEt.setText(contact.getEmail());
		confirmEditBtn.setOnClickListener(this);
		cancelEditBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.confirmEditBtn:
			Contact contact = new Contact();
			String name = editNameEt.getText().toString().trim();
			String email = editEmailEt.getText().toString().trim();
			String phone = editPhoneEt.getText().toString().trim();
			List<String> phoneList = new ArrayList<String>();
			phoneList.add(phone);
			contact.set_id(edit_id);
			contact.setName(name);
			contact.setPhoneList(phoneList);
			contact.setEmail(email);
			ContactsOperationUtil.updateContact(this, contact, new String[] {
					"name", "email", "phoneList" });
			finish();
			break;
		case R.id.cancelEditBtn:
			finish();
			break;
		default:
			break;
		}
	}
}
