package com.hq.ays.fragment;

import java.io.FileNotFoundException;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hq.ays.activity.R;
import com.hq.ays.utils.BitmapUtils;
import com.hq.ays.utils.MyConstants;

public class RegistNextFragment extends Fragment implements OnClickListener{
	TextView tvCardFront,tvCardRear,tvCard;
	EditText etAge,etName;
	ImageView frontPhoto,backPhoto,certPhoto;
	private String frontPhotoPath,backPhotoPath,certPhotoPath;
	public String getFrontPhotoPath() {
		return frontPhotoPath;
	}

	public void setFrontPhotoPath(String frontPhotoPath) {
		this.frontPhotoPath = frontPhotoPath;
	}

	public String getBackPhotoPath() {
		return backPhotoPath;
	}

	public void setBackPhotoPath(String backPhotoPath) {
		this.backPhotoPath = backPhotoPath;
	}

	public String getCertPhotoPath() {
		return certPhotoPath;
	}

	public void setCertPhotoPath(String certPhotoPath) {
		this.certPhotoPath = certPhotoPath;
	}

	private Uri frontUri,backUri,certUri;
	public TextView getTvCardFront() {
		return tvCardFront;
	}

	public void setTvCardFront(TextView tvCardFront) {
		this.tvCardFront = tvCardFront;
	}

	public TextView getTvCardRear() {
		return tvCardRear;
	}

	public void setTvCardRear(TextView tvCardRear) {
		this.tvCardRear = tvCardRear;
	}

	public TextView getTvCard() {
		return tvCard;
	}

	public void setTvCard(TextView tvCard) {
		this.tvCard = tvCard;
	}

	public EditText getEtAge() {
		return etAge;
	}

	public void setEtAge(EditText etAge) {
		this.etAge = etAge;
	}

	public EditText getEtName() {
		return etName;
	}

	public void setEtName(EditText etName) {
		this.etName = etName;
	}

	public ImageView getFrontPhoto() {
		return frontPhoto;
	}

	public void setFrontPhoto(ImageView frontPhoto) {
		this.frontPhoto = frontPhoto;
	}

	public ImageView getBackPhoto() {
		return backPhoto;
	}

	public void setBackPhoto(ImageView backPhoto) {
		this.backPhoto = backPhoto;
	}

	public ImageView getCertPhoto() {
		return certPhoto;
	}

	public void setCertPhoto(ImageView certPhoto) {
		this.certPhoto = certPhoto;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v= inflater.inflate(R.layout.registe_fragment_two, null);
		tvCardFront = (TextView) v.findViewById(R.id.tv_card_front);
		tvCardRear = (TextView) v.findViewById(R.id.tv_card_rear);
		tvCard = (TextView) v.findViewById(R.id.tv_card);
		etAge = (EditText) v.findViewById(R.id.et_age);
		etName = (EditText) v.findViewById(R.id.et_name);
		frontPhoto = (ImageView) v.findViewById(R.id.front_photo);
		backPhoto = (ImageView) v.findViewById(R.id.back_photo);
		certPhoto = (ImageView) v.findViewById(R.id.cert_photo);
		//如果uri不为空
		if(frontUri!=null){
			try {
				frontPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(frontUri, getActivity()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(backUri!=null){
			try {
				backPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(frontUri, getActivity()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(certUri!=null){
			try {
				certPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(frontUri, getActivity()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tvCardFront.setOnClickListener(this);
		tvCardRear.setOnClickListener(this);
		tvCard.setOnClickListener(this);
		return v;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_card_front:
			//去身份证正面
			doPickPhotoFromGallery(MyConstants.FRONT_CARD);
			break;
		case R.id.tv_card_rear:
			//获取身份证背面
			doPickPhotoFromGallery(MyConstants.REAL_CARD);
			break;
		case R.id.tv_card:
			doPickPhotoFromGallery(MyConstants.CERT_CARD);
			//获取其他证件
			break;
		default:
			break;
		}
	}
	
	
	public void doPickPhotoFromGallery(int requestCode){
		//开启相机或者图库
		//首先判断sd是否存在
		String state = Environment.getExternalStorageState();
		if(!state.equals(Environment.MEDIA_MOUNTED)){
			Toast.makeText(this.getActivity(), "未找到sd卡", 1000).show();
			return;
		}else{
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/");
			startActivityForResult(intent, requestCode);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case MyConstants.FRONT_CARD:
			//已经选取了正面的图片
			Log.i("INFO", "frontPhoto");
			try {
				frontUri = data.getData();
				frontPhotoPath = BitmapUtils.getPicPathByUri(frontUri, getActivity());
				frontPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(), getActivity()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MyConstants.REAL_CARD:
			//背面
			try {
				backUri = data.getData();
				backPhotoPath = BitmapUtils.getPicPathByUri(backUri, getActivity());
				backPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(), getActivity()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MyConstants.CERT_CARD:
			//其他证件
			try {
				certUri = data.getData();
				certPhotoPath = BitmapUtils.getPicPathByUri(certUri, getActivity());
				certPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(), getActivity()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
}
