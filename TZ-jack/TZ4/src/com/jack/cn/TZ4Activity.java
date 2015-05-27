package com.jack.cn;

import java.util.ArrayList;
import java.util.List;

import com.jack.entity.Student;
import com.jack.until.MyAdapate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TZ4Activity extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */
	ListView list;
	 MyAdapate adapate;
	private List<Student> st;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不再显示 标题
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        list =(ListView) findViewById(R.id.list);       
        
       adapate=new MyAdapate(this, query());
       //给listview 设置 适配器
       list.setAdapter(adapate);
       //list.setOnItemClickListener(this);
       list.setOnItemClickListener(this);		
    }
    
    
    /**
     *@jack  数据源
     */
    public List query(){
    	//图片id
    	int[] imgs=new int[]{
    			R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
    			R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,
    			R.drawable.i,R.drawable.ic_launcher   			
    	};    	
    	//网名
       String[] t1=new String[]{
    		 "binbin","xxx","打广告",
    		 "扫地僧","Grace","影子",
    		 "美女瑶瑶1号","瑶瑶2号","3号瑶瑶",
    		 "android"
       };
       //性别
       String[] t3=new String[]{
    	"男神","人妖","人妖",
    	"男","女","女",
    	"女神","女神","女神",
    	"不详"
       };
       //爱好
       String[] t4=new String[]{
    		   "撸代码","不详","不详",
    		   "练功","萌萌哒","呵呵呵",
    		   "不详","不详","不详",
    		   "不详"    		   
       };
    	st = new ArrayList<Student>();
    	for(int i=0;i<10;i++){
    		Student student=new Student();
    		//设置图片资源    		
    		student.setImgs(imgs[i]);
    		student.setT1(t1[i]);
    		student.setT3(t3[i]);
    		student.setT4(t4[i]);
    		st.add(student);
    	}
    	return st;    	
    }

    /**
     *@author cxc 为下拉列表设置监听 ,当点击某一行时则会删除该行
     */
	public void onItemClick(AdapterView<?> arg0, View view, int a1, long id) {
	 //Toast.makeText(this, arg2, 0).show();
		st.remove(a1);
		
		adapate.notifyDataSetChanged();
	}


}