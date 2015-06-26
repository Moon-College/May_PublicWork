package com.casit.bean;

import java.util.List;

import org.w3c.dom.Text;

import com.casit.hc.R;
import com.casit.students.MyStudents;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

	public Context context;
	public List<MyStudents> data;
	public LayoutInflater inflater;
	public MyAdapter(Context context,List<MyStudents> data ){
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
    @Override
    public void notifyDataSetChanged() {
    	// TODO Auto-generated method stub
    	
    	super.notifyDataSetChanged();
    }
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyStudents student = data.get(position);
		LinearLayout  linearLayout = (LinearLayout) inflater.inflate(R.layout.friendslist, null);
		ImageView imageview = (ImageView) linearLayout.findViewById(R.id.faceimage);
		TextView nickname = (TextView) linearLayout.findViewById(R.id.nickname);
		TextView sex = (TextView) linearLayout.findViewById(R.id.sex);
		TextView apperance = (TextView) linearLayout.findViewById(R.id.appearance);
		TextView hobby = (TextView) linearLayout.findViewById(R.id.hobby);
		imageview.setImageResource(student.getmStudentsFace());
		nickname.setText(student.getmNickName());
		if("ÄÐ" == student.getmSex())			
			linearLayout.setBackgroundColor(Color.argb(255, 0, 0, 255));
		else if("Å®" == student.getmSex())
			linearLayout.setBackgroundColor(Color.argb(255, 255, 0, 0));
		sex.setText(student.getmSex());
		apperance.setText(student.getmAppearance());
		hobby.setText(student.getmHobby());
		return linearLayout;
	}

}
