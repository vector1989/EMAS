package com.evmtv.epg.utils;

import java.io.UnsupportedEncodingException;

/**
 * <p>Title: 字符传操作工具</p> 
 * <p>Description: 字符传操作工具</p> 
 * <p>Date: 2013年12月5日下午3:31:54</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.2
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