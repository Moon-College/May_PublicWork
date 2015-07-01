package com.decent.decentviewpager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.decent.decentviewpager.R;

public class ImmortalsFragment extends Fragment {

	public static final String IMG_RES = "imgRes";
	public static final String DES_RES = "desRes";
	public static final String IMMORTAL_INDEX = "immortalIndex";
	
	private static final String TAG = "ImmortalsFragment";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * inflate�õ�view
		 */
		View view = inflater.inflate(R.layout.immortal_item, null);
		/*
		 * �õ�Arguments
		 */
		Bundle bundle = getArguments();
		int imgRes = bundle.getInt(IMG_RES);
		int desRes = bundle.getInt(DES_RES);
		int immortalIndex = bundle.getInt(IMMORTAL_INDEX);
		Log.d(TAG, "onCreateView immortalIndex="+immortalIndex);
		/*
		 * ������Ӧ��resources���ؼ���
		 */
		ImageView immortalImg = (ImageView) view.findViewById(R.id.immortalImg);
		TextView  immortalDes = (TextView) view.findViewById(R.id.immortalDes);
		immortalImg.setImageResource(imgRes);
		immortalDes.setText(desRes);
		/*
		 * ����
		 */
		return view;
	}

}
