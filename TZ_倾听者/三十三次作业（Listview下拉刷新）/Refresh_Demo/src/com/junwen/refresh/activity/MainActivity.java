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
	
	private PullableListView listview; //�Զ���ListView
	private HttpAdapter adapter; //������
	private List<TextModel> data; //���ݼ���
	private PullToRefreshLayout layout; //����
	private int index = 1; //��ǰ���ص�����
	private int state = 0; //���������Ǽ���
	private int REFRESH = 1;
	private int LOADDING = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		listview = (PullableListView) findViewById(R.id.content_view);
		layout = (PullToRefreshLayout) findViewById(R.id.refresh);
		layout.setOnRefreshListener(this);
		data = new ArrayList<TextModel>();
		HttpUtil.getTextList(index,this,null);
	}
	
	/**
	 * ���������ݳɹ�
	 */
	@Override
	public void onSuccess(List<TextModel> model,
			PullToRefreshLayout pullToRefreshLayout) {
		//���¼��ص�������ӵ�ԭ����������
		data.addAll(model);
		adapter = new HttpAdapter(this, data);
		listview.setAdapter(adapter);
		//���������ˢ�����,���������ָ�ԭ����λ��
		if(pullToRefreshLayout != null && state == REFRESH){
			pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
			//���������ɣ����ü��ػص�ԭ����λ��
		}else if(pullToRefreshLayout != null && state == LOADDING){
			pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			//�����������ͣ���ڼ��صõط�
			listview.setSelection(data.size()-20);
		}
		//����״̬
		state = 0;
	}
	
	/**
	 * ��ˢ��ʧ�ܵ�ʱ��
	 */
	@Override
	public void onFail(String result) {
		Toast.makeText(MainActivity.this, "������", 0).show();
	}
	
	/**
	 * ������ˢ�µ�ʱ��
	 */
	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
		state = REFRESH;
		//��ʼ����ָ����ֵ�������������»�ȡ���ݽ�������ˢ��
		HttpUtil.getTextList(index,this,pullToRefreshLayout);
	}
	
	@Override
	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
		//��������
		index +=10;
		state = LOADDING;
		//��ʼ����ָ����ֵ�������������»�ȡ���ݽ�������ˢ��
		HttpUtil.getTextList(index,this,pullToRefreshLayout);
	}
}
