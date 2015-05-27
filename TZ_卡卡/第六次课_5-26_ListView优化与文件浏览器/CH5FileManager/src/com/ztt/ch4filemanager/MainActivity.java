package com.ztt.ch4filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ztt.ch4filemanager.adapter.FileAdapter;
import com.ztt.ch4filemanager.model.SDFile;
import com.ztt.ch4filemanager.utils.CommData;
import com.ztt.ch4filemanager.utils.Utils;

public class MainActivity extends Activity
{
    
    private ListView lv;
    
    private List<SDFile> fileList = new ArrayList<SDFile>();
    
    private FileAdapter fileAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }
    
    private void initDatas()
    {
        //获取SD卡根目录
        fileList = Utils.getFilRoot(this, CommData.FILE_ROOT);
        fileAdapter = new FileAdapter(this, fileList);
        lv.setAdapter(fileAdapter);
        
        lv.setOnItemClickListener(new OnItemClickListener()
        {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id)
            {
                SDFile sf = fileList.get(position);
                File file = new File(sf.getPath());
                if (file.isDirectory())
                {
                    fileList =
                        Utils.getFilRoot(getApplicationContext(),
                            fileList.get(position).getPath());
                    fileAdapter.setData(fileList);
                }
                else if (sf.isPic())
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse("file://" + file.getPath()),
                        "image/*");
                    startActivity(intent);
                }
                
            }
            
        });
    }
    
    private void initViews()
    {
        lv = (ListView) findViewById(R.id.lv);
        
    }
    
}
