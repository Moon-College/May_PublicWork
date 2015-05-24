package com.decentsoft.hellohighview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.decentsoft.hellohighview.adapter.DsBaseAdapter;
import com.decentsoft.hellohiview.bean.Immortal;

public class HelloHighViewActivity extends Activity implements OnItemLongClickListener, OnClickListener {
	
    private ListView lv;
	private List<Immortal> data;
	private DsBaseAdapter dba;
	/**
	 * 神仙数组
	 */
    private Immortal[] Immortals = {
    		new Immortal("老子",Immortal.MALE,"上善若水",R.drawable.immortal0),
    		new Immortal("庄子",Immortal.MALE,"庄周梦蝶",R.drawable.immortal1),
    		new Immortal("慈航真人",Immortal.FEMALE,"普度众生",R.drawable.immortal2),
    		new Immortal("张果老",Immortal.MALE,"好吃:)",R.drawable.immortal3),
    		new Immortal("葛洪",Immortal.MALE,"丹道",R.drawable.immortal4),
    		new Immortal("张道陵",Immortal.MALE,"法术",R.drawable.immortal5),
    		new Immortal("孙不二",Immortal.FEMALE,"平静",R.drawable.immortal6),
    		new Immortal("王重阳",Immortal.MALE,"自然，全真无二",R.drawable.immortal7),
    		new Immortal("何仙姑",Immortal.FEMALE,"河流",R.drawable.immortal8),
    		new Immortal("吕洞宾",Immortal.MALE,"好酒:)",R.drawable.immortal9),
    };
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.decentsoft.hellohighview.R.layout.high);
        lv = (ListView) findViewById(com.decentsoft.hellohighview.R.id.lv);
        /**
         * 把神仙数组的内容加到data里面
         */
        data = new ArrayList<Immortal>();
        for(int i=0;i<Immortals.length;i++)
        {
        	data.add(Immortals[i]);
        }
        //设置adapter
        dba = new DsBaseAdapter(this, data);
        lv.setAdapter(dba);
        //设置lv的item长按删除的响应
        lv.setOnItemLongClickListener(this);
        
        //
        Button reset_btn = (Button) findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(this);
    }

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		// TODO Auto-generated method stub
		final Immortal imortal = (Immortal) arg0.getItemAtPosition(arg2);
		new AlertDialog.Builder(this).setTitle("删除?")
		    .setNegativeButton("取消", null)
		    .setPositiveButton("确定",  
		    		new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which)
						{
							Runnable delete = new Runnable(){
								public void run()
								{
									/* 更新dba里面的data，删除正在被按到的这个imortal */
									dba.getData().remove(imortal);
									/* 通知数据dba变化了，重新显示 */
									dba.notifyDataSetChanged();
								}
							};
							delete.run();
						}
		            })
		    .show();
		return false;
	}

	public void onClick(View arg0)
	{
		Runnable reset = new Runnable()
		{
			public void run()
			{
				/**
				 * 清空data，重新写入数据
				 */
				data.clear();
		        for(int i=0;i<Immortals.length;i++)
		        {
		        	data.add(Immortals[i]);
		        }
				/* 设置dba的data为初始化时候的data */
				dba.setData(data);
				/* 通知数据dba变化了，重新显示 */
				dba.notifyDataSetChanged();
			}
		};
		reset.run();
	}
}