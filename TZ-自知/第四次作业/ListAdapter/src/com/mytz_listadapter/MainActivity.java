package com.mytz_listadapter;
import java.util.ArrayList;
import java.util.List;

import com.example.listadapter.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity{
	private ListView listview;
	private List<UserBean> userlist=new ArrayList<UserBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listview=(ListView) findViewById(R.id.listview);
		
		initData();
		final MyAdapter ma=new MyAdapter(userlist, this);
		listview.setAdapter(ma);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ma.datalist.remove(position);
				ma.notifyDataSetChanged();
			}
		});
	}
	private void initData() {
		
		UserBean userBean0=new UserBean(R.drawable.a0, "张三", "男", 65, "吃饭  睡觉  打豆豆");
		UserBean userBean1=new UserBean(R.drawable.a1, "张三丰", "男", 44, "吃饭  运动");
		UserBean userBean2=new UserBean(R.drawable.a2, "赵敏", "女", 88, "打篮球  LOL");
		UserBean userBean3=new UserBean(R.drawable.a3, "张无忌", "男", 99, "吃饭  睡觉");
		UserBean userBean4=new UserBean(R.drawable.a4, "张燕", "女", 33, "吃饭  睡觉  打豆豆");
		UserBean userBean5=new UserBean(R.drawable.a5, "张蕾", "女", 99, "打篮球  打豆豆");
		UserBean userBean6=new UserBean(R.drawable.a6, "张铁林", "男", 11, "吃饭  睡觉");
		UserBean userBean7=new UserBean(R.drawable.a7, "张雨晴", "女", 87, "吃饭  打豆豆");
		UserBean userBean8=new UserBean(R.drawable.a8, "张鸽", "女", 43, "睡觉  打豆豆");
		UserBean userBean9=new UserBean(R.drawable.a9, "张芳", "女", 54, "打架  打豆豆");
		UserBean userBean10=new UserBean(R.drawable.a10, "张道陵", "男", 14, "砍人 睡觉  打豆豆");
		UserBean userBean11=new UserBean(R.drawable.a11, "张雅", "女", 88, "吃饭  学习  打豆豆");
		UserBean userBean12=new UserBean(R.drawable.a12, "张宗祥", "男", 77, "吃饭  熬夜  旅游");
		UserBean userBean13=new UserBean(R.drawable.a13, "张敏", "女", 66, "吃饭  睡觉  看小说");
		UserBean userBean14=new UserBean(R.drawable.a14, "张甜甜", "男", 56, "吃饭  睡觉");
		userlist.add(userBean14);
		userlist.add(userBean13);
		userlist.add(userBean12);
		userlist.add(userBean11);
		userlist.add(userBean10);
		userlist.add(userBean9);
		userlist.add(userBean8);
		userlist.add(userBean7);
		userlist.add(userBean6);
		userlist.add(userBean5);
		userlist.add(userBean4);
		userlist.add(userBean3);
		userlist.add(userBean2);
		userlist.add(userBean1);
		userlist.add(userBean0);
	}
}
