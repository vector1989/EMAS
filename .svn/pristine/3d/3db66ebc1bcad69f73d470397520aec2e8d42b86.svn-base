package com.evmtv.epg.utils;

import net.sf.json.JSONObject;

/**
 * 分页属性
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-14 下午4:06:36
 */
public class ReturnJsonUtils {
	/**
	 * 
	 * @param limit 每一页大小
	 * @param total 总记录数
	 * @return 总页数
	 */
	public static int getTotalPage(int limit, int total){
		if(limit==0){
			return 1;
		}
		return total % limit == 0 ? total/ limit : total/ limit +1;
	}
	
	public static String getReturnJsonStr(Object obj,int total,int totalPage,int page){
		JSONObject json = new JSONObject();
		json.put("source", obj);
		json.put("total", total);
		json.put("totalPage", totalPage);
		json.put("page", page);
		return json.toString();
	}
}
