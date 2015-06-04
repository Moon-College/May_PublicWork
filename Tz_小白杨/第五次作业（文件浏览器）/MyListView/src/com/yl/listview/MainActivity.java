package com.yl.listview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.yl.adapter.ListViewAdapter;
import com.yl.bean.SdFile;
import com.yl.constants.MyConstant;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView lv;
	private List<SdFile> filelist;
	private SdFile sdfile;
	private ListViewAdapter adapter;
	private String parentPath;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) this.findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        filelist=new ArrayList<SdFile>();
        initData(MyConstant.Root);
    }
    private void initData(String path){
    	filelist.clear();
    	File file=new File(path);
    	if(!file.exists()){
    		Toast.makeText(this, "没有sd卡", 1000).show();
    	}else{
    		//初始化返回按扭
	    	sdfile=new SdFile();
	    	sdfile.setName("返回");
	    	parentPath=path.substring(0, path.lastIndexOf("/"));
	    	Log.i("INFO", parentPath);
	    	sdfile.setPath(parentPath);
	    	sdfile.setFile(new File(parentPath));
	    	sdfile.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.bak));
	    	sdfile.setIsbak(true);
	    	filelist.add(sdfile);
	    	
	    	//获得文件列表
	    	File [] farray=file.listFiles(); 
	    	for ( File f : farray){
	    		
	    		sdfile=new SdFile();
	    		sdfile.setName(f.getName());
	    		sdfile.setPath(f.getAbsolutePath());
	    		sdfile.setFile(f);
	    		if (f.isDirectory()){
	    			sdfile.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
	    		}else if(sdfile.getName().toLowerCase().endsWith(".png") || sdfile.getName().toLowerCase().endsWith(".jpg") || sdfile.getName().toLowerCase().endsWith(".gif")){
	    			//同步加载图片耗费内存，选设为nul然后异步加载图片
	    			//sdfile.setImg(BitmapFactory.decodeFile(sdfile.getPath()));
	    			sdfile.setImg(null);
	    		}else{
	    			sdfile.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.file));
	    		}
	    		
	    		filelist.add(sdfile);	    		
	    	}
	    	//绑定适配器
	    	List<SdFile> data=new ArrayList<SdFile>();
	    	data.addAll(filelist);
	    	adapter = new ListViewAdapter(this,data);
    		lv.setAdapter(adapter);
    	}
    	
    }
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SdFile sdf=filelist.get(position);
		String path=sdf.getPath();
		File file=new File(path);
		if(file.isDirectory()){
			initData(sdf.getPath());
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			String parentRoot=MyConstant.Root.substring(0, MyConstant.Root.lastIndexOf("/"));
					
			if(parentPath.equals(parentRoot)){
				MainActivity.this.finish();
			}else{
				initData(parentPath);
			}
			break;
		default:
			break;
		}
		return true;
	}
}