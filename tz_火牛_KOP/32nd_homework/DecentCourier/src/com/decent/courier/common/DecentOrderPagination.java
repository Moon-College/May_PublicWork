package com.decent.courier.common;

import java.util.List;

public class DecentOrderPagination extends DecentPagination {
	private List<OrderItem> items;

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "DecentOrderPagination [items=" + items + "]";
	}
	
}
