package com.decentsoft.hellohighview.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.decentsoft.hellohighview.R;
import com.decentsoft.hellohiview.bean.Immortal;

/**
 * 扩展的用于显示神仙的适配器
 * @author John
 *
 */
public class DsBaseAdapter extends BaseAdapter
{
    private static final String TAG = "DsBaseAdapter";
	private Context context;
	private List<Immortal> data;

	public List<Immortal> getData()
	{
		return data;
	}
	public void setData(List<Immortal> data)
	{
		this.data = data;
	}
	public DsBaseAdapter(Context context,List<Immortal> data)
	{
		this.context = context;
		this.data = data;
	}
	public int getCount()
	{
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		/**
		 * 通过context获取inflater,填充dslist_item到里面，得到最外层的LinearLayout
		 */
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout ll= (LinearLayout) inflater.inflate(com.decentsoft.hellohighview.R.layout.dslist_item, null);
		
		/**
		 * 获取用于显示的控件
		 */
		ImageView img= (ImageView) ll.findViewById(com.decentsoft.hellohighview.R.id.img);
		TextView  ts_sex = (TextView) ll.findViewById(com.decentsoft.hellohighview.R.id.tx_sex);
		TextView  ts_interst = (TextView) ll.findViewById(com.decentsoft.hellohighview.R.id.tx_interst);
		TextView  ts_name =  (TextView) ll.findViewById(com.decentsoft.hellohighview.R.id.tx_name);
		
		/**
		 * 获得对应的神仙信息，显示
		 */
		Immortal immortal = data.get(position);
		img.setImageResource(immortal.getHeadPic());
		ts_sex.setText(Immortal.SEX+immortal.getSex());
		ts_interst.setText(Immortal.INTEREST+immortal.getInterest());
		ts_name.setText(Immortal.NAME+immortal.getName());
		
		/**
		 * 根据性别设置背景显示
		 */
		if(Immortal.MALE.equals(immortal.getSex()))
		{
			Log.d(TAG, "MALE blue color");
			/* 蓝色 */
			ll.setBackgroundResource(R.color.blue);
			//ll.setBackgroundColor(Color.BLUE);
		}
		else if(Immortal.FEMALE.equals(immortal.getSex()))
		{
			Log.d(TAG, "FEMALE red color");
			/* 红色 */
			ll.setBackgroundResource(R.color.red);
			//ll.setBackgroundColor(Color.RED);
		}
		
		/**
		 * 返回ll
		 */
		return ll;
	}

}
