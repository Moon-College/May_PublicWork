package com.example.lesson5;

import java.util.ArrayList;
import java.util.List;

import com.example.lesson5.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/** 
 * @author Carlos
 * @version 1.0
 * @updateTime 2015年5月25日 上午1:43:00
 * Description: 
 */
public class MainActivity extends Activity {
	private ListView mListView;
	private List<User> data = new ArrayList<User>();
	private MyAdapter mMyAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mListView = new ListView(this);
		mListView.setDividerHeight(4);
		//mListView.setDivider(new ColorDrawable(0xffA99797));
		setContentView(mListView);
		initData();
		mMyAdapter = new MyAdapter(this, data);
		mListView.setAdapter(mMyAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mMyAdapter.remove(position);
				mMyAdapter.notifyDataSetChanged();
			}
		});
	}

	private void initData() {
		data.add(new User("潭州-喵星人-深圳","小说,撸代码", 1, 75, R.drawable.head0));
		data.add(new User("潭州-snowj-上海","撸代码", 0, 80, R.drawable.head1));
		data.add(new User("潭州-逗比二号", "撸代码",1, 70, R.drawable.head2));
		data.add(new User("潭州-轩儿","看书,撸代码", 0, 98, R.drawable.head10));
		data.add(new User("潭州学院-瑶瑶老师","唱歌", 0, 100, R.drawable.head11));
		data.add(new User("潭州-徐半仙-北京","撸代码", 1, 70, R.drawable.head12));
		data.add(new User("谭州学院Grace老师","卖萌", 0, 100, R.drawable.head13));
		data.add(new User("潭州-大西/kf-粤","撸代码", 1, 80, R.drawable.head14));
		data.add(new User("潭州android助理—影子", "卖萌",0, 100, R.drawable.head15));
		data.add(new User("潭州-绯雨Chirs-深圳", "撸代码",1, 75, R.drawable.head3));
		data.add(new User("潭州-倾听者-福州","撸代码", 1, 80, R.drawable.head4));
		data.add(new User("潭州-依旧-北京","撸代码", 0, 80, R.drawable.head5));
		data.add(new User("潭州- 合群-江西","撸代码", 1, 70, R.drawable.head6));
		data.add(new User("潭州-fromZero-北京","撸代码", 1, 70, R.drawable.head7));
		data.add(new User("潭州-GEMTLEMAN-深圳","撸代码", 1, 88, R.drawable.head8));
		data.add(new User("潭州-Rocy-长沙","撸代码", 1, 78, R.drawable.head9));
		data.add(new User("潭州-binbin-上海","撸代码",1, 75, R.drawable.head16));
		data.add(new User("潭州-学渣-上海", "撸代码",1, 80, R.drawable.head17));
		
	}
	
	private class MyAdapter extends BaseAdapter{
		private Context context;
		private LayoutInflater mLayoutInflater;
		private List<User> list;
		
		

		public MyAdapter(Context context,List<User> list) {
			super();
			this.context = context;
			this.mLayoutInflater = LayoutInflater.from(context);
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list==null?0:list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		public void remove(int position){
			list.remove(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = mLayoutInflater.inflate(R.layout.list_item_user, null);
			}
			ImageView iv_head = ViewHolder.get(convertView, R.id.iv_head);
			TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
			TextView tv_sex = ViewHolder.get(convertView, R.id.tv_sex);
			TextView tv_appearance = ViewHolder.get(convertView, R.id.tv_appearance);
			TextView tv_hobby = ViewHolder.get(convertView, R.id.tv_hobby);
			User user = list.get(position);
			iv_head.setBackgroundResource(user.getHead());
			tv_name.setText(user.getName());
			String sex = "";
			if(user.getSex()==0){
				sex = "女";
				convertView.setBackgroundColor(Color.RED);
			}else{
				sex = "男";
				convertView.setBackgroundColor(Color.BLUE);
			}
			tv_sex.setText("性别:"+sex);
			tv_appearance.setText("颜值:"+user.getAppearance());
			tv_hobby.setText("爱好:"+user.getHobby());
			return convertView;
		}}

}
