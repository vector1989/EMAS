package com.evmtv.epg.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 
 * TODO 数组操作工具
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name ArraysUtils.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013年7月23日上午9:18:03
 * @type_name ArraysUtils
 */
public class ArraysUtils {

	/**
	 * 整型数组之和
	 * 
	 * @param arr
	 *            数组
	 * @return
	 */
	public static int plus(int[] arr) {
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

	/**
	 * 字符串数组转字符串,使用“，”分割
	 * 
	 * @param arr
	 * @return
	 */
	public static String toString(String[] arr) {
		StringBuilder str = new StringBuilder();
		for (String s : arr) {
			str.append(s).append(",");
		}
		return str.substring(0, str.length() - 1);
	}

	/**
	 * 字符串数组转字符串
	 * 
	 * @param arr
	 * @param spilt
	 *            分割字符串
	 * @return
	 */
	public static String toString(String[] arr, char split) {
		StringBuilder str = new StringBuilder();
		for (String s : arr) {
			str.append(s).append(split);
		}
		return str.substring(0, str.length() - 1);
	}

	/**
	 * 将字符串数组转成Long性集合
	 * 
	 * @param arr
	 * @return
	 */
	public static List<Long> toLongList(String[] arr) {
		List<Long> list = new ArrayList<Long>();
		if (arr != null)
			for (String s : arr) {
				list.add(Long.valueOf(s));
			}
		return list;
	}

	/**
	 * 将字符串转为Long集合，使用“，”分割
	 * 
	 * @param str
	 * @return
	 */
	public static List<Long> toLongList(String str) {
		String[] arr = null;
		if (StringUtils.hasText(str))
			arr = str.split(",");

		return toLongList(arr);
	}

	/**
	 * 将字符串转为Long集合
	 * 
	 * @param str
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static List<Long> toLongList(String str, String split) {

		String[] arr = str.split(split);

		return toLongList(arr);
	}

	/**
	 * 集合转字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(List<Long> obj) {
		String result = null;
		if (CollectionUtills.hasElements(obj)) {
			StringBuilder sb = new StringBuilder();
			for (Object o : obj) {
				sb.append(o).append(",");
			}
			result = sb.substring(0, sb.length() - 1);
		}
		return result;
	}

	/**
	 * String集合转为Long集合
	 * 
	 * @param obj
	 * @return
	 */
	public static List<Long> toLongList(List<String> obj) {
		List<Long> list = new ArrayList<Long>();
		if (CollectionUtills.hasElements(obj)) {
			for (String s : obj) {
				if (StringUtils.hasText(s))
					list.add(Long.valueOf(s));
			}
		}
		return list;
	}
}
