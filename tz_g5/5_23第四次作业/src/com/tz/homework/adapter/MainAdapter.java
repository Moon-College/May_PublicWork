package com.tz.homework.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tz.homework.R;
import com.tz.homework.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinhan on 15/5/25.
 */
public class MainAdapter extends BaseAdapter {
    private List<User> mUsers;
    private Context mContext;

    public MainAdapter(List<User> users, Context context) {
        if (users != null) {
            mUsers = users;
        } else {
            mUsers = new ArrayList<User>();
        }
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item, null);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_gender = ViewHolder.get(convertView, R.id.tv_gender);
        TextView tv_favourite = ViewHolder.get(convertView, R.id.tv_favourite);
        TextView tv_look = ViewHolder.get(convertView, R.id.tv_look);

        ImageView iv_logo = ViewHolder.get(convertView, R.id.iv_logo);

        User temp = (User) getItem(position);
        switch (temp.gender) {
            case MALE:
                convertView.setBackgroundColor(Color.BLUE);
                break;
            case FEMALE:
                convertView.setBackgroundColor(Color.RED);
                break;
            case UNKNOWN:
                convertView.setBackgroundColor(Color.YELLOW);
                break;
        }

        tv_name.setText("姓名："+temp.name);
        tv_favourite.setText("爱好："+temp.favourite);
        tv_gender.setText("性别："+temp.gender.info);
        tv_look.setText("颜值："+temp.look.level);

        iv_logo.setImageResource(temp.resId);
        return convertView;
    }

    public void addUser(User user) {
        mUsers.add(user);
        notifyDataSetChanged();
    }

    public User deleteUser(int position) {
        User temp = mUsers.remove(position);
        notifyDataSetChanged();
        return temp;
    }

    public void addAll(List<User> users) {
        if (users!=null) mUsers.addAll(users);
        notifyDataSetChanged();
    }

    public void clear() {
        mUsers.clear();
        notifyDataSetChanged();
    }
}
