package com.hq.ays.http;

import org.apache.http.NameValuePair;

import com.hq.ays.entity.request.HttpParam;
import com.hq.ays.entity.response.AysResponse;

public interface IHttpRequest {
	//ֱ�ӷ�Post��Get
	//Post����
	public AysResponse RequestByPost(String url,HttpParam params,NameValuePair header);

	public AysResponse RequestByPost(String url,HttpParam params);
	
	public AysResponse RequestByPost(String url);
	
	
	//Get����
	public AysResponse RequestByGet(String url,HttpParam params,NameValuePair head);

	public AysResponse RequestByGet(String url,HttpParam params);
	
	public AysResponse RequestByGet(String url);
	
}
