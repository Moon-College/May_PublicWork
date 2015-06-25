package com.yl.listview;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
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
    		Toast.makeText(this, "û��sd��", 1000).show();
    	}else{
    		//��ʼ�����ذ�Ť
	    	sdfile=new SdFile();
	    	sdfile.setName("����");
	    	parentPath=path.substring(0, path.lastIndexOf("/"));
	    	Log.i("INFO", parentPath);
	    	sdfile.setPath(parentPath);
	    	sdfile.setFile(new File(parentPath));
	    	sdfile.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.bak));
	    	filelist.add(sdfile);
	    	
	    	//����ļ��б�
	    	File [] farray=file.listFiles(); 
	    	for ( File f : farray){
	    		
	    		sdfile=new SdFile();
	    		sdfile.setName(f.getName());
	    		sdfile.setPath(f.getAbsolutePath());
	    		sdfile.setFile(f);
	    		if (f.isDirectory()){
	    			sdfile.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.dirs));
	    			sdfile.setIsimg(false);
	    		}else if(sdfile.getName().toLowerCase().endsWith(".png") || sdfile.getName().toLowerCase().endsWith(".jpg") || sdfile.getName().toLowerCase().endsWith(".gif")){
	    			//ͬ������ͼƬ�ķ��ڴ棬ѡ��ΪnulȻ���첽����ͼƬ
	    			//sdfile.setImg(BitmapFactory.decodeFile(sdfile.getPath()));
	    			//sdfile.setImg(null);
	    			sdfile.setSoftBitmap(new SoftReference<Bitmap>(null));
	    			sdfile.setIsimg(true);
	    		}else{
	    			sdfile.setImg(BitmapFactory.decodeResource(getResources(), R.drawable.file));
	    			sdfile.setIsimg(false);
	    		}
	    		
	    		filelist.add(sdfile);	    		
	    	}
	    	//��������
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