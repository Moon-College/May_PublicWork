package com.decent.courier.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	/**
	 * 从json字符串获取对应的对象实例
	 * @param jsonStr json字符串
	 * @param clazz 类型
	 * @return 对应的对象实例
	 */
	public static <T> T getInstanceFromJsonStr(String jsonStr, Class<T> clazz) {
         T newInstance;
         newInstance = JSON.parseObject(jsonStr, clazz);
         return newInstance;
	}
}
