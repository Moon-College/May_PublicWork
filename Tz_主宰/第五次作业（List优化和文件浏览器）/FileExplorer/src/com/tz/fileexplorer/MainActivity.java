package com.tz.fileexplorer;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.tz.filebrowser.adapter.BrowserAdapter;
import com.tz.filebrowser.consts.FileType;
import com.tz.filebrowser.manager.DataProvider;
import com.tz.filebrowser.vo.MyFile;

public class MainActivity extends Activity {
	private ListView listView;
	private BrowserAdapter adapter;
	private TextView pathView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DataProvider.getInstance(this);

		// 创建自定义adapter
		List<MyFile> data = DataProvider.getInstance().getFilesFromPath(
				DataProvider.ROOT_PATH);
		adapter = new BrowserAdapter(this, data);

		pathView = (TextView) findViewById(R.id.main_current_path);
		pathView.setText(DataProvider.ROOT_PATH);

		// 初始化listview
		listView = (ListView) findViewById(R.id.main_listview);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyFile myFile = (MyFile) adapter.getItem(position);
				if (myFile.getFileType() == FileType.DIR) {
					pathView.setText(myFile.getPath());
					adapter.clear();
					adapter.addItems(DataProvider.getInstance(MainActivity.this).getFilesFromPath(
							myFile.getPath()));
					adapter.notifyDataSetChanged();
				} else {
					DataProvider.getInstance().openFile(myFile.getFile());
				}
			}
		});
		listView.setAdapter(adapter);
	}
}
