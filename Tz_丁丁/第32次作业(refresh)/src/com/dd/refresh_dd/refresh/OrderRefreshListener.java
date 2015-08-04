package com.dd.refresh_dd.refresh;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by Administrator on 2015/6/8.
 */
public class OrderRefreshListener implements PullToRefreshLayout.OnRefreshListener{
	
	public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
		// 下拉刷新操作
		new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 千万别忘了告诉控件刷新完毕了哦！
				pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
				Log.i("INFO","onRefresh");
			}
		}.sendEmptyMessageDelayed(0, 5000);
	}

	public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
		// 加载操作
		new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 千万别忘了告诉控件加载完毕了哦！
				pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				Log.i("INFO","onLoadMore");
			}
		}.sendEmptyMessageDelayed(0, 5000);
	}
	
}
