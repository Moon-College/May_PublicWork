package com.decent.courier.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.decent.courier.adapter.CommentListAdapter;
import com.decent.courier.adapter.OrderListAdapter;
import com.decent.courier.bean.request.HttpRequestCommentList;
import com.decent.courier.bean.request.HttpRequestOrderList;
import com.decent.courier.bean.result.HttpResultCommentList;
import com.decent.courier.bean.result.HttpResultDecentOrderList;
import com.decent.courier.common.BaseActivity;
import com.decent.courier.common.CommentItem;
import com.decent.courier.common.DecentCommentPagination;
import com.decent.courier.common.DecentOrderPagination;
import com.decent.courier.common.OrderItem;
import com.decent.courier.listener.IHttpRequestCallback;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentLogUtil;
import com.decent.courier.utils.DecentToast;
import com.decent.courier.utils.JsonUtil;
import com.decent.courier.view.PullToRefreshLayout;
import com.decent.courier.view.PullToRefreshLayout.OnRefreshListener;
import com.decent.courier.view.PullableListView;

public class BusinessActivity extends BaseActivity implements OnClickListener,
		OnRefreshListener, OnItemClickListener {

	/**
	 * 查看历史订单菜单的btn，此activity默认也是显示历史订单
	 */
	private Button history_order_btn;
	/**
	 * 查看评价的btn
	 */
	private Button comment_order_btn;
	/**
	 * 下拉刷新，上拉加载的refresh_view
	 */
	private PullToRefreshLayout refresh_view;

	/**
	 * 显示内容的listView
	 */
	private PullableListView content_view;
	/**
	 * 没有数据显示的text
	 */
	private TextView data_no_view;
	/**
	 * 网络异常的text
	 */
	private TextView network_error_view;
	private boolean isShowOrder;
	private List<OrderItem> orderList;
	private List<CommentItem> commentList;
	private ProgressDialog dialog;
	private OrderListAdapter mOrderListAdapter;

	private DecentOrderPagination decentOrderPagination;
	private CommentListAdapter mCommentListAdapter;
	protected DecentCommentPagination mDecentCommentPagination;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_business);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		history_order_btn = (Button) findViewById(R.id.history_order_btn);
		comment_order_btn = (Button) findViewById(R.id.comment_order_btn);
		refresh_view = (PullToRefreshLayout) findViewById(R.id.refresh_view);
		content_view = (PullableListView) findViewById(R.id.content_view);
		data_no_view = (TextView) findViewById(R.id.data_no_view);
		network_error_view = (TextView) findViewById(R.id.network_error_view);
		dialog = new ProgressDialog(this);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		history_order_btn.setOnClickListener(this);
		comment_order_btn.setOnClickListener(this);
		refresh_view.setOnRefreshListener(this);
		content_view.setOnItemClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		isShowOrder = true;
		orderList = new ArrayList<OrderItem>();
		commentList = new ArrayList<CommentItem>();

		// 初始化的时候,加载orderlist到contentview里面显示
		HttpRequestOrderList orderListRequest = new HttpRequestOrderList(0,
				DecentConstants.ITEM_PER_PAGE);
		getOrderListFromServer(false, false, orderListRequest);
	}

	private void getCommentListFromServer(final boolean isLoadMore,
			boolean isRefresh, HttpRequestCommentList request) {
		if (!isRefresh && !isLoadMore) {
			if (!dialog.isShowing()) {
				dialog.show();
			}
		}
		// TODO Auto-generated method stub
		mHttpRequest.doQuestByPostMethod(DecentConstants.ALL_COMMENTS_URL,
				request, true, new IHttpRequestCallback() {

					@Override
					public void onRequestSuccess(String result) {
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						// TODO Auto-generated method stub
						DecentLogUtil.d("comment result=" + result);
						HttpResultCommentList commentListResult = JsonUtil
								.getInstanceFromJsonStr(result,
										HttpResultCommentList.class);
						mDecentCommentPagination = commentListResult
								.getPagination();
						if (isLoadMore) {
							mergeCommentResult(commentList,
									mDecentCommentPagination.getItems());
						} else {
							// 刷新和第一次加载不需要合并数据，直接使用返回值
							commentList = mDecentCommentPagination.getItems();
						}
						mCommentListAdapter = new CommentListAdapter(
								commentList, BusinessActivity.this);
						content_view.setAdapter(mCommentListAdapter);
						refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
					}

					@Override
					public void onRequestFail(String result) {
						// TODO Auto-generated method stub
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
						DecentToast.showToastShort(BusinessActivity.this,
								result);
					}
				});
	}

	/**
	 * 合并结果列表，剔除重复的的item，会更新到oldCommentList里面
	 * 
	 * @param oldCommentList
	 *            老的结果列表
	 * @param newResultItems
	 *            新的结果列表
	 */
	protected void mergeCommentResult(List<CommentItem> oldCommentList,
			List<CommentItem> newResultItems) {
		List<CommentItem> tmpList = new ArrayList<CommentItem>();
		// TODO Auto-generated method stub
		for (CommentItem newItem : newResultItems) {
			boolean isRealNewResult = true;
			for (CommentItem oldItem : oldCommentList) {
				if (oldItem.getId() == newItem.getId()) {
					isRealNewResult = false;
				}
			}
			if (isRealNewResult) {
				tmpList.add(newItem);
			}
		}
		oldCommentList.addAll(tmpList);
	}

	/**
	 * 从服务器获取订单列表
	 * 
	 * @param isLoadMore
	 *            是否是加载更多
	 * @param isFresh
	 *            是否是更新
	 * @param orderListRequest
	 *            请求内容
	 */
	private void getOrderListFromServer(final boolean isLoadMore,
			boolean isReFresh, HttpRequestOrderList orderListRequest) {
		if (!isReFresh && !isLoadMore) {
			dialog.setMessage("正在加载订单");
			dialog.show();
		}
		// TODO Auto-generated method stub
		mHttpRequest.doMultiQuestByPostMethod(DecentConstants.ORDERLIST_URL,
				orderListRequest, true, new IHttpRequestCallback() {

					@Override
					public void onRequestSuccess(String result) {
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						// TODO Auto-generated method stub
						HttpResultDecentOrderList ordersListResult = JsonUtil
								.getInstanceFromJsonStr(result,
										HttpResultDecentOrderList.class);
						DecentLogUtil.d("oder result=" + result);
						decentOrderPagination = ordersListResult
								.getPagination();
						// 如果是loadMore存在一个和之前的内容合并的问题
						if (isLoadMore) {
							mergeOrderList(orderList,
									decentOrderPagination.getItems());
						} else {
							// 如果是第一次加载和fresh则就直接是结果
							orderList = decentOrderPagination.getItems();
						}
						mOrderListAdapter = new OrderListAdapter(orderList,
								BusinessActivity.this);
						content_view.setAdapter(mOrderListAdapter);
						refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);

					}

					@Override
					public void onRequestFail(String result) {
						// TODO Auto-generated method stub
						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
						DecentToast.showToastShort(BusinessActivity.this,
								result);
					}
				});

	}

	/**
	 * 新的订单list和之前的订单列表把相同的内容合并调 会更新oldOrderList的内容
	 * 
	 * @param oldOrderList
	 *            老的结果列表
	 * @param newResultList
	 *            新的结果列表
	 */
	private void mergeOrderList(List<OrderItem> oldOrderList,
			List<OrderItem> newResultList) {
		// TODO Auto-generated method stub
		List<OrderItem> tmpList = new ArrayList<OrderItem>();
		for (OrderItem newResultItem : newResultList) {
			boolean isRealNewItem = true;
			for (OrderItem oldItem : oldOrderList) {
				// 如果出现相同的则表示不需要添加到tmpList
				if (newResultItem.getId() == oldItem.getId()) {
					isRealNewItem = false;
					break;
				}
			}
			if (isRealNewItem) {
				tmpList.add(newResultItem);
			}
		}
		// 把tmpList加入到oldOrderList
		oldOrderList.addAll(tmpList);
	}

	@Override
	public void onClick(View v) {
		// 就是根据id,给content_view切换不同的adapter
		switch (v.getId()) {
		case R.id.history_order_btn:
			isShowOrder = true;
			if (mOrderListAdapter == null) {
				mOrderListAdapter = new OrderListAdapter(orderList, this);
			}
			content_view.setAdapter(mOrderListAdapter);
			break;
		case R.id.comment_order_btn:
			isShowOrder = false;
			if (mCommentListAdapter == null) {
				HttpRequestCommentList request = new HttpRequestCommentList(0,
						DecentConstants.ITEM_PER_PAGE);
				// 就让第一次的mCommentListAdapter在HttpRequestCommentList里面去创建
				getCommentListFromServer(false, false, request);
			} else {
				content_view.setAdapter(mCommentListAdapter);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
		// TODO Auto-generated method stub
		if (isShowOrder) {
			// 更新第一页
			HttpRequestOrderList orderListRequest = new HttpRequestOrderList(0,
					DecentConstants.ITEM_PER_PAGE);
			getOrderListFromServer(false, true, orderListRequest);
		} else {
			HttpRequestCommentList commentListRequest = new HttpRequestCommentList(
					0, DecentConstants.ITEM_PER_PAGE);
			getCommentListFromServer(false, true, commentListRequest);
		}
	}

	@Override
	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
		// TODO Auto-generated method stub
		if (isShowOrder) {
			// onLoadMore的时候则是，加载下一页
			HttpRequestOrderList orderListRequest = new HttpRequestOrderList(
					decentOrderPagination.getCurrentPage() + 1,
					DecentConstants.ITEM_PER_PAGE);
			getOrderListFromServer(true, false, orderListRequest);
		} else {
			HttpRequestCommentList commentListRequest = new HttpRequestCommentList(
					mDecentCommentPagination.getCurrentPage() + 1,
					DecentConstants.ITEM_PER_PAGE);
			getCommentListFromServer(true, false, commentListRequest);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// 获取到点击的orderItem然后
		if (isShowOrder) {
			Intent orderInfoIntent = new Intent();
			orderInfoIntent.setClass(this, OrderItemActivity.class);
			//获取点击对应的orderItem，放到putExtra里面然后开启OrderInfoActivity
			OrderItem orderItem = orderList.get(position);
			orderInfoIntent.putExtra(DecentConstants.ORDER_ITEM, orderItem);
			orderInfoIntent.putExtra(DecentConstants.IS_GRAP_ORDER, false);
			startActivity(orderInfoIntent);
		}else{
			
		}
	}

}
