package com.decent.courier.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.decent.courier.activity.R;
import com.decent.courier.utils.BitmapUtils;
import com.decent.courier.utils.DecentToast;
import com.decent.courier.utils.RegExUtil;

public class Register2ndFragment extends Fragment implements OnClickListener {
	private static final int FRONT_IMAGE_REQUEST = 0;
	private static final int REAR_IMAGE_REQUEST = 1;
	private static final int CARD_IMAGE_REQUEST = 2;
	// 用于预览的三个ImageView
	private ImageView front_photo;
	private ImageView back_photo;
	private ImageView cert_photo;

	// 三个点击弹出图片选取窗口的TextView
	private TextView tv_card_front;
	private TextView tv_card_rear;
	private TextView tv_card;

	// 这一页还有的两个输入框
	private EditText et_age;
	private EditText et_name;
	private String front_photo_path;
	private String rear_photo_path;
	private String cert_photo_path;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.register_fragment_two, null);
		// 用于预览的三个ImageView
		front_photo = (ImageView) view.findViewById(R.id.front_photo);
		back_photo = (ImageView) view.findViewById(R.id.back_photo);
		cert_photo = (ImageView) view.findViewById(R.id.cert_photo);

		if(!RegExUtil.isStringEmpty(front_photo_path)){
			front_photo.setImageBitmap(BitmapUtils.getScaleBitmapByPath(front_photo_path,
				this.getActivity()));
		}

		if(!RegExUtil.isStringEmpty(rear_photo_path)){
			back_photo.setImageBitmap(BitmapUtils.getScaleBitmapByPath(rear_photo_path,
				this.getActivity()));
		}
		
		if(!RegExUtil.isStringEmpty(cert_photo_path)){
			cert_photo.setImageBitmap(BitmapUtils.getScaleBitmapByPath(cert_photo_path,
				this.getActivity()));
		}		
		
		tv_card_front = (TextView) view.findViewById(R.id.tv_card_front);
		tv_card_rear = (TextView) view.findViewById(R.id.tv_card_rear);
		tv_card = (TextView) view.findViewById(R.id.tv_card);

		// 设置三个图片选取text的onclick函数
		tv_card_front.setOnClickListener(this);
		tv_card_rear.setOnClickListener(this);
		tv_card.setOnClickListener(this);

		et_age = (EditText) view.findViewById(R.id.et_age);
		et_name = (EditText) view.findViewById(R.id.et_name);
		// TODO Auto-generated method stub
		return view;
	}

	public EditText getEt_age() {
		return et_age;
	}

	public void setEt_age(EditText et_age) {
		this.et_age = et_age;
	}

	public EditText getEt_name() {
		return et_name;
	}

	public void setEt_name(EditText et_name) {
		this.et_name = et_name;
	}

	public String getFront_photo_path() {
		return front_photo_path;
	}

	public void setFront_photo_path(String front_photo_path) {
		this.front_photo_path = front_photo_path;
	}

	public String getRear_photo_path() {
		return rear_photo_path;
	}

	public void setRear_photo_path(String rear_photo_path) {
		this.rear_photo_path = rear_photo_path;
	}

	public String getCert_photo_path() {
		return cert_photo_path;
	}

	public void setCert_photo_path(String cert_photo_path) {
		this.cert_photo_path = cert_photo_path;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		switch (v.getId()) {
		case R.id.tv_card_front:
			startActivityForResult(intent, FRONT_IMAGE_REQUEST);
			break;
		case R.id.tv_card_rear:
			startActivityForResult(intent, REAR_IMAGE_REQUEST);
			break;
		case R.id.tv_card:
			startActivityForResult(intent, CARD_IMAGE_REQUEST);
			break;
		default:
			break;
		}
	}

	/*
	 * private static final int FRONT_IMAGE_REQUEST = 0; private static final
	 * int REAR_IMAGE_REQUEST = 1; private static final int CARD_IMAGE_REQUEST =
	 * 2;(non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Activity.RESULT_OK != resultCode || data == null) {
			DecentToast.showToastLong(this.getActivity(), "获取图片失败");
			return;
		}
		Uri selectedImageUri = data.getData();
		String selectedImagePath = BitmapUtils.getPicPathByUri(
				selectedImageUri, this.getActivity());
		Bitmap bitmap = BitmapUtils.getScaleBitmapByPath(selectedImagePath,
				this.getActivity());
		switch (requestCode) {
		case FRONT_IMAGE_REQUEST:
			front_photo.setImageBitmap(bitmap);
			front_photo_path = selectedImagePath;
			break;
		case REAR_IMAGE_REQUEST:
			back_photo.setImageBitmap(bitmap);
			rear_photo_path = selectedImagePath;
			break;
		case CARD_IMAGE_REQUEST:
			cert_photo.setImageBitmap(bitmap);
			cert_photo_path = selectedImagePath;
			break;
		default:
			break;
		}
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

}
