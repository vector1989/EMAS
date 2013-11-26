package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TAdvClass;

/**
 * 广告位
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 下午6:38:35
 */
public interface IAdvClass {

	int insert(TAdvClass adv);
	
	int delete(List<Long> id);
	
	int update(TAdvClass adv);
	
	TAdvClass selectByKey(Long id);
	/**
	 * 查询
	 * @param adv
	 * @return
	 */
	List<TAdvClass> selectByExample(TAdvClass adv);
	/**
	 * 
	 * @param id
	 * @return
	 */
	List<TAdvClass> select(List<Long> id);
}
