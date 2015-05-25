package com.dd.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.chris.userdefineadapter.R;
import com.dd.bean.People;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter
{
	private List<People> mData;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public MainAdapter(List<People> mData, Context mContext)
	{
		this.mData = mData;
		this.mContext = mContext;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{
		return mData.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mData.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View itemView = mInflater.inflate(R.layout.charater_adapter_item_layout, null);
		People people = (People) getItem(position);	
		
		ImageView photoImg = (ImageView) itemView.findViewById(R.id.photoImg);
		photoImg.setImageResource(R.drawable.ic_launcher);
		
		TextView name = (TextView) itemView.findViewById(R.id.name);
		name.setText(people.getName());
		
		TextView phoneNum = (TextView) itemView.findViewById(R.id.phoneNum);
		phoneNum.setText(people.getPhoneNum());
		
		TextView sex = (TextView) itemView.findViewById(R.id.sex);
		sex.setText(people.getSex());	
		if (people.getSex().equals("男")) {
			itemView.setBackgroundColor(0xffff0000);
		}else {
			
		}
		return itemView;
	}

}
