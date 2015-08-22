package com.decent.courier.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	/**
	 * ��json�ַ�����ȡ��Ӧ�Ķ���ʵ��
	 * @param jsonStr json�ַ���
	 * @param clazz ����
	 * @return ��Ӧ�Ķ���ʵ��
	 */
	public static <T> T getInstanceFromJsonStr(String jsonStr, Class<T> clazz) {
         T newInstance;
         newInstance = JSON.parseObject(jsonStr, clazz);
         return newInstance;
	}
}
