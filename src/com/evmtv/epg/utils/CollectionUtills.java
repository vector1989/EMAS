package com.evmtv.epg.utils;

import java.util.Collection;

/**
 * 集合操作工具
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name CollectionUtills.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013年7月24日上午11:39:09
 * @type_name CollectionUtills
 */
public class CollectionUtills {

	/**
	 * 判断集合是否为空
	 * @param coll
	 * @return
	 */
	public static boolean hasElements(Collection<?> coll){
		return coll != null && !coll.isEmpty();
	}
}
