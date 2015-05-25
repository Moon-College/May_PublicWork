package com.tz.ssk.advancedwidget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	private ListView mlistview;
	private Context mContext;
	private MyAdapter mMyAdapter;
	private List<Entity> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_main);
        mlistview=(ListView) findViewById(R.id.listview);
        initdata();
    }
    
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 10;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder mViewHolder=null;
			if(convertView==null)
			{
				mViewHolder=new ViewHolder();
				convertView=LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
				
				mViewHolder.iv_userimg=(ImageView) convertView.findViewById(R.id.iv_userimg);
				mViewHolder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
				mViewHolder.tv_sex=(TextView) convertView.findViewById(R.id.tv_sex);
				mViewHolder.tv_hobby=(TextView) convertView.findViewById(R.id.tv_hobby);
				mViewHolder.tv_yanvalue=(TextView) convertView.findViewById(R.id.tv_yanvalue);
				convertView.setTag(mViewHolder);
			}else
			{
				mViewHolder=(ViewHolder) convertView.getTag();
			}
			
			mViewHolder.tv_name.setText("昵称:"+list.get(position).name);
			mViewHolder.tv_sex.setText("性别:"+list.get(position).sex);
			mViewHolder.tv_hobby.setText("爱好:"+list.get(position).hobby);
			mViewHolder.tv_yanvalue.setText("颜值:"+(list.get(position).yanvalue>100?"全宇宙最高":list.get(position).yanvalue));
			mViewHolder.iv_userimg.setImageResource(list.get(position).userimg);
			return convertView;
		}
		
		final class ViewHolder {
			ImageView iv_userimg;
			TextView tv_name;
			TextView tv_sex;
			TextView tv_hobby;
			TextView tv_yanvalue;
		}
    }
	private void initdata() {
		// TODO Auto-generated method stub
    	list=new ArrayList<MainActivity.Entity>();
    	Entity et=new Entity();
    	et.name="潭州-Danny";
    	et.sex="男";
    	et.hobby="挖挖挖坑";
    	et.userimg=R.drawable.tz3;
    	et.yanvalue=101;
    	list.add(et);
    	et=new Entity();
    	et.name="潭州-喵星人-深圳";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head0;
    	et.yanvalue=75;
    	list.add(et);
    	et=new Entity();
    	et.name="潭州-snowj-上海";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head1;
    	et.yanvalue=75;
    	list.add(et);
    	et=new Entity();
    	et.name="潭州-逗比二号";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head2;
    	et.yanvalue=75;
    	list.add(et);
    	
      	et=new Entity();
    	et.name="潭州-轩儿";
    	et.sex="女";
    	et.hobby="未知";
    	et.userimg=R.drawable.head10;
    	et.yanvalue=101;
    	list.add(et);
    	
    	et=new Entity();
    	et.name="潭州学院-瑶瑶老师";
    	et.sex="女";
    	et.hobby="未知";
    	et.userimg=R.drawable.head11;
    	et.yanvalue=101;
    	list.add(et);
    	
     	et=new Entity();
    	et.name="潭州-徐半仙-北京";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head12;
    	et.yanvalue=75;
    	list.add(et);
    	
      	et=new Entity();
    	et.name="谭州学院Grace老师";
    	et.sex="女";
    	et.hobby="未知";
    	et.userimg=R.drawable.head13;
    	et.yanvalue=101;
    	list.add(et);
    	
    	
    	et=new Entity();
    	et.name="潭州-大西/kf-粤";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head14;
    	et.yanvalue=20;
    	list.add(et);
    	
    	et=new Entity();
    	et.name="潭州android助理—影子";
    	et.sex="女";
    	et.hobby="未知";
    	et.userimg=R.drawable.head15;
    	et.yanvalue=101;
    	list.add(et);
    	
     	et=new Entity();
    	et.name="潭州-绯雨Chirs-深圳";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head3;
    	et.yanvalue=60;
    	list.add(et);
    	
       	et=new Entity();
    	et.name="潭州-倾听者-福州";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head4;
    	et.yanvalue=40;
    	list.add(et);
    	
    	et=new Entity();
    	et.name="潭州-依旧-北京";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head5;
    	et.yanvalue=50;
    	list.add(et);
    	
    	et=new Entity();
    	et.name="潭州- 合群-江西";
    	et.sex="男";
    	et.hobby="女";
    	et.userimg=R.drawable.head6;
    	et.yanvalue=60;
    	list.add(et);
		
    	 mMyAdapter=new MyAdapter();
         mlistview.setAdapter(mMyAdapter);
	}
    private class Entity{
    	public int userimg;
    	public String name;
    	public String sex;
    	public String hobby;
    	public int yanvalue;
    }
   
}
