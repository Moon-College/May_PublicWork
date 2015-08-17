package com.dd.courier.bean.request;

/**
 *业务员发送自己的位置
 * Created by Administrator on 2015/5/18.
 */
public class RequestCourierLocation implements HttpParams{
    private String longitude;//业务员的经度
    private String latitude;//业务员的纬度
    private String userName;
    private String password;
    private int deviceType;
    private String token;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
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

	public RequestCourierLocation(String longitude, String latitude,String userName,String password,int deviceType,String token) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.userName = userName;
        this.password = password;
        this.deviceType  = deviceType;
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


    
}
