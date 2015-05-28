package com.example.demolistview;

import java.util.ArrayList;

import com.example.demolistview.adapter.ListViewAdapter;
import com.example.demolistview.adapter.bean.Star;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayList<Star>stars;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView=(ListView)findViewById(R.id.listView);
		stars=new ArrayList<Star>();
		String[] names=new String[]{"葛优","谢娜","马可","汪涵","李湘","成龙","刘德华","张学友","谭维维","韩红"};
		int[] imgs=new int[]{
				R.drawable.pic1,
				R.drawable.pic2,
				R.drawable.pic3,
				R.drawable.pic1,
				R.drawable.pic2,
				R.drawable.pic3,
				R.drawable.pic1,
				R.drawable.pic3,
				R.drawable.pic6,
				R.drawable.pic2,};
		String[] sex=new String[]{"男","女","男","男","女","男","男","男","女","女",}; 
		for(int i=0;i<10;i++){
			Star star=new Star();
			star.setName(names[i]);
			star.setSex(sex[i]);
			star.setHead(imgs[i]);
			star.setFace("98");
			star.setHoobby("运动");
			stars.add(star);
		}
		final ListViewAdapter adapter=new ListViewAdapter(this, stars);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				stars.remove(position);
				adapter.notifyDataSetChanged();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
