package com.decent.courier.common;

import java.io.Serializable;

import android.os.Parcel;

/**
 * Created by Administrator on 2015/5/30.
 */
public class OrderItem implements Serializable {

	private long id;
	private String startPoint;
	private String endPoint;
	private String goods;
	private Integer quality;
	private Integer price;
	private String cellphone;
	private String createTime;
	private Integer status;
	private String receiver;
	private String receiverCellphone;
	private String receiveTime;
	private Integer userId;
	private boolean hasComment;
	private Integer fromCity;
	private Integer toCity;
	private Integer orderType;
	private String sender;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverCellphone() {
		return receiverCellphone;
	}

	public void setReceiverCellphone(String receiverCellphone) {
		this.receiverCellphone = receiverCellphone;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isHasComment() {
		return hasComment;
	}

	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}

	public Integer getFromCity() {
		return fromCity;
	}

	public void setFromCity(Integer fromCity) {
		this.fromCity = fromCity;
	}

	public Integer getToCity() {
		return toCity;
	}

	public void setToCity(Integer toCity) {
		this.toCity = toCity;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "OrdersItems{" + "id=" + id + ", startPoint='" + startPoint
				+ '\'' + ", endPoint='" + endPoint + '\'' + ", goods='" + goods
				+ '\'' + ", quality=" + quality + ", price=" + price
				+ ", cellphone='" + cellphone + '\'' + ", createTime='"
				+ createTime + '\'' + ", status=" + status + ", receiver='"
				+ receiver + '\'' + ", receiverCellphone='" + receiverCellphone
				+ '\'' + ", receiveTime='" + receiveTime + '\'' + ", userId="
				+ userId + ", hasComment=" + hasComment + ", fromCity="
				+ fromCity + ", toCity=" + toCity + ", orderType=" + orderType
				+ '}';
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}
}
