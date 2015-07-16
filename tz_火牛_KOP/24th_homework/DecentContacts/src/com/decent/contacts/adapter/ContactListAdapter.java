package com.decent.contacts.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.decent.contacts.R;
import com.decent.contacts.bean.Contact;

public class ContactListAdapter extends BaseAdapter {

	private List<Contact> mContactList;
	private Context mContext;

	public ContactListAdapter(Context context, List<Contact> data) {
		mContext = context;
		mContactList = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContactList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mContactList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Contact contact = mContactList.get(position);
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.contact_item, null);
		TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
		TextView phoneTv = (TextView) view.findViewById(R.id.phoneTv);
		TextView emailTv = (TextView) view.findViewById(R.id.emailTv);
		nameTv.setText(contact.getName());
		if (contact.getPhoneList().size() > 0) {
			phoneTv.setText(contact.getPhoneList().get(0));
		}
		emailTv.setText(contact.getEmail());
		if (position % 2 == 0) {
			view.setBackgroundResource(R.color.gray100);
		} else {
			view.setBackgroundResource(R.color.blue400);
		}
		return view;
	}

	public List<Contact> getContactList() {
		return mContactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.mContactList = contactList;
	}

}
