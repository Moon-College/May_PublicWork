package com.example.demolistview.adapter;

import java.util.ArrayList;

import com.example.demolistview.R;
import com.example.demolistview.adapter.bean.Star;

import android.content.Context;
import android.graphics.LinearGradient;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private ArrayList<Star> stars; 
	private LayoutInflater inflater;
	private Star star;
	
    public ListViewAdapter(Context context,ArrayList<Star> stars) {
    	if(stars!=null){
		 this.stars=stars;
    	}else{
    		stars=new ArrayList<Star>();
    	}
    	inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return stars.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return stars.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	ViewHolder viewHolder=null;
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=inflater.inflate(R.layout.list_item, null);
			viewHolder=new ViewHolder();
			viewHolder.tvName=(TextView)convertView.findViewById(R.id.tvName);
			viewHolder.tvSex=(TextView)convertView.findViewById(R.id.tvSex);
			viewHolder.head=(ImageView)convertView.findViewById(R.id.imgHead);
			viewHolder.tvface=(TextView)convertView.findViewById(R.id.tvface);
			viewHolder.tvHobby=(TextView)convertView.findViewById(R.id.tvHobby);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
	    star=stars.get(position);
	    viewHolder.tvName.setText(star.getName());
	    viewHolder.tvSex.setText(star.getSex());
	    viewHolder.tvface.setText(star.getFace());
	    viewHolder.tvHobby.setText(star.getHoobby());
	    viewHolder.head.setBackgroundResource(star.getHead());
		
	    if(stars.get(position).getSex().equals("ÄÐ")){
	    	convertView.setBackgroundResource(R.color.light_blue);
	    }else{
	    	convertView.setBackgroundResource(R.color.light_red);
	    }
		return convertView;
	}
 class ViewHolder{
		private TextView tvName;
		private TextView tvSex;
		private ImageView head;
		private TextView tvface;
		private TextView tvHobby;
	}
     @Override
    public void notifyDataSetChanged() {
	// TODO Auto-generated method stub
	super.notifyDataSetChanged();
}

}
