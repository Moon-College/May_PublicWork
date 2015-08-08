package com.junwen.refresh.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.refresh_demo.R;
import com.junwen.refresh.adapter.HttpAdapter;
import com.junwen.refresh.model.TextModel;
import com.junwen.refresh.util.HttpUtil;
import com.junwen.refresh.util.HttpUtil.OnDownImageListener;
import com.tz.refresh.view.PullToRefreshLayout;
import com.tz.refresh.view.PullToRefreshLayout.OnRefreshListener;
import com.tz.refresh.view.PullableListView;

public class MainActivity extends Activity implements OnDownImageListener, OnRefreshListener {
	
	private PullableListView listview; //自定义ListView
	private HttpAdapter adapter; //适配器
	private List<TextModel> data; //数据集合
	private PullToRefreshLayout layout; //下拉
	private int index = 1; //当前加载得总数
	private int state = 0; //是上啦还是加载
	private int REFRESH = 1;
	private int LOADDING = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		listview = (PullableListView) findViewById(R.id.content_view);
		layout = (PullToRefreshLayout) findViewById(R.id.refresh);
		layout.setOnRefreshListener(this);
		data = new ArrayList<TextModel>();
		HttpUtil.getTextList(index,this,null);
	}
	
	/**
	 * 当加载数据成功
	 */
	@Override
	public void onSuccess(List<TextModel> model,
			PullToRefreshLayout pullToRefreshLayout) {
		//吧新加载得数据添加到原来的数据中
		data.addAll(model);
		adapter = new HttpAdapter(this, data);
		listview.setAdapter(adapter);
		//如果是下啦刷新完成,则让下拉恢复原来的位置
		if(pullToRefreshLayout != null && state == REFRESH){
			pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
			//如果加载完成，则让加载回到原来的位置
		}else if(pullToRefreshLayout != null && state == LOADDING){
			pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			//加载完后，让他停留在加载得地方
			listview.setSelection(data.size()-20);
		}
		//重置状态
		state = 0;
	}
	
	/**
	 * 当刷新失败的时候
	 */
	@Override
	public void onFail(String result) {
		Toast.makeText(MainActivity.this, "错误了", 0).show();
	}
	
	/**
	 * 当正在刷新得时候
	 */
	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
		state = REFRESH;
		//开始根据指定数值，到服务器重新获取数据进行重新刷新
		HttpUtil.getTextList(index,this,pullToRefreshLayout);
	}
	
	@Override
	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
		//增加数量
		index +=10;
		state = LOADDING;
		//开始根据指定数值，到服务器重新获取数据进行重新刷新
		HttpUtil.getTextList(index,this,pullToRefreshLayout);
	}
}
