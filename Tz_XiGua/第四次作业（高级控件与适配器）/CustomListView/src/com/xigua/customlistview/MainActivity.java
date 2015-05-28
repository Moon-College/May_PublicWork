package com.xigua.customlistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private ListView mList;
	private ListAdapter mAdapter;
	private List<TzStudent> list;
	private int[] imgs = {R.drawable.binbin,R.drawable.crystal,R.drawable.danny,R.drawable.david,
			R.drawable.dingding,R.drawable.eyre,R.drawable.feiyu,R.drawable.grace,R.drawable.jason,
			R.drawable.yaoyao};
	private String[] names = {"binbin","crystal","danny","david","dingding",
			"eyre","feiyu","grace","jason","yaoyao"};
	private String[] favorite = {"ÀºÇò","ÓðÃ«Çò","×ãÇò","Æ¹ÅÒÇò","Ì¨Çò","¸ß¶û·òÇò",
			"ÍøÇò","¿´Çò","¼ªËû","ÅÅÇò"};
	private String[] appearance = {"ÑÕÖµ£º80","ÑÕÖµ£º82","ÑÕÖµ£º81","ÑÕÖµ£º84","ÑÕÖµ£º83","ÑÕÖµ£º85","ÑÕÖµ£º84","ÑÕÖµ£º85","ÑÕÖµ£º82","ÑÕÖµ£º85"};
	private String[] sex = {"ÄÐ","Å®","ÄÐ","ÄÐ","ÄÐ","ÄÐ","ÄÐ","Å®","ÄÐ","Å®"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mList = (ListView) findViewById(R.id.list);
		list = new ArrayList<TzStudent>();
		for(int i=0;i<10;i++){
			TzStudent tzStudent = new TzStudent();
			tzStudent.setImg(imgs[i]);
			tzStudent.setName(names[i]);
			tzStudent.setSex(sex[i]);
			tzStudent.setFavorite(favorite[i]);
			tzStudent.setAppearance(appearance[i]);
			list.add(tzStudent);
		}
		mAdapter = new ListAdapter(this, list);
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Toast.makeText(MainActivity.this, list.get(position).getName()+"ÒÑ¾­±»É¾³ý", Toast.LENGTH_SHORT).show();
				list.remove(position);
				mAdapter.notifyDataSetChanged();
				
			}
			
		});
		
        
	}

	

}
