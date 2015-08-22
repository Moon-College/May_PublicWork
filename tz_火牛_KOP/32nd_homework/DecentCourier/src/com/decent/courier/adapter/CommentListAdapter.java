package com.decent.courier.adapter;

import java.util.List;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.decent.courier.common.CommentItem;

public class CommentListAdapter extends BaseAdapter {

	private Context mContext;
	private List<CommentItem> mCommentList;
	private LayoutInflater mLayoutInflater;
	
	public CommentListAdapter(List<CommentItem> commentList,
			Context contex) {
		// TODO Auto-generated constructor stub
		mContext = contex;
		mCommentList = commentList;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCommentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCommentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
