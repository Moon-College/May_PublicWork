package com.lin.baselistviewstudi.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lin.baselistviewstudi.app.R;
import com.lin.baselistviewstudi.app.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 2015/5/24.
 */
public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Student> list;
    private final LayoutInflater inflater;

    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Student getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item, null);
        Student item = getItem(position);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView hobbies = (TextView) view.findViewById(R.id.hobbies);
        TextView sex = (TextView) view.findViewById(R.id.sex);
        TextView prettyNum = (TextView) view.findViewById(R.id.prettyNum);
        ImageView img = (ImageView) view.findViewById(R.id.img);


        name.setText("姓名：" + item.getName());
        hobbies.setText("爱好" + item.getHobbies());
        sex.setText("性别：" + item.getSex());
        img.setImageResource(item.getImg());
        prettyNum.setText("颜值:" + item.getPrettyNum());


        view.setBackgroundColor('男'==item.getSex() ? Color.BLUE : Color.RED);

        return view;
    }
}
