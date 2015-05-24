package com.tz.michael.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tz.michael.adapter.MyListAdapter;
import com.tz.michael.bean.Students;

public class TaskFourActivity extends Activity implements OnItemClickListener {
	
	private ListView  lv;
	private MyListAdapter adapter;
	private List<Students> ll;
	/**头像数组*/
	private int[] potraits=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};
	/**名字数组*/
	private String[] names=new String[]{"张三","王五","赵六","公孙氏","西门氏","夏侯氏","诸葛氏","欧阳氏","独孤氏"};
	/**年龄数组*/
	private int[] ages=new int[]{18,20,30,38,23,33,28,40,45};
	/**兴趣数组*/
	private String[] interests=new String[]{"迟到","偷网","睡觉","研习兵法","风流","皮糙肉厚","出谋划策","独孤九剑"};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.main);
        lv=(ListView) findViewById(R.id.lv);
        adapter=new MyListAdapter(this, ll, R.layout.lv_items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    /**
     * 初始化数据
     */
	private void initData() {
		ll=new ArrayList<Students>();
		for(int i=0;i<20;i++){
			Students s=new Students();
			s.setName(names[i%names.length]);
			s.setAge(ages[i%ages.length]);
			s.setInterest(interests[i%interests.length]);
			s.setPotrait(potraits[i%potraits.length]);
			s.setGender(i%2);
			ll.add(s);
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ll.remove(position);
		adapter.notifyDataSetChanged();
	}
}