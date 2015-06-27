package com.caist.hc;

import java.io.File;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import com.caist.bean.NewAdapter;
import com.caist.container.MyContainer;
import com.caist.myfile.FolderFile;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MyFolderActivity extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */
	ListView listView;
	List<FolderFile> data;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         listView = (ListView) findViewById(R.id.lv);
         data = new ArrayList<FolderFile>();
         InitData(MyContainer.SD_PATH);
         listView.setOnItemClickListener(this);

    }
	private void InitData(String sdPath) {
		// TODO Auto-generated method stub
		data.clear();
		File file = new File(sdPath);
		if(null==file){
			Toast.makeText(this, "Ã»SD¿¨", 1000).show();
		}
		else{
			FolderFile back = new FolderFile();
			String back_path = sdPath.substring(0, sdPath.lastIndexOf("/"));
			back.setFilePath(back_path);
			back.setFileName("·µ»Ø");
			back.setFile(new File(back_path));
			back.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
			back.setImage(false);
			data.add(back);
			File[] file_list = file.listFiles();
			for(File f:file_list){
				FolderFile ffile = new FolderFile();
				ffile.setFile(f);
				ffile.setFilePath(f.getAbsolutePath());
				ffile.setFileName(f.getName());
				if(f.isDirectory()){
					ffile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));					
				}else if(ffile.getFileName().toLowerCase().endsWith(".jpg")
						||ffile.getFileName().toLowerCase().endsWith("png")) {
					//ffile.setBitmap(BitmapFactory.decodeFile(f.getAbsolutePath()));	
					ffile.setBitmap(null);
				}
				else{
					ffile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));					
				}
				data.add(ffile);	
			}
	         NewAdapter adapter = new NewAdapter(this,data);
	         listView.setAdapter(adapter);
		}		
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String path = data.get(position).getFilePath();
		Log.i("INFO",path);
		File file = new File(path);
		if(file.isDirectory()){
           InitData(file.getAbsolutePath());
		}
		// TODO Auto-generated method stub		
	}
}