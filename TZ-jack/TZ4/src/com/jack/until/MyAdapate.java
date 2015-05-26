package com.jack.until;

import java.util.List;

import com.jack.cn.R;
import com.jack.entity.Student;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapate extends BaseAdapter {

	Context con;
	List<Student> li;
	public MyAdapate(Context context,List list){
		con=context;
		li=list;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return li.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return li.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Student student=li.get(position);//得到一个 条目对象
		Ltem item;
		View view;
	   if(convertView==null){
		  convertView=LayoutInflater.from(con).inflate(R.layout.item,null);		
		  item=new Ltem();
		  //绑定条目
		  item.imageView=(ImageView) convertView.findViewById(R.id.image);
		  item.t1=(TextView) convertView.findViewById(R.id.t1);
		  item.t3=(TextView) convertView.findViewById(R.id.t3);
		  item.t4=(TextView) convertView.findViewById(R.id.t4);
		  convertView.setTag(item);
	   }else{
		  item= (Ltem) convertView.getTag();
	   }
	  
	 //赋值
	   if(student.getT3().equals("女")||student.getT3().equals("女神")) {
		 convertView.setBackgroundColor(Color.RED);
	   }
	   item.imageView.setImageResource(student.getImgs());
	   item.t1.setText(student.getT1());
	   item.t3.setText(student.getT3());
	   item.t4.setText(student.getT4());
		return convertView;
	}
	
	class Ltem{
		ImageView imageView;
		TextView t1;
		TextView t3;
		TextView t4;
	}

}
