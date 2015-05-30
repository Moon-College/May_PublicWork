package com.jzlg.cn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jzlg.entity.MyFile;
import com.jzlg.until.Cons;
import com.jzlg.until.MyBaseAdapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */	
	List<MyFile> list;
	MyBaseAdapter myBaseAdapter;
	MyFile myFile;
	ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        listView=(ListView) findViewById(R.id.list);
        ReadSd(Cons.con);
        listView.setOnItemClickListener(this);
    }
    
    /**
     * @param path  要迭代的 路径
     * @author cxc  
     * */
    public void ReadSd(String path){
    	Log.i("path", path);
    	File file=new File(path);
    	File[] files=file.listFiles();
    	if(file!=null){
    		myFile=new MyFile();
    		list=new ArrayList<MyFile>();
    		myFile.setName("返回");
    		myFile.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dirs));//设置图片
    		String filepath=path.substring(0, path.lastIndexOf("/"));
    		myFile.setFilepath(filepath);//设置父目录
    		myFile.setFile(new File(filepath));//文件对象 
    		myFile.setTNT(false);//不是图片
    		list.add(myFile);
    		Log.i("cxc", "如果爱可以重来");
    		Log.i("dsdsf", ""+files.length);
    		for(int i=0;i<files.length;i++){
    			myFile=new MyFile();
    			myFile.setFile(files[i]);//文件对象 
    			myFile.setFilepath(files[i].getAbsolutePath());//文件 路径
    			myFile.setName(files[i].getName());//文件名称
    			//设置文件  log
    			Log.d("mainactivity", "Test");
    			if(files[i].isDirectory()){
    				myFile.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dirs));//设置图片
    			}else if(files[i].getName().toLowerCase().endsWith(".png")||files[i].getName().toLowerCase().endsWith(".jpg")||files[i].getName().toLowerCase().endsWith(".gif")){
    				//判断 文件 是否为 图片
    				//1 将文件名称装换成小写
    				//2通过endswith 是否为 图片格式            getAbsolutePath()得到的 是 绝对 路径    getpath得到的 是 相对路径
    				myFile.setBitmap(BitmapFactory.decodeFile(files[i].getAbsolutePath()));
    			}else {
    				//如果 为 普通 文件时
    				myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
    			}
    			list.add(myFile);    			
    		}   
    		myBaseAdapter=new MyBaseAdapter(this, list);
    		listView.setAdapter(myBaseAdapter);
    	}
    	Toast.makeText(this, "扯犊子吧,请先插入sd卡", 0).show();
    }

    /**
     * @author cxc  相应 条目 控件
     * */
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		//得到 点击栏条目路径
		String path=list.get(arg2).getFilepath();
		File file=list.get(arg2).getFile();
		if(file.isDirectory()){
			//当该条目 为 目录 时  迭代
			ReadSd(path);
		}
	}      
}