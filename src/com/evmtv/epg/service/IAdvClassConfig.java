package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TAdvClassConfig;

/**
 * 广告位配置
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IAdvClassConfig.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013-8-12上午11:02:48
 * @type_name IAdvClassConfig
 */
public interface IAdvClassConfig {
	/**
	 * 
	 * @param config
	 * @return
	 */
	int insert(TAdvClassConfig config);
	/**
	 * 
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 
	 * @param config
	 * @return
	 */
	int update(TAdvClassConfig config);
	/**
	 * 
	 * @param id
	 * @return
	 */
	TAdvClassConfig selectByKey(Long id);
	/**
	 * 查询
	 * @param config
	 * @return
	 */
	List<TAdvClassConfig> selectByExample(TAdvClassConfig config);
	
	/**
	 * 条件查询对象
	 * @param config
	 * @return
	 */
	TAdvClassConfig selectByObject(TAdvClassConfig config);
}
