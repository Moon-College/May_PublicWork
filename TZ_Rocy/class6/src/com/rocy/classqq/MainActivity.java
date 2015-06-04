package com.rocy.classqq;

import java.io.File;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import com.rocy.classqq.bean.SDCard;
import com.rocy.classqq.observer.ASpeakObserver;
import com.rocy.classqq.observer.SpeakObserver;
import com.rocy.classqq.observer.SpeakObservered;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView lv;
	private List<SDCard> list =new ArrayList<SDCard>();
	private MyAdapter adapter;
	private SpeakObservered observered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView)findViewById(R.id.lv);
		File root =Environment.getExternalStorageDirectory();
		//被观察者
		observered =new SpeakObservered(this);
		observered.registerObserver(new SpeakObserver(this));
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//sd卡存在
			initdata(root.getAbsolutePath());
			adapter =new MyAdapter(this, list);
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					SDCard sdCard = list.get(position);
					File file = sdCard.getFile();
					if(file.isDirectory()){
						//是目录
						list.clear();
						initdata(file.getAbsolutePath());
						adapter.notifyDataSetChanged();
					}else{
						if(file.getName().toLowerCase().endsWith(".jpg")){
							//监控图片
							observered.setMap(true);
							observered.notifySetChange();
						}else{
							//监控
							observered.setMap(false);
							observered.notifySetChange();
						}
					}
				}
			});
		}else{
			Toast.makeText(this, "sd卡不存在", 1000).show();
		}
	}
	//获取数据
	private void initdata(String absolutePath) {
		// TODO Auto-generated method stub
		Log.i("info", absolutePath);
		//上一级路径
		String back=absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		File backFile =new File(back);
		SDCard sd =new SDCard();
		sd.setMap(false);
		sd.setDownLoad(false);
		sd.setName("返回");
		sd.setFile(backFile);
		list.add(sd);
		File root =new File(absolutePath);
		File[] files = root.listFiles();
		for (File file : files) {
			SDCard child =new SDCard();
			if(file.getName().toLowerCase().endsWith(".jpg")||file.getName().toLowerCase().endsWith(".png")){
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.gege);
				child.setBitmap(bitmap);
				child.setMap(true);
				sd.setDownLoad(false);
			}else{
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.file);
				child.setBitmap(bitmap);
				child.setMap(false);
				sd.setDownLoad(false);
			}
			child.setName(file.getName());
			child.setFile(file);
			list.add(child);
		}
	}
}
