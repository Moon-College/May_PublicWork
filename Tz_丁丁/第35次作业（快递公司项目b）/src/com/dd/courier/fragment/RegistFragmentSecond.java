package com.dd.courier.fragment;

import com.dd.ays_dd_courier.R;
import com.dd.courier.common.MyConstants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class RegistFragmentSecond extends Fragment implements OnClickListener{
	private String faceImage;//正面证件照
	private String backImage;//背面身份证证件照
	private String licenseImage;//其他证件，比如驾驶证
	private EditText et_age;
	private EditText et_name;
	public String getFaceImage() {
		return faceImage;
	}
	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}
	public String getBackImage() {
		return backImage;
	}
	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}
	public String getLicenseImage() {
		return licenseImage;
	}
	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
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
	private ImageView img_faceImage;
	private ImageView img_backImage;
	private ImageView img_licenseImage;
	private TextView tv_front_img;
	private TextView tv_back_img;
	private TextView tv_card_img;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.registe_fragment_two, null);
		et_age = (EditText) view.findViewById(R.id.et_age);
		et_name = (EditText) view.findViewById(R.id.et_name);
		img_faceImage = (ImageView) view.findViewById(R.id.front_photo);
		img_backImage = (ImageView) view.findViewById(R.id.back_photo);
		img_licenseImage = (ImageView) view.findViewById(R.id.cert_photo);
		tv_front_img = (TextView) view.findViewById(R.id.tv_card_front);
		tv_back_img = (TextView) view.findViewById(R.id.tv_card_rear);
		tv_card_img = (TextView) view.findViewById(R.id.tv_card);
		tv_front_img.setOnClickListener(this);
		return view;
	}
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		
		switch (v.getId()) {
		case R.id.tv_card_front:
			startActivityForResult(intent, MyConstants.PHOTO_CDRD_FRONT_DATA);
			break;
		case R.id.tv_card_rear:
			startActivityForResult(intent, MyConstants.PHOTO_CDRD_REAR_DATA);
			break;
		case R.id.tv_card:
			startActivityForResult(intent, MyConstants.PHOTO_CDRD_WITH_DATA);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * 作业，补充如何获取图片路径，以及通过uri加载图片与缩放显示在imageview里
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case MyConstants.PHOTO_CDRD_FRONT_DATA:
			//前面的图片
			Uri uri_front = data.getData();
			//通过uri加载并且缩放显示在imageview上，并且通过uri获取图片路径
			break;
		case MyConstants.PHOTO_CDRD_REAR_DATA:
			//背面图片
			Uri uri_back = data.getData();
			break;
		case MyConstants.PHOTO_CDRD_WITH_DATA:
			//证件图片
			Uri uri_card = data.getData();
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
