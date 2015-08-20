package com.tz.asy.http.common;

import java.util.List;

public class CommentPagination extends CourierPagination{
	private List<CommentItem> items;

	public List<CommentItem> getItems() {
		return items;
	}

	public void setItems(List<CommentItem> items) {
		this.items = items;
	}
}
