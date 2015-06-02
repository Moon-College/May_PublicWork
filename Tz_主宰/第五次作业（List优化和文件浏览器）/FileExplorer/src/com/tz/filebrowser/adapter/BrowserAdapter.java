package com.tz.filebrowser.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.filebrowser.consts.FileType;
import com.tz.filebrowser.manager.DataProvider;
import com.tz.filebrowser.vo.MyFile;
import com.tz.fileexplorer.R;

public class BrowserAdapter extends BaseAdapter {
	private Bitmap BACK_BITMAP;
	private Bitmap FILE_BITMAP;
	private Bitmap DIR_BITMAP;
	private List<MyFile> data;
	private LayoutInflater inflater;

	public BrowserAdapter(Context context, List<MyFile> data) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);

		// 初始化默认图片
		BACK_BITMAP = DataProvider.getInstance().getResDrawable(
				DataProvider.BACK_DIR_ICON_RESID);
		FILE_BITMAP = DataProvider.getInstance().getResDrawable(
				DataProvider.FILE_ICON_RESID);
		DIR_BITMAP = DataProvider.getInstance().getResDrawable(
				DataProvider.DIR_ICON_RESID);
	}

	public void addItems(List<MyFile> data) {
		if (data != null) {
			this.data.addAll(data);
		}
	}

	public void addItem(MyFile people) {
		if (people != null) {
			data.add(people);
		}
	}

	public void clear() {
		if (data != null) {
			data.clear();
		}
	}

	public void addItem(List<MyFile> data) {
		if (data != null) {
			data.addAll(data);
		}
	}

	public void remove(int position) {
		if (position < 0 || position > data.size()) {
			return;
		}
		data.remove(position);
	}

	@Override
	public int getCount() {
		// 数据为null，则返回0
		if (data == null) {
			return 0;
		}
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		if (data != null) {
			return data.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		// 初始化view
		if (convertView == null) {
			// inflate item view
			convertView = inflater.inflate(R.layout.item_file, null);

			holder = new ViewHolder();
			holder.iconView = (ImageView) convertView
					.findViewById(R.id.item_file_icon);
			holder.nameView = (TextView) convertView
					.findViewById(R.id.item_file_name);
			holder.attributeView = (TextView) convertView
					.findViewById(R.id.item_file_attribute);
			holder.arrowView = (ImageView) convertView
					.findViewById(R.id.item_file_arrow);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 获取数据
		MyFile myFile = data.get(position);

		holder.nameView.setText(myFile.getName());
		String attrs;
		if (myFile.getFileType() == FileType.DIR) {// 文件夹
			holder.arrowView.setVisibility(View.VISIBLE);
			holder.iconView.setImageBitmap(position == 0 ? BACK_BITMAP
					: DIR_BITMAP);
			attrs = String.format("文件：%d，文件夹：%d", myFile.getChildFileCount(),
					myFile.getChildDirCount());
		} else {// 文件
			holder.arrowView.setVisibility(View.GONE);
			String date = DataProvider.date2String(myFile.getCreateTime(),
					"yyyy年MM月dd日");

			// 计算大小获取对应单位
			if (myFile.getSize() < (1 << 10)) {
				attrs = String.format("%s %.2fB", date,
						((double) myFile.getSize()));
			}
			if (myFile.getSize() < (1 << 20)) {
				attrs = String.format("%s %.2fKB", date,
						(((double) myFile.getSize()) / (1 << 10)));
			} else if (myFile.getSize() < (1 << 30)) {
				attrs = String.format("%s %.2fMB", date,
						(((double) myFile.getSize()) / (1 << 20)));
			} else {
				attrs = String.format("%s %.2fGB", date,
						(((double) myFile.getSize()) / (1 << 30)));
			}

			if (myFile.getFileType() == FileType.FILE) {
				holder.iconView.setImageBitmap(FILE_BITMAP);
			} else {
				DataProvider.getInstance().displayBitmap(myFile,
						holder.iconView);
			}
		}
		holder.attributeView.setText(attrs);

		return convertView;
	}

	private class ViewHolder {
		ImageView iconView;
		TextView nameView;
		TextView attributeView;
		ImageView arrowView;
	}
}
