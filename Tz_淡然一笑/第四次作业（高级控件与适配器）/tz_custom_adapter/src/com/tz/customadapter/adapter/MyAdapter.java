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
 * �Զ���������
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

	// �ж��ٸ���Ŀ
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

	// ÿ����Ŀ�ľ�������
	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/**
		 * //��ȡ��Ӧ�±��ѧ�� Student stu = data.get(position);
		 * //view����ĳһ����Ŀ���ⲿ����LinearLayout View view =
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
		 * colorValue.setBackgroundColor("Ů".equals(stu.getSex()) ? Color.RED :
		 * Color.BLUE); hobby.setText(stu.getHobby());
		 */

		ViewHolder holder = null;
		if (convertView == null) {
			// ˵��û�л���
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
		// ��ȡ��Ӧ�±��ѧ��
		Student stu = data.get(position);
		// ��������ʾ�ڶ�Ӧ�Ŀؼ���
		holder.faceImg.setImageResource(stu.getFaceImg());
		holder.netName.setText(stu.getNetName());
		holder.sex.setText(stu.getSex());
		holder.hobby.setText(stu.getHobby());
		convertView.setBackgroundColor("Ů" == stu.getSex() ? Color.RED : Color.BLUE);

		return convertView;
	}

	private class ViewHolder {
		// ͼ��
		ImageView faceImg;
		// ����
		TextView netName;
		// �Ա�
		TextView sex;
		// ����
		TextView hobby;
	}

}
