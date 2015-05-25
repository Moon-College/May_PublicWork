package com.chris.userdefineadapter.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.chris.userdefineadapter.character.Characters;
import com.chris.userdefineadapter.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CharaterAdapter extends BaseAdapter
{
	private List<Characters> mData;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public CharaterAdapter(List<Characters> mData, Context mContext)
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
		Characters oneChar = (Characters) getItem(position);	
		
		ImageView faceImg = (ImageView) itemView.findViewById(R.id.faceImg);
		faceImg.setImageResource(oneChar.getFaceImg());
		
		TextView name = (TextView) itemView.findViewById(R.id.name);
		name.setText(oneChar.getName());
		
		TextView phoneNum = (TextView) itemView.findViewById(R.id.phoneNum);
		phoneNum.setText(oneChar.getPhoneNum());
		
		TextView sex = (TextView) itemView.findViewById(R.id.sex);
		sex.setText(oneChar.getSex());	
		
		return itemView;
	}

}
