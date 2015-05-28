package com.tz.customadapter.adapter;

import java.util.List;
import com.tz.customadapter.R;
import com.tz.customadapter.bean.Student;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 自定义适配器
 * 
 * @author fcc
 * 
 */
public class MyAdapter extends BaseAdapter {

	private List<Student> data;
	private Context context;
	private LayoutInflater inflater;

	public MyAdapter(Context context, List<Student> data) {
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	// 有多少个条目
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

	// 每个条目的具体内容
	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/**
		 * //获取对应下标的学生 Student stu = data.get(position);
		 * //view就是某一个条目的外部容器LinearLayout View view =
		 * inflater.inflate(R.layout.list_item, null);
		 * 
		 * ImageView faceImg = (ImageView) view.findViewById(R.id.img); TextView
		 * netName = (TextView) view.findViewById(R.id.netName); TextView sex =
		 * (TextView) view.findViewById(R.id.sex); TextView colorValue =
		 * (TextView) view.findViewById(R.id.colorValue); TextView hobby =
		 * (TextView) view.findViewById(R.id.hobby);
		 * 
		 * faceImg.setImageResource(stu.getFaceImg());
		 * netName.setText(stu.getNetName()); sex.setText(stu.getSex());
		 * colorValue.setBackgroundColor("女".equals(stu.getSex()) ? Color.RED :
		 * Color.BLUE); hobby.setText(stu.getHobby());
		 */

		ViewHolder holder = null;
		if (convertView == null) {
			// 说明没有缓存
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.faceImg = (ImageView) convertView.findViewById(R.id.img);
			holder.netName = (TextView) convertView.findViewById(R.id.netName);
			holder.sex = (TextView) convertView.findViewById(R.id.sex);
			holder.hobby = (TextView) convertView.findViewById(R.id.hobby);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 获取对应下标的学生
		Student stu = data.get(position);
		// 将数据显示在对应的控件上
		holder.faceImg.setImageResource(stu.getFaceImg());
		holder.netName.setText(stu.getNetName());
		holder.sex.setText(stu.getSex());
		holder.hobby.setText(stu.getHobby());
		convertView.setBackgroundColor("女" == stu.getSex() ? Color.RED : Color.BLUE);

		return convertView;
	}

	private class ViewHolder {
		// 图像
		ImageView faceImg;
		// 网名
		TextView netName;
		// 性别
		TextView sex;
		// 爱好
		TextView hobby;
	}

}
