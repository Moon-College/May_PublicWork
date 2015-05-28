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
        //������ʾ ����
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        list =(ListView) findViewById(R.id.list);       
        
       adapate=new MyAdapate(this, query());
       //��listview ���� ������
       list.setAdapter(adapate);
       //list.setOnItemClickListener(this);
       list.setOnItemClickListener(this);		
    }
    
    
    /**
     *@jack  ����Դ
     */
    public List query(){
    	//ͼƬid
    	int[] imgs=new int[]{
    			R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
    			R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,
    			R.drawable.i,R.drawable.ic_launcher   			
    	};    	
    	//����
       String[] t1=new String[]{
    		 "binbin","xxx","����",
    		 "ɨ��ɮ","Grace","Ӱ��",
    		 "��Ů����1��","����2��","3������",
    		 "android"
       };
       //�Ա�
       String[] t3=new String[]{
    	"����","����","����",
    	"��","Ů","Ů",
    	"Ů��","Ů��","Ů��",
    	"����"
       };
       //����
       String[] t4=new String[]{
    		   "ߣ����","����","����",
    		   "����","������","�ǺǺ�",
    		   "����","����","����",
    		   "����"    		   
       };
    	st = new ArrayList<Student>();
    	for(int i=0;i<10;i++){
    		Student student=new Student();
    		//����ͼƬ��Դ    		
    		student.setImgs(imgs[i]);
    		student.setT1(t1[i]);
    		student.setT3(t3[i]);
    		student.setT4(t4[i]);
    		st.add(student);
    	}
    	return st;    	
    }

    /**
     *@author cxc Ϊ�����б����ü��� ,�����ĳһ��ʱ���ɾ������
     */
	public void onItemClick(AdapterView<?> arg0, View view, int a1, long id) {
	 //Toast.makeText(this, arg2, 0).show();
		st.remove(a1);
		
		adapate.notifyDataSetChanged();
	}


}