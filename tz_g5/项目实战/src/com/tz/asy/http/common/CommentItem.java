package com.tz.asy.http.common;

import java.io.Serializable;

public class CommentItem implements Serializable{
	private Integer id;
	private Integer orderId;
	private Integer userId;
	private Integer serviceAttitude;
	private Integer distributionSpeed;
	private Integer perfection;
	private String comment;
	private String createTime;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getServiceAttitude() {
		return serviceAttitude;
	}
	public void setServiceAttitude(Integer serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}
	public Integer getDistributionSpeed() {
		return distributionSpeed;
	}
	public void setDistributionSpeed(Integer distributionSpeed) {
		this.distributionSpeed = distributionSpeed;
	}
	public Integer getPerfection() {
		return perfection;
	}
	public void setPerfection(Integer perfection) {
		this.perfection = perfection;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CommentItem [id=" + id + ", orderId=" + orderId + ", userId="
				+ userId + ", serviceAttitude=" + serviceAttitude
				+ ", distributionSpeed=" + distributionSpeed + ", perfection="
				+ perfection + ", comment=" + comment + ", createTime="
				+ createTime + "]";
	}
	
	
}
