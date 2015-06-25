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
		perList.add(new Person(imgID[0],"Luccy","女","国色天香","看电影"));
		perList.add(new Person(imgID[1],"Danny","男","帅气逼人","听歌"));
		perList.add(new Person(imgID[2],"Kate","男","小巧乖萌","玩泥巴"));
		perList.add(new Person(imgID[3],"Spinn","男","国色天香","跳舞"));
		perList.add(new Person(imgID[4],"Slina","女","妖艳妩媚","吃烧烤"));
		perList.add(new Person(imgID[5],"Malia","女","天生丽质","钓凯子"));
		perList.add(new Person(imgID[6],"Otcab","女","倾国倾城","看帅哥"));
		perList.add(new Person(imgID[7],"Tank","男","长相一般","赛车"));
		perList.add(new Person(imgID[8],"Jany","男","凡夫俗子","游泳"));
		perList.add(new Person(imgID[9],"Mute","男","英俊潇洒","足球"));
		perList.add(new Person(imgID[10],"Kucct","男","挫男一个","收集股东"));
		perList.add(new Person(imgID[11],"Ecuty","男","普普通通","溜冰"));
		perList.add(new Person(imgID[12],"Zhang","女","闭月羞花","炒股"));
		perList.add(new Person(imgID[13],"Nubia","男","阳刚之美","追星"));
		perList.add(new Person(imgID[14],"Diaosi","男","霸气侧漏","潮流打扮"));
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
				holder.onlineName.setText("网名：" + perList.get(position).getOnlineName());
				
				holder.sex.setText("性别:" + perList.get(position).getSex());
				if(perList.get(position).getSex().equals("男")) {
					convertView.setBackgroundResource(R.drawable.man_bg_selector);
				}else {
					convertView.setBackgroundResource(R.drawable.woman_bg_selector);
				}
				holder.hobby.setText("爱好:" + perList.get(position).getHobby());
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
		if(sex.equals("男")) {
			message = "你确定要删除这个帅哥吗?";
		}else {
			message = "你确定要删除这个美女吗?";
		}
		
		dialog = new AlertDialog.Builder(mContext).setTitle("删除").setMessage(message).
				setPositiveButton("确定",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteData(position);
				
			}
		}).setNegativeButton("取消", new OnClickListener() {
			
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
