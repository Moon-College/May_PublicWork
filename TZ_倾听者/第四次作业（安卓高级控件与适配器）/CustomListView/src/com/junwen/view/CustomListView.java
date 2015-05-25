package com.junwen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class CustomListView extends ListView {
	//按下的X坐标
	private float scrollX =-1;
	//按下的Y坐标
	private float scrollY = -1;
	private OnDeleteListener ondelete;
	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//按下时,获得按下的X和Y坐标
			scrollX = ev.getX();
			scrollY = ev.getY();
			break;
		case MotionEvent.ACTION_UP:
			//获取按下和松开的距离
			float distansX = ev.getX() - scrollX;
			float distantY = ev.getY() - scrollY;
			//如果距离超过600
			if(distansX>600)
			{
				//可以删除了
				if(ondelete!=null)
				{
					int pointToPosition = pointToPosition((int)scrollX, (int)scrollY);
					ondelete.ondeleteitem(pointToPosition);
					return true;
				}
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	/**
	 * 设置监听器
	 * @param ondelete
	 */
	public void setOndelete(OnDeleteListener ondelete) {
		this.ondelete = ondelete;
	}
	/**
	 * 接口创建
	 * @author admi
	 *
	 */
	public interface OnDeleteListener
	{
		void ondeleteitem(int index);
	}
}
