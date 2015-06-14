package com.tz.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;

public class MyActivity extends Activity implements View.OnTouchListener,AbsListView.OnScrollListener{

    private ScrollView mScrollView;

    private ListView mListView;

    private TextView tv_top,tv_bottom;

    private boolean isOverScroll = false;

    private ArrayAdapter adapter;

    private float Y;


    private int delay = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mScrollView = (ScrollView) findViewById(R.id.sv);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        delay = 0;
                        break;
                }
                return false;
            }
        });

        mListView = (ListView) findViewById(R.id.lv);
        mListView.setOnTouchListener(this);
        mListView.setOnScrollListener(this);

        tv_top = (TextView) findViewById(R.id.tv_top);
        tv_bottom = (TextView) findViewById(R.id.tv_bottom);

        Integer sizes[] = new Integer[100];
        for (int i = 0; i < 100; i++) {
            sizes[i] = i;
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sizes);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Y = event.getY();
                isOverScroll = true;
                mScrollView.requestDisallowInterceptTouchEvent(isOverScroll);

                break;
            case MotionEvent.ACTION_MOVE:
                boolean isDown = Y - event.getAction()>0;
                Y = event.getY();
                Log.v("isOverScroll", isOverScroll + "");
                mScrollView.requestDisallowInterceptTouchEvent(isOverScroll);

                if (mListView.getLastVisiblePosition() == (mListView.getCount() - 1)) {
                    float childBottom = mListView.getChildAt(mListView.getChildCount() - 1).getBottom();
                    float bottom = mListView.getBottom();
                    Log.v("Y", childBottom + "");
                    if (childBottom + tv_top.getHeight() == bottom) {
                        isOverScroll = false;
                    }
                }
                // 判断滚动到顶部
                if (mListView.getFirstVisiblePosition() == 0) {
                    int childTop = mListView.getChildAt(0).getTop();
                    int top = mListView.getTop();
                    if (childTop+tv_top.getHeight() == top) {
                        isOverScroll = false;

                    }
                }
                if (!isOverScroll) {
                    if (delay < 100) {
                        delay+=10;

                        isOverScroll = true;
                        Log.v("delay","delay" );
                    }
                } else {
                    delay = 0;
                }
                Log.v("isOverScroll", isOverScroll + "");
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            // 当不滚动时
            case SCROLL_STATE_IDLE:
                // 判断滚动到底部
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.v("!", "!");
    }
}
