package com.snowj.volume.widget;

import android.view.View;
import android.view.ViewGroup;

public interface ListItem {
   public View getView(int position, View convertView, ViewGroup parent);
}
