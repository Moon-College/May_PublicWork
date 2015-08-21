package com.decent.courier.bean.request;

public class HttpRequestLocationReport implements HttpParams {
	private String longitude;// 业务员的经度
	private String latitude;// 业务员的纬度
	private String userName;
	private String password;
	private int deviceType;
	private String token;

	/**
	 * 
	 * @param longitude 经度
	 * @param latitude  维度
	 * @param userName  用户名
	 * @param password  密码（md5之后）
	 * @param deviceType device类型
	 * @param token 令牌
	 */
	public HttpRequestLocationReport(String longitude, String latitude,
			String userName, String password, int deviceType, String token) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.userName = userName;
		this.password = password;
		this.deviceType = deviceType;
		this.token = token;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
