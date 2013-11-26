package com.evmtv.epg.utils;

import net.sf.json.JSONObject;
/**
 * json转换
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name JsonUtil.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013-8-27下午1:33:14
 * @type_name JsonUtil
 */
public class JsonUtil {
	public static String objectToJson(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}

	public static Object jsonToObject(String jsonString, Class<?> classes) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Object object = JSONObject.toBean(jsonObject, classes);
		return object;
	}
}
