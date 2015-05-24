package com.tz.michael.adapter;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tz.michael.activity.R;
import com.tz.michael.bean.Students;
import com.tz.michael.utils.ViewHolder;

public class MyListAdapter extends MyCustomAdapter<Students>{

	public MyListAdapter(Context mContext, List<Students> list, int layoutId) {
		super(mContext, list, layoutId);
	}

	@Override
	public void convert(ViewHolder holder, Students t) {
		RelativeLayout ra=holder.getView(R.id.ra);
		if(t.getGender()==0){
			ra.setBackgroundColor(0x33ff0000);
		}else{
			ra.setBackgroundColor(0x330000ff);
		}
		ImageView img=holder.getView(R.id.img_potrait);
		img.setImageResource(t.getPotrait());
		holder.setText(R.id.tv_name, t.getName()).setText(R.id.tv_age, t.getAge()+"").setText(R.id.tv_interest, t.getInterest());
	}

}
