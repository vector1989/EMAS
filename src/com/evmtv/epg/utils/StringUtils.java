package com.evmtv.epg.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字符传操作工具
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name StringUtils.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013年10月23日下午5:36:13
 * @type_name StringUtils
 */
public class StringUtils {
	
	/**
	 * 字符串是否为空
	 * <b>不为空返回真，为空返回假</b>
	 * @param s
	 * @return 
	 */
	public static boolean hasText(String s){
		return s != null && !"".equals(s);
	}
	
	/**
	 * 字符串转码
	 * @param str
	 * @param originCharset 源编码
	 * @param targetCharset 目标编码
	 * @return
	 */
	public static String transcoding(String str,String originCharset,String targetCharset){
		try {
			return new String(str.getBytes(originCharset),targetCharset);
		} catch (UnsupportedEncodingException e) {
			return "源编码："+originCharset+";目标编码："+targetCharset+";错误信息："+e.getMessage();
		}
	}
}