package com.junwen.contact.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactdemo.R;
import com.junwen.contact.modle.Contacts;

public class ContactsAdapter extends BaseAdapter{

	private Context context;
	private List<Contacts> data; //通讯录集合
	private LayoutInflater inflater;
	
	public ContactsAdapter(Context context, List<Contacts> data) {
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			convertView = inflater.inflate(R.layout.item_listview, null);
			holder = new ViewHolder();
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
			holder.tv_email = (TextView) convertView.findViewById(R.id.tv_email);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Contacts contacts = data.get(position);
		holder.tv_name.setText(contacts.getName());
		holder.tv_number.setText(contacts.getPhoneNumber());
		holder.tv_email.setText(contacts.getEmail());
		return convertView;
	}
	class ViewHolder {
		TextView tv_name;
		TextView tv_number;
		TextView tv_email;
	}
	
	
	
}
