package com.wrz.customadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.wrz.customadapter.adapter.PeopleAdapter;
import com.wrz.customadapter.bean.People;

public class MainActivity extends Activity {
	
	ListView lv; 
	List<People> data;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initCustomAdapter();
    }

	private void initCustomAdapter() {
		// 获取ListView
		lv = (ListView) findViewById(R.id.lv);
		getData();
		PeopleAdapter paAdapter = new PeopleAdapter(this, data);
		lv.setAdapter(paAdapter);
	}
	
	public List<People> getData(){
		data = new ArrayList<People>();
		// 头像
		int[] imgs = new int[]{
				R.drawable.hz1, // 索隆
				R.drawable.hz3, // 娜美
				R.drawable.hz2, // 香吉士
				R.drawable.hz4, // 罗宾
				R.drawable.hz5,  // 路飞
				R.drawable.yw_1,
				R.drawable.yw_2,
				R.drawable.yw_3,
				R.drawable.yw_4,
				R.drawable.yw_5
		};
		// 名字 
		String[] names = new String[]{
				"索隆",
				"娜美",
				"香吉士",
				"罗宾",
				"路飞",
				"纳兹",
				"露西",
				"忘了名字",
				"这个也忘了",
				"忘了+1"
				
		};
		
		// 性别
		String[] sexs = new String[]{"男","女","男","女","男","男","女","男","男","女"};
		
		// 颜值
		String[] numbers = new String[]{
				"颜值：80",
				"颜值：95",
				"颜值：75",
				"颜值：100",
				"颜值：100",
				"颜值：100",
				"颜值：79",
				"颜值：67",
				"颜值：89",
				"颜值：100"
				
		};
		
		//爱好
		String[] likes = new String[]{
				"爱好：练剑，健身，战斗",
				"爱好：贪财",
				"爱好：美食，美女",
				"爱好：读书，探险",
				"爱好：吃肉",
				"爱好：不熟啊啊 啊",
				"爱好：不熟啊啊 啊",
				"爱好：不熟啊啊 啊",
				"爱好：不熟啊啊 啊",
				"爱好：不熟啊啊 啊"
		};
		for (int i = 0; i < 10; i++) {
			People people = new People();
			people.setName(names[i]);
			people.setHeadImg(imgs[i]);
			people.setSex(sexs[i]);
			people.setLike(likes[i]);
			people.setFaceNumber(numbers[i]);
			data.add(people);
		}
		return data;
	}
}