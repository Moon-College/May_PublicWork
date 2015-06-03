package com.tz.listview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {

	private MyAdapter adapter;
	
	private ListView mListView;
	
	private Context mContext;
	
	private ArrayList<Person> perList = new ArrayList<Person>();
	
	private static final int imgID[] = {R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,
		R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,R.drawable.img_7,
		R.drawable.img_8,R.drawable.img_9,R.drawable.img_10,R.drawable.img_11,
		R.drawable.img_12,R.drawable.img_13,R.drawable.img_14,R.drawable.img_15};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;
		loadData();
		adapter = new MyAdapter(mContext,perList);
		mListView = (ListView) findViewById(R.id.listview);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}
	
	private void loadData() {
		perList.add(new Person(imgID[0],"Luccy","Ů","��ɫ����","����Ӱ"));
		perList.add(new Person(imgID[1],"Danny","��","˧������","����"));
		perList.add(new Person(imgID[2],"Kate","��","С�ɹ���","�����"));
		perList.add(new Person(imgID[3],"Spinn","��","��ɫ����","����"));
		perList.add(new Person(imgID[4],"Slina","Ů","��������","���տ�"));
		perList.add(new Person(imgID[5],"Malia","Ů","��������","������"));
		perList.add(new Person(imgID[6],"Otcab","Ů","������","��˧��"));
		perList.add(new Person(imgID[7],"Tank","��","����һ��","����"));
		perList.add(new Person(imgID[8],"Jany","��","��������","��Ӿ"));
		perList.add(new Person(imgID[9],"Mute","��","Ӣ������","����"));
		perList.add(new Person(imgID[10],"Kucct","��","����һ��","�ռ��ɶ�"));
		perList.add(new Person(imgID[11],"Ecuty","��","����ͨͨ","���"));
		perList.add(new Person(imgID[12],"Zhang","Ů","�����߻�","����"));
		perList.add(new Person(imgID[13],"Nubia","��","����֮��","׷��"));
		perList.add(new Person(imgID[14],"Diaosi","��","������©","�������"));
	}

	private class MyAdapter extends BaseAdapter {

		private Context context;
		
		private ArrayList<Person> perList = new ArrayList<Person>();
		
		public MyAdapter(Context context,ArrayList<Person> perList) {
			this.context = context;
			this.perList = perList;
		}
		
		public void setNewData(ArrayList<Person> data) {
			this.perList.clear();
			if(data != null) {
				this.perList.addAll(data);
			}
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			
			return perList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
				holder.photo = (ImageView) convertView.findViewById(R.id.img_photo);
				holder.onlineName = (TextView)convertView.findViewById(R.id.tv_online);
				holder.sex = (TextView)convertView.findViewById(R.id.tv_sex);
				holder.beauty = (TextView)convertView.findViewById(R.id.tv_beauty);
				holder.hobby = (TextView)convertView.findViewById(R.id.tv_hobby);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			if(perList != null && perList.size() > 0) {
				holder.photo.setImageResource(perList.get(position).getPhotoId());
				holder.onlineName.setText("������" + perList.get(position).getOnlineName());
				
				holder.sex.setText("�Ա�:" + perList.get(position).getSex());
				if(perList.get(position).getSex().equals("��")) {
					convertView.setBackgroundResource(R.drawable.man_bg_selector);
				}else {
					convertView.setBackgroundResource(R.drawable.woman_bg_selector);
				}
				holder.hobby.setText("����:" + perList.get(position).getHobby());
				holder.beauty.setText(perList.get(position).getBeauty());
			}
			return convertView;
		}
		
		private class ViewHolder {
			ImageView photo;
			TextView onlineName;
			TextView sex;
			TextView beauty;
			TextView hobby;
		}
		
	}

	Dialog dialog;
	
	private void showDelteDialog(final int position) {
		String message = null;
		String sex = perList.get(position).getSex();
		if(sex.equals("��")) {
			message = "��ȷ��Ҫɾ�����˧����?";
		}else {
			message = "��ȷ��Ҫɾ�������Ů��?";
		}
		
		dialog = new AlertDialog.Builder(mContext).setTitle("ɾ��").setMessage(message).
				setPositiveButton("ȷ��",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteData(position);
				
			}
		}).setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).show();
	}
	
	void deleteData(int position) {
		ArrayList<Person> tempList = new ArrayList<Person>();
		for(int i = 0; i < perList.size(); i++) {
			if(i != position) {
				tempList.add(perList.get(i));
			}
		}
		
		perList.clear();
		perList = tempList;
		adapter.setNewData(perList);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		showDelteDialog(position);
	}

}
