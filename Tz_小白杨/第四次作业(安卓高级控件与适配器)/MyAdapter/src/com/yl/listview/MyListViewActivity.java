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
    		"������ʦ",
    		"Ӱ����ʦ",
    		"С����",
    		"����",
    		"����",
    		"���ȶ���"   		
    		};
    String []sex=new String[]{
    		"��",  
    		"��",
    		"Ů",
    		"��",
    		"Ů",
    		"Ů",
    		"��",
    		"��",
    		"��",
    		"��"   		
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
    		"��Ӣ�����",  
    		"����",
    		"������",
    		"����",
    		"�Ͽ�",
    		"ѧϰ",
    		"������",
    		"����",
    		"д��",
    		"��Ӣ�����"   		
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
     * ��ʼ��
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