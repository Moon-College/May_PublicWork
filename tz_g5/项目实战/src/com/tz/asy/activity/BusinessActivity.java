package com.tz.asy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.tz.asy.R;
import com.tz.asy.adapter.CommentAdapter;
import com.tz.asy.adapter.OrderAdapter;
import com.tz.asy.bean.request.RequestCourierComments;
import com.tz.asy.bean.request.RequestCourierOrders;
import com.tz.asy.bean.result.ResultCourierComments;
import com.tz.asy.bean.result.ResultCourierOrders;
import com.tz.asy.common.BaseActivity;
import com.tz.asy.common.MyConstants;
import com.tz.asy.http.common.CommentItem;
import com.tz.asy.http.common.CommentPagination;
import com.tz.asy.http.common.OrderPagination;
import com.tz.asy.http.common.OrdersItem;
import com.tz.asy.listener.HttpCallback;
import com.tz.asy.view.PullToRefreshLayout;
import com.tz.asy.view.PullableListView;


public class BusinessActivity extends BaseActivity implements OnClickListener {
	private Button history_order;// 历史订单
	private Button comment_order;// 评论
	private PullToRefreshLayout refreshLayout;// 下拉刷新组件
	private PullableListView listView;// listview
	private List<OrdersItem> ordersList;// 获取订单列表
	private List<CommentItem> commentList;// 评论列表
	private ProgressDialog dialog;
	private OrderAdapter orderAdapter;
	private CommentAdapter commentAdapter;
	private boolean isOrderStatu;
	private OrderPagination orderPagination;
	private CommentPagination commentPagination;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_business);

	}

	@Override
	public void initView() {
		history_order = (Button) findViewById(R.id.history_order);
		comment_order = (Button) findViewById(R.id.comment_order);
		refreshLayout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
		listView = (PullableListView) findViewById(R.id.content_view);
		dialog = new ProgressDialog(this);
		refreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {

			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				if (isOrderStatu) {
					// 订单状态
					RequestCourierOrders orders = new RequestCourierOrders(0,
							MyConstants.PERPAGES);
					getOrdersFromServer(true, false, orders);
				}else{
					//评论刷新
					RequestCourierComments courierComments = new RequestCourierComments(0, MyConstants.PERPAGES);
					getCommentsFromServer(false,false,courierComments);
				}
			}

			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				if (isOrderStatu) {
					RequestCourierOrders orders = new RequestCourierOrders(
							orderPagination.getCurrentPage() + 1,
							MyConstants.PERPAGES);
					getOrdersFromServer(false, true, orders);
				}else{
					RequestCourierComments courierComments = new RequestCourierComments(commentPagination.getCurrentPage()+1, MyConstants.PERPAGES);
					getCommentsFromServer(false,false,courierComments);
				}
			}
		});
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		history_order.setOnClickListener(this);
		comment_order.setOnClickListener(this);
	}

	@Override
	public void initData() {
		isOrderStatu = true;// 默认是订单状态
		ordersList = new ArrayList<OrdersItem>();
		commentList = new ArrayList<CommentItem>();
		// 先从服务器加载订单数据
		RequestCourierOrders orders = new RequestCourierOrders(0,
				MyConstants.PERPAGES);
		getOrdersFromServer(false, false, orders);

	}
	
	/**
	 * 启动请求获取服务端的订单
	 * @param isRefresh
	 * @param isLoadMore
	 * @param orders
	 */
	public void getOrdersFromServer(final boolean isRefresh,
			final boolean isLoadMore, RequestCourierOrders orders) {
		if (!isRefresh) {
			dialog.setMessage("正在加载订单");
			dialog.show();
		}
		request.doQuestByPostMethod(MyConstants.ORDERLIST, orders, true,
				new HttpCallback() {

					public void success(String result) {
						// TODO Auto-generated method stub
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						ResultCourierOrders resultCourierOrders = request
								.formatResult(result, ResultCourierOrders.class);
						orderPagination = resultCourierOrders.getPagination();// 订单的分页
						if (isLoadMore) {
							List<OrdersItem> items = orderPagination.getItems();// 服务器新页的订单数组
							addItemsById(ordersList, items);//添加非重复的订单到原订单列表
						}else{
							ordersList = orderPagination.getItems();// 订单列表
						}
						orderAdapter = new OrderAdapter(ordersList,
								BusinessActivity.this);
						listView.setAdapter(orderAdapter);
						refreshLayout
								.refreshFinish(PullToRefreshLayout.SUCCEED);
					}

					public void fail(String result) {
						// TODO Auto-generated method stub
						refreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						Toast.makeText(BusinessActivity.this, result,
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	/**
	 * 启动请求获取服务端的评论
	 */
	private void getCommentsFromServer(boolean isRefresh, final boolean isLoadMore,
			RequestCourierComments courierComments) {
		if (!isRefresh) {
			dialog.setMessage("正在加载评论");
			dialog.show();
		}
		request.doQuestByPostMethod(MyConstants.COMMENTLIST, courierComments, true, new HttpCallback() {
			
			@Override
			public void success(String result) {
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
				ResultCourierComments courierComments = request.formatResult(result, ResultCourierComments.class);
				commentPagination = courierComments.getPagination();// 订单的分页
				if (isLoadMore) {
					List<CommentItem> items = commentPagination.getItems();// 服务器新页的订单数组
					addCommentItemsById(commentList, items);//添加非重复的订单到原订单列表
				}else{
					commentList = commentPagination.getItems();// 订单列表
				}
				if(commentAdapter == null){
					commentAdapter = new CommentAdapter(
						BusinessActivity.this,commentList);
					listView.setAdapter(commentAdapter);
				}else{
					commentAdapter.notifyDataSetChanged();
				}
				refreshLayout
						.refreshFinish(PullToRefreshLayout.SUCCEED);
			}
			
			@Override
			public void fail(String result) {
				// TODO Auto-generated method stub
				refreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
				Toast.makeText(BusinessActivity.this, result,
						Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.history_order:
			isOrderStatu = true;
			if(orderAdapter == null){
				orderAdapter = new OrderAdapter(ordersList, this);
			}
			listView.setAdapter(orderAdapter);
			break;
		case R.id.comment_order:
			isOrderStatu = false;
			if(commentAdapter == null){
				commentAdapter = new CommentAdapter(this, commentList);
				RequestCourierComments courierComments = new RequestCourierComments(0, MyConstants.PERPAGES);
				getCommentsFromServer(false,false,courierComments);
			}
			listView.setAdapter(commentAdapter);//默认是空数据
			//加载commentList数据
			break;
		default:
			break;
		}
	}
	

	/**
	 * 根据id来进行订单的拼接，去除重复
	 * 
	 * @param ordersList
	 * @param items
	 */
	private void addItemsById(List<OrdersItem> ordersList,
			List<OrdersItem> items) {
		List<OrdersItem> tempList = new ArrayList<OrdersItem>();
		for (OrdersItem item : items) {
			// 新订单的每一个元素
			for (OrdersItem ordersItem : ordersList) {
				// 原订单的元素
				if (ordersItem.getId() == item.getId()) {
					// 重复
				} else {
					tempList.add(item);
				}
			}
		}
		ordersList.addAll(tempList);
	}
	
	/**
	 * 根据id来进行订单的拼接，去除重复
	 * 
	 * @param commentList
	 * @param items
	 */
	private void addCommentItemsById(List<CommentItem> commentList,
			List<CommentItem> items) {
		List<CommentItem> tempList = new ArrayList<CommentItem>();
		for (CommentItem item : items) {
			// 新评论的每一个元素
			for (CommentItem commentItem : commentList) {
				// 原评论的元素
				if (commentItem.getId() == item.getId()) {
					// 重复
				} else {
					tempList.add(item);
				}
			}
		}
		commentList.addAll(tempList);
	}
}
