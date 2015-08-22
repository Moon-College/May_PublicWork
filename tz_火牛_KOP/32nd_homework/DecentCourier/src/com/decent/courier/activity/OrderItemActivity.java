package com.decent.courier.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.decent.courier.bean.request.HttpRequestGrapOrder;
import com.decent.courier.common.BaseActivity;
import com.decent.courier.common.OrderItem;
import com.decent.courier.listener.IHttpRequestCallback;
import com.decent.courier.utils.CityDatabaseService;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentToast;
import com.decent.courier.utils.MyDataUtils;

public class OrderItemActivity extends BaseActivity implements OnClickListener {

	private static final String STR_TEL = "tel:";
	private OrderItem mOrderItem;
	private boolean mIsGrapOrder;

	// 一堆layout上面的控件
	private TextView startpointTv;
	private TextView cellphoneTv;
	private TextView fromcityTv;
	private TextView goodsTv;
	private TextView qualityTv;
	private TextView createtimeTv;
	private TextView receivetimeTv;
	private TextView priceTv;
	private TextView statusTv;
	private TextView endpointTv;
	private TextView receivercellphoneTv;
	private TextView receiverTv;
	private TextView tocityTv;
	private TextView sender;
	private Button comOrder;
	private ProgressDialog dialog;
	private SQLiteDatabase mDb;
	private LinearLayout orderInfoLayout;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_order_info);
		Intent intent = getIntent();
		mOrderItem = (OrderItem) intent.getExtras().getSerializable(
				DecentConstants.ORDER_ITEM);
		mIsGrapOrder = intent.getBooleanExtra(DecentConstants.IS_GRAP_ORDER,
				false);
		// sqlite直接open数据库文件，初始化全国区市县的数据库，显示的时候好查询
		mDb = SQLiteDatabase.openDatabase(DecentConstants.LOCATION_DB_PATH,
				null, Context.MODE_PRIVATE);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		// 一堆findViewById,初始化控件
		startpointTv = (TextView) findViewById(R.id.startpoint_tv);
		cellphoneTv = (TextView) findViewById(R.id.cellphone_tv);
		goodsTv = (TextView) findViewById(R.id.goods_tv);
		sender = (TextView) findViewById(R.id.sender_tv);
		qualityTv = (TextView) findViewById(R.id.quality_tv);
		createtimeTv = (TextView) findViewById(R.id.createtime_tv);
		receivetimeTv = (TextView) findViewById(R.id.receivetime_tv);
		priceTv = (TextView) findViewById(R.id.price_tv);
		statusTv = (TextView) findViewById(R.id.status_tv);
		endpointTv = (TextView) findViewById(R.id.endpoint_tv);
		receivercellphoneTv = (TextView) findViewById(R.id.receivercellphone_tv);
		receiverTv = (TextView) findViewById(R.id.receiver_tv);
		tocityTv = (TextView) findViewById(R.id.tocity_tv);
		fromcityTv = (TextView) findViewById(R.id.fromcity_tv);
		comOrder = (Button) findViewById(R.id.com_order);

		//总的layout
		orderInfoLayout = (LinearLayout)findViewById(R.id.orderInfoLayout);
		// 通过订单状态和是否是抢单的order,设置抢单按钮comOrder的是否显示还有电话号码是否显示
		if (mIsGrapOrder && mOrderItem.getStatus() == -1) {
			comOrder.setVisibility(View.VISIBLE);
			// 快递发送者的电话
			cellphoneTv.setText(mOrderItem.getCellphone());
			// 快递接受人的的电话
			receivercellphoneTv.setText(mOrderItem.getReceiverCellphone());
		} else {
			comOrder.setVisibility(View.GONE);
			cellphoneTv.setText(getString(R.string.user_order_cell));
			receivercellphoneTv
					.setText(getString(R.string.receiver_order_cell));
		}

		// 下面是对那些空控件内容的填充
		startpointTv.setText(mOrderItem.getStartPoint());
		cellphoneTv.setTextColor(getResources().getColor(R.color.gold));

		// 城市应该直接从服务器解析
		if (mDb != null) {
			String fromCity = CityDatabaseService.getCityById(mDb,
					mOrderItem.getFromCity());
			fromcityTv.setText(fromCity);
			String toCity = CityDatabaseService.getCityById(mDb,
					mOrderItem.getToCity());
			tocityTv.setText(toCity);
		}

		goodsTv.setText(mOrderItem.getGoods());
		sender.setText(mOrderItem.getSender());
		qualityTv.setText(String.valueOf(mOrderItem.getQuality()));
		createtimeTv.setText(mOrderItem.getCreateTime());
		receivetimeTv.setText(mOrderItem.getReceiveTime());
		priceTv.setText(String.valueOf(mOrderItem.getPrice()));

		// 获取订单状态的字符串数组，显示相应的状态
		String[] status = this.getResources().getStringArray(
				R.array.order_statu);
		int statu = mOrderItem.getStatus().intValue();
		// 等于-1的时候就是可以抢单，暂时还没有状态
		if (statu != -1) {
			statusTv.setText(status[statu]);
		}

		endpointTv.setText(mOrderItem.getEndPoint());
		receivercellphoneTv.setTextColor(getResources().getColor(R.color.gold));
		receiverTv.setText(mOrderItem.getReceiver());
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		// 设置抢单动作的处理
		comOrder.setOnClickListener(this);
		// 设置发送快递人的电话拨号动作
		cellphoneTv.setOnClickListener(this);
		// 设置接收快递人电话拨号动作
		receivercellphoneTv.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.com_order:
			String userName = MyDataUtils.getData(this, DecentConstants.USER, DecentConstants.USERNAME, String.class);
			String password = MyDataUtils.getData(this, DecentConstants.USER, DecentConstants.PASSWORD, String.class);
            //发送抢单请求
			HttpRequestGrapOrder request = new HttpRequestGrapOrder(mOrderItem.getId(), userName, password);
			mHttpRequest.doQuestByPostMethod(DecentConstants.GRAP_ORDER_URL, request, false, new IHttpRequestCallback() {
				
				@Override
				public void onRequestSuccess(String result) {
					// TODO Auto-generated method stub
					JSONObject object = JSON.parseObject(result);
					Integer ret = object.getInteger("ret");
					String msg = object.getString("msg");
					DecentToast.showToastShort(OrderItemActivity.this, msg);
					if(ret == 0){
						//抢单成功重新更新那几个字段
						orderInfoLayout.requestLayout();
					}
				}
				
				@Override
				public void onRequestFail(String result) {
					// TODO Auto-generated method stub
					DecentToast.showToastShort(OrderItemActivity.this, result);
				}
			});
			break;
		case R.id.cellphone_tv:
			Intent cellPhoneCall = new Intent(Intent.ACTION_CALL);
			cellPhoneCall.setData(Uri.parse(STR_TEL+mOrderItem.getCellphone()));
			startActivity(cellPhoneCall);
			break;
		case R.id.receivercellphone_tv:
			Intent receivercellPhoneCall = new Intent(Intent.ACTION_CALL);
			receivercellPhoneCall.setData(Uri.parse(STR_TEL+mOrderItem.getReceiverCellphone()));
			startActivity(receivercellPhoneCall);
			break;
		default:
			break;
		}
	}

}
