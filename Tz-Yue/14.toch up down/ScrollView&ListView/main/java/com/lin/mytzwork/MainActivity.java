package com.lin.mytzwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.lin.mytzwork.util.L;
import com.lin.mytzwork.util.ReflectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/17.
 */
public class MainActivity extends Activity implements ViewGroup.OnTouchListener {
    private Activity activity;
    private ScrollView sv;
    private ListView lv;

    private List<String> list = new ArrayList<String>(100);
    private ArrayAdapter<String> adapter = null;
    private int scro = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ReflectUtil.initView(activity);
        initData();
        initView();
        L.i("info", "onCreate");
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            list.add("哈哈" + i);
        }
    }

    private void initView() {

        adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        lv.setAdapter(adapter);
        sv.setOnTouchListener(this);
        lv.setOnTouchListener(this);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                L.i("info", firstVisibleItem + " " + visibleItemCount + " " + totalItemCount);
                if (firstVisibleItem == 0) {
                    scro = -1; //第一个位置
                } else if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    scro = 1;//最后一个位置
                } else {
                    scro = 0;
                }

            }
        });
    }

    private float y = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                L.i("info", "SV_ACTION_DOWN");
                if (v.getId() == R.id.lv) {
                    y = event.getY();
                }
                //按下就先让父容器放行
                sv.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                L.i("info", "SV_ACTION_MOVE");

                if (v.getId() == R.id.lv) { //当前是listView时
                    //向下滑动 且 listview 是0下标     或者     向上滑动 且 listView 是 最后一个下标
                    //就放过让scrllView 拦截 事件 自己 滑动
                    if (event.getY() - y > 0 && scro == -1 || event.getY() - y < 0 && scro == 1) {
                        sv.requestDisallowInterceptTouchEvent(false);
                        L.i("info", "sv滑动");
                    } else { //否则 就 scrollView 就先放行  让listView 滑动
                        sv.requestDisallowInterceptTouchEvent(true);
                        L.i("info", "lv滑动");
                    }
                }

                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                L.i("info", "SV_ACTION_UP");
                break;
        }

        return super.

                onTouchEvent(event);
    }
}
