package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TVersionSource;

/**
 * 广告版本数据
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IVersionSource.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013年11月9日下午1:15:50
 * @type_name IVersionSource
 */
public interface IVersionSource {
	/**
	 * 新增
	 * @param v
	 * @return
	 */
	int insert(TVersionSource v);
	/**
	 * 新增
	 * @param v
	 * @return
	 */
	int batchInsert(List<TVersionSource> vs);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Long id);
	/**
	 * 修改
	 * @param adv
	 * @return
	 */
	int update(TVersionSource adv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TVersionSource selectByKey(Long id);
	
	/**
	 * 查询
	 * @param adv
	 * @return
	 */
	List<TVersionSource> selectByExample(TVersionSource vs);
	/**
	 * 广告版本信息
	 * @param vs
	 * @return
	 */
	List<TSource> selectSourceJoinByVersionSource(TVersionSource vs);
	/**
	 * 根据广告索引查询广告使用版本
	 * @param sids
	 * @return
	 */
	List<Long> selectRvidBySid(String sids);
}