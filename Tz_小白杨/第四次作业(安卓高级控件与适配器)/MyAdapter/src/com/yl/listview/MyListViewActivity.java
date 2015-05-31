package com.yl.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yl.adapter.MyAdapter;
import com.yl.bean.Person;

public class MyListViewActivity extends Activity implements OnItemClickListener {
    private ListView lv;
    private List<Person> list;
    private MyAdapter myadapter;
    int []img=new int[]{
    		R.drawable.danny,
    		R.drawable.david,
    		R.drawable.grace,
    		R.drawable.jason,
    		R.drawable.yaoyao,
    		R.drawable.yingzi,
    		R.drawable.me,
    		R.drawable.student,
    		R.drawable.student,
    		R.drawable.student    		
    		};
    String []name=new String[]{
    		"danny",  
    		"david",
    		"grace",
    		"jason",
    		"瑶瑶老师",
    		"影子老师",
    		"小白杨",
    		"大西",
    		"丁丁",
    		"逗比二号"   		
    		};
    String []sex=new String[]{
    		"男",  
    		"男",
    		"女",
    		"男",
    		"女",
    		"女",
    		"男",
    		"男",
    		"男",
    		"男"   		
    		};
    String []num=new String[]{
    		"100",  
    		"99",
    		"98",
    		"97",
    		"96",
    		"95",
    		"94",
    		"94",
    		"93",
    		"92"   		
    		};
    String []hobby=new String[]{
    		"讲英语，看书",  
    		"讲课",
    		"打篮球",
    		"唱歌",
    		"上课",
    		"学习",
    		"踢足球",
    		"看书",
    		"写字",
    		"讲英语，看书"   		
    		};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) this.findViewById(R.id.lv);
        init();
        lv.setOnItemClickListener(this);
    }
    /**
     * 初始化
     */
    public void init() {
    	list=new ArrayList<Person>();
    	for (int i = 0 ; i < 10 ; i++) {
    		Person person = new Person();
    		person.setImg(img[i]);
    		person.setName(name[i]);
    		person.setSex(sex[i]);
    		person.setNum(num[i]);
    		person.setHobby(hobby[i]);
    		list.add(person);
    	}
    	//SimpleAdapter sla=new SimpleAdapter(this, list, R.layout.layout_item, new String[]{"name","img"}, new int[]{R.id.textView1,R.id.img});
    	myadapter=new MyAdapter(this, list);
    	lv.setAdapter(myadapter);
    }
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		myadapter.data.remove(position);		
		myadapter.notifyDataSetChanged();
	}
}