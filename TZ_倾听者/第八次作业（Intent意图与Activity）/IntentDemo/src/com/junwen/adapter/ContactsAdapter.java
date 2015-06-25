package com.junwen.adapter;

import java.util.List;

import com.example.intentdemo.R;
import com.junwen.bean.Contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {
	
	//上下文
	private Context context;
	//联系人集合
	private List<Contacts> data;
	//布局加载器
	private LayoutInflater inflater;

	public ContactsAdapter(Context context, List<Contacts> data) {
		this.context = context;
		this.data = data;
		//获取布局加载器
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_layout, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.number = (TextView) convertView.findViewById(R.id.number);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//获取当前的联系人
		Contacts contacts = data.get(position);
		//设置联系人
		holder.name.setText(contacts.getName());
		//对获取出来的电话号码进行修改
		String phoneNum = contacts.getPhoneNum().replace("-", " ").replace(" ", "");
		holder.number.setText(phoneNum.trim());
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView number;
	}
}
