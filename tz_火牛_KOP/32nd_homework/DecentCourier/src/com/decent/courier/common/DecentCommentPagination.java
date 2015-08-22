package com.decent.courier.common;

import java.util.List;

public class DecentCommentPagination extends DecentPagination {
	private List<CommentItem> items;

	public List<CommentItem> getItems() {
		return items;
	}

	public void setItems(List<CommentItem> items) {
		this.items = items;
	}

}
