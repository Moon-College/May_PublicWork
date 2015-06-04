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
	
	//������
	private Context context;
	//��ϵ�˼���
	private List<Contacts> data;
	//���ּ�����
	private LayoutInflater inflater;

	public ContactsAdapter(Context context, List<Contacts> data) {
		this.context = context;
		this.data = data;
		//��ȡ���ּ�����
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
		//��ȡ��ǰ����ϵ��
		Contacts contacts = data.get(position);
		//������ϵ��
		holder.name.setText(contacts.getName());
		//�Ի�ȡ�����ĵ绰��������޸�
		String phoneNum = contacts.getPhoneNum().replace("-", " ").replace(" ", "");
		holder.number.setText(phoneNum.trim());
		return convertView;
	}

	class ViewHolder {
		TextView name;
		TextView number;
	}
}
