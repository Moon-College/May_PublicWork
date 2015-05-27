package com.tz.michael.activity;

import java.io.File;
import java.lang.ref.SoftReference;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import com.tz.michael.adapter.MyFileListAdapter;
import com.tz.michael.bean.FileBean;
import com.tz.michael.constant.ConstantConfig;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 文件浏览
 * @author admin
 *
 */
public class TaskFiveActivity extends Activity implements OnItemClickListener {
	
	private List<FileBean> data=new ArrayList<FileBean>();
	private ListView lv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) findViewById(R.id.lv);
        initData(ConstantConfig.ROOT);
        lv.setOnItemClickListener(this);
    }

    /**
     * 拿到指定路径下的所有文件
     * @param root
     */
	@SuppressWarnings("unused")
	private void initData(String root) {
		data.clear();
		File f=new File(root);
		if(f==null){
			Toast.makeText(this, "SD卡连接异常", 0).show();
		}else{
			//创建最上层返回文件
			FileBean fileBean=new FileBean();
			//获取文件路径
			String path=root.substring(0, root.lastIndexOf("/"));
			fileBean.setFilePath(path);
			fileBean.setFile(new File(path));
			fileBean.setFileName("返回");
			fileBean.setPic(false);
			fileBean.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
			data.add(fileBean);
			File[] files=f.listFiles();
			for(File ff:files){
				FileBean tempFileBean=new FileBean();
				tempFileBean.setFile(ff);
				tempFileBean.setFileName(ff.getName());
				tempFileBean.setFilePath(ff.getAbsolutePath());
				//如果是文件夹
				if(ff.isDirectory()){
					tempFileBean.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
				}else{
					//如果是图片
					if(ff.getName().toUpperCase().endsWith(".PNG")||ff.getName().toUpperCase().endsWith("JPG")){
						//普通的加载图片
//						tempFileBean.setBitmap(BitmapFactory.decodeFile(ff.getAbsolutePath()));
						tempFileBean.setBitmap(null);
					}else{
						tempFileBean.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.file)));
					}
				}
				data.add(tempFileBean);
				MyFileListAdapter adapter=new MyFileListAdapter(this, data, R.layout.flv_item);
				lv.setAdapter(adapter);
			}
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		FileBean fb=data.get(position);
		if(fb.getFile().isDirectory()){
			initData(fb.getFilePath());
		}
	}
}