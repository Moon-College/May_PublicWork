package com.lin.baselistviewstudi.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.lin.baselistviewstudi.app.adapter.StudentAdapter;
import com.lin.baselistviewstudi.app.entity.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView lv;
    int[] imags = new int[]{
            R.mipmap.icon_1,
            R.mipmap.icon_2,
            R.mipmap.icon_3,
            R.mipmap.icon_4,
            R.mipmap.icon_5,
            R.mipmap.icon_6,
            R.mipmap.icon_7,
            R.mipmap.icon_8,
            R.mipmap.icon_9,
            R.mipmap.icon_10,
            R.mipmap.icon_11,
            R.mipmap.icon_12,
            R.mipmap.icon_13,
            R.mipmap.icon_14,
            R.mipmap.icon_15,
            R.mipmap.icon_16,

    };
    private List<Student> students;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void initData() {
        students = new ArrayList<Student>();

        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            String name = "张" + i;
            char sex = random.nextInt(2) == 0 ? '女' : '男';
            int prettyNum = random.nextInt(100) + 1; //颜值
            String hobbies ='男'==sex ?"爱好男":"爱好女";

            Student student = new Student(name, sex, imags[i], prettyNum, hobbies);
            students.add(student);
        }


    }

    private void initView() {
        lv = (ListView) this.findViewById(R.id.lv);
        studentAdapter = new StudentAdapter(this, students);
        lv.setAdapter(studentAdapter);

        lv.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student item = studentAdapter.getItem(position);
        students.remove(position);
        studentAdapter.notifyDataSetChanged();
        Toast.makeText(this, "删除" + item.toString(), Toast.LENGTH_LONG).show();
    }
}
