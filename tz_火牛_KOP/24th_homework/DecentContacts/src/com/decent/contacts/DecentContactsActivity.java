package com.decent.contacts;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.decent.contacts.adapter.ContactListAdapter;
import com.decent.contacts.bean.Contact;
import com.decent.contacts.util.ContactsOperationUtil;
import com.decent.decentutil.ReflictionUtil;

public class DecentContactsActivity extends Activity implements
		OnClickListener, OnItemClickListener, OnItemLongClickListener, OnTouchListener {
	private static final String TAG = "DecentContactsActivity";
	private ListView listView;
	private List<Contact> mContactList;
	private ContactListAdapter mContactListAdapter;
	private ImageButton addNewBtn;
	private ViewGroup scrollView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflictionUtil.InjectionView(DecentContactsActivity.class.getName(),
				R.class.getName(), this);
		// 查询最新的联系人列表,初始化mContactListAdapter，设置进入listView
		mContactList = ContactsOperationUtil.getAllContacts(Contact.class,
				this, "deleted = ?", new String[] { String.valueOf(0) });
		mContactListAdapter = new ContactListAdapter(this, mContactList);
		listView.setAdapter(mContactListAdapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		listView.setOnTouchListener(this);
		addNewBtn.setOnClickListener(this);
		/*
		 * for (Contact contact : contactList) { DecentLogUtil.d(TAG,
		 * "name:"+contact.getName()); DecentLogUtil.d(TAG,
		 * "email:"+contact.getEmail()); List<String> phoneList =
		 * contact.getPhoneList(); for(String phone:phoneList){
		 * DecentLogUtil.d(TAG, "phone:"+phone); } DecentLogUtil.d(TAG,
		 * "name:"+contact.getName()); }
		 */
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		freshContactListView();
	}

	private void freshContactListView() {
		// TODO Auto-generated method stub
		mContactList = ContactsOperationUtil.getAllContacts(Contact.class,
				this, "deleted = ?", new String[] { String.valueOf(0) });
		mContactListAdapter.setContactList(mContactList);
		mContactListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addNewBtn:
			Intent intent = new Intent();
			intent.setClass(this, DecentContactAddActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Contact contact = mContactListAdapter.getContactList().get(position);
		Intent intent = new Intent();
		intent.putExtra("_id", contact.get_id());
		intent.setClass(this, DecentContactsEditActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		final Contact contact = mContactListAdapter.getContactList().get(
				position);
		AlertDialog alert = new AlertDialog.Builder(this)
				.setMessage("delete " + contact.getName() + "?")
				.setPositiveButton("confirm",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								ContactsOperationUtil.deleteSingleContactById(
										DecentContactsActivity.this, contact);
								freshContactListView();
							}
						}).setNegativeButton("cancel", null).create();
		alert.show();
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		/*
		 * 为了解决listView和scrollView的冲突
		 * 1、滑动事件希望scrollView来处理,scrollView去请求秘书放行
		 * 2、其他的正常处理
		 */
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			scrollView.requestDisallowInterceptTouchEvent(true);  
			break;

		default:
			break;
		}
 
		return super.onTouchEvent(event);
	}
}