package com.decent.courier.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
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
		OnRefreshListener {

	/**
	 * �鿴��ʷ�����˵���btn����activityĬ��Ҳ����ʾ��ʷ����
	 */
	private Button history_order_btn;
	/**
	 * �鿴���۵�btn
	 */
	private Button comment_order_btn;
	/**
	 * ����ˢ�£��������ص�refresh_view
	 */
	private PullToRefreshLayout refresh_view;

	/**
	 * ��ʾ���ݵ�listView
	 */
	private PullableListView content_view;
	/**
	 * û��������ʾ��text
	 */
	private TextView data_no_view;
	/**
	 * �����쳣��text
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
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		isShowOrder = true;
		orderList = new ArrayList<OrderItem>();
		commentList = new ArrayList<CommentItem>();

		// ��ʼ����ʱ��,����orderlist��contentview������ʾ
		HttpRequestOrderList orderListRequest = new HttpRequestOrderList(0,
				DecentConstants.ITEM_PER_PAGE);
		getOrderListFromServer(false, false, orderListRequest);
	}

	private void getCommentListFromServer(final boolean isLoadMore,
			boolean isRefresh, HttpRequestCommentList request) {
		if (!isRefresh&&!isLoadMore) {
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
							// ˢ�º͵�һ�μ��ز���Ҫ�ϲ����ݣ�ֱ��ʹ�÷���ֵ
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
	 * �ϲ�����б��޳��ظ��ĵ�item������µ�oldCommentList����
	 * 
	 * @param oldCommentList
	 *            �ϵĽ���б�
	 * @param newResultItems
	 *            �µĽ���б�
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
	 * �ӷ�������ȡ�����б�
	 * 
	 * @param isLoadMore
	 *            �Ƿ��Ǽ��ظ���
	 * @param isFresh
	 *            �Ƿ��Ǹ���
	 * @param orderListRequest
	 *            ��������
	 */
	private void getOrderListFromServer(final boolean isLoadMore,
			boolean isReFresh, HttpRequestOrderList orderListRequest) {
		if (!isReFresh && !isLoadMore) {
			dialog.setMessage("���ڼ��ض���");
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
						// �����loadMore����һ����֮ǰ�����ݺϲ�������
						if (isLoadMore) {
							mergeOrderList(orderList,
									decentOrderPagination.getItems());
						} else {
							// ����ǵ�һ�μ��غ�fresh���ֱ���ǽ��
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
	 * �µĶ���list��֮ǰ�Ķ����б����ͬ�����ݺϲ��� �����oldOrderList������
	 * 
	 * @param oldOrderList
	 *            �ϵĽ���б�
	 * @param newResultList
	 *            �µĽ���б�
	 */
	private void mergeOrderList(List<OrderItem> oldOrderList,
			List<OrderItem> newResultList) {
		// TODO Auto-generated method stub
		List<OrderItem> tmpList = new ArrayList<OrderItem>();
		for (OrderItem newResultItem : newResultList) {
			boolean isRealNewItem = true;
			for (OrderItem oldItem : oldOrderList) {
				// ���������ͬ�����ʾ����Ҫ��ӵ�tmpList
				if (newResultItem.getId() == oldItem.getId()) {
					isRealNewItem = false;
					break;
				}
			}
			if (isRealNewItem) {
				tmpList.add(newResultItem);
			}
		}
		// ��tmpList���뵽oldOrderList
		oldOrderList.addAll(tmpList);
	}

	@Override
	public void onClick(View v) {
		// ���Ǹ���id,��content_view�л���ͬ��adapter
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
				// ���õ�һ�ε�mCommentListAdapter��HttpRequestCommentList����ȥ����
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
			// ���µ�һҳ
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
			// onLoadMore��ʱ�����ǣ�������һҳ
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

}
