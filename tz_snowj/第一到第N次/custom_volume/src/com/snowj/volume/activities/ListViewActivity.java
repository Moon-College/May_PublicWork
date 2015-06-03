package com.snowj.volume.activities;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.snowj.volume.BaseActivity;
import com.snowj.volume.R;
import com.snowj.volume.model.StudentInfo;
import com.snowj.volume.widget.ListAdapter;
import com.snowj.volume.widget.ListItem;

/**
 * 第五次作业
 * @author lixuejiao
 *
 */
public class ListViewActivity extends BaseActivity{

	
	private ListView listView;
	
	
	
	private List<StudentInfo> list = new ArrayList<StudentInfo>();



	private ListAdapter<StudentInfo> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		getView();
		initView();
		initData();
	}
	
	/**获取控件*/
	private void getView() {
		listView = (ListView) findViewById(R.id.listView);
		
	}
	/**初始化控件*/
	private void initView() {
		adapter = new ListAdapter<StudentInfo>(list, buildListViewItem());
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				list.remove(position);
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	/**初始化数据源*/
	private void initData(){
		list.clear();
		for (int i = 0; i < 20; i++) {
			StudentInfo info = new StudentInfo();
			info.setHeadPortrait(getResources().getDrawable(R.drawable.ic_launcher));
			info.setNickName("student"+(i+1));
			if(i%2==0){
				info.setGender("girl");
				info.setColorValue(getResources().getString(R.string.color_girl));
				info.setInterest("film");
			}else{
				info.setGender("boy");
				info.setColorValue(getResources().getString(R.string.color_boy));
				info.setInterest("football");
			}
			list.add(info);
		}
		adapter.notifyDataSetChanged();
	}
	
	/**适配数据*/
	private ListItem buildListViewItem(){
		return new ListItem() {
			
			@SuppressLint("InlinedApi")
			@SuppressWarnings("deprecation")
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder vh;
				if(convertView ==null){
					vh = new ViewHolder();
					convertView  = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.item_listview, null);
					vh.iv_face = (ImageView) convertView.findViewById(R.id.iv_item);
					vh.tv_name = (TextView) convertView.findViewById(R.id.tv_item_name);
					vh.tv_gender = (TextView) convertView.findViewById(R.id.tv_item_gender);
					vh.tv_colorvalue = (TextView) convertView.findViewById(R.id.tv_item_colorvalue);
					vh.tv_interest = (TextView) convertView.findViewById(R.id.tv_item_interest);
					convertView.setTag(vh);
					
				}else{
					vh = (ViewHolder) convertView.getTag();
				}
				
				StudentInfo info = list.get(position);
				if (info.getGender().equals("girl")) {
					convertView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
				}else{
					convertView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
				}
				
				vh.iv_face.setBackgroundDrawable(info.getHeadPortrait());
				vh.tv_name.setText(info.getNickName());
				vh.tv_gender.setText(info.getGender());
				vh.tv_colorvalue.setText(info.getColorValue());
				vh.tv_interest.setText(info.getInterest());
				
				
				return convertView;
			}
		};
	}
	
	class ViewHolder{
		ImageView iv_face;
		TextView tv_name;
		TextView tv_gender;
		TextView tv_interest;
		TextView tv_colorvalue;
	}

}
