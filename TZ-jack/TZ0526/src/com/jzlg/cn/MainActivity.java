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
        //ȥ������
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        listView=(ListView) findViewById(R.id.list);
        ReadSd(Cons.con);
        listView.setOnItemClickListener(this);
    }
    
    /**
     * @param path  Ҫ������ ·��
     * @author cxc  
     * */
    public void ReadSd(String path){
    	Log.i("path", path);
    	File file=new File(path);
    	File[] files=file.listFiles();
    	if(file!=null){
    		myFile=new MyFile();
    		list=new ArrayList<MyFile>();
    		myFile.setName("����");
    		myFile.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dirs));//����ͼƬ
    		String filepath=path.substring(0, path.lastIndexOf("/"));
    		myFile.setFilepath(filepath);//���ø�Ŀ¼
    		myFile.setFile(new File(filepath));//�ļ����� 
    		myFile.setTNT(false);//����ͼƬ
    		list.add(myFile);
    		Log.i("cxc", "�������������");
    		Log.i("dsdsf", ""+files.length);
    		for(int i=0;i<files.length;i++){
    			myFile=new MyFile();
    			myFile.setFile(files[i]);//�ļ����� 
    			myFile.setFilepath(files[i].getAbsolutePath());//�ļ� ·��
    			myFile.setName(files[i].getName());//�ļ�����
    			//�����ļ�  log
    			Log.d("mainactivity", "Test");
    			if(files[i].isDirectory()){
    				myFile.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.dirs));//����ͼƬ
    			}else if(files[i].getName().toLowerCase().endsWith(".png")||files[i].getName().toLowerCase().endsWith(".jpg")||files[i].getName().toLowerCase().endsWith(".gif")){
    				//�ж� �ļ� �Ƿ�Ϊ ͼƬ
    				//1 ���ļ�����װ����Сд
    				//2ͨ��endswith �Ƿ�Ϊ ͼƬ��ʽ            getAbsolutePath()�õ��� �� ���� ·��    getpath�õ��� �� ���·��
    				myFile.setBitmap(BitmapFactory.decodeFile(files[i].getAbsolutePath()));
    			}else {
    				//��� Ϊ ��ͨ �ļ�ʱ
    				myFile.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.file));
    			}
    			list.add(myFile);    			
    		}   
    		myBaseAdapter=new MyBaseAdapter(this, list);
    		listView.setAdapter(myBaseAdapter);
    	}
    	Toast.makeText(this, "�����Ӱ�,���Ȳ���sd��", 0).show();
    }

    /**
     * @author cxc  ��Ӧ ��Ŀ �ؼ�
     * */
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		//�õ� �������Ŀ·��
		String path=list.get(arg2).getFilepath();
		File file=list.get(arg2).getFile();
		if(file.isDirectory()){
			//������Ŀ Ϊ Ŀ¼ ʱ  ����
			ReadSd(path);
		}
	}      
}