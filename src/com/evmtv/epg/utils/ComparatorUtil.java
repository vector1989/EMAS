package com.evmtv.epg.utils;

import java.util.Comparator;

import com.evmtv.epg.entity.TreeJson;
/**
 * 比较
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ComparatorUtil.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013年10月28日上午11:49:19
 * @type_name ComparatorUtil
 */
public class ComparatorUtil implements Comparator<TreeJson>{

	@Override
	public int compare(TreeJson o1, TreeJson o2) {
		//首先比较年龄，如果年龄相同，则比较名字
		Integer p1 = o1.getPositionid();
		p1 = p1 < 0 ? Math.abs(p1) + 1 : p1;
		Integer p2 = o2.getPositionid();
		p2 = p2 < 0 ? Math.abs(p2) + 1 : p2;
		
		int flag = p1.compareTo(p2);
		if(flag == 0){
			return o1.getStarttime().compareTo(o2.getStarttime());
		}else{
			return flag;
		}
	}
	
}
