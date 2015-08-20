package com.tz.asy.http.common;

import java.util.List;

public class OrderPagination extends CourierPagination{
	private List<OrdersItem> items;

	public List<OrdersItem> getItems() {
		return items;
	}

	public void setItems(List<OrdersItem> items) {
		this.items = items;
	}
}
