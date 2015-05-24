package com.tx.adpter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
    private ListView mListView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
       
    }

	private void initUI() {
		 mListView=(ListView)findViewById(R.id.listView);
	     peopleAdapter smAdapter = new peopleAdapter(this, getDate());
	     mListView.setAdapter(smAdapter);
	}
	/**
	 * 获取数据
	 * @return people实体
	 */
	private List<People> getDate(){
		List<People> lists = new ArrayList<People>();
		
		People date1 = new People();
		date1.setIcon(R.drawable.f10);
		date1.setName("小茹");
		date1.setSex("女");
		date1.setColorValue("颜值:88");
		date1.setInterest("旅游");
		lists.add(date1);
		People date2 = new People();
		date2.setIcon(R.drawable.f1);
		date2.setName("Walker.sun");
		date2.setSex("男");
		date2.setColorValue("颜值:66");
		date2.setInterest("游戏");
		lists.add(date2);
		People date3 = new People();
		date3.setIcon(R.drawable.f2);
		date3.setName("Sunday");
		date3.setSex("男");
		date3.setColorValue("颜值:77");
		date3.setInterest("运动");
		lists.add(date3);
		People date4 = new People();
		date4.setIcon(R.drawable.f3);
		date4.setName("蓝天");
		date4.setSex("男");
		date4.setColorValue("颜值:72");
		date4.setInterest("lol");
		lists.add(date4);
		People date5 = new People();
		date5.setIcon(R.drawable.f4);
		date5.setName("Grace老师");
		date5.setSex("女");
		date5.setColorValue("颜值：88");
		date5.setInterest("看书");
		lists.add(date5);
		People date6 = new People();
		date6.setIcon(R.drawable.f5);
		date6.setName("坚持着唯一的执着");
		date6.setSex("男");
		date6.setColorValue("颜值：79");
		date6.setInterest("美食");
		lists.add(date6);
		People date7 = new People();
		date7.setIcon(R.drawable.f6);
		date7.setName("倾听者_");
		date7.setSex("男");
		date7.setColorValue("颜值:75");
		date7.setInterest("爬山");
		lists.add(date7);
		People date8 = new People();
		date8.setIcon(R.drawable.f7);
		date8.setName("〆、执笔丶写青春");
		date8.setSex("男");
		date8.setColorValue("颜值:70");
		date8.setInterest("骑行");
		lists.add(date8);
		People date9 = new People();
		date9.setIcon(R.drawable.f8);
		date9.setName("卡卡");
		date9.setSex("男");
		date9.setColorValue("颜值：74");
		date9.setInterest("游泳");
		lists.add(date9);
		People date10 = new People();
		date10.setIcon(R.drawable.f9);
		date10.setName("杀死凯");
		date10.setSex("男");
		date10.setColorValue("颜值:76");
		date10.setInterest("足球");
		lists.add(date10);
		
		return lists;	
	}
}