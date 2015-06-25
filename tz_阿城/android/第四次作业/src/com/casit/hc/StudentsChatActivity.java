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
        		"可爱","天真","活波","帅气","土气","丑","黑","白富美","高富帅","傻"
        };
        String[] hobby = new String[]{
        		"抽烟","喝酒真","嫖妓","打牌","唱歌","打篮球","踢足球","睡觉","打麻将","LOL"
        };
        String[] nickname = new String[]{
        		"阿毛","阿狗","张三","李四","王老五","高小三","你的爱","FUCK","美","帅哥在此"
        };   
        String[] sex = new String[]{
        		"男","女","男","女","男","女","男","男","女","男"
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