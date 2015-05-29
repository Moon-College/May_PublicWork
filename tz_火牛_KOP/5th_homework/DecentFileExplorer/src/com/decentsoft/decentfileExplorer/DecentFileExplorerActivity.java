package com.decentsoft.decentfileExplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.decentsoft.adapter.FileListAdapter;
import com.decentsoft.bean.SdcardFile;
import com.decentsoft.constraint.ConstraintTool;

public class DecentFileExplorerActivity extends Activity implements
		OnItemClickListener
{
	private static final String TAG = "DecentFileExplorerActivity";
	private ListView mLv;
	private List<SdcardFile> mFileList;
	private static Bitmap mFolderIcon;
	private static Bitmap mFileIcon;
	private static Bitmap mUpFileIcon;
	public static Bitmap mLoadingIcon;
	private FileListAdapter mFa;
    private static boolean isExit = false;
    private Handler mHandler = new Handler(){
    	 @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             isExit = false;
         }
    };
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//��ʼ������������ͼ�굽�ڴ棬����ÿ�ε���BitmapFactory.decodeResource
		if (mFolderIcon == null)
		{
			mFolderIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.folder);
		}
		if (mFileIcon == null)
		{
			mFileIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.file);
		}
		if (mUpFileIcon == null)
		{
			mUpFileIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.upfolder);
		}
		if(mLoadingIcon == null)
		{
			mLoadingIcon = BitmapFactory.decodeResource(getResources(),
					R.drawable.loading);
		}
		// ��ʼ�����ݵ�����
		mFileList = new ArrayList<SdcardFile>();
		initFileList(ConstraintTool.ROOT_PATH);

		// ��ȡListView�ؼ�������adapter
		mLv = (ListView) findViewById(R.id.lv);
		mFa = new FileListAdapter(this, mFileList);
		mLv.setAdapter(mFa);
		mLv.setOnItemClickListener(this);
	}

	/**
	 * ��path������ļ�����mFileList
	 * 
	 * @author John
	 * @param path
	 */
	private void initFileList(String path)
	{
		// TODO Auto-generated method stub
		// �����mFileList������
		if (mFileList.size() != 0)
		{
			mFileList.clear();
		}

		File rootFile = new File(path);
		if (rootFile.exists())
		{
			Log.d(TAG, "path=" + path + ",lastIndexOf=" + path.lastIndexOf("/"));
			// ��ȡ��һ��Ŀ¼,����mFileList�ĵ�һλ
			String upDirFath = path.substring(0, path.lastIndexOf("/"));
			File upDirFile = new File(upDirFath);
			SdcardFile upDirSdf = new SdcardFile();
			upDirSdf.setName(upDirFile.getName());
			upDirSdf.setPath(upDirFile.getAbsolutePath());
			upDirSdf.setIcon(mUpFileIcon);
			mFileList.add(upDirSdf);

			// ��ȡĿ¼������ļ��б�,���mFileList
			File[] subFileList = rootFile.listFiles();
			if (subFileList != null)
			{
				for (int i = 0; i < subFileList.length; i++)
				{
					File subFile = subFileList[i];
					SdcardFile sdf = new SdcardFile();
					sdf.setName(subFile.getName());
					sdf.setPath(subFile.getAbsolutePath());
					if (subFile.isDirectory())
					{
						sdf.setIcon(mFolderIcon);
					}
					else if (ConstraintTool.isPicFileName(subFile.getName()))
					{
						//�����ͼƬ����ʱ������ʾmLoadingIcon����������ͼƬ������ŵ�adapter�����첽����
						sdf.setIcon(null);
					}
					else
					{
						sdf.setIcon(mFileIcon);
					}
					mFileList.add(sdf);
				}
				if(subFileList.length == 0)
				{
					Toast.makeText(getApplicationContext(), "Ŀ¼Ϊ��", Toast.LENGTH_SHORT).show();
				}
			}
			else
			{
				//Log.d(TAG, msg);
				Toast.makeText(getApplicationContext(), "Ŀ¼Ϊ��", Toast.LENGTH_SHORT).show();
			}
		} 
		else
		{
			Toast.makeText(getApplicationContext(), "·��������:" + path, Toast.LENGTH_SHORT).show();
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		// TODO Auto-generated method stub
		SdcardFile sdf = (SdcardFile) parent.getItemAtPosition(position);
		File file = new File(sdf.getPath());
		//��������ͼ����Ŀ¼������߸���activity��ͼ����ʾ��һ���ļ��б��·��
		if (file.isDirectory())
		{
			initFileList(sdf.getPath());
			mFa.setData(mFileList);
			mFa.notifyDataSetChanged();
		} 
		else
		{   
			/* �����ļ��ʹ��ļ� */
			openFile(file);
		}
	}

	/**
	 * �½�һ��Intent��������ļ�
	 * @param file �ļ�
	 */
	private void openFile(File file)
	{
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		Log.d(TAG, "file.getName():" + file.getName() + ",MIMEType="
				+ ConstraintTool.getFileMIMEType(file.getName()));
		intent.setDataAndType(Uri.fromFile(file),
				ConstraintTool.getFileMIMEType(file.getName()));
		startActivity(intent);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			String upDirPath = mFileList.get(0).getPath();
			/* ����Ƿ��ذ�ť���ֲ��Ƕ��㣬�����һ��Ŀ¼ */
			if(!"/mnt".equals(upDirPath))
			{
				/* mFileList.get(0).getPath() ����б�ĵ�һ����������һ��Ŀ¼��·�� */
			    initFileList(mFileList.get(0).getPath());
			    mFa.setData(mFileList);
			    mFa.notifyDataSetChanged();
			    return false;
			}
			else
			{
				/**
				 * �����/mntĿ¼,��������˳�
				 */
				handleExit();
				return false;
			}
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	private void handleExit()
	{
		// TODO Auto-generated method stub
	    if(!isExit)
	    {
	    	isExit = true;
	    	Toast.makeText(this, "�ٰ�һ�ξ��˳�������", Toast.LENGTH_SHORT).show();
	    	mHandler.sendEmptyMessageDelayed(0, 2000);
	    }
	    else
	    {
	    	finish();
	    	System.exit(0);
	    }
	}
}