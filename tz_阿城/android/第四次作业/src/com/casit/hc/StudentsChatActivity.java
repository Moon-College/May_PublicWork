package com.casit.hc;

import java.util.ArrayList;
import java.util.List;

import com.casit.bean.MyAdapter;
import com.casit.students.MyStudents;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class StudentsChatActivity extends Activity {
    /** Called when the activity is first created. */
	List<MyStudents> data;
	MyAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView lv =  (ListView) this.findViewById(R.id.lv);
        data = new ArrayList<MyStudents>();
        String[] apperance = new String[]{
        		"�ɰ�","����","�","˧��","����","��","��","�׸���","�߸�˧","ɵ"
        };
        String[] hobby = new String[]{
        		"����","�Ⱦ���","�μ�","����","����","������","������","˯��","���齫","LOL"
        };
        String[] nickname = new String[]{
        		"��ë","����","����","����","������","��С��","��İ�","FUCK","��","˧���ڴ�"
        };   
        String[] sex = new String[]{
        		"��","Ů","��","Ů","��","Ů","��","��","Ů","��"
        };       
        int[] studentface = new int[]{
        		R.drawable.face1,
        		R.drawable.face2,
        		R.drawable.face3,
        		R.drawable.face4,
        		R.drawable.face5,
        		R.drawable.face6,
        		R.drawable.face7,
        		R.drawable.face8,
        		R.drawable.face9,
        		R.drawable.face10
        };
        for(int i = 0; i<10;i++){
        	Log.println(3, "INFO", "s"+i);
        	MyStudents student = new MyStudents();
        	student.setmAppearance(apperance[i]);
        	student.setmHobby(hobby[i]);
        	student.setmNickName(nickname[i]);
        	student.setmSex(sex[i]);
        	student.setmStudentsFace(studentface[i]);
        	data.add(student);
        }
        adapter = new MyAdapter(this, data);
        lv.setAdapter(adapter);
     //   lv.setBackgroundColor(125);
    }
}