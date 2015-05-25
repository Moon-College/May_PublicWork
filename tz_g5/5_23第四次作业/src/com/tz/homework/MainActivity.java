package com.tz.homework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.tz.homework.adapter.MainAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    /**
     * Called when the activity is first created.
     */

    private ListView mListView;
    private MainAdapter mMainAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mListView = (ListView) findViewById(R.id.lv_main);
        mMainAdapter = new MainAdapter(new ArrayList<User>(), this);
        mListView.setAdapter(mMainAdapter);
        mListView.setOnItemClickListener(this);

        generateData();
    }

    public void generateData() {
        ArrayList<User> temps = new ArrayList<User>();

        for (int i = 0; i < 5; i++) {

            User user1 = new User();
            user1.name = "Danny";
            user1.gender = User.Gender.MALE;
            user1.favourite = "吹比";
            user1.look = User.Look.UGLY;
            user1.resId = R.drawable.danny;
            temps.add(user1);

            User user2 = new User();
            user2.name = "Grace";
            user2.gender = User.Gender.FEMALE;
            user2.favourite = "卖萌";
            user2.look = User.Look.CUTE;
            user2.resId = R.drawable.grace;
            temps.add(user2);
        }

        mMainAdapter.addAll(temps);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mMainAdapter.deleteUser(position);
    }
}
