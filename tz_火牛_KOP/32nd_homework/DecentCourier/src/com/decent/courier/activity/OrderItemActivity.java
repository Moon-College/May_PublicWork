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

	// һ��layout����Ŀؼ�
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
		// sqliteֱ��open���ݿ��ļ�����ʼ��ȫ�������ص����ݿ⣬��ʾ��ʱ��ò�ѯ
		mDb = SQLiteDatabase.openDatabase(DecentConstants.LOCATION_DB_PATH,
				null, Context.MODE_PRIVATE);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		// һ��findViewById,��ʼ���ؼ�
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

		//�ܵ�layout
		orderInfoLayout = (LinearLayout)findViewById(R.id.orderInfoLayout);
		// ͨ������״̬���Ƿ���������order,����������ťcomOrder���Ƿ���ʾ���е绰�����Ƿ���ʾ
		if (mIsGrapOrder && mOrderItem.getStatus() == -1) {
			comOrder.setVisibility(View.VISIBLE);
			// ��ݷ����ߵĵ绰
			cellphoneTv.setText(mOrderItem.getCellphone());
			// ��ݽ����˵ĵĵ绰
			receivercellphoneTv.setText(mOrderItem.getReceiverCellphone());
		} else {
			comOrder.setVisibility(View.GONE);
			cellphoneTv.setText(getString(R.string.user_order_cell));
			receivercellphoneTv
					.setText(getString(R.string.receiver_order_cell));
		}

		// �����Ƕ���Щ�տؼ����ݵ����
		startpointTv.setText(mOrderItem.getStartPoint());
		cellphoneTv.setTextColor(getResources().getColor(R.color.gold));

		// ����Ӧ��ֱ�Ӵӷ���������
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

		// ��ȡ����״̬���ַ������飬��ʾ��Ӧ��״̬
		String[] status = this.getResources().getStringArray(
				R.array.order_statu);
		int statu = mOrderItem.getStatus().intValue();
		// ����-1��ʱ����ǿ�����������ʱ��û��״̬
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
		// �������������Ĵ���
		comOrder.setOnClickListener(this);
		// ���÷��Ϳ���˵ĵ绰���Ŷ���
		cellphoneTv.setOnClickListener(this);
		// ���ý��տ���˵绰���Ŷ���
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
            //������������
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
						//�����ɹ����¸����Ǽ����ֶ�
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
